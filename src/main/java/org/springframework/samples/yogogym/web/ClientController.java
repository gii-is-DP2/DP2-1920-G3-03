package org.springframework.samples.yogogym.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController {

	private final ClientService clientService;
	private final TrainingService trainingService;
	private final TrainerService trainerService;

	@Autowired
	public ClientController(final ClientService clientService, TrainingService trainingService, final TrainerService trainerService) {
		this.clientService = clientService;
		this.trainingService = trainingService;
		this.trainerService = trainerService;
	}
	
	// TRAINING
	
	@GetMapping("/client/{clientUsername}/trainings")
	public String getTrainingDetails(@PathVariable("clientUsername")final String clientUsername, Model model)
	{
		Client client = this.clientService.findClientByUsername(clientUsername);
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(client.getId());
		
		model.addAttribute("trainings",trainings);
		
		return "client/trainings/trainingsList";
	}

	// TRAINER

	@GetMapping("/trainer/{trainerUsername}/clients")
	public String findClients(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);
		return "trainer/clients/clientsList";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}")
	public String ClientDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, Model model) {
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Client client = this.clientService.findClientById(clientId);

		if (trainer.getClients().contains(client)) {
			model.addAttribute("client", client);
			return "trainer/clients/clientsDetails";
		} else {
			return "exception";
		}
	}
}
