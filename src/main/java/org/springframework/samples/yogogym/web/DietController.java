package org.springframework.samples.yogogym.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class DietController {
		
	private final DietService dietService;

	@Autowired
	public DietController(DietService dietService) {
		this.dietService = dietService;
	}	

	@GetMapping("/mainMenu/diets")
	public String findAllDiet(Model model) 
	{	  
		//Find Diets
	   	Collection<Diet> dietCollection = this.dietService.findAllDiet();
	   	model.addAttribute("diets",dietCollection);
	   	
	    return "mainMenu/diets/dietsList";
	}
	
	@GetMapping("/mainMenu/diets/{dietId}")
	public String findDietDetailById(@PathVariable("dietId") int id,Model model) 
	{	  
		//Find Diet by Id
		
	   	Diet diet = this.dietService.findDietById(id);
	   	model.addAttribute("diet",diet);
	   	
	    return "mainMenu/diets/dietsDetails";
	}

	@ModelAttribute("diet")
	public Diet loadDiet() {
		Diet diet = new Diet();
		return diet;
	}
	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
	@GetMapping(value = "/mainMenu/diets/new")
	public String initNewVisitForm(Map<String, Object> model) {
		return "mainMenu/diets/createOrUpdateDietForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
	@PostMapping(value = "/mainMenu/diets/new")
	public String processNewVisitForm(@Valid Diet diet, BindingResult result) {
		if (result.hasErrors()) {
			return "mainMenu/diets/createOrUpdateDietForm";
		}
		else {
			this.dietService.saveDiet(diet);
			return "redirect:/mainMenu/diets";
		}
	}

	@GetMapping(value = "/mainMenu/diets/{dietId}/update")
	public String initUpdateDietForm(@PathVariable("dietId") int dietId, Model model) {
		Diet diet = this.dietService.findDietById(dietId);
		model.addAttribute(diet);
		return "mainMenu/diets/createOrUpdateDietForm";
	}

	@PostMapping(value = "/mainMenu/diets/{dietId}/update")
	public String processUpdateDietForm(@Valid Diet diet, BindingResult result,
			@PathVariable("dietId") int dietId) {
		if (result.hasErrors()) {
			return "mainMenu/diets/createOrUpdateDietForm";
		}
		else {
			diet.setId(dietId);
			this.dietService.saveDiet(diet);
			return "redirect:/mainMenu/diets/{dietId}";
		}
	}
}
