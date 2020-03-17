package org.springframework.samples.yogogym.service;



import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class InscriptionServiceTest {

	@Autowired
	protected InscriptionService inscriptionService;
	
	
	@Test
	void shouldFindInscriptionsByChallengeId(){
		Collection<Inscription> inscriptions = this.inscriptionService.findInscriptionsByChallengeId(1);
		assertThat(inscriptions.size()).isEqualTo(2);	
	}
	
	@Test
	void shouldFindSubmittedInscriptions(){
		Collection<Inscription> inscriptions = this.inscriptionService.findSubmittedInscriptions();
		assertThat(inscriptions.size()).isEqualTo(1);	
	}
	
	@Test
	void shouldCreateInscription() {
		
		Collection<Inscription> inscriptions = this.inscriptionService.findAll();
		int found = inscriptions.size();
		Inscription i = new Inscription();
		
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
		
		i.setChallenge(c);
		i.setStatus(Status.PARTICIPATING);
		i.setUrl("http://test.com");
		
		this.inscriptionService.saveInscription(i);
		
		i = this.inscriptionService.findInscriptionsByInscriptionId(found + 1);
		assertThat(i.getUrl()).isEqualTo("http://test.com");
	}
	
	@Test
	void shouldUpdateInscription() {
		
		Inscription i = this.inscriptionService.findInscriptionsByInscriptionId(1);
		i.setUrl("https://TestUpdate.com");;
		this.inscriptionService.saveInscription(i);;
		
		i = this.inscriptionService.findInscriptionsByInscriptionId(1);
		assertThat(i.getUrl()).isEqualTo("https://TestUpdate.com");
	}
	
	@Ignore
	void shouldDeleteInscription() {
		
		Collection<Inscription> inscriptions = this.inscriptionService.findAll();
		int foundBefore = inscriptions.size();
		
		Inscription i = this.inscriptionService.findInscriptionsByInscriptionId(1);
		this.inscriptionService.deleteInscription(i);;
		
		inscriptions = this.inscriptionService.findAll();
		int foundAfter = inscriptions.size();
		
		assertThat(foundBefore).isGreaterThan(foundAfter);
	}
}
