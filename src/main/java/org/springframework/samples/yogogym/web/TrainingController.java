package org.springframework.samples.yogogym.web;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrainingController {

	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;

	@Autowired
	public TrainingController(final ClientService clientService, final TrainerService trainerService,
			final TrainingService trainingService) {
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.trainingService = trainingService;
	}

	// TRAINER

	@GetMapping("/trainer/{trainerUsername}/trainings")
	public String ClienTrainingList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);

		return "trainer/trainings/trainingsList";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}")
	public String ClientTrainingDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("trainingId") int trainingId, Model model) {
		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);

		model.addAttribute("client", client);
		model.addAttribute("training", training);

		return "trainer/trainings/trainingsDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/create")
	public String initTrainingCreateForm(@PathVariable("clientId") int clientId, Model model) {
		Training training = new Training();
		Client client = this.clientService.findClientById(clientId);

		model.addAttribute("training", training);
		model.addAttribute("client", client);

		return "trainer/trainings/trainingCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/create")
	public String processTrainingCreateForm(@Valid Training training, BindingResult result,
			@PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername,
			Model model) {
		if (result.hasErrors()) {
			return "trainer/trainings/trainingCreateOrUpdate";
		} else {
			Client client = this.clientService.findClientById(clientId);
			client.getTrainings().add(training);

			this.clientService.saveClient(client);

			Trainer trainer = this.trainerService.findTrainer(trainerUsername);

			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/trainings";
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String initTrainingUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, Model model) {
		Training training = this.trainingService.findTrainingById(trainingId);
		Client client = this.clientService.findClientById(clientId);
		model.addAttribute("training", training);
		model.addAttribute("client", client);
		return "trainer/trainings/trainingCreateOrUpdate";
	}
}
