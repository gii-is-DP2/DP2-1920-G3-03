package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Challenge;
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
public class ExerciseServiceTest {
	
	@Autowired
	protected ExerciseService exerciseService;
	
	@Autowired
	protected ChallengeService challengeService;
	
	@Autowired
	protected EquipmentService equipmentService;
	
	@Test
	void shouldFindExerciseById(){
		Exercise exercise = this.exerciseService.findExerciseById(1);
		Equipment equipment = this.equipmentService.findEquipmentById(1);
			
		Boolean notNull = exercise != null;
		Boolean sameNameAndNotEmpty = exercise.getName().equals("Squat") && !exercise.getName().isEmpty();
		Boolean sameDescAndNotEmpty = exercise.getDescription().equals("Crouch and stand up") && !exercise.getDescription().isEmpty();
		Boolean sameKcalAndNotNull = exercise.getKcal().equals(100) && exercise.getKcal() != null;
		Boolean sameIntensityAndNotNull = exercise.getIntensity().equals(Intensity.LOW) && exercise.getIntensity() != null;
		Boolean sameEquipmentAndNotNull = exercise.getEquipment().equals(equipment) && exercise.getEquipment() != null;
		Boolean sameBodyTypeAndNotNull = exercise.getBodyPart().equals(BodyParts.LOWER_TRUNK) && exercise.getBodyPart() != null;
		Boolean sameRepetitionTypeAndNotNull = exercise.getRepetitionType().equals(RepetitionType.TIME_AND_REPS) && exercise.getRepetitionType() != null;
		
		
		assertThat(notNull && sameNameAndNotEmpty && sameDescAndNotEmpty && sameKcalAndNotNull && sameIntensityAndNotNull && sameEquipmentAndNotNull && sameBodyTypeAndNotNull && sameRepetitionTypeAndNotNull);
	}
	
	@Test
	void shouldFindAll(){
		
		Collection<Exercise> allExercise = this.exerciseService.findAllExercise();
				
		Boolean notNullAndEmpty = allExercise.size() != 0 && allExercise != null;
		Boolean hasFoundAll = allExercise.size() == 65;
		
		assertThat(notNullAndEmpty && hasFoundAll);		
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldCreateExercise(){
		
		final int equipmentId = 1;
		Equipment equipment = this.equipmentService.findEquipmentById(equipmentId);
		
		Exercise exercise = new Exercise();
		exercise.setEquipment(equipment);
		exercise.setName("Exercise test");
		exercise.setDescription("Desc");
		exercise.setKcal(100);
		exercise.setIntensity(Intensity.LOW);
		exercise.setRepetitionType(RepetitionType.TIME_AND_REPS);
		exercise.setBodyPart(BodyParts.ARMS);
		
		Collection<Exercise> beforeAdding = this.exerciseService.findAllExercise();
		
		this.exerciseService.saveExercise(exercise);
		
		Collection<Exercise> afterAdding = this.exerciseService.findAllExercise();
		Exercise addedExercise = new ArrayList<>(afterAdding).get(afterAdding.size()-1);
		
		Boolean hasBeenAdded = beforeAdding.size() < afterAdding.size();
		Boolean sameName = exercise.getName().equals(addedExercise.getName());
		Boolean sameDescription = exercise.getDescription().equals(addedExercise.getDescription());
		Boolean sameEquipment = exercise.getEquipment().equals(addedExercise.getEquipment());
		Boolean sameKcal = exercise.getKcal().equals(addedExercise.getKcal());
		Boolean sameIntensity = exercise.getIntensity().equals(addedExercise.getIntensity());
		Boolean sameRepetitionType = exercise.getRepetitionType().equals(addedExercise.getRepetitionType());
		Boolean sameBodyPart = exercise.getBodyPart().equals(addedExercise.getBodyPart());
		
		assertThat(hasBeenAdded && sameName && sameDescription && sameEquipment && sameKcal && sameIntensity && sameRepetitionType && sameBodyPart);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void shouldDeleteExercise() throws DataAccessException, ChallengeSameNameException, ChallengeMore3Exception, ChallengeWithInscriptionsException{
		
		final int exerciseId = 1;
		final int challengeId = 1;
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		Collection<Exercise> beforeDelete = this.exerciseService.findAllExercise();
		
		Challenge challenge = this.challengeService.findChallengeById(challengeId);
		challenge.setExercise(null);
		this.challengeService.saveChallenge(challenge);
		
		this.exerciseService.deleteExercise(exercise);
		
		Collection<Exercise> afterDelete = this.exerciseService.findAllExercise();
		
		Boolean hasBeenDeleted = afterDelete.size() < beforeDelete.size();
		Boolean notExist = this.exerciseService.findExerciseById(exerciseId) == null;
		
		assertThat(hasBeenDeleted && notExist);
	}
}
