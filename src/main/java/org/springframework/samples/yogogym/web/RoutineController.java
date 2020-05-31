package org.springframework.samples.yogogym.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.MaxRoutinesException;
import org.springframework.samples.yogogym.service.exceptions.NotEditableException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.samples.yogogym.service.exceptions.RoutineRepsPerWeekNotValid;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoutineController {

	// VIEWS
	private static final String CREATE_OR_UPDATE_TRAINER = "trainer/routines/routinesCreateOrUpdate";
	private static final String CREATE_OR_UPDATE_CLIENT = "client/routines/routinesCreateOrUpdate";
	private static final String EXCEPTION = "exception";

	// REDIRECTS
	private static final String REDIRECT_TRAINER = "redirect:/trainer/";
	private static final String REDIRECT_CLIENT = "redirect:/client/";
	private static final String CLIENTS = "/clients/";
	private static final String TRAININGS = "/trainings/";
	private static final String ROUTINES = "/routines";

	// VARIABLE
	private static final String CLIENT = "client";
	private static final String TRAINER = "trainer";
	private static final String ROUTINE = "routine";
	private static final String TRAINING = "training";

	private final RoutineService routineService;
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;

	@Autowired
	public RoutineController(final RoutineService routineService, final ClientService clientService,
			final TrainerService trainerService, final TrainingService trainingService) {
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
	// ==================================================================================

	@GetMapping("/trainer/{trainerUsername}/routines")
	public String routinesList(@PathVariable("trainerUsername") String trainerUsername, ModelMap model) {

		Boolean isLogged = isLoggedTrainer(trainerUsername);

		if (Boolean.FALSE.equals(isLogged))
			return EXCEPTION;

		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String actualDate = dateFormat.format(date);

		model.addAttribute("actualDate", actualDate);

		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute(TRAINER, trainer);

		return "trainer/routines/routinesList";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}")
	public String clientRoutineDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("routineId") int routineId,
			@PathVariable("trainingId") int trainingId, ModelMap model) {

		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean routineExist = routineExist(routineId);

		if (Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.FALSE.equals(routineExist))
			return EXCEPTION;

		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String actualDate = dateFormat.format(date);

		model.addAttribute("actualDate", actualDate);

		Client client = this.clientService.findClientById(clientId);
		Routine routine = this.routineService.findRoutineById(routineId);
		Training training = this.trainingService.findTrainingById(trainingId);

		model.addAttribute(CLIENT, client);
		model.addAttribute(ROUTINE, routine);
		model.addAttribute(TRAINING, training);

		return "trainer/routines/routineDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String initRoutineCreateForm(@PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId, @PathVariable("trainerUsername") final String trainerUsername,
			final ModelMap model) {

		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean trainingFinish = isTrainingFinished(trainingId);

		if (Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.TRUE.equals(trainingFinish))
			return EXCEPTION;

		Client client = this.clientService.findClientById(clientId);

		Routine routine = new Routine();
		Collection<RoutineLine> routineLine = new ArrayList<>();
		routine.setRoutineLine(routineLine);

		model.addAttribute(CLIENT, client);
		model.addAttribute(ROUTINE, routine);

		return CREATE_OR_UPDATE_TRAINER;
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String processRoutineCreationForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId, final ModelMap model, RedirectAttributes redirectAttrs)
			throws PastInitException, EndBeforeEqualsInitException, InitInTrainingException, EndInTrainingException,
			PeriodIncludingTrainingException, PastEndException, LongerThan90DaysException, TrainingFinished,
			NotEditableException {

		Client client = this.clientService.findClientById(clientId);
		Training training = this.trainingService.findTrainingById(trainingId);

		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean trainingFinish = isTrainingFinished(trainingId);

		if (Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.TRUE.equals(trainingFinish))
			return EXCEPTION;

		if (result.hasErrors()) {
			model.put(CLIENT, client);
			return CREATE_OR_UPDATE_TRAINER;
		} else {

			try {
				this.routineService.saveRoutine(routine, trainerUsername, trainingId);
				training.getRoutines().add(routine);
				this.trainingService.saveTraining(training, client);
			} catch (MaxRoutinesException e) {
				String error = "You cannot create more than 10 routines";
				redirectAttrs.addFlashAttribute("error", error);
				return REDIRECT_TRAINER + trainerUsername + CLIENTS + clientId + TRAININGS + trainingId;
			} catch (RoutineRepsPerWeekNotValid e) {
				model.put(CLIENT, client);
				result.rejectValue("repsPerWeek", "",
						"The repetition per week cannot be greater than 20 or less than 1");
				return CREATE_OR_UPDATE_TRAINER;
			}

		}
		return REDIRECT_TRAINER + trainerUsername + CLIENTS + clientId + TRAININGS + trainingId;
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit")
	public String initEditRoutine(@PathVariable("routineId") int routineId, @PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, @PathVariable("trainerUsername") String trainerUsername,
			ModelMap model) {
		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean routineEx = routineExist(routineId);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean trainingFinish = isTrainingFinished(trainingId);

		if (Boolean.FALSE.equals(routineEx) || Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.TRUE.equals(trainingFinish))
			return EXCEPTION;

		Routine routine = this.routineService.findRoutineById(routineId);
		Client client = this.clientService.findClientById(clientId);

		model.put(CLIENT, client);
		model.put(ROUTINE, routine);

		return CREATE_OR_UPDATE_TRAINER;
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit")
	public String processRoutineEditForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId, @PathVariable("routineId") final int routineId,
			final ModelMap model, RedirectAttributes redirectAttrs)
			throws TrainingFinished, NotEditableException, MaxRoutinesException {

		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean routineEx = routineExist(routineId);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean trainingFinish = isTrainingFinished(trainingId);

		if (Boolean.FALSE.equals(routineEx) || Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.TRUE.equals(trainingFinish))
			return EXCEPTION;

		if (result.hasErrors()) {
			routine.setId(routineId);
			Client client = this.clientService.findClientById(clientId);
			model.put(CLIENT, client);

			return CREATE_OR_UPDATE_TRAINER;
		} else {
			Routine oldRoutine = this.routineService.findRoutineById(routineId);

			routine.setId(routineId);
			routine.setRoutineLine(oldRoutine.getRoutineLine());

			String loggedUsername = getLoggedUsername();

			try {
				this.routineService.saveRoutine(routine, loggedUsername, trainingId);
			} catch (Exception e) {

				return EXCEPTION;
			}

			String updateRoutine = " '" + routine.getName() + "' " + "has been updated succesfully";
			redirectAttrs.addFlashAttribute("updateRoutine", updateRoutine);

			return REDIRECT_TRAINER + trainerUsername + CLIENTS + clientId + TRAININGS + trainingId + ROUTINES + "/"
					+ +routineId;
		}
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete")
	public String deleteRoutine(@PathVariable("routineId") int routineId, @PathVariable("clientId") int clientId,
			@PathVariable("trainingId") int trainingId, @PathVariable("trainerUsername") String trainerUsername,
			RedirectAttributes redirectAttrs) {
		Boolean isLogged = isLoggedTrainer(trainerUsername);
		Boolean routineEx = routineExist(routineId);
		Boolean clientOfTrainer = isClientOfLoggedTrainer(clientId);
		Boolean trainingFinish = isTrainingFinished(trainingId);

		if (Boolean.FALSE.equals(routineEx) || Boolean.FALSE.equals(isLogged) || Boolean.FALSE.equals(clientOfTrainer)
				|| Boolean.TRUE.equals(trainingFinish))
			return EXCEPTION;

		Routine routine = this.routineService.findRoutineById(routineId);

		try {
			this.routineService.deleteRoutine(routine, trainerUsername, trainingId);
		} catch (Exception e) {

			return EXCEPTION;
		}

		String deleteRoutine = " '" + routine.getName() + "' " + "has been deleted succesfully";
		redirectAttrs.addFlashAttribute("deleteRoutine", deleteRoutine);

		return REDIRECT_TRAINER + trainerUsername + ROUTINES;
	}

	// CLIENT

	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/routine/create")
	public String initProcessForm(@PathVariable("trainingId") int trainingId,
			@PathVariable("clientUsername") final String clientUsername, final ModelMap model) {
		Client client = this.clientService.findClientByUsername(clientUsername);

		Boolean trainingFinish = isTrainingFinished(trainingId);
		Boolean isLogged = isLoggedTrainer(clientUsername);
		Boolean trainingFClient = isTrainingFromClient(client);
		if (Boolean.TRUE.equals(trainingFinish) || Boolean.FALSE.equals(isLogged)
				|| Boolean.FALSE.equals(trainingFClient))
			return EXCEPTION;

		Routine routine = new Routine();
		Collection<RoutineLine> routineLine = new ArrayList<>();
		routine.setRoutineLine(routineLine);
		model.addAttribute(ROUTINE, routine);

		return CREATE_OR_UPDATE_CLIENT;
	}

	@PostMapping("/client/{clientUsername}/trainings/{trainingId}/routine/create")
	public String postProcessForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainingId") int trainingId, @PathVariable("clientUsername") final String clientUsername,
			final ModelMap model, RedirectAttributes redirectAttrs)
			throws TrainingFinished, NotEditableException, RoutineRepsPerWeekNotValid {
		Client client = this.clientService.findClientByUsername(clientUsername);

		Boolean trainingFinish = isTrainingFinished(trainingId);
		Boolean isLogged = isLoggedTrainer(clientUsername);
		Boolean trainingFClient = isTrainingFromClient(client);
		if (Boolean.TRUE.equals(trainingFinish) || Boolean.FALSE.equals(isLogged)
				|| Boolean.FALSE.equals(trainingFClient))
			return EXCEPTION;

		if (result.hasErrors()) {
			return CREATE_OR_UPDATE_CLIENT;
		} else {

			Training training = this.trainingService.findTrainingById(trainingId);

			try {
				this.routineService.saveRoutine(routine, clientUsername, trainingId);
			} catch (MaxRoutinesException e) {

				String error = "You cannot create more than 10 routines";
				redirectAttrs.addFlashAttribute("error", error);
				return REDIRECT_CLIENT + clientUsername + TRAININGS + trainingId;
			}

			training.getRoutines().add(routine);

			try {
				this.trainingService.saveTraining(training, client);
			} catch (Exception e) {

				return EXCEPTION;
			}

			return REDIRECT_CLIENT + clientUsername + TRAININGS + trainingId;
		}
	}

	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update")
	public String initUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("routineId") int routineId,
			@PathVariable("clientUsername") final String clientUsername, final ModelMap model) {
		Client client = this.clientService.findClientByUsername(clientUsername);

		Boolean routineEx = routineExist(routineId);
		Boolean trainingFinish = isTrainingFinished(trainingId);
		Boolean isLogged = isLoggedTrainer(clientUsername);
		Boolean trainFClient = isTrainingFromClient(client);

		if (Boolean.FALSE.equals(routineEx) || Boolean.TRUE.equals(trainingFinish) || Boolean.FALSE.equals(isLogged)
				|| Boolean.FALSE.equals(trainFClient))
			return EXCEPTION;

		Routine routine = this.routineService.findRoutineById(routineId);

		model.addAttribute(ROUTINE, routine);

		return CREATE_OR_UPDATE_CLIENT;
	}

	@PostMapping("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update")
	public String postUpdateForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainingId") int trainingId, @PathVariable("routineId") int routineId,
			@PathVariable("clientUsername") final String clientUsername, final ModelMap model,
			RedirectAttributes redirectAttrs) {
		Client client = this.clientService.findClientByUsername(clientUsername);

		Boolean routineEx = routineExist(routineId);
		Boolean trainingFinish = isTrainingFinished(trainingId);
		Boolean isLogged = isLoggedTrainer(clientUsername);
		Boolean trainFClient = isTrainingFromClient(client);

		if (Boolean.FALSE.equals(routineEx) || Boolean.TRUE.equals(trainingFinish) || Boolean.FALSE.equals(isLogged)
				|| Boolean.FALSE.equals(trainFClient))
			return EXCEPTION;

		if (result.hasErrors()) {
			routine.setId(routineId);
			model.put(CLIENT, client);

			return CREATE_OR_UPDATE_CLIENT;
		} else {
			Routine oldRoutine = this.routineService.findRoutineById(routineId);

			routine.setId(routineId);
			routine.setRoutineLine(oldRoutine.getRoutineLine());

			String loggedUsername = getLoggedUsername();

			try {
				this.routineService.saveRoutine(routine, loggedUsername, trainingId);
			} catch (Exception e) {

				return EXCEPTION;
			}

			String updateRoutine = " '" + routine.getName() + "' " + "has been updated succesfully";
			redirectAttrs.addFlashAttribute("updateRoutine", updateRoutine);

			return REDIRECT_CLIENT + clientUsername + TRAININGS + trainingId;
		}
	}

	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/delete")
	public String deleteRoutine(@PathVariable("routineId") int routineId,
			@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") int trainingId,
			RedirectAttributes redirectAttrs) {

		Client client = this.clientService.findClientByUsername(clientUsername);

		Boolean routineEx = routineExist(routineId);
		Boolean trainingFinish = isTrainingFinished(trainingId);
		Boolean isLogged = isLoggedTrainer(clientUsername);
		Boolean trainFClient = isTrainingFromClient(client);

		if (Boolean.FALSE.equals(routineEx) || Boolean.TRUE.equals(trainingFinish) || Boolean.FALSE.equals(isLogged)
				|| Boolean.FALSE.equals(trainFClient))
			return EXCEPTION;

		Routine routine = this.routineService.findRoutineById(routineId);

		try {
			this.routineService.deleteRoutine(routine, clientUsername, trainingId);
		} catch (Exception e) {

			return EXCEPTION;
		}

		String deleteRoutine = " '" + routine.getName() + "' " + "has been deleted succesfully";
		redirectAttrs.addFlashAttribute("deleteRoutine", deleteRoutine);

		return REDIRECT_CLIENT + clientUsername + TRAININGS + trainingId;
	}

	// Security Utils Check

	public String getLoggedUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String trainerUsername = "";

		if (principal instanceof String)
			trainerUsername = principal.toString();
		else
			trainerUsername = ((UserDetails) principal).getUsername();

		return trainerUsername;
	}

	public Boolean routineExist(final int routineId) {
		return this.routineService.findRoutineById(routineId) != null;
	}

	public Boolean isLoggedTrainer(final String trainerUsername) {
		String principalUsername = getLoggedUsername();

		return principalUsername.trim().equalsIgnoreCase(trainerUsername.trim().toLowerCase());
	}

	public Boolean isClientOfLoggedTrainer(final int clientId) {

		String trainerUsername = getLoggedUsername();

		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Client client = this.clientService.findClientById(clientId);

		return trainer.getClients().contains(client);
	}

	public Boolean isTrainingFinished(final int trainingId) {
		Calendar now = Calendar.getInstance();
		Date actualDate = now.getTime();

		Training training = this.trainingService.findTrainingById(trainingId);

		return training.getEndDate().before(actualDate);
	}

	public Boolean isTrainingFromClient(Client client) {

		return getLoggedUsername().trim().equalsIgnoreCase(client.getUser().getUsername().trim().toLowerCase());
	}

}
