
package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
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

	@Autowired
	private final RoutineService routineService;
	private final ExerciseService exerciseService;
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;

	@Autowired
	public RoutineController(final RoutineService routineService, final ExerciseService exerciseService,
			final ClientService clientService, final TrainerService trainerService,
			final TrainingService trainingService) {
		this.routineService = routineService;
		this.exerciseService = exerciseService;
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.trainingService = trainingService;
	}
	
	@InitBinder("routine")
	public void initRoutineBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new RoutineValidator(routineService));
	}

	// TRAINER
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}")
	public String ClientRoutineDetails(@PathVariable("trainerUsername") String trainerUsername,
			@PathVariable("clientId") int clientId, @PathVariable("routineId") int routineId,
			@PathVariable("trainingId") int trainingId, Model model) {
		Client client = this.clientService.findClientById(clientId);
		Routine routine = this.routineService.findRoutineById(routineId);
		Training training = this.trainingService.findTrainingById(trainingId);

		model.addAttribute("client", client);
		model.addAttribute("routine", routine);
		model.addAttribute("training", training);

		return "trainer/routines/routineDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/routines")
	public String RoutinesList(@PathVariable("trainerUsername") String trainerUsername, Model model) {
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		model.addAttribute("trainer", trainer);

		return "trainer/routines/routinesList";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String initRoutineCreateForm(@PathVariable("clientId") int clientId, final Model model) {
		Collection<RoutineLine> routineLine = new ArrayList<>();

		Routine routine = new Routine();
		routine.setRoutineLine(routineLine);

		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		Client client = this.clientService.findClientById(clientId);

		model.addAttribute("client", client);
		model.addAttribute("routine", routine);
		model.addAttribute("exercises", exerciseCollection);

		return "trainer/routines/routinesCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create")
	public String processRoutineCreationForm(@Valid Routine routine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("trainingId") int trainingId,
			@PathVariable("clientId") int clientId,  final ModelMap model) throws DataAccessException, PastInitException, EndBeforeInitException, InitInTrainingException, EndInTrainingException, PeriodIncludingTrainingException, PastEndException {
		if (result.hasErrors()) {
			return "trainer/routines/routinesCreateOrUpdate";
		} else {
			Training training = this.trainingService.findTrainingById(trainingId);
			training.getRoutines().add(routine);

			this.trainingService.saveTraining(training);

			return "redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/"
					+ trainingId;
		}
	}

}
