package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.FoodService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.FoodDuplicatedException;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.samples.yogogym.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodController {
	
	private static final String EXCEPTION = "exception";
	
	private FoodService foodService;
	private TrainingService trainingService;
	private DietService dietService;
	private ClientService clientService;



	@Autowired
	public FoodController(FoodService foodService, TrainingService trainingService, DietService dietService,ClientService clientService) {
		this.foodService = foodService;
		this.trainingService = trainingService;
		this.dietService = dietService;
		this.clientService = clientService;
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/foods")
	public String foodList(@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") Integer trainingId, @PathVariable("dietId") Integer dietId, Model model) {
		Boolean isLogged = SecurityUtils.isLoggedUser(clientUsername, false, this.clientService, null);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;
		
		Collection<Food> foods = this.foodService.findAllFoods();
		Training training = this.trainingService.findTrainingById(trainingId);
		Diet diet = this.dietService.findDietById(dietId);
		
		model.addAttribute("foods",foods);
		model.addAttribute("training",training);
		model.addAttribute("diet",diet);
		
		return "client/foods/foodsList";
		
	}
		
}
