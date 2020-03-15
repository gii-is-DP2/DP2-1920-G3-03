package org.springframework.samples.yogogym.web;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InscriptionController {

	
	private ChallengeService challengeService;
	private InscriptionService inscriptionService;
	private ClientService clientService;
	
	@Autowired
	public InscriptionController(ChallengeService challengeService, InscriptionService inscriptionService, ClientService clientService) {
		this.challengeService = challengeService;
		this.inscriptionService = inscriptionService;
		this.clientService = clientService;
	}
		
	
	// CLIENT:
	
	@GetMapping("/client/{clientUsername}/challenges/{challengeId}/inscription/create")
	public String createInscriptionByChallengeId(@PathVariable("clientUsername") String clientUsername, @PathVariable("challengeId") int challengeId, Model model) {	  
		
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		Client client = this.clientService.findClientByClientUsername(clientUsername);
	   	Inscription inscription = new Inscription();
	   	
	   	inscription.setChallenge(challenge);
	   	inscription.setStatus(Status.PARTICIPATING);
	   	inscription.setSubmitted(false);
	   	this.inscriptionService.saveInscription(inscription);
	   	
	   	client.addInscription(inscription);
	   	this.clientService.saveClient(client);
	   	
	   	
	    return "redirect:/client/"+clientUsername+"/challenges";
	}
}
