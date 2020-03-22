package org.springframework.samples.yogogym.web;

import java.util.Calendar;
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
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	// TRAINER

	@GetMapping("/trainer/{trainerUsername}/trainings")
	public String ClientTrainingList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		
		if(!isLoggedTrainer(trainerUsername))
			return "exception";
		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);
		
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");		
		String actualDate = dateFormat.format(date);
		
		model.addAttribute("actualDate", actualDate);
		
		return "trainer/trainings/trainingsList";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}")
	public String ClientTrainingDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("trainingId") int trainingId, Model model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);

		model.addAttribute("client", client);
		model.addAttribute("training", training);

		return "trainer/trainings/trainingsDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/create")
	public String initTrainingCreateForm(@PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Training training = new Training();
		Client client = this.clientService.findClientById(clientId);

		model.addAttribute("training", training);
		model.addAttribute("client", client);

		return "trainer/trainings/trainingCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/create")
	public String processTrainingCreateForm(@Valid Training training, BindingResult result,
			@PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername,
			ModelMap model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Client client = this.clientService.findClientById(clientId);
		model.addAttribute("client", client);
		
		if (result.hasErrors()) {
			model.put("training", training);
			return "trainer/trainings/trainingCreateOrUpdate";
		} else {
			try {
				this.trainingService.saveTraining(training);
			} catch (PastInitException e) {
				result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (PastEndException e) {
				result.rejectValue("endDate", null, "The end date cannot be in the past");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (EndBeforeEqualsInitException e) {
				result.rejectValue("endDate", null, "The end date must be after the initial date");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (LongerThan90DaysException e) {
				result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (InitInTrainingException e) {
				result.rejectValue("initialDate", null, "The training cannot start in a period "
					+ "with other training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (EndInTrainingException e) {
				result.rejectValue("endDate", null, "The training cannot end in a period "
					+ "with other training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (PeriodIncludingTrainingException e) {
				result.rejectValue("endDate", null, "The training cannot be in a period "
					+ "which includes another training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			}
			
			Trainer trainer = this.trainerService.findTrainer(trainerUsername);
			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/trainings";
		}
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String initTrainingUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, Model model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Training training = this.trainingService.findTrainingById(trainingId);
		Client client = this.clientService.findClientById(clientId);
		
		Date now = new Date();
		now = new Date(now.getYear(), now.getMonth(), now.getDate());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("endDateAux", training.getEndDate());
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("training", training);
		model.addAttribute("client", client);
		return "trainer/trainings/trainingCreateOrUpdate";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String processTrainingUpdateForm(@Valid Training training, BindingResult result, 
		@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Training oldTraining = this.trainingService.findTrainingById(trainingId);
		Client client = this.clientService.findClientById(clientId);
		Date now = new Date();
		now = new Date(now.getYear(), now.getMonth(), now.getDate());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("endDateAux", oldTraining.getEndDate());
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("client", client);
		
		training.setId(trainingId);
		
		if (result.hasErrors()) {
			model.put("training", training);
			return "trainer/trainings/trainingCreateOrUpdate";
		} 
		else {
			training.setClient(oldTraining.getClient());
			training.setInitialDate(oldTraining.getInitialDate());
			training.setDiet(oldTraining.getDiet());
			training.setRoutines(oldTraining.getRoutines());
			training.setId(trainingId);
			try {
				this.trainingService.saveTraining(training);
			} catch (PastInitException e) {
				result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (PastEndException e) {
				result.rejectValue("endDate", null, "The end date cannot be in the past");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (EndBeforeEqualsInitException e) {
				result.rejectValue("endDate", null, "The end date must be after the initial date");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (LongerThan90DaysException e) {
				result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (InitInTrainingException e) {
				result.rejectValue("initialDate", null, "The training cannot start in a period "
					+ "with other training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (EndInTrainingException e) {
				result.rejectValue("endDate", null, "The training cannot end in a period "
					+ "with other training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			} catch (PeriodIncludingTrainingException e) {
				result.rejectValue("endDate", null, "The training cannot be in a period "
					+ "which includes another training (The other training is from " + e.getInitAssoc() + " to " + e.getEndAssoc() + ")");
				return "trainer/trainings/trainingCreateOrUpdate";
			}
			
			return "redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}";
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete")
	public String processDeleteTrainingForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername,RedirectAttributes redirectAttrs) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername))
			return "exception";
		
		Training training = this.trainingService.findTrainingById(trainingId);
		if(training!=null) {
			this.trainingService.deleteTraining(training);
			redirectAttrs.addFlashAttribute("deleteMessage", "The training was deleted successfully");
			return "redirect:/trainer/{trainerUsername}/trainings";
		}
		else {
			return "exception";
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
	
}
