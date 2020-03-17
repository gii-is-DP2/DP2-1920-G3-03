package org.springframework.samples.yogogym.service;

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
	public Challenge findChallengeById(int challengeId) {
		return challengeRepo.findById(challengeId).get();
	}
	
	@Transactional
	public void saveChallenge(Challenge challenge) {
		challengeRepo.save(challenge);
	}
	
	
	@Transactional
	public void deleteChallenge(Challenge challenge) {
		challengeRepo.delete(challenge);
	}

	public Iterable<Challenge> findSubmittedChallenges() {
		
		return challengeRepo.findSubmittedChallenges();
	}

}
