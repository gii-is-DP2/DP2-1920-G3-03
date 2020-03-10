package org.springframework.samples.yogogym.web;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Intensity;
import org.springframework.samples.yogogym.model.Machine;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/challenges")
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	@InitBinder("challenges")
	public void initChallengeBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ChallengeValidator());
	}
	
	@ModelAttribute("intensities")
	public Collection<Intensity> populateIntensities() {
		return  new ArrayList<Intensity>(Arrays.asList(Intensity.values()));
	}
	
	@GetMapping()
	public String listChallenges(ModelMap modelMap) {
		
		String vista = "challenges/challengesList";
		
		Iterable<Challenge> challenges = challengeService.findAll();
		modelMap.addAttribute("challenges", challenges);
		
		return vista;
	}
	
	@GetMapping(path="/new")
	public String initCreationForm(ModelMap modelMap) {
		
		String view = "challenges/challengesEdit";
		Challenge c = new Challenge();
		Exercise e = new Exercise();
		Machine m = new Machine();
		e.setMachine(m);
		c.setExercise(e);
		modelMap.addAttribute("challenge", c);
		return view;
	}
	
	@PostMapping(path="/new")
	public String processCreationForm(@Valid Challenge challenge, BindingResult result, ModelMap modelMap) {
		
		if(result.hasErrors()) {
			modelMap.put("challenge", challenge);
			return "challenges/challengesEdit";
		}
		else {
			challengeService.save(challenge);
			modelMap.addAttribute("message", "Reto creado Satisfactoriamente");
			return listChallenges(modelMap);
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
