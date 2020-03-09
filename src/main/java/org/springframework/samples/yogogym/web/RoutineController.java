
package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.RoutineLineService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoutineController {

	private final RoutineService routineService;
	private final ExerciseService exerciseService;
	private final ClientService clientService;
	private final TrainerService trainerService;
	private final RoutineLineService routineLineService;

	@Autowired
	public RoutineController(final RoutineService routineService, final ExerciseService exerciseService,final ClientService clientService, final TrainerService trainerService, final RoutineLineService routineLineService) {
		this.routineService = routineService;
		this.exerciseService = exerciseService;
		this.clientService = clientService;
		this.trainerService = trainerService;
		this.routineLineService = routineLineService;
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/routines/create")
	public String initRoutineCreateForm(@PathVariable("clientId") int clientId, final Model model) {
		Collection<RoutineLine> routineLine = new ArrayList<>();
		
		Routine routine = new Routine();
		routine.setRoutineLine(routineLine);
		
		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		Client client = this.clientService.findClientById(clientId);
		
		model.addAttribute("client",client);
		model.addAttribute("routine",routine);
		model.addAttribute("exercises",exerciseCollection);	
		
		return "trainer/routines/routinesCreateOrUpdate";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/routines/create")
	public String processRoutineCreationForm(@Valid Routine routine, BindingResult result, @PathVariable("trainerUsername")String trainerUsername, @PathVariable("clientId")int clientId)
	{
		if(result.hasErrors())
		{
			return "trainer/routines/routinesCreateOrUpdate";
		}
		else
		{
			this.routineService.saveRoutine(routine);
			
			Trainer trainer = this.trainerService.findTrainer(trainerUsername);
			
			Client client = this.clientService.findClientById(clientId);
			client.getRoutines().add(routine);
			this.clientService.saveClient(client);
			
			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/routines";
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/routines/{routineId}/routineLine/create")
	public String initRoutineLineCreateForm(@PathVariable("clientId") int clientId,@PathVariable("routineId") int routineId, final Model model) {
		Routine routine = this.routineService.findRoutineById(routineId);
		
		Exercise exercise = new Exercise();
		
		RoutineLine routineLine = new RoutineLine();
		routineLine.setExercises(exercise);
		
		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		Client client = this.clientService.findClientById(clientId);
		
		model.addAttribute("routine",routine);
		model.addAttribute("client",client);
		model.addAttribute("routineLine",routineLine);
		model.addAttribute("exercises",exerciseCollection);	
		
		return "trainer/routines/routinesLineCreateOrUpdate";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/routines/{routineId}/routineLine/create")
	public String processRoutineLineCreationForm(@Valid RoutineLine routineLine, BindingResult result, @PathVariable("trainerUsername")String trainerUsername, @PathVariable("routineId")int routineId, @PathVariable("clientId")int clientId, @ModelAttribute("exercise") int exerciseId)
	{
		if(result.hasErrors())
		{
			return "trainer/routines/routinesLineCreateOrUpdate";
		}
		else
		{
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setExercises(exercise);
			
			Routine routine = this.routineService.findRoutineById(routineId);
			routine.getRoutineLine().add(routineLine);
			
			this.routineService.saveRoutine(routine);			
						
			Trainer trainer = this.trainerService.findTrainer(trainerUsername);
			Client client = this.clientService.findClientById(clientId);
			
			return "redirect:/trainer/" + trainer.getUser().getUsername() + "/clients/" + client.getId() + "/routines/" + routine.getId();
		}
	}
}
