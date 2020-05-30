package org.springframework.samples.yogogym.web;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.samples.yogogym.service.RoutineLineService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoutineLineController {

	//Services
	private RoutineService routineService;
	private ExerciseService exerciseService;
	private ClientService clientService;
	private RoutineLineService routineLineService;
	private TrainerService trainerService;
	private TrainingService trainingService;
	
	//ROUTINES ATTRIBUTES
	public static final String ROUTINE_ID = "routineId";
	public static final String CLIENT = "client";
	public static final String ROUTINE_LINE = "routineLine";
	public static final String EXERCISES = "exercises";
		
	//VIEWS
	public static final String EXCEPTION = "exception";
	public static final String TRAINER_CREATE_OR_UPDATE = "trainer/routines/routinesLineCreateOrUpdate";
	public static final String CLIENT_CREATE_OR_UPDATE = "client/routines/routinesLineCreateOrUpdate"; 
		
	@Autowired
	public RoutineLineController(final RoutineService routineService, final ExerciseService exerciseService,
			final ClientService clientService, final RoutineLineService routineLineService, final TrainerService trainerService, final TrainingService trainingService) {
		this.routineService = routineService;
		this.exerciseService = exerciseService;
		this.clientService = clientService;
		this.routineLineService = routineLineService;
		this.trainerService = trainerService;
		this.trainingService = trainingService;
	
	}

	@InitBinder("routineLine")
	public void initRoutineLineBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new RoutineLineValidator(this.routineLineService,this.exerciseService));
	}
	
	// TRAINER
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String initRoutineLineCreateForm(@PathVariable("clientId") int clientId,
			@PathVariable("routineId") int routineId, @PathVariable("trainerUsername")final String trainerUsername, @PathVariable("trainingId")final int trainingId, final ModelMap model) {
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(clientId);
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer))
			return EXCEPTION;
		
		RoutineLine routineLine = new RoutineLine();
		Client client = this.clientService.findClientById(clientId);
			
		return this.createUpdateView('t',model, client, routineId, routineLine);
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String processRoutineLineCreationForm(@Valid RoutineLine routineLine,BindingResult result, @ModelAttribute("exercise.id")final int exerciseId,
			@PathVariable("trainerUsername") String trainerUsername, @ModelAttribute("routineId") int routineId,
			@PathVariable("clientId") int clientId, @PathVariable("trainingId") int trainingId, final ModelMap model){
			
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(clientId);
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer))
			return EXCEPTION;
		
		if (result.hasErrors()) {
			
			Client client = this.clientService.findClientById(clientId);
			return createUpdateView('t',model, client, routineId, routineLine);
		} 
		else 
		{
			Routine routine = this.routineService.findRoutineById(routineId);
			
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setExercise(exercise);
			
			routine.getRoutineLine().add(routineLine);			

			try
			{
				this.routineLineService.saveRoutineLine(routineLine, trainingId);
			}
			catch(Exception e)
			{
				Client client = this.clientService.findClientById(clientId);
				return createUpdateView('t',model, client, routineId, routineLine);
			}
			
			return generateRedirectTrainer(trainerUsername,clientId,trainingId,routineId);
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update")
	public String initRoutineLineUpdateForm(@PathVariable("clientId") int clientId, @PathVariable("trainerUsername")final String trainerUsername,
			@PathVariable("routineId") int routineId, @PathVariable("routineLineId") int routineLineId,@PathVariable("trainingId")final int trainingId,final ModelMap model) {
		
		Boolean routineExist = routineExist(routineId);
		Boolean routineLineExist = routineLineExist(routineLineId); 
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(clientId);
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.FALSE.equals(routineLineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer))
			return EXCEPTION;
			
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		Client client = this.clientService.findClientById(clientId);
	
		return createUpdateView('t',model, client, routineId, routineLine);
	}

	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update")
	public String processRoutineLineUpdateForm(@Valid RoutineLine routineLine,BindingResult result, @ModelAttribute("exercise.id")final int exerciseId,
			@PathVariable("trainerUsername") String trainerUsername, @ModelAttribute("routineId") int routineId,
			@PathVariable("clientId") int clientId, @PathVariable("trainingId") int trainingId,@PathVariable("routineLineId")final int routineLineId, final ModelMap model, RedirectAttributes redirectAttrs){
			
		Boolean routineExist = routineExist(routineId);
		Boolean routineLineExist = routineLineExist(routineLineId); 
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(clientId);
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.FALSE.equals(routineLineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer))
			return EXCEPTION;
		
		if (result.hasErrors()) 
		{
			Client client = this.clientService.findClientById(clientId);
			return createUpdateView('t',model, client, routineId, routineLine);
		} 
		else 
		{			
			routineLine.setId(routineLineId);
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setExercise(exercise);
	
			try
			{
				this.routineLineService.saveRoutineLine(routineLine,trainingId);
			}
			catch (Exception e)
			{
				Client client = this.clientService.findClientById(clientId);
				return createUpdateView('t',model, client, routineId, routineLine);
			}
			
			String updateRoutineLine = generateMessage('u',routineLineId,routineId);
			redirectAttrs.addFlashAttribute("updateRoutineLine", updateRoutineLine);
			
			return generateRedirectTrainer(trainerUsername,clientId,trainingId,routineId);
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/delete")
	public String deleteRoutineLine(@PathVariable("routineId")int routineId,@PathVariable("routineLineId")int routineLineId, @PathVariable("clientId")int clientId, @PathVariable("trainingId")int trainingId, @PathVariable("trainerUsername")String trainerUsername, ModelMap model, RedirectAttributes redirectAttrs)
	{
		Boolean routineExist = routineExist(routineId);
		Boolean routineLineExist = routineLineExist(routineLineId); 
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(clientId);
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.FALSE.equals(routineLineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer))
			return EXCEPTION;
		
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		try
		{
			this.routineLineService.deleteRoutineLine(routineLine,trainingId);			
		}
		catch(Exception e)
		{
			return EXCEPTION;
		}
		
		String deleteRoutineLine = generateMessage('d',routineLineId, routineId);
		redirectAttrs.addFlashAttribute("deleteRoutineLine", deleteRoutineLine);
		
		return generateRedirectTrainer(trainerUsername, clientId, trainingId, routineId);
	}
	
	// CLIENT
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String initRoutineLineCreateForm(@PathVariable("trainingId") int trainingId,
			@PathVariable("routineId") int routineId, @PathVariable("clientUsername")final String clientUsername, final ModelMap model) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isLoggedTrainer = isLoggedTrainer(clientUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient))
			return EXCEPTION;
				
		RoutineLine routineLine = new RoutineLine();		
		return createUpdateView('c',model, client, routineId, routineLine);
	}
	
	@PostMapping("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create")
	public String processRoutineLineCreateForm(@Valid RoutineLine routineLine, BindingResult result, @PathVariable("trainingId") int trainingId,
			@PathVariable("routineId") int routineId, @PathVariable("clientUsername")final String clientUsername, @ModelAttribute("exercise.id")final int exerciseId, final ModelMap model) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isLoggedTrainer = isLoggedTrainer(clientUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient))
			return EXCEPTION;
		
		if (result.hasErrors()) 
		{				
			return createUpdateView('c',model,client, routineId,routineLine);
		} 
		else 
		{
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setExercise(exercise);
			
			Routine routine = this.routineService.findRoutineById(routineId);
			routine.getRoutineLine().add(routineLine);			

			try
			{
				this.routineLineService.saveRoutineLine(routineLine, trainingId);
			}
			catch(Exception e)
			{
				return createUpdateView('c',model, client, routineId, routineLine);
			}

			return generateRedirectClient(clientUsername, trainingId);
		}
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update")
	public String initRoutineLineUpdateForm(@PathVariable("clientUsername")final String clientUsername,
			@PathVariable("routineId") int routineId, @PathVariable("routineLineId") int routineLineId,@PathVariable("trainingId")final int trainingId,final ModelMap model) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isLoggedTrainer = isLoggedTrainer(clientUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient))
			return EXCEPTION;
			
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		return createUpdateView('c',model,client, routineId, routineLine);
	}
	
	@PostMapping("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update")
	public String processRoutineLineUpdateForm(@Valid RoutineLine routineLine,BindingResult result, @ModelAttribute("exercise.id")final int exerciseId,
			@PathVariable("clientUsername") String clientUsername, @ModelAttribute("routineId") int routineId,
			@PathVariable("trainingId") int trainingId,@PathVariable("routineLineId")final int routineLineId, final ModelMap model, RedirectAttributes redirectAttrs){
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isLoggedTrainer = isLoggedTrainer(clientUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient))
			return EXCEPTION;
		
		if (result.hasErrors()) {
			
			routineLine.setId(routineLineId);
			return createUpdateView('c',model,client, routineId, routineLine);
			
		} else {
			
			Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
			routineLine.setId(routineLineId);
			routineLine.setExercise(exercise);
	
			try
			{
				this.routineLineService.saveRoutineLine(routineLine,trainingId);
			}
			catch (Exception e)
			{
				return createUpdateView('c',model,client, routineId, routineLine);
			}

			String updateRoutineLine = generateMessage('u', routineLineId, routineId);
			redirectAttrs.addFlashAttribute("updateRoutineLine", updateRoutineLine);
			
			return generateRedirectClient(clientUsername, trainingId);
		}
	}
	
	@GetMapping("client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/delete")
	public String deleteRoutineLine(@PathVariable("routineId")int routineId,@PathVariable("routineLineId")int routineLineId, @PathVariable("trainingId")int trainingId, @PathVariable("clientUsername")String clientUsername, Model model, RedirectAttributes redirectAttrs)
	{
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isLoggedTrainer = isLoggedTrainer(clientUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient))
			return EXCEPTION;
		
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		try
		{
			this.routineLineService.deleteRoutineLine(routineLine,trainingId);			
		}
		catch(Exception e)
		{
			return EXCEPTION;
		}
		
		String deleteRoutineLine = generateMessage('d', routineLineId, routineId);
		redirectAttrs.addFlashAttribute("deleteRoutineLine", deleteRoutineLine);
		
		return generateRedirectClient(clientUsername, trainingId);
	}
	
	//Security Utils
	
	public Boolean checkTrainerSecurity(char u, int routineId, int trainingId, String trainerUsername, Client client)
	{
		Boolean routineExist = routineExist(routineId);
		Boolean isTrainingFinished = isTrainingFinished(trainingId);
		Boolean isClientOfLoggedTrainer = isClientOfLoggedTrainer(client.getId());
		Boolean isLoggedTrainer = isLoggedTrainer(trainerUsername);
		Boolean isTrainingFromClient = isTrainingFromClient(client);
		
		if(u == 't')
			return Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isClientOfLoggedTrainer) || Boolean.FALSE.equals(isLoggedTrainer);
		else
			return Boolean.FALSE.equals(routineExist) || Boolean.TRUE.equals(isTrainingFinished) || Boolean.FALSE.equals(isLoggedTrainer) || Boolean.FALSE.equals(isTrainingFromClient);
	}
	
	public String createUpdateView(char u, ModelMap model, Client client, int routineId, @Valid RoutineLine routineLine)
	{
		Collection<Exercise> exerciseCollection = this.exerciseService.findAllExercise();
		Map<Integer,String> selectVals = generateSelectValues(exerciseCollection);
			
		model.addAttribute(ROUTINE_ID, routineId);
		model.addAttribute(CLIENT, client);
		model.addAttribute(ROUTINE_LINE, routineLine);
		model.addAttribute(EXERCISES, selectVals);
		
		if(u == 't')
			return TRAINER_CREATE_OR_UPDATE;
		else
			return CLIENT_CREATE_OR_UPDATE;
	}
	
	public String getLoggedUsername()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		return ((UserDetails)principal).getUsername();
	}
	
	public Boolean routineExist(final int routineId)
	{
		return this.routineService.findRoutineById(routineId) != null;
	}
	
	public Boolean routineLineExist(final int routineLineId)
	{
		return this.routineLineService.findRoutineLineById(routineLineId) != null;
	}
	
	public Boolean isLoggedTrainer(final String trainerUsername)
	{		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String principalUsername = ((UserDetails)principal).getUsername();
		
		return principalUsername.trim().equalsIgnoreCase(trainerUsername.trim());
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
	
	public String generateRedirectTrainer(String trainerUsername, int clientId, int trainingId, int routineId)
	{
		String res = "redirect:";
		res+="/trainer/"+ trainerUsername + "/clients/" + clientId + "/trainings/" + trainingId + "/routines/" + routineId;
		return res;
	}
	
	public String generateRedirectClient(String clientUsername, int trainingId)
	{
		String res = "redirect:";
		res+="/client/" + clientUsername + "/trainings/" + trainingId;
		return res;
	}
	
	public String generateMessage(char o, int routineLineId, int routineId)
	{
		String res ="RoutineLine #";
		
		switch(o)
		{
		case 'u':
			res += routineLineId + "' " + "has been updated succesfully from 'Routine with id #" + routineId + "'";
			break;
		case 'd':
			res += routineLineId + "' " + "has been deleted succesfully from 'Routine with id #" + routineId + "'";
			break;
		default:
			break;
		}		
		return res;
	}
	
	public Map<Integer,String> generateSelectValues(Collection<Exercise> exerciseCollection)
	{
		Map<Integer,String> selectVals = new TreeMap<>();
		
		for(Exercise e:exerciseCollection)
		{	
			if(e.getEquipment() != null)
				selectVals.put(e.getId(),  e.getName()+", "+e.getEquipment().getName());
			else
				selectVals.put(e.getId(),  e.getName());
		}
		
		return selectVals;
	}
	
	public Boolean isTrainingFromClient(Client client)
	{
		return getLoggedUsername().trim().equalsIgnoreCase(client.getUser().getUsername().trim());
	}
}
