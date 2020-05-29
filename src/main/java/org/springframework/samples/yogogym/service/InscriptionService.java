package org.springframework.samples.yogogym.service;


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.repository.InscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscriptionService {

	private InscriptionRepository inscriptionRepo;
	
	@Autowired
	public InscriptionService(InscriptionRepository inscriptionRepo) {
		this.inscriptionRepo = inscriptionRepo;
	}
	
	@Transactional(readOnly=true)
	public Collection<Inscription> findInscriptionsByChallengeId(int challengeId) throws DataAccessException {
		
		return inscriptionRepo.findInscriptionsByChallengeId(challengeId);
	}
	
	@Transactional(readOnly=true)
	public Collection<Inscription> findSubmittedInscriptions() {
		
		return inscriptionRepo.findSubmittedInscriptions();
	}

	@Transactional(readOnly=true)
	public Inscription findInscriptionByInscriptionId(int inscriptionId) {
		
		Optional<Inscription> i = inscriptionRepo.findById(inscriptionId);
		if(i.isPresent())
			return i.get();
		else
			return null;
	}
	
	@Transactional(readOnly=true)
	public Collection<Inscription> findAll() {
		
		return (Collection<Inscription>) this.inscriptionRepo.findAll();
	}

	@Transactional(readOnly=true)
	public Inscription findInscriptionByClientAndChallenge(Client client, Challenge challenge) {
		
		Inscription inscription = null;
		
		Collection<Inscription> inscriptions = this.findInscriptionsByChallengeId(challenge.getId());
		if(inscriptions.isEmpty()) {
			return null;
		}
		
		Optional<Inscription> optional = inscriptions.stream().filter(i -> client.getInscriptions().contains(i)).findFirst();
		if(optional.isPresent()) {
			inscription = (Inscription) optional.get();
		}
		
		return inscription;
	}
	
	@CacheEvict(cacheNames = {"percentageClients", "percentageGuilds", "topPointClient", "topPointGuild"}, allEntries = true)
	@Transactional
	public void saveInscription(Inscription inscription) throws DataAccessException{
		inscriptionRepo.save(inscription);
	}

}
