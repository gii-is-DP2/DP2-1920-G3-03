package org.springframework.samples.yogogym.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Intensity;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ExerciseService;
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

	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private ExerciseService exerciseService;
	
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
	
	@GetMapping("/admin/challenges/new")
	public String initCreationForm(ModelMap modelMap) {
	
		Challenge c = new Challenge();

		Collection<Exercise> exercises = this.exerciseService.findAllExercise();
				
		modelMap.addAttribute("challenge", c);
		modelMap.addAttribute("exercises",exercises);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/new")
	public String processCreationForm(Challenge challenge, @ModelAttribute("exerciseId")int exerciseId ,BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "/admin/challenges/challengesCreateOrUpdate";
		}
		else {
			
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			challenge.setExercise(exercise);
			challengeService.saveChallenge(challenge);
			
			return "redirect:/admin/challenges";
		}
	}
	
	@GetMapping("/admin/challenges/{challengeId}/edit/")
	public String initUpdateForm(@PathVariable("challengeId")int challengeId, Model model) {
		
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		model.addAttribute(challenge);
		
		return "/admin/challenges/challengesCreateOrUpdate";
	}
	
	@PostMapping("/admin/challenges/{challengeId}/edit/")
	public String processUpdateForm(Challenge challenge, @PathVariable("challengeId")int challengeId, @ModelAttribute("exerciseId")int exerciseId ,BindingResult result) {
		
		if (result.hasErrors()) {
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
	
	/*@GetMapping(path="/delete/{challengeId}")
	public String deleteChallenge(@PathVariable("challengeId") int challengeId, ModelMap modelMap) {
		
		String view = listChallenges(modelMap);
		
		Optional<Challenge> c = challengeService.findById(challengeId);
		if(c.isPresent()) {
			challengeService.delete(c.get());
			modelMap.addAttribute("message", "Reto eliminado satisfactoriamente");
		}
		else {
			modelMap.addAttribute("message", "Reto no encontrado");
		}
		
		return view;
	}*/
	
}
