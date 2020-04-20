/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.yogogym.service;


import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.repository.InscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class InscriptionService {

	
	private InscriptionRepository inscriptionRepo;
	
	@Autowired
	public InscriptionService(InscriptionRepository inscriptionRepo) {
		this.inscriptionRepo = inscriptionRepo;
	}

	
	public Collection<Inscription> findInscriptionsByChallengeId(int challengeId) throws DataAccessException {
		
		return inscriptionRepo.findInscriptionsByChallengeId(challengeId);
	}
	
	
	@Transactional
	public Collection<Inscription> findSubmittedInscriptions() {
		
		return inscriptionRepo.findSubmittedInscriptions();
	}

	
	public Inscription findInscriptionByInscriptionId(int inscriptionId) {
		
		return inscriptionRepo.findById(inscriptionId).get();
	}
	

	public Collection<Inscription> findAll() {
		
		return (Collection<Inscription>) this.inscriptionRepo.findAll();
	}

	public Inscription findInscriptionByClientAndChallenge(Client client, Challenge challenge) {
		
		Collection<Inscription> inscriptions = this.findInscriptionsByChallengeId(challenge.getId());
		Inscription inscription = inscriptions.stream().filter(i -> client.getInscriptions().contains(i)).findFirst().get();
	   	
		//If the endDate have passed, its fails
	   	Calendar now = Calendar.getInstance();
	   	if(challenge.getEndDate().before(now.getTime())) {
	   		if(inscription.getStatus().equals(Status.PARTICIPATING) || inscription.getStatus().equals(Status.SUBMITTED)) {
	   			inscription.setStatus(Status.FAILED);
	   			this.saveInscription(inscription);
	   		}
	   	}
		return inscription;
	}
	
	@Transactional
	public void saveInscription(Inscription inscription) throws DataAccessException{
		inscriptionRepo.save(inscription);
	}

}
