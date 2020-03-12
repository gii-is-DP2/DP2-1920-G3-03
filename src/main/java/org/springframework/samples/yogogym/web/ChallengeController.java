package org.springframework.samples.yogogym.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Intensity;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
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
		dataBinder.setValidator(new ChallengeValidator());
	}
	
	@ModelAttribute("intensities")
	public Collection<Intensity> populateIntensities() {
		return  new ArrayList<Intensity>(Arrays.asList(Intensity.values()));
	}
	
	// ADMIN:
	
	@GetMapping("/admin/challenges")
	public String listChallenges(ModelMap modelMap) {
			
		Iterable<Challenge> challenges = challengeService.findAll();
		modelMap.addAttribute("challenges", challenges);
		
		return "admin/challenges/challengesList";
	}
	
	@GetMapping("/admin/challenges/{challengeId}")
	public String showChallengeById(@PathVariable("challengeId") int challengeId, Model model) {	  

	   	Challenge challenge = this.challengeService.findChallengeById(challengeId);
	   	model.addAttribute("challenge", challenge);
	   	
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
	public String processCreationForm(@Valid Challenge challenge, @ModelAttribute("exerciseId")int exerciseId ,BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("challenge",challenge);
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
		
		model.addAttribute(challenge);
		model.addAttribute("exercises",exercises);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/{challengeId}/edit")
	public String processUpdateForm(@Valid Challenge challenge, @PathVariable("challengeId")int challengeId, @ModelAttribute("exerciseId")int exerciseId ,BindingResult result,ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("challenge",challenge);
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
	public String deleteChallenge(@PathVariable("challengeId") int challengeId) {
		
		System.out.println("  ");
		Challenge challenge = challengeService.findChallengeById(challengeId);
		Inscription inscription = inscriptionService.findInscriptionByChallengeId(challengeId);
		
		challenge.setExercise(null);
		
		if(inscription != null) {
			

			Client client = clientService.findClientByInscriptionId(inscription.getId());
			Collection<Inscription> inscriptions = client.getInscriptions();
			inscriptions.remove(inscription);
			client.setInscriptions(inscriptions);
			
			this.clientService.saveClient(client);
			this.inscriptionService.deleteInscription(inscription);
		}
		else {
			this.challengeService.deleteChallenge(challenge);
		}
		
		return "redirect:/admin/challenges";
	}
	
}
