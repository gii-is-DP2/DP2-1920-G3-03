package org.springframework.samples.yogogym.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
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
	@Autowired
	protected ClientService clientService;
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	final Calendar cal = Calendar.getInstance();
	
	@Test
	void shouldFindAllChallenges(){
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		assertThat(challenges.size()).isEqualTo(5);
	}
	
	@Test
	void shouldFindChallengeById(){
		Challenge challenge = this.challengeService.findChallengeById(2);
		assertThat(challenge.getName()).isEqualTo("Challenge2");	
	}
	
	@Test
	void shouldSaveChallenge() {
		
		List<Challenge> challenges = (List<Challenge>) this.challengeService.findAll();
		int found = challenges.size();
		Challenge c = createTestingChallenge();
		c.setId(found + 1);
		try {
			this.challengeService.saveChallenge(c);
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		
		List<Challenge> afterAdding = (List<Challenge>) this.challengeService.findAll();
		int newSize = afterAdding.size();
		
		Challenge addedChallenge = afterAdding.get(newSize-1);
		
		assertThat(c.getName()).isEqualTo(addedChallenge.getName());
		assertThat(c.getDescription()).isEqualTo(addedChallenge.getDescription());
		assertThat(c.getInitialDate()).isEqualTo(addedChallenge.getInitialDate());	
		assertThat(c.getEndDate()).isEqualTo(addedChallenge.getEndDate());	
		assertThat(c.getPoints()).isEqualTo(addedChallenge.getPoints());	
		assertThat(c.getReward()).isEqualTo(addedChallenge.getReward());
		assertThat(c.getWeight()).isEqualTo(addedChallenge.getWeight());
		assertThat(c.getExercise()).isEqualTo(addedChallenge.getExercise());		
		
		assertThat(found).isLessThan(newSize);
	}
	
	@Test
	void shouldNotSaveChallengeWhenMore3SameWeek() {
		
		cal.set(2050, 1, 1);
		Date initialDate = cal.getTime();
		dateFormat.format(initialDate);
		
		Challenge c1 = createTestingChallenge();
		Challenge c2 = createTestingChallenge();
		Challenge c3 = createTestingChallenge();
		Challenge c4 = createTestingChallenge();
		
		c1.setInitialDate(initialDate);
		c2.setInitialDate(initialDate);
		c3.setInitialDate(initialDate);
		c4.setInitialDate(initialDate);
		c1.setName("Test1");
		c2.setName("Test2");
		c3.setName("Test3");
		c4.setName("Test4");
		
		try {
			this.challengeService.saveChallenge(c1);
			this.challengeService.saveChallenge(c2);
			this.challengeService.saveChallenge(c3);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Assertions.assertThrows(ChallengeMore3Exception.class, () ->{
			this.challengeService.saveChallenge(c4);
		});		

	}
	
	@Test
	void shouldNotSaveChallengeWhenSameNameSameWeek() {
		
		cal.set(2030, 1, 1);
		Date initialDate = cal.getTime();
		dateFormat.format(initialDate);
		
		Challenge c1 = createTestingChallenge();
		Challenge c2 = createTestingChallenge();
		
		c1.setInitialDate(initialDate);
		c2.setInitialDate(initialDate);
		c1.setName("Same Name");
		c2.setName("Same Name");
		
		try {
			this.challengeService.saveChallenge(c1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Assertions.assertThrows(ChallengeSameNameException.class, () ->{
			this.challengeService.saveChallenge(c2);
		});		

	}
	
	@Test
	void shouldUpdateOwner() {
		
		Challenge challenge = this.challengeService.findChallengeById(1);
		challenge.setDescription("UpdateTest");;
		try {
			this.challengeService.saveChallenge(challenge);
		} catch (DataAccessException | ChallengeSameNameException | ChallengeMore3Exception | ChallengeWithInscriptionsException e) {
			e.printStackTrace();
		}
		
		challenge = this.challengeService.findChallengeById(1);
		assertThat(challenge.getDescription()).isEqualTo("UpdateTest");
	}
	
	
	@Test
	void shouldDeleteChallenge() {
		
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundBefore = challenges.size();
		
		Challenge challenge = this.challengeService.findChallengeById(4);
		try {
			this.challengeService.deleteChallenge(challenge);
		} catch (ChallengeWithInscriptionsException e) {
			e.printStackTrace();
		}
		challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundAfter = challenges.size();
		
		assertThat(foundBefore).isGreaterThan(foundAfter);
	}
	
	@Test
	void shouldNotDeleteChallengeWhenHaveInscriptions() {
		
		Challenge c = this.challengeService.findChallengeById(2);
		
		Assertions.assertThrows(ChallengeWithInscriptionsException.class, () ->{
			this.challengeService.deleteChallenge(c);
		});		
	}
	
	@Test
	void shouldFindSubmittedChallenges(){
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findSubmittedChallenges();
		assertThat(challenges.size()).isEqualTo(1);
	}
	
	@Test
	void shouldFindAllChallengesCLients(){
		Client client = this.clientService.findClientById(2);
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAllChallengesClients(client.getId(), client.getInscriptions());
		assertThat(challenges.size()).isEqualTo(2);
	}
	
	private Challenge createTestingChallenge() {
		Challenge c = new Challenge();
		cal.set(2040, 1, 1);
		Date initialDate = cal.getTime();
		dateFormat.format(initialDate);
		
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
		
		return c;
	}
}
