package org.springframework.samples.yogogym.web;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChallengeController {

	
	private ChallengeService challengeService;
	private ExerciseService exerciseService;
	private InscriptionService inscriptionService;
	private ClientService clientService;
	
	@Autowired
	public ChallengeController(ChallengeService challengeService, ExerciseService exerciseService, InscriptionService inscriptionService, ClientService clientService) {
		this.challengeService = challengeService;
		this.exerciseService = exerciseService;
		this.inscriptionService = inscriptionService;
		this.clientService = clientService;
	}
	
	@InitBinder("challenge")
	public void initChallengeBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ChallengeValidator(challengeService));
	}

	
	// ADMIN:
	
	@GetMapping("/admin/challenges")
	public String listChallengesAdmin(ModelMap modelMap) {
			
		Iterable<Challenge> challenges = challengeService.findAll();
		modelMap.addAttribute("challenges", challenges);
		
		return "admin/challenges/challengesList";
	}
	
	@GetMapping("/admin/challenges/{challengeId}")
	public String showChallengeByIdAdmin(@PathVariable("challengeId") int challengeId, Model model) {	  

	   	Challenge challenge = this.challengeService.findChallengeById(challengeId);
	   	model.addAttribute("challenge", challenge);
	   	
	 // If there are inscriptions, it cannot be edited or deleted
	 Inscription inscription = inscriptionService.findInscriptionByChallengeId(challengeId);
	 if(inscription == null) {
	 	model.addAttribute("isModifiable", true);
	 }
	   	
	    return "admin/challenges/challengeDetails";
	}
	
	@GetMapping("/admin/challenges/new")
	public String initCreationForm(ModelMap modelMap) {
	
		Challenge c = new Challenge();

		Collection<Exercise> exercises = this.exerciseService.findAllExercise();
				
		modelMap.addAttribute("challenge", c);
		modelMap.addAttribute("exercises",exercises);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/new")
	public String processCreationForm(@ModelAttribute("exerciseId")int exerciseId ,@Valid Challenge challenge,BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("challenge",challenge);
			Collection<Exercise> exercises = this.exerciseService.findAllExercise();
			model.addAttribute("exercises",exercises);
			
			return "/admin/challenges/challengesCreateOrUpdate";
		}
		else {
			
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			challenge.setExercise(exercise);
			challengeService.saveChallenge(challenge);
			
			return "redirect:/admin/challenges";
		}
	}
	
	@GetMapping("/admin/challenges/{challengeId}/edit")
	public String initUpdateForm(@PathVariable("challengeId")int challengeId, Model model) {
		
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		Collection<Exercise> exercises = this.exerciseService.findAllExercise();
		
		// If there are inscriptions, it cannot be edited
		Inscription inscription = inscriptionService.findInscriptionByChallengeId(challengeId);
		if(inscription != null) {
			return showChallengeByIdAdmin(challengeId,model);
		}
					
		model.addAttribute(challenge);
		model.addAttribute("exercises",exercises);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/{challengeId}/edit")
	public String processUpdateForm(@PathVariable("challengeId")int challengeId, @ModelAttribute("exerciseId")int exerciseId, @Valid Challenge challenge,  BindingResult result,ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("challenge",challenge);
			Collection<Exercise> exercises = this.exerciseService.findAllExercise();
			model.addAttribute("exercises",exercises);
			
			return "/admin/challenges/challengesCreateOrUpdate";
		}
		else {
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			challenge.setExercise(exercise);
			challenge.setId(challengeId);
			this.challengeService.saveChallenge(challenge);
			return "redirect:/admin/challenges";
		}
	}
	
	@GetMapping("admin/challenges/{challengeId}/delete")
	public String deleteChallenge(@PathVariable("challengeId") int challengeId, Model model) {
		
		System.out.println("  ");
		Challenge challenge = challengeService.findChallengeById(challengeId);
		Inscription inscription = inscriptionService.findInscriptionByChallengeId(challengeId);
		
		// If there are inscriptions, it cannot be deleted
		if(inscription != null) {
			return showChallengeByIdAdmin(challengeId,model);
		}
		
		challenge.setExercise(null);
		this.challengeService.deleteChallenge(challenge);
		
		return "redirect:/admin/challenges";
	}
	
	// CLIENT:
	
	@GetMapping("/client/{clientUsername}/challenges")
	public String listChallengesClient(@PathVariable("clientUsername") String clientUsername, ModelMap modelMap) {
			
		List<Challenge> challenges = (List<Challenge>) challengeService.findAll();
		Client client = this.clientService.findClientByClientUsername(clientUsername);
		
		//Only if the end date is posterior to today's
		List<Challenge> challengesFiltered = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		Date d = now.getTime();
		for(Challenge c : challenges) {
			if(c.getEndDate().after(d)) {
				challengesFiltered.add(c);
			}
		}
		//and they are not already inscribed:
		if(client.getInscriptions() != null) {
			for(Inscription i : client.getInscriptions()) {
				Challenge c = i.getChallenge();
				if(challengesFiltered.contains(c)) {
					challengesFiltered.remove(c);
				}
			}
		}
		
		modelMap.addAttribute("challenges", challengesFiltered);
		
		return "client/challenges/challengesList";
	}
	
	@GetMapping("/client/{clientUsername}/challenges/{challengeId}")
	public String showChallengeByIdClient(@PathVariable("clientUsername") String clientUsername, @PathVariable("challengeId") int challengeId, Model model) {	  

	   	Challenge challenge = this.challengeService.findChallengeById(challengeId);
	   	model.addAttribute("challenge", challenge);
	   	
	    return "client/challenges/challengeDetails";
	}
}
