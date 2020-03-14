package org.springframework.samples.yogogym.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.DietType;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.TrainerService;
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

	// @Autowired
	// public DietController(DietService dietService) {
	// this.dietService = dietService;
	// }

	// @GetMapping("/mainMenu/diets")
	// public String findAllDiet(Model model)
	// {
	// //Find Diets
	// Collection<Diet> dietCollection = this.dietService.findAllDiet();
	// model.addAttribute("diets",dietCollection);

	// return "mainMenu/diets/dietsList";
	// }

	// @GetMapping("/mainMenu/diets/{dietId}")
	// public String findDietDetailById(@PathVariable("dietId") int id,Model model)
	// {
	// //Find Diet by Id

	// Diet diet = this.dietService.findDietById(id);
	// model.addAttribute("diet",diet);

	// return "mainMenu/diets/dietsDetails";
	// }

	// @ModelAttribute("diet")
	// public Diet loadDiet() {
	// Diet diet = new Diet();
	// return diet;
	// }
	// // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	// @GetMapping(value = "/mainMenu/diets/new")
	// public String initNewVisitForm(Map<String, Object> model) {
	// return "mainMenu/diets/createOrUpdateDietForm";
	// }

	// // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm
	// is called
	// @PostMapping(value = "/mainMenu/diets/new")
	// public String processNewVisitForm(@Valid Diet diet, BindingResult result) {
	// if (result.hasErrors()) {
	// return "mainMenu/diets/createOrUpdateDietForm";
	// }
	// else {
	// this.dietService.saveDiet(diet);
	// return "redirect:/mainMenu/diets";
	// }
	// }

	// @GetMapping(value = "/mainMenu/diets/{dietId}/update")
	// public String initUpdateDietForm(@PathVariable("dietId") int dietId, Model
	// model) {
	// Diet diet = this.dietService.findDietById(dietId);
	// model.addAttribute(diet);
	// return "mainMenu/diets/createOrUpdateDietForm";
	// }

	// @PostMapping(value = "/mainMenu/diets/{dietId}/update")
	// public String processUpdateDietForm(@Valid Client client, BindingResult
	// result,
	// @PathVariable("dietId") int dietId) {
	// Diet diet = new Diet();
	// Double HarrisBenedict =
	// (10*client.getWeight())+(6.25*client.getHeight())-(5*client.getAge()) + 5;
	// Double KatchMcArdle =
	// 21.6*(client.getWeight()-client.getWeight()*client.getFatPercentage()/100)+370;
	// Double TMB = (HarrisBenedict+KatchMcArdle)/2;
	// Double caloriasMantemiento = TMB*1.65;
	// Double proteins = 2*client.getWeight();
	// Double carbs = 4*client.getWeight();
	// Double fat = (caloriasMantemiento-(proteins+carbs)*4)/9;

	// diet.setName("Mantenmiento");
	// diet.setKcal(caloriasMantemiento.intValue());
	// diet.setProtein(proteins.intValue());
	// diet.setCarb(carbs.intValue());
	// diet.setFat(fat.intValue());

	// if (result.hasErrors()) {
	// return "mainMenu/diets/createOrUpdateDietForm";
	// }
	// else {
	// diet.setId(dietId);
	// this.dietService.saveDiet(diet);
	// return "redirect:/mainMenu/diets/{dietId}";
	// }
	// }
	// }
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final DietService dietService;

	@Autowired
	public DietController(final ClientService clientService, final TrainerService trainerService,
			final DietService dietService) {
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.dietService = dietService;
	}

	// TRAINER

	// LIST
	@GetMapping("/trainer/{trainerUsername}/diets")
	public String ClienDietList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);

		return "trainer/diets/dietsList";
	}

	// GET
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/diets/{dietId}")
	public String ClientDietDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("dietId") int dietId, Model model) {
		Client client = this.clientService.findClientById(clientId);
		Diet diet = this.dietService.findDietById(dietId);

		model.addAttribute("client", client);
		model.addAttribute("diet", diet);

		return "trainer/diets/dietsDetails";
	}

	// POST
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/diets/create")
	public String initDietCreateForm(@PathVariable("clientId") int clientId, Model model) {
		Diet diet = new Diet();
		Client client = this.clientService.findClientById(clientId);
		List<DietType> dietTypes = List.of(DietType.values());

		model.addAttribute("diet", diet);
		model.addAttribute("client", client);
		model.addAttribute("dietTypes", dietTypes);

		return "trainer/diets/dietsCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/diets/create")
	public String processDietCreateForm(@Valid Diet diet, BindingResult result, @PathVariable("clientId") int clientId,
			@PathVariable("trainerUsername") String trainerUsername, Model model) {

		Client client = this.clientService.findClientById(clientId);
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);

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

		diet.setKcal(caloriasMantemiento.intValue());
		diet.setProtein(proteins.intValue());
		diet.setCarb(carbs.intValue());
		diet.setFat(fat.intValue());
		if (result.hasErrors()) {
			return "trainer/diets/dietsCreateOrUpdate";
		} else {
			client.getDiets().add(diet);
			this.clientService.saveClient(client);
			this.dietService.saveDiet(diet);
			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/diets";
		}
	}
}