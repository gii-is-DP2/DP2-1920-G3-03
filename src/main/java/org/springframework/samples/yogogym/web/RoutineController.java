
package org.springframework.samples.yogogym.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
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

@Controller
public class RoutineController {

	private final RoutineService routineService;
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;

	@Autowired
	public RoutineController(final RoutineService routineService,
			final ClientService clientService, final TrainerService trainerService,
			final TrainingService trainingService) {
		this.routineService = routineService;
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.trainingService = trainingService;
	}
	
	@InitBinder("routine")
	public void initRoutineBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new RoutineValidator(routineService));
	}

	// TRAINER
	@GetMapping("/trainer/{trainerUsername}/routines")
	public String RoutinesList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		
		if(!isLoggedTrainer(trainerUsername))
			return "exception";
		
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");		
		String actualDate = dateFormat.format(date);
		
		model.addAttribute("actualDate", actualDate);
		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);
		
		return "trainer/routines/routinesList";
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}")
	public String ClientRoutineDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("routineId") int routineId,
			@PathVariable("trainingId") int trainingId, Model model) {
		
		if(!routineExist(routineId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");		
		String actualDate = dateFormat.format(date);
		
		model.addAttribute("actualDate", actualDate);

		Client client = this.clientService.findClientById(clientId);
		Routine routine = this.routineService.findRoutineById(routineId);
		Training training = this.trainingService.findTrainingById(trainingId);

		model.addAttribute("client", client);
		model.addAttribute("routine", routine);
		model.addAttribute("training", training);

		return "trainer/routines/routineDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String initRoutineCreateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId,@PathVariable("trainerUsername")final String trainerUsername, final Model model) {
		
		if(isTrainingFinished(trainingId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		Client client = this.clientService.findClientById(clientId);
		
		Routine routine = new Routine();
		Collection<RoutineLine> routineLine = new ArrayList<>();
		routine.setRoutineLine(routineLine);

		model.addAttribute("client", client);
		model.addAttribute("routine", routine);
		
		return "trainer/routines/routinesCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String processRoutineCreationForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId,  final ModelMap model) {
		
		if(isTrainingFinished(trainingId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		if (result.hasErrors()) {
			
			Client client = this.clientService.findClientById(clientId);
			
			model.put("client",client);
			
			return "trainer/routines/routinesCreateOrUpdate";
		} else {
			Training training = this.trainingService.findTrainingById(trainingId);
			training.getRoutines().add(routine);

			this.trainingService.saveTraining(training);

			return "redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/"
					+ trainingId;
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit")
	public String initEditRoutine(@PathVariable("routineId")int routineId, @PathVariable("clientId")int clientId, @PathVariable("trainingId")int trainingId, @PathVariable("trainerUsername")String trainerUsername, ModelMap model)
	{
		
		if(!routineExist(routineId) || isTrainingFinished(trainingId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		Routine routine = this.routineService.findRoutineById(routineId);
		Client client = this.clientService.findClientById(clientId);
		
		model.put("client", client);
		model.put("routine", routine);
		
		return "trainer/routines/routinesCreateOrUpdate";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit")
	public String processRoutineEditForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId, @PathVariable("routineId")final int routineId, final ModelMap model) throws DataAccessException, TrainingFinished {
		
		if(routineExist(routineId) || isTrainingFinished(trainingId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		if (result.hasErrors()) {
			routine.setId(routineId);
			Client client = this.clientService.findClientById(clientId);
			model.put("client",client);
			
			return "trainer/routines/routinesCreateOrUpdate";
		} else {
			Routine oldRoutine = this.routineService.findRoutineById(routineId);
		
			routine.setId(routineId);
			routine.setRoutineLine(oldRoutine.getRoutineLine());
			
			this.routineService.saveRoutine(routine,trainingId);
			
			return "redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/"
					+ trainingId + "/routines/" + routineId;
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete")
	public String deleteRoutine(@PathVariable("routineId")int routineId, @PathVariable("clientId")int clientId, @PathVariable("trainingId")int trainingId, @PathVariable("trainerUsername")String trainerUsername) throws DataAccessException, TrainingFinished
	{		
		if(!routineExist(routineId) || isTrainingFinished(trainingId) || !isClientOfLoggedTrainer(clientId) || !isLoggedTrainer(trainerUsername))
			return "exception";
		
		Routine routine = this.routineService.findRoutineById(routineId);
		
		this.routineService.deleteRoutine(routine,trainingId);
		
		return "redirect:/trainer/"+ trainerUsername + "/routines";
	}
	
	//Security Utils Check
	
	public Boolean routineExist(final int routineId)
	{
		return this.routineService.findRoutineById(routineId) != null;
	}
	
	public Boolean isLoggedTrainer(final String trainerUsername)
	{		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String principalUsername = ((UserDetails)principal).getUsername();
		
		return principalUsername.trim().toLowerCase().equals(trainerUsername.trim().toLowerCase());
	}
	
	public Boolean isClientOfLoggedTrainer(final int clientId)
	{		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String trainerUsername = ((UserDetails)principal).getUsername();
		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Client client = this.clientService.findClientById(clientId);
		
		return trainer.getClients().contains(client);
	}
	
	public Boolean isTrainingFinished(final int trainingId)
	{
		Calendar now = Calendar.getInstance();
		Date actualDate = now.getTime();
				
		Training training = this.trainingService.findTrainingById(trainingId);
		
		return training.getEndDate().before(actualDate);
	}

}
