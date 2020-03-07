package org.springframework.samples.yogogym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.repository.ChallengeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChallengeService {
	
	@Autowired
	private ChallengeRepository challengeRepo;
	
	@Transactional
	public Iterable<Challenge> findAll(){
		
		return challengeRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Optional<Challenge> findById(int challengeId) {
		return challengeRepo.findById(challengeId);
	}
	
	@Transactional
	public void save(Challenge challenge) {
		challengeRepo.save(challenge);
	}
	
	@Transactional
	public void delete(Challenge challenge) {
		challengeRepo.delete(challenge);
	}

}
