package org.springframework.samples.yogogym.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.service.exceptions.ChallengeMore3Exception;
import org.springframework.samples.yogogym.service.exceptions.ChallengeSameNameException;
import org.springframework.samples.yogogym.service.exceptions.ChallengeWithInscriptionsException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ChallengeServiceTest {

	@Autowired
	protected ChallengeService challengeService;
	
	@Test
	void shouldFindAllChallenges(){
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		assertThat(challenges.size()).isEqualTo(4);
	}
	
	@Test
	void shouldFindChallengeById(){
		Challenge challenge = this.challengeService.findChallengeById(2);
		assertThat(challenge.getName()).isEqualTo("Challenge2");	
	}
	
	@Test
	void shouldCreateChallenge() {
		
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		int found = challenges.size();
		Challenge c = new Challenge();
		Date initialDate = new Date();
		Date endDate = new Date();
		
		Equipment equipment = new Equipment();
		equipment.setName("EquipmentTest");
		equipment.setLocation("Test");
		
		Exercise exercise = new Exercise();
		exercise.setName("ExerciseTest");
		exercise.setDescription("Test");
		exercise.setIntensity(Intensity.LOW);
		exercise.setKcal(10);
		exercise.setEquipment(equipment);
		
		c.setName("ChallengeTest");
		c.setDescription("Test");
		c.setInitialDate(initialDate);
		c.setEndDate(endDate);
		c.setPoints(10);
		c.setReps(10);
		c.setReward("Test");
		c.setWeight(10.);
		c.setExercise(exercise);
		
		try {
			this.challengeService.saveChallenge(c);
		} catch (DataAccessException | ChallengeSameNameException | ChallengeMore3Exception | ChallengeWithInscriptionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c = this.challengeService.findChallengeById(found + 1);
		assertThat(c.getName()).isEqualTo("ChallengeTest");
	}
	
	@Test
	void shouldUpdateOwner() {
		
		Challenge challenge = this.challengeService.findChallengeById(1);
		challenge.setName("UpdateTest");
		try {
			this.challengeService.saveChallenge(challenge);
		} catch (DataAccessException | ChallengeSameNameException | ChallengeMore3Exception | ChallengeWithInscriptionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		challenge = this.challengeService.findChallengeById(1);
		assertThat(challenge.getName()).isEqualTo("UpdateTest");
	}
	
	
	@Test
	void shouldDeleteChallenge() {
		
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundBefore = challenges.size();
		
		Challenge challenge = this.challengeService.findChallengeById(4);
		try {
			this.challengeService.deleteChallenge(challenge);
		} catch (ChallengeWithInscriptionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundAfter = challenges.size();
		
		assertThat(foundBefore).isGreaterThan(foundAfter);
	}
}
