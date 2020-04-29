package org.springframework.samples.yogogym.web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.DietType;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DietController {

	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;
	private final DietService dietService;

	@Autowired
	public DietController(final ClientService clientService, final TrainerService trainerService,
			final DietService dietService,final TrainingService trainingService) {
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.dietService = dietService;
		this.trainingService = trainingService;

	}

	// TRAINER
	
	@GetMapping("/trainer/{trainerUsername}/diets")
	public String ClienDietList(@PathVariable("trainerUsername") String trainerUsername, Model model) {

		if(!isLoggedTrainer(trainerUsername))
			return "exception";
		
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");		
		String actualDate = dateFormat.format(date);
		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
	
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("trainer", trainer);
		
		return "trainer/diets/dietsList";
	 }

	// GET
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}")
	public String ClientDietDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("dietId") int dietId, @PathVariable("trainingId") int trainingId, Model model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
			
		Client client = this.clientService.findClientById(clientId);
		Diet diet = this.dietService.findDietById(dietId);
		Training training = this.trainingService.findTrainingById(trainingId);
		
		model.addAttribute("client", client);
		model.addAttribute("diet", diet);
		model.addAttribute("training", training);

		return "trainer/diets/dietsDetails";
	}

	// POST
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create")
	public String initDietCreateForm(@PathVariable("trainerUsername") String trainerUsername, @PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, Model model) {

		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";

		Diet diet = new Diet();
		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);
		List<DietType> dietTypes = Arrays.asList(DietType.values());

		model.addAttribute("diet", diet);
		model.addAttribute("client", client);
		model.addAttribute("dietTypes", dietTypes);
		model.addAttribute("training", training);

		return "trainer/diets/dietsCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create")
	public String processDietCreateForm(@Valid Diet diet, BindingResult result, @PathVariable("clientId") int clientId,
			@PathVariable("trainerUsername") String trainerUsername, 
			@PathVariable("trainingId") int trainingId, Model model) {

		if(!isClientOfLoggedTrainer(clientId,trainerUsername) || isTrainingFinished(trainingId))
			return "exception";

		if (result.hasErrors()) {
			List<DietType> dietTypes = Arrays.asList(DietType.values());
			model.addAttribute("dietTypes", dietTypes);

			return "trainer/diets/dietsCreateOrUpdate";
		} else {
			Trainer trainer = this.trainerService.findTrainer(trainerUsername);
			Training training = this.trainingService.findTrainingById(trainingId);

			diet = generateDiet(diet, clientId);
						
			training.setDiet(diet);

			try {
				this.trainingService.saveTraining(training);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/trainer/"+ trainer.getUser().getUsername() + "/clients/" + clientId + "/trainings/"+ training.getId();
			
		}
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit")
	public String initDietUpdateForm(@PathVariable("trainerUsername") String trainerUsername, @PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, @PathVariable("dietId") int dietId, Model model) {

		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Diet diet = this.dietService.findDietById(dietId);
		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);
		List<DietType> dietTypes = Arrays.asList(DietType.values());
		
		model.addAttribute("diet", diet);
		model.addAttribute("client", client);
		model.addAttribute("dietTypes", dietTypes);
		model.addAttribute("training", training);

		return "trainer/diets/dietsCreateOrUpdate";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit")
	public String processDietUpdateForm(@PathVariable("trainerUsername") String trainerUsername, @Valid Diet diet, BindingResult result, @PathVariable("clientId") int clientId,
			 @PathVariable("trainingId") int trainingId, @PathVariable("dietId") int dietId, ModelMap model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";

		diet.setId(dietId);
	
		if (result.hasErrors()) {
			List<DietType> dietTypes = Arrays.asList(DietType.values());
			model.put("dietTypes", dietTypes);
			model.put("diet", diet);
			return "trainer/diets/dietsCreateOrUpdate";
		} 
		else {

			diet.setId(dietId);		
			Training training = this.trainingService.findTrainingById(trainingId);
			training.setDiet(diet);
			
			try {
				this.trainingService.saveTraining(training);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/trainer/" + trainerUsername + "/clients/" + clientId +
			 "/trainings/" + trainingId + "/diets/" + diet.getId();
		}
	}
	
	//CLIENT
	@GetMapping("/clients/{clientId}/trainings/{trainingId}/diets/create")
	public String initDietCreateForm(@PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, Model model) {
		
		Diet diet = new Diet();
		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);
		List<DietType> dietTypes = Arrays.asList(DietType.values());

		model.addAttribute("diet", diet);
		model.addAttribute("client", client);
		model.addAttribute("dietTypes", dietTypes);
		model.addAttribute("training", training);

		return "client/diets/dietsCreateOrUpdate";
	}
	
	@PostMapping("/clients/{clientId}/trainings/{trainingId}/diets/create")
	public String processDietCreateForm(@Valid Diet diet, BindingResult result, @PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, Model model) {
		DietType dietType = this.dietService.selectDietType(trainingId);
		
		if(isTrainingFinished(trainingId))
			return "exception";

		if (result.hasErrors()) {
			
			model.addAttribute("dietType", dietType);
			return "client/diets/dietsCreateOrUpdate";
			
		} else {
			
			Training training = this.trainingService.findTrainingById(trainingId);
			
			diet.setType(dietType);
			diet = generateDiet(diet, clientId);
					
			training.setDiet(diet);

			try {
				this.trainingService.saveTraining(training);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/clients/" + clientId + "/trainings/"+ training.getId();
			
		}
	}


	private Boolean isClientOfLoggedTrainer(final int clientId, final String trainerUsername) {		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Client client = this.clientService.findClientById(clientId);
		
		return isLoggedTrainer(trainerUsername)&&trainer.getClients().contains(client);
	}
	
	private Boolean isLoggedTrainer(final String trainerUsername) {
		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		
		return trainer.getUser().getUsername().equals(username);
	}
	
	public Boolean isTrainingFinished(final int trainingId)
	{
		Calendar now = Calendar.getInstance();
		Date actualDate = now.getTime();
				
		Training training = this.trainingService.findTrainingById(trainingId);
		
		return training.getEndDate().before(actualDate);
	}

	private Diet generateDiet(Diet diet, Integer clientId){
		Client client = this.clientService.findClientById(clientId);
		

		Double HarrisBenedict = (10 * client.getWeight()) + (6.25 * client.getHeight()) - (5 * client.getAge()) + 5;
		Double KatchMcArdle = 21.6 * (client.getWeight() - client.getWeight() * client.getFatPercentage() / 100) + 370;
		Double TMB = (HarrisBenedict + KatchMcArdle) / 2;
		Double caloriasMantemiento = TMB * 1.65;
		Double proteins;
		Double carbs;

		switch (diet.getType()) {
			case VOLUME:
				caloriasMantemiento = caloriasMantemiento * 1.125;
				proteins = 2.2 * client.getWeight();
				carbs = 5 * client.getWeight();
				break;
			case DEFINITION:
				caloriasMantemiento = caloriasMantemiento * 0.85;
				proteins = 2.2 * client.getWeight();
				carbs = 3 * client.getWeight();
				break;
			case EXTREME_DEFINITION:
				caloriasMantemiento = client.getWeight() * 22;
				proteins = 2.2 * client.getWeight();
				carbs =  client.getWeight();
				break;
			default:
				proteins = 2 * client.getWeight();
				carbs = 4* client.getWeight();
		}
		
		Double fat = (caloriasMantemiento - (proteins + carbs) * 4) / 9;

		fat = (fat >= 0)? fat : 0;
		caloriasMantemiento = (caloriasMantemiento >= 0)? caloriasMantemiento : 0;
		proteins = (proteins >= 0)? proteins : 0;
		carbs = (carbs >= 0)? carbs : 0;

		diet.setKcal(caloriasMantemiento.intValue());
		diet.setProtein(proteins.intValue());
		diet.setCarb(carbs.intValue());
		diet.setFat(fat.intValue());

		return diet;
	}
}