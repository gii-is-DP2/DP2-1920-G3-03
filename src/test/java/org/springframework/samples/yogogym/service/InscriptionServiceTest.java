package org.springframework.samples.yogogym.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InscriptionServiceTest {

	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_50 = 50;
	private static final int INSCRIPTION_ID_1 = 1;
	private static final int INSCRIPTION_ID_2 = 2;
	private static final int CLIENT_ID_1 = 1;
	private static final String URL = "http://test.com";
	
	@Autowired
	protected InscriptionService inscriptionService;
	@Autowired
	protected ClientService clientService;
	
	
	@Test
	void shouldFindInscriptionsByChallengeId(){
		
		Collection<Inscription> inscriptions = this.inscriptionService.findInscriptionsByChallengeId(CHALLENGE_ID_1);
		assertThat(inscriptions.size()).isEqualTo(2);
		
		inscriptions = this.inscriptionService.findInscriptionsByChallengeId(CHALLENGE_ID_50);
		assertThat(inscriptions.size()).isEqualTo(0);
	}
	
	@Test
	void shouldFindSubmittedInscriptions(){
		
		Collection<Inscription> inscriptions = this.inscriptionService.findSubmittedInscriptions();
		assertThat(inscriptions.size()).isEqualTo(1);	
	}
	
	@Test
	void shouldFindInscriptionsByInscriptionId(){
		
		Inscription inscription = this.inscriptionService.findInscriptionByInscriptionId(INSCRIPTION_ID_1);
		assertThat(inscription.getUrl()).isEqualTo("https://allamericanfitness.com/wp-content/uploads/2016/11/Treadmill-XR-Console.jpg");	
	}
	
	@Test
	void shouldFindAllInscriptions(){
		
		Collection<Inscription> inscriptions = this.inscriptionService.findAll();
		assertThat(inscriptions.size()).isEqualTo(8);	
	}
	
	@Test
	void shouldFindInscriptionByClientAndChallenge(){
		
		Client client = this.clientService.findClientById(CLIENT_ID_1);
		Challenge challenge = client.getInscriptions().get(1).getChallenge();
		Inscription inscription = this.inscriptionService.findInscriptionByClientAndChallenge(client, challenge);
		
		assertThat(inscription.getId()).isEqualTo(INSCRIPTION_ID_2);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldCreateInscription() {
		
		Collection<Inscription> inscriptions = this.inscriptionService.findAll();
		int found = inscriptions.size();
		Inscription i = new Inscription();
		
		Challenge c = createFilledChallenge();
		
		i.setChallenge(c);
		i.setStatus(Status.PARTICIPATING);
		i.setUrl(URL);
		
		this.inscriptionService.saveInscription(i);
		
		i = this.inscriptionService.findInscriptionByInscriptionId(found + 1);
		assertThat(i.getUrl()).isEqualTo(URL);
	}
	
	@Test
	void shouldUpdateInscription() {
		
		Inscription i = this.inscriptionService.findInscriptionByInscriptionId(INSCRIPTION_ID_1);
		i.setUrl(URL + "update");
		this.inscriptionService.saveInscription(i);;
		
		i = this.inscriptionService.findInscriptionByInscriptionId(INSCRIPTION_ID_1);
		assertThat(i.getUrl()).isEqualTo(URL + "update");
	}
	
	//UTILS
	private Challenge createFilledChallenge() {
		
		Date initialDate = new Date();
		Date endDate = new Date();
		Challenge c = new Challenge();
		
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

}
