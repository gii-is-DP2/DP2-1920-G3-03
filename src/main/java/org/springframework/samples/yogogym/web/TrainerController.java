package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrainerController {
		
	private final TrainerService trainerService;
	private final ClientService clientService;
	private final RoutineService routineService;
	
	@Autowired
	public TrainerController(TrainerService trainerService, ClientService clientService, RoutineService routineService) {
		this.trainerService = trainerService;
		this.clientService = clientService;
		this.routineService = routineService;
	}	

	@GetMapping("/trainer/{trainerUsername}/clients")
	public String findClients(@PathVariable("trainerUsername") String trainerUsername,Model model) 
	{	  
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);		
	   	model.addAttribute("trainer",trainer);	   	
	    return "trainer/clients/clientsList";
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}")
	public String ClientDetails(@PathVariable("trainerUsername") String trainerUsername,@PathVariable("clientId") int clientId,Model model) 
	{	  
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);		
		Client client = this.clientService.findClientById(clientId);
				
		if(trainer.getClients().contains(client))
		{
			model.addAttribute("client",client);		
			return "trainer/clients/clientsDetails";			
		}
		else
		{
			return "exception";
		}	   	
	}
	
	@GetMapping("/trainer/{trainerUsername}/clients/{clientId}/routines/{routineId}")
	public String ClientRoutineDetails(@PathVariable("trainerUsername") String trainerUsername,@PathVariable("clientId") int clientId,@PathVariable("routineId") int routineId,Model model) 
	{	  
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);		
		Client client = this.clientService.findClientById(clientId);
		
		if(trainer.getClients().contains(client))
		{
			model.addAttribute("client",client);
			
			Routine routine = this.routineService.findRoutineById(routineId);			
			if(client.getRoutines().contains(routine))
			{
				model.addAttribute("routine",routine);		
				return "trainer/routines/clientsRoutineDetails";							
			}
			else
			{
				return "exception";
			}
		}
		else
		{
			return "exception";
		}	   	
	}
	
	@GetMapping("/trainer/{trainerUsername}/routines")
	public String RoutinesList(@PathVariable("trainerUsername") String trainerUsername,Model model) 
	{	  
		Trainer trainer = this.trainerService.findTrainer(trainerUsername);		
		model.addAttribute("trainer",trainer);
		
		return "trainer/routines/routinesList";
	}
}
