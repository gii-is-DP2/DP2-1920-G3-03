package org.springframework.samples.yogogym.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		if(!isLoggedUser(trainerUsername,true))
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

		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");		
		String actualDate = dateFormat.format(date);
		
		model.addAttribute("actualDate", actualDate);
		
		model.addAttribute("client", client);
		model.addAttribute("training", training);
		if((training.getRoutines().isEmpty() || training.getRoutines()==null) && training.getDiet()==null) {
			model.addAttribute("hasNotRoutine",true);
		}

		return "trainer/trainings/trainingsDetails";
	}

	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/create")
	public String initTrainingCreateForm(@PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)) {
			return "exception";
		}
		
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
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)) {
			return "exception";
		}
		
		Client client = this.clientService.findClientById(clientId);
		model.addAttribute("client", client);
		
		if (result.hasErrors()) {
			model.put("training", training);
			return "trainer/trainings/trainingCreateOrUpdate";
		} else {
			if(!training.getAuthor().equals(trainerUsername)||training.getEditingPermission().equals(EditingPermission.CLIENT)) {
				return "exception";
			}
			try {			
				this.trainingService.saveTraining(training,client);
			} 
			catch (Exception e) {
				if(e instanceof PastInitException) {
					result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				}
				else if (e instanceof PastEndException) {
					result.rejectValue("endDate", null, "The end date cannot be in the past");
				}
				else if (e instanceof EndBeforeEqualsInitException) {
					result.rejectValue("endDate", null, "The end date must be after the initial date");
				}
				else if (e instanceof LongerThan90DaysException) {
					result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				}
				else if (e instanceof InitInTrainingException) {
					InitInTrainingException ex = (InitInTrainingException) e;
					result.rejectValue("initialDate", null, "The training cannot start in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof EndInTrainingException) {
					EndInTrainingException ex = (EndInTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot end in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof PeriodIncludingTrainingException) {
					PeriodIncludingTrainingException ex = (PeriodIncludingTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot be in a period "
						+ "which includes another training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				return "trainer/trainings/trainingCreateOrUpdate";
			} 
			
			List<Training> allTrainingsClient = new ArrayList<Training>(this.trainingService.findTrainingFromClient(clientId));
			Training newTraining = allTrainingsClient.get(allTrainingsClient.size()-1);
			return "redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/"+newTraining.getId();
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String initTrainingUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, Model model) {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)||training.getEditingPermission().equals(EditingPermission.CLIENT)) {
			return "exception";
		}
		
		Client client = this.clientService.findClientById(clientId);
		
		Date now = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("endDateAux", training.getEndDate());
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("training", training);
		model.addAttribute("client", client);
		return "trainer/trainings/trainingCreateOrUpdate";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit")
	public String processTrainingUpdateForm(@Valid Training training, BindingResult result, 
		@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		Training oldTraining = this.trainingService.findTrainingById(trainingId);
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)||oldTraining.getEditingPermission().equals(EditingPermission.CLIENT)) {
			return "exception";
		}

		Client client = this.clientService.findClientById(clientId);
		Date now = Calendar.getInstance().getTime();
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
			
			String oldTrainingEndDate = dateFormat.format(oldTraining.getEndDate());
			String newTrainingEndDate = dateFormat.format(training.getEndDate());
			
			if(oldTraining.getEndDate().before(now)&&!newTrainingEndDate.equals(oldTrainingEndDate)
				||training.getEditingPermission().equals(EditingPermission.CLIENT)
				||(!oldTraining.getAuthor().equals(trainerUsername)&&!training.getEditingPermission().equals(oldTraining.getEditingPermission()))) {
				return "exception";
			}
			
			training.setAuthor(oldTraining.getAuthor());
			training.setInitialDate(oldTraining.getInitialDate());
			training.setDiet(oldTraining.getDiet());
			training.setRoutines(oldTraining.getRoutines());
			training.setId(trainingId);
			
			try {
				this.trainingService.saveTraining(training,client);
			} 
			catch (Exception e) {
				if(e instanceof PastInitException) {
					result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				}
				else if (e instanceof PastEndException) {
					result.rejectValue("endDate", null, "The end date cannot be in the past");
				}
				else if (e instanceof EndBeforeEqualsInitException) {
					result.rejectValue("endDate", null, "The end date must be after the initial date");
				}
				else if (e instanceof LongerThan90DaysException) {
					result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				}
				else if (e instanceof InitInTrainingException) {
					InitInTrainingException ex = (InitInTrainingException) e;
					result.rejectValue("initialDate", null, "The training cannot start in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof EndInTrainingException) {
					EndInTrainingException ex = (EndInTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot end in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof PeriodIncludingTrainingException) {
					PeriodIncludingTrainingException ex = (PeriodIncludingTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot be in a period "
						+ "which includes another training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				return "trainer/trainings/trainingCreateOrUpdate";
			}
			
			return "redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}";
		}
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete")
	public String processDeleteTrainingForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername,RedirectAttributes redirectAttrs) {
		
		Client client = this.clientService.findClientById(clientId);
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		if(training==null||!isClientOfLoggedTrainer(clientId,trainerUsername)||!training.getAuthor().equals(trainerUsername)) {
			return "exception";
		}
		else {
			this.trainingService.deleteTraining(training,client);
			redirectAttrs.addFlashAttribute("deleteMessage", "The training was deleted successfully");
			return "redirect:/trainer/{trainerUsername}/trainings";
		}
	}
	
	//Copy training
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining")
	public String getTrainingListCopy(@PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)||training.getEditingPermission().equals(EditingPermission.CLIENT)||!isTrainingOfClient(trainingId,clientId)||!isTrainingEmpty(trainingId)) {
			return "exception";
		}
		
		Collection<Training> trainings = this.trainingService.findTrainingWithPublicClient();		
		if(trainings.isEmpty()) {
			model.addAttribute("notHaveTrainingsPublic", true);
		}else {
			Collection<Training> tr = trainings.stream().filter(t->t!=null).filter(t->t.getDiet()!=null || !t.getRoutines().isEmpty()).collect(Collectors.toList());
			if(tr.isEmpty()) {
				model.addAttribute("notHaveTrainingsPublic", true);
			}else {
				model.addAttribute("trainings", tr);
			}
		}
		return "trainer/trainings/listCopyTraining";
	}
	
	@PostMapping("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining")
	public String processTrainingCopy(@ModelAttribute("trainingIdToCopy") int idTrainingToCopy, @PathVariable("trainingId") int trainingId, @PathVariable("clientId") int clientId, @PathVariable("trainerUsername") String trainerUsername, ModelMap model) {
		
		Client client = this.clientService.findClientById(clientId);
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		if(!isClientOfLoggedTrainer(clientId,trainerUsername)||training.getEditingPermission().equals(EditingPermission.CLIENT)||!isTrainingOfClient(trainingId,clientId)||!isTrainingEmpty(trainingId)||!this.clientService.isPublicByTrainingId(idTrainingToCopy)) {
			return "exception";
		}
		Training trainingToCopy = this.trainingService.findTrainingById(idTrainingToCopy);
		
		Training nuevo = new Training();
		if(trainingToCopy.getDiet()!=null) {
			nuevo.setDiet(trainingToCopy.getDiet());
		}
		if(trainingToCopy.getRoutines()!=null) {
			Collection<Routine> routines = new ArrayList<>();
			for(Routine r : trainingToCopy.getRoutines()) {
				Routine nueva = new Routine();
				if(r.getRoutineLine()!=null) {
					Collection<RoutineLine> routinesLines = new ArrayList<>();
					for(RoutineLine rl : r.getRoutineLine()) {
						RoutineLine nuevaRl = new RoutineLine();
						nuevaRl.setExercise(rl.getExercise());
						nuevaRl.setReps(rl.getReps());
						nuevaRl.setSeries(rl.getSeries());
						nuevaRl.setTime(rl.getTime());
						nuevaRl.setWeight(rl.getWeight());
						routinesLines.add(nuevaRl);
					}
					nueva.setRoutineLine(routinesLines);
				}
				nueva.setDescription(r.getDescription());
				nueva.setName(r.getName());
				nueva.setRepsPerWeek(r.getRepsPerWeek());
				routines.add(nueva);
			}
			nuevo.setRoutines(routines);
		}
		nuevo.setAuthor(training.getAuthor());
		nuevo.setEditingPermission(training.getEditingPermission());
		nuevo.setEndDate(training.getEndDate());
		nuevo.setId(training.getId());
		nuevo.setInitialDate(training.getInitialDate());
		nuevo.setName(training.getName());
		
		try {
			this.trainingService.saveTraining(nuevo,client);
		}catch(Exception e) {
			
		}
		
		return "redirect:/trainer/{trainerUsername}/trainings";
	}

	private boolean isTrainingEmpty(int trainingId) {
		Training training = this.trainingService.findTrainingById(trainingId);
		return training.getDiet()==null && training.getRoutines().isEmpty();
	}

	private boolean isTrainingOfClient(int trainingId, int clientId) {
		Collection<Integer> list = this.trainingService.findTrainingIdFromClient(clientId);
		return list.contains(trainingId);
	}

	//CLIENT
		
	@GetMapping("/client/{clientUsername}/trainings")
	public String getTrainingList(@PathVariable("clientUsername") String clientUsername, Model model) {
		
		if(!isLoggedUser(clientUsername,false)) {
			return "exception";
		}
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		Collection<Training> trainings = this.trainingService.findTrainingFromClient(client.getId());
		
		model.addAttribute("trainings",trainings);
		
		return "client/trainings/trainingsList";
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}")
	public String getTrainingDetails(@PathVariable("clientUsername") String clientUsername, @PathVariable("trainingId") int trainingId, Model model) {
		
		if(!isLoggedUser(clientUsername,false)) {
			return "exception";
		}
		
		Training training = this.trainingService.findTrainingById(trainingId);
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		model.addAttribute("training",training);
		model.addAttribute("client",client);
		
		return "client/trainings/trainingsDetails";
	}
	
	@GetMapping("/client/{clientUsername}/trainings/create")
	public String initTrainingCreateForm(@PathVariable("clientUsername") String clientUsername, ModelMap model) {
		
		if(!isLoggedUser(clientUsername,false)) {
			return "exception";
		}
		
		Training training = new Training();
		Client client = this.clientService.findClientByUsername(clientUsername);

		model.addAttribute("training", training);
		model.addAttribute("client", client);

		return "client/trainings/trainingCreateOrUpdate";
	}

	@PostMapping("/client/{clientUsername}/trainings/create")
	public String processTrainingCreateForm(@Valid Training training, BindingResult result,
			@PathVariable("clientUsername") String clientUsername, ModelMap model) {
		
		if(!isLoggedUser(clientUsername,false)) {
			return "exception";
		}
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		model.addAttribute("client", client);
		
		if (result.hasErrors()) {
			model.put("training", training);
			return "client/trainings/trainingCreateOrUpdate";
		} else {
			if(!training.getAuthor().equals(clientUsername)||training.getEditingPermission().equals(EditingPermission.TRAINER)) {
				return "exception";
			}
			try {			
				this.trainingService.saveTraining(training,client);
			} 
			catch (Exception e) {
				if(e instanceof PastInitException) {
					result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				}
				else if (e instanceof PastEndException) {
					result.rejectValue("endDate", null, "The end date cannot be in the past");
				}
				else if (e instanceof EndBeforeEqualsInitException) {
					result.rejectValue("endDate", null, "The end date must be after the initial date");
				}
				else if (e instanceof LongerThan90DaysException) {
					result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				}
				else if (e instanceof InitInTrainingException) {
					InitInTrainingException ex = (InitInTrainingException) e;
					result.rejectValue("initialDate", null, "The training cannot start in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof EndInTrainingException) {
					EndInTrainingException ex = (EndInTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot end in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof PeriodIncludingTrainingException) {
					PeriodIncludingTrainingException ex = (PeriodIncludingTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot be in a period "
						+ "which includes another training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				return "client/trainings/trainingCreateOrUpdate";
			} 
			
			List<Training> allTrainingsClient = new ArrayList<Training>(this.trainingService.findTrainingFromClient(client.getId()));
			Training newTraining = allTrainingsClient.get(allTrainingsClient.size()-1);
			return "redirect:/client/{clientUsername}/trainings/"+newTraining.getId();
		}
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/edit")
	public String initTrainingUpdateForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientUsername") String clientUsername, Model model) {
		
		Training training = this.trainingService.findTrainingById(trainingId);
		
		if(!isLoggedUser(clientUsername,false)||training.getEditingPermission().equals(EditingPermission.TRAINER)) {
			return "exception";
		}
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Date now = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("endDateAux", training.getEndDate());
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("training", training);
		model.addAttribute("client", client);
		return "client/trainings/trainingCreateOrUpdate";
	}
	
	@PostMapping("/client/{clientUsername}/trainings/{trainingId}/edit")
	public String processTrainingUpdateForm(@Valid Training training, BindingResult result, 
		@PathVariable("trainingId") int trainingId, @PathVariable("clientUsername") String clientUsername, ModelMap model) {
		
		Training oldTraining = this.trainingService.findTrainingById(trainingId);
		
		if(!isLoggedUser(clientUsername,false)||oldTraining.getEditingPermission().equals(EditingPermission.TRAINER)) {
			return "exception";
		}

		Client client = this.clientService.findClientByUsername(clientUsername);
		Date now = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String actualDate = dateFormat.format(now);
		
		model.addAttribute("endDateAux", oldTraining.getEndDate());
		model.addAttribute("actualDate", actualDate);
		model.addAttribute("client", client);
		
		training.setId(trainingId);
		
		if (result.hasErrors()) {
			model.put("training", training);
			return "client/trainings/trainingCreateOrUpdate";
		} 
		else {
						
			String oldTrainingEndDate = dateFormat.format(oldTraining.getEndDate());
			String newTrainingEndDate = dateFormat.format(training.getEndDate());
			
			if(oldTraining.getEndDate().before(now)&&!newTrainingEndDate.equals(oldTrainingEndDate)
				||training.getEditingPermission().equals(EditingPermission.TRAINER)
				||(!oldTraining.getAuthor().equals(clientUsername)&&!training.getEditingPermission().equals(oldTraining.getEditingPermission()))) {
				return "exception";
			}
			
			training.setAuthor(oldTraining.getAuthor());
			training.setInitialDate(oldTraining.getInitialDate());
			training.setDiet(oldTraining.getDiet());
			training.setRoutines(oldTraining.getRoutines());
			training.setId(trainingId);
			
			try {
				this.trainingService.saveTraining(training,client);
			} 
			catch (Exception e) {
				if(e instanceof PastInitException) {
					result.rejectValue("initialDate", null, "The initial date cannot be in the past");
				}
				else if (e instanceof PastEndException) {
					result.rejectValue("endDate", null, "The end date cannot be in the past");
				}
				else if (e instanceof EndBeforeEqualsInitException) {
					result.rejectValue("endDate", null, "The end date must be after the initial date");
				}
				else if (e instanceof LongerThan90DaysException) {
					result.rejectValue("endDate", null, "The training cannot be longer than 90 days");
				}
				else if (e instanceof InitInTrainingException) {
					InitInTrainingException ex = (InitInTrainingException) e;
					result.rejectValue("initialDate", null, "The training cannot start in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof EndInTrainingException) {
					EndInTrainingException ex = (EndInTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot end in a period "
						+ "with other training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				else if (e instanceof PeriodIncludingTrainingException) {
					PeriodIncludingTrainingException ex = (PeriodIncludingTrainingException) e;
					result.rejectValue("endDate", null, "The training cannot be in a period "
						+ "which includes another training (The other training is from " + ex.getInitAssoc() + " to " + ex.getEndAssoc() + ")");
				}
				return "client/trainings/trainingCreateOrUpdate";
			}
			
			return "redirect:/client/{clientUsername}/trainings/{trainingId}";
		}
	}
	
	@GetMapping("/client/{clientUsername}/trainings/{trainingId}/delete")
	public String processDeleteTrainingForm(@PathVariable("trainingId") int trainingId, @PathVariable("clientUsername") String clientUsername,RedirectAttributes redirectAttrs) {
		
		Client client = this.clientService.findClientByUsername(clientUsername);
		
		Training training = this.trainingService.findTrainingById(trainingId);
				
		if(training==null||!isLoggedUser(clientUsername,false)||!training.getAuthor().equals(clientUsername)) {
			return "exception";
		}
		else {
			this.trainingService.deleteTraining(training,client);
			redirectAttrs.addFlashAttribute("deleteMessage", "The training was deleted successfully");
			return "redirect:/client/{clientUsername}/trainings";
		}
	}
	
	//Derivative
	
	private Boolean isClientOfLoggedTrainer(final int clientId, final String trainerUsername) {		
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);
		Client client = this.clientService.findClientById(clientId);
		
		return isLoggedUser(trainerUsername,true) && trainer.getClients().contains(client);
	}
	
	private Boolean isLoggedUser(final String usernameURL, boolean isTrainer) {
		
		User user;
		
		if(isTrainer) {
			Trainer trainer = this.trainerService.findTrainer(usernameURL);
			user = trainer.getUser();
		}
		else {
			Client client = this.clientService.findClientByUsername(usernameURL);
			user = client.getUser();
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		
		return user.getUsername().equals(username);
	}
	
}
