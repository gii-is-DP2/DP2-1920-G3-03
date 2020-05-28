package org.springframework.samples.yogogym.util;

import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
	
	private SecurityUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Boolean isLoggedUser(final String usernameURL, boolean isTrainer, ClientService clientService, TrainerService trainerService) {
			
		User user;
		
		if(isTrainer) {
			Trainer trainer = trainerService.findTrainer(usernameURL);
			user = trainer.getUser();
		}
		else {
			Client client = clientService.findClientByUsername(usernameURL);
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
	
	public static Boolean isClientOfLoggedTrainer(final int clientId, final String trainerUsername, ClientService clientService, TrainerService trainerService) {		
		Trainer trainer = trainerService.findTrainer(trainerUsername);
		Client client = clientService.findClientById(clientId);
		
		return SecurityUtils.isLoggedUser(trainerUsername,true,clientService,trainerService) && trainer.getClients().contains(client);
	}
}
