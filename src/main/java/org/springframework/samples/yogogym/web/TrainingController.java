package org.springframework.samples.yogogym.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@InitBinder("training")
	public void initTrainingBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new TrainingValidator(trainingService));
	}

	// TRAINER

	@GetMapping("/trainer/{trainerUsername}/trainings")
	public String ClientTrainingList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
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
			Client client = this.clientService.findClientById(clientId);
			model.addAttribute("client", client);
			return "trainer/trainings/trainingCreateOrUpdate";
		} else {
			Client client = this.clientService.findClientById(clientId);
			client.getTrainings().add(training);

			this.clientService.saveClient(client);

			Trainer trainer = this.trainerService.findTrainer(trainerUsername);

			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/trainings";
		}
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String initTrainingUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, Model model) {
		Training training = this.trainingService.findTrainingById(trainingId);
		Client client = this.clientService.findClientById(clientId);
		
		Date now = new Date();
		now = new Date(now.getYear(), now.getMonth(), now.getDate());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("training", training);
		model.addAttribute("client", client);
		return "trainer/trainings/trainingCreateOrUpdate";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String processTrainingUpdateForm(@Valid Training training, BindingResult result, 
		@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("training", training);
			Client client = this.clientService.findClientById(clientId);
			model.addAttribute("client", client);
			return "trainer/trainings/trainingCreateOrUpdate";
		} 
		else {
			Date now = new Date();
			now = new Date(now.getYear(), now.getMonth(), now.getDate());
			
			Training oldTraining = this.trainingService.findTrainingById(trainingId);
			oldTraining.setName(training.getName());
			if(!training.getEndDate().before(now)) {
				oldTraining.setEndDate(training.getEndDate());
			}
			this.trainingService.saveTraining(oldTraining);
			return "redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}";
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete")
	public String processDeleteTrainingForm(@PathVariable("trainingId") int trainingId, RedirectAttributes redirectAttrs) {
		Training training = this.trainingService.findTrainingById(trainingId);
		if(training!=null) {
			Client client = training.getClient();
			client.getTrainings().remove(training);
			this.clientService.saveClient(client);
			this.trainingService.deleteTraining(training);
			redirectAttrs.addFlashAttribute("deleteMessage", "The training was deleted successfully");
		}
		return "redirect:/trainer/{trainerUsername}/trainings";
	}
	
}
