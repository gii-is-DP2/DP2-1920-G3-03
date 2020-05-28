package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.repository.ChallengeRepository;
import org.springframework.samples.yogogym.service.exceptions.ChallengeMore3Exception;
import org.springframework.samples.yogogym.service.exceptions.ChallengeSameNameException;
import org.springframework.samples.yogogym.service.exceptions.ChallengeWithInscriptionsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChallengeService {
	
	@Autowired
	private ChallengeRepository challengeRepo;
	
	@Autowired
	private InscriptionService inscriptionService;	
	
  
	@Transactional(readOnly=true)
	public Iterable<Challenge> findAll(){
		
		return challengeRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Challenge findChallengeById(int challengeId) {
		return challengeRepo.findById(challengeId).get();
	}
	
	@CacheEvict(cacheNames = {"percentageClients", "percentageGuilds"}, allEntries = true)
	@Transactional(rollbackFor = {ChallengeSameNameException.class, ChallengeMore3Exception.class})
	public void saveChallenge(Challenge challenge) throws DataAccessException, ChallengeSameNameException, ChallengeMore3Exception, ChallengeWithInscriptionsException {
		
		/////////////////////// Check DATA ////////////////////////////////////////////////////////////////////////////////////////////
		Date initialDate = challenge.getInitialDate();
		List<Challenge> challenges = (List<Challenge>) this.findAll();
		
		// All the challenges in the same week as the one we are validating
		Calendar challengeCalendar = Calendar.getInstance();
		challengeCalendar.setTime(initialDate);
		int week = challengeCalendar.get(GregorianCalendar.WEEK_OF_YEAR);
		int year = challengeCalendar.get(GregorianCalendar.YEAR);
		List<Challenge> challengesSameWeek = challenges.stream().filter(ch -> sameWeekAndYear(ch,week,year)).collect(Collectors.toList());
		
		//If we are editing, delete the challenge that is being edited
		if(challenge.getId() != null) {
			challengesSameWeek = challengesSameWeek.stream().filter(c -> c.getId() != challenge.getId()).collect(Collectors.toList());
		}
		/////////////////////// Check DATA ////////////////////////////////////////////////////////////////////////////////////////////
		
		// Challenge with that name already exist this week
		if(challengesSameWeek.stream().anyMatch(c -> c.getName().equals(challenge.getName()))) {
			throw new ChallengeSameNameException();
		}else if(challengesSameWeek.size() > 2){  // No more than 3 challenges the same week
			throw new ChallengeMore3Exception();
		}else {
			challengeRepo.save(challenge);
		}	
		
	}
	
	@CacheEvict(cacheNames = {"percentageClients", "percentageGuilds"}, allEntries = true)
	@Transactional(rollbackFor = ChallengeWithInscriptionsException.class)
	public void deleteChallenge(Challenge challenge) throws ChallengeWithInscriptionsException{
		
		Collection<Inscription> inscriptions = inscriptionService.findInscriptionsByChallengeId(challenge.getId());
		
		if(!inscriptions.isEmpty()) {
			throw new ChallengeWithInscriptionsException();
		}else {
			challengeRepo.delete(challenge);
		}
		
	}

	@Transactional(readOnly=true)
	public Iterable<Challenge> findSubmittedChallenges() {
		
		return challengeRepo.findSubmittedChallenges();
	}
	
	@Transactional(readOnly=true)
	public List<Challenge> findAllChallengesClients(Integer clientId, List<Inscription> inscriptions) {
		
		List<Challenge> challenges = (List<Challenge>) this.findAll();
		
		// Only if the end date is posterior to today's
		Calendar now = Calendar.getInstance();
		Date d = now.getTime();
		challenges = challenges.stream().filter(c -> c.getEndDate().after(d)).collect(Collectors.toList());
		
		// and they are not already inscribed:
		if (inscriptions != null) {
			for (Inscription i : inscriptions) {
				Challenge c = i.getChallenge();
				if (challenges.contains(c)) {
					challenges.remove(c);
				}
			}
		}
		return challenges;
	}
	
	
	//Clasification
	@Transactional(readOnly=true)
	public List<Challenge> findChallengesByUsername(String username){
		return this.challengeRepo.findChallengesByUsername(username);
	}
	
	@Transactional(readOnly=true)
	public Integer sumPointChallengesByUsername(String username) {
		return this.challengeRepo.sumPointChallengesByUsername(username);
	}
	
	
	//Util
	private boolean sameWeekAndYear(Challenge c, int week, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getInitialDate());
		int week2 = cal.get(GregorianCalendar.WEEK_OF_YEAR);
		int year2 = cal.get(GregorianCalendar.YEAR);
		return week == week2 && year == year2;
	}


}