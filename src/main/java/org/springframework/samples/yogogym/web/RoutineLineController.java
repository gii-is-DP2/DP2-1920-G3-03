
package org.springframework.samples.yogogym.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoutineLineController {

	private final RoutineService routineService;
	private final ExerciseService exerciseService;
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final TrainingService trainingService;

	@Autowired
	public RoutineLineController(final RoutineService routineService, final ExerciseService exerciseService,
			final ClientService clientService, final TrainerService trainerService,
			final TrainingService trainingService) {
		this.routineService = routineService;
		this.exerciseService = exerciseService;
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.trainingService = trainingService;
	}

	// TRAINER

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String initRoutineLineCreateForm(@PathVariable("clientId") int clientId,
			@PathVariable("routineId") int routineId, final Model model) {
		Routine routine = this.routineService.findRoutineById(routineId);

		Exercise exercise = new Exercise();

		RoutineLine routineLine = new RoutineLine();
		routineLine.setExercise(exercise);

		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		Client client = this.clientService.findClientById(clientId);

		model.addAttribute("routine", routine);
		model.addAttribute("client", client);
		model.addAttribute("routineLine", routineLine);
		model.addAttribute("exercises", exerciseCollection);

		return "trainer/routines/routinesLineCreateOrUpdate";
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String processRoutineLineCreationForm(@Valid RoutineLine routineLine, BindingResult result,
			@PathVariable("trainerUsername") String trainerUsername, @PathVariable("routineId") int routineId,
			@PathVariable("clientId") int clientId, @PathVariable("trainingId") int trainingId,
			@ModelAttribute("exerciseId") int exerciseId) {
		if (result.hasErrors()) {
			return "trainer/routines/routinesLineCreateOrUpdate";
		} else {
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setExercise(exercise);

			Routine routine = this.routineService.findRoutineById(routineId);
			routine.getRoutineLine().add(routineLine);

			this.routineService.saveRoutine(routine);

			Trainer trainer = this.trainerService.findTrainer(trainerUsername);
			Client client = this.clientService.findClientById(clientId);
			Training training = this.trainingService.findTrainingById(trainingId);

			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/clients/" + client.getId() + "/trainings/"
					+ training.getId() + "/routines/" + routine.getId();
		}
	}
}
