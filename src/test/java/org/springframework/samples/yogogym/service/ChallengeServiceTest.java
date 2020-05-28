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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.exceptions.ChallengeMore3Exception;
import org.springframework.samples.yogogym.service.exceptions.ChallengeSameNameException;
import org.springframework.samples.yogogym.service.exceptions.ChallengeWithInscriptionsException;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ChallengeServiceTest {

	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_2 = 2;
	private static final int CHALLENGE_ID_4 = 4;
	private static final int CLIENT_ID_1 = 1;
	private static final String CLIENT_USERNAME_3 = "client3";
	
	
	@Autowired
	protected ChallengeService challengeService;
	@Autowired
	protected ClientService clientService;
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	final Calendar cal = Calendar.getInstance();
	
	
	@Test
	void shouldFindAllChallenges(){
		
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		assertThat(challenges.size()).isEqualTo(6);
	}
	
	@Test
	void shouldFindChallengeById(){
		
		Challenge challenge = this.challengeService.findChallengeById(CHALLENGE_ID_2);
		assertThat(challenge.getName()).isEqualTo("Challenge2");	
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldSaveChallenge() {
		
		List<Challenge> challenges = (List<Challenge>) this.challengeService.findAll();
		int found = challenges.size();
		Challenge c = createTestingChallenge();
		c.setId(found + 1);
		try {
			this.challengeService.saveChallenge(c);
		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldNotSaveChallengeWhenMore3SameWeek() {
			
		Date initialDate = getDateOf(2050, 1, 1);
		
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldNotSaveChallengeWhenSameNameSameWeek() {
		
		Date initialDate = getDateOf(2030, 1, 1);
		
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
	void shouldUpdateChallenge() {
		
		Challenge challenge = this.challengeService.findChallengeById(CHALLENGE_ID_1);
		challenge.setDescription("UpdateTest");;
		try {
			this.challengeService.saveChallenge(challenge);
		} catch (DataAccessException | ChallengeSameNameException | ChallengeMore3Exception | ChallengeWithInscriptionsException e) {
			e.printStackTrace();
		}
		
		challenge = this.challengeService.findChallengeById(CHALLENGE_ID_1);
		assertThat(challenge.getDescription()).isEqualTo("UpdateTest");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldDeleteChallenge() throws ChallengeWithInscriptionsException {
		
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundBefore = challenges.size();
		Challenge challenge = this.challengeService.findChallengeById(CHALLENGE_ID_4);
		
		this.challengeService.deleteChallenge(challenge);
		
		challenges = (Collection<Challenge>) this.challengeService.findAll();
		int foundAfter = challenges.size();
		assertThat(challenges.stream().anyMatch(c -> c.getId().equals(CHALLENGE_ID_4))).isFalse();
		assertThat(foundAfter).isEqualTo(foundBefore - 1);
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
	void shouldFindAllChallengesClients(){
		
		Client client = this.clientService.findClientById(CLIENT_ID_1);
		Collection<Challenge> challenges = (Collection<Challenge>) this.challengeService.findAllChallengesClients(client.getId(), client.getInscriptions());
		assertThat(challenges.size()).isEqualTo(2);
	}
	
	
	// Classification
	@Test
	void shouldFindChallengesByUsername() {
		List<Challenge> challenges = this.challengeService.findChallengesByUsername(CLIENT_USERNAME_3);
		assertThat(challenges.size()).isEqualTo(1);
	}

	@Test
	void shouldSumPointChallengesByUsername() {
		Integer sum = this.challengeService.sumPointChallengesByUsername(CLIENT_USERNAME_3);
		assertThat(sum).isEqualTo(10);
	}
	
	
	// UTILS
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
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setBodyPart(BodyParts.ALL);
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
	
	private Date getDateOf(int year, int month, int day) {
		
		cal.set(year, month, day);
		Date date = cal.getTime();
		dateFormat.format(date);
		return date;
	}
	
}
