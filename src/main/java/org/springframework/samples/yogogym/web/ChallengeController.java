package org.springframework.samples.yogogym.web;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.samples.yogogym.service.exceptions.ChallengeMore3Exception;
import org.springframework.samples.yogogym.service.exceptions.ChallengeSameNameException;
import org.springframework.samples.yogogym.service.exceptions.ChallengeWithInscriptionsException;
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
		dataBinder.setValidator(new ChallengeValidator());
	}
	
	// ADMIN:
	
	@GetMapping("/admin/challenges")
	public String listChallengesAdmin(ModelMap modelMap) {
			
		Iterable<Challenge> challenges = challengeService.findAll();
		modelMap.addAttribute("challenges", challenges);
		
		return "admin/challenges/challengesList";
	}
	
	@GetMapping("/admin/challenges/{challengeId}")
	public String showChallengeByIdAdmin(@PathVariable("challengeId") int challengeId, ModelMap model) {

		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		model.addAttribute("challenge", challenge);

		// If there are inscriptions, it cannot be edited or deleted
		Collection<Inscription> inscriptions = inscriptionService.findInscriptionsByChallengeId(challengeId);
		if (inscriptions.isEmpty()) {
			model.addAttribute("isModifiable", true);
		}

		return "admin/challenges/challengeDetails";
	}
	
	@GetMapping("/admin/challenges/new")
	public String initCreationForm(ModelMap modelMap) {
	
		Challenge c = new Challenge();

		Collection<Exercise> exercises = this.exerciseService.findAllExercise();
		Map<Integer,String> selectVals = new TreeMap<>();
		
		String value = "";
		for(Exercise e:exercises)
		{
			value = e.getName();
			
			if(e.getEquipment() != null)
				value = value.concat(", Equipment: "+e.getEquipment().getName());
			
			selectVals.put(e.getId(), value);
		}
				
		modelMap.addAttribute("challenge", c);
		modelMap.addAttribute("exercises",selectVals);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/new")
	public String processCreationForm(@ModelAttribute("exercise.id")int exerciseId ,@Valid Challenge challenge,BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("challenge",challenge);
			Collection<Exercise> exercises = this.exerciseService.findAllExercise();
			Map<Integer,String> selectVals = new TreeMap<>();
			
			String value = "";
			for(Exercise e:exercises)
			{
				value = e.getName();
				
				if(e.getEquipment() != null)
					value = value.concat(", Equipment: "+e.getEquipment().getName());
				
				selectVals.put(e.getId(), value);
			}
			model.addAttribute("exercises",selectVals);
			
			return "/admin/challenges/challengesCreateOrUpdate";
		}
		else {
			try{
				Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
				challenge.setExercise(exercise);
				challengeService.saveChallenge(challenge);
				
            }catch(Exception ex){
            	if(ex instanceof ChallengeSameNameException) {
            		result.rejectValue("name", "required: ", "There is already a challenge with that name the same week");
            	}
            	else if(ex instanceof ChallengeMore3Exception) {
            		result.rejectValue("initialDate", "required: ", "There must not be more than 3 challenges per week");
            	}
            	
            	Collection<Exercise> exercises = this.exerciseService.findAllExercise();
    			Map<Integer,String> selectVals = new TreeMap<>();
    			
    			String value = "";
    			for(Exercise e:exercises)
    			{
    				value = e.getName();
    				
    				if(e.getEquipment() != null)
    					value = value.concat(", Equipment: "+e.getEquipment().getName());
    				
    				selectVals.put(e.getId(), value);
    			}
    			model.addAttribute("exercises",selectVals);
    			
                return "/admin/challenges/challengesCreateOrUpdate";
            }

			return "redirect:/admin/challenges";
		}
	}
	
	@GetMapping("/admin/challenges/{challengeId}/edit")
	public String initUpdateForm(@PathVariable("challengeId")int challengeId, ModelMap model) {
		
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		Collection<Exercise> exercises = this.exerciseService.findAllExercise();
		Map<Integer,String> selectVals = new TreeMap<>();
		
		String value = "";
		for(Exercise e:exercises)
		{
			value = e.getName();
			
			if(e.getEquipment() != null)
				value = value.concat(", Equipment: "+e.getEquipment().getName());
			
			selectVals.put(e.getId(), value);
		}
		
		// If there are inscriptions, it cannot be edited
		Collection<Inscription> inscriptions = inscriptionService.findInscriptionsByChallengeId(challengeId);
		if(!inscriptions.isEmpty()) {
			return showChallengeByIdAdmin(challengeId,model);
		}
					
		model.addAttribute(challenge);
		model.addAttribute("exercises",selectVals);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/{challengeId}/edit")
	public String processUpdateForm(@PathVariable("challengeId")int challengeId, 
			@ModelAttribute("exercise.id")int exerciseId, @Valid Challenge challenge,  BindingResult result,ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("challenge",challenge);
			Collection<Exercise> exercises = this.exerciseService.findAllExercise();
			Map<Integer,String> selectVals = new TreeMap<>();
			
			String value = "";
			for(Exercise e:exercises)
			{
				value = e.getName();
				
				if(e.getEquipment() != null)
					value = value.concat(", Equipment: "+e.getEquipment().getName());
				
				selectVals.put(e.getId(), value);
			}
			
			model.addAttribute("exercises",selectVals);
			
			return "/admin/challenges/challengesCreateOrUpdate";
		}
		else {
			try{
				Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
				challenge.setExercise(exercise);
				challenge.setId(challengeId);
				challengeService.saveChallenge(challenge);
				
            }catch(Exception ex){
            	if(ex instanceof ChallengeSameNameException) {
            		result.rejectValue("name", "required: ", "There is already a challenge with that name the same week");
            	}
            	else if(ex instanceof ChallengeMore3Exception) {
            		result.rejectValue("initialDate", "required: ", "There must not be more than 3 challenges per week");
            	}
            	
            	Collection<Exercise> exercises = this.exerciseService.findAllExercise();
    			model.addAttribute("exercises",exercises);
                return "/admin/challenges/challengesCreateOrUpdate";
            }

			return "redirect:/admin/challenges";
		}
	}
	
	@GetMapping("admin/challenges/{challengeId}/delete")
	public String deleteChallenge(@PathVariable("challengeId") int challengeId, Model model) {
		
		Challenge challenge = challengeService.findChallengeById(challengeId);
		try {
			this.challengeService.deleteChallenge(challenge);
		}catch(ChallengeWithInscriptionsException ex){
			return "exception";
		}
		
		return "redirect:/admin/challenges";
	}
	
	// CLIENT:
	
	@GetMapping("/client/{clientUsername}/challenges")
	public String listChallengesClient(@PathVariable("clientUsername") String clientUsername, ModelMap modelMap) {
			
		if(!isLoggedPrincipal(clientUsername))
			return "exception";
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		List<Challenge> challenges = this.challengeService.findAllChallengesClients(client.getId(), client.getInscriptions());
		modelMap.addAttribute("challenges", challenges);
		
		return "client/challenges/challengesList";
	}
	
	@GetMapping("/client/{clientUsername}/challenges/{challengeId}")
	public String showChallengeByIdClient(@PathVariable("clientUsername") String clientUsername, @PathVariable("challengeId") int challengeId, Model model) {	  

		if(!isLoggedPrincipal(clientUsername))
			return "exception";
		
	   	Challenge challenge = this.challengeService.findChallengeById(challengeId);
	    //Not already inscribed
	    Client client = this.clientService.findClientByUsername(clientUsername);
	    if(client.getInscriptions().stream().anyMatch(i -> i.getChallenge().equals(challenge)))
	    	return "exception";
	  		
	   	model.addAttribute("challenge", challenge);
	   	
	    return "client/challenges/challengeDetails";
	}
	
	@GetMapping("/client/{clientUsername}/challenges/mine")
	public String listMyChallengesClient(@PathVariable("clientUsername") String clientUsername, ModelMap modelMap) {
			
		if(!isLoggedPrincipal(clientUsername))
			return "exception";

		Client client = this.clientService.findClientByUsername(clientUsername);
		
		List<Inscription> inscriptions = client.getInscriptions();

		modelMap.addAttribute("inscriptions", inscriptions);
		
		return "client/challenges/myChallengesList";
	}
	

	@GetMapping("/client/{clientUsername}/challenges/mine/{challengeId}")
	public String showAndEditMyChallengeByIdClient(@PathVariable("clientUsername") String clientUsername, @PathVariable("challengeId") int challengeId, Model model) {	  

		if(!isLoggedPrincipal(clientUsername))
			return "exception";
	
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
	   	Client client = this.clientService.findClientByUsername(clientUsername);
	   	Inscription inscription;
	   	
	   	if(client.getInscriptions().stream().anyMatch(i -> i.getChallenge().equals(challenge))) {
	   		inscription = client.getInscriptions().stream().filter(i -> i.getChallenge().equals(challenge)).findFirst().get();
	   	}else {
	   		return "exception";
	   	}
	   	
	   	model.addAttribute("challenge", challenge);
	   	model.addAttribute("inscription",inscription);
	   	
	    return "client/challenges/myChallengeDetailsAndUpdate";
	}
	
	private boolean isLoggedPrincipal(String Username) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String principalUsername = ((UserDetails)principal).getUsername();
		
		return principalUsername.trim().toLowerCase().equals(Username.trim().toLowerCase());
	}
}
