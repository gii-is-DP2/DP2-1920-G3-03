package org.springframework.samples.yogogym.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/challenges")
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	@GetMapping()
	public String listChallenges(ModelMap modelMap) {
		
		String vista = "challenges/challengesList";
		
		Iterable<Challenge> challenges = challengeService.findAll();
		modelMap.addAttribute("challenges", challenges);
		
		return vista;
	}
	
	@GetMapping(path="/new")
	public String createChallenge(ModelMap modelMap) {
		
		String view = "challenges/challengesEdit";
		Challenge c = new Challenge();
		c.setExercise(null);
		modelMap.addAttribute("challenge", c);
		return view;
	}
	
	@GetMapping(path="/save")
	public String saveChallenge(@Valid Challenge challenge, BindingResult result, ModelMap modelMap) {
		
		String view;
		
		if(result.hasErrors()) {
			modelMap.addAttribute("challenge", challenge);
			view = "challenges/challengesEdit";
		}
		else {
			challengeService.save(challenge);
			modelMap.addAttribute("message", "Reto creado Satisfactoriamente");
			view = listChallenges(modelMap);
		}
		
		return view;
	}
	
	@GetMapping(path="/delete/{challengeId}")
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
	}
	
}
