package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.FoodService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodController {
	

	private FoodService foodService;
	private TrainingService trainingService;
	private DietService dietService;



	@Autowired
	public FoodController(FoodService foodService, TrainingService trainingService, DietService dietService) {
		this.foodService = foodService;
		this.trainingService = trainingService;
		this.dietService = dietService;
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/foods")
	public String foodList(@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") Integer trainingId, @PathVariable("dietId") Integer dietId, Model model) {
		Collection<Food> foods = this.foodService.findAllFoods();
		Training training = this.trainingService.findTrainingById(trainingId);
		Diet diet = this.dietService.findDietById(dietId);
		
		model.addAttribute("foods",foods);
		model.addAttribute("training",training);
		model.addAttribute("diet",diet);
		
		return "client/foods/foodsList";
		
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/food/{foodId}")
	public String foodDetails(@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") Integer trainingId, @PathVariable("dietId") Integer dietId,@PathVariable("foodId") Integer foodId, Model model) {
		Food food = this.foodService.findFoodById(foodId);
		Training training = this.trainingService.findTrainingById(trainingId);
		Diet diet = this.dietService.findDietById(dietId);
	
		model.addAttribute("training",training);
		model.addAttribute("diet",diet);
		model.addAttribute("food",food);
	
		return "client/foods/foodDetails";
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/food/{foodId}/addFood")
	public String addFood(@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") Integer trainingId, @PathVariable("dietId") Integer dietId,@PathVariable("foodId") Integer foodId, Model model) {
		
		Diet d = this.dietService.findDietById(dietId);
		Food f = this.foodService.findFoodById(foodId);
		d.getFoods().add(f);
		try {
			this.dietService.saveDiet(d, trainingId);
		} catch (TrainingFinished e) {
			// TODO Auto-generated catch block
			return "exception";
		}
		
		return "redirect:/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}";
	}
		
}
