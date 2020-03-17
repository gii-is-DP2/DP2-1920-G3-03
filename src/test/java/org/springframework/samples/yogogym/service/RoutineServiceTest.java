package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RoutineServiceTest {
	
	@Autowired
	protected RoutineService routineService;
	@Autowired
	protected TrainingService trainingService;
	@Autowired
	protected RoutineLineService routineLineService;
	@Autowired
	protected ExerciseService exerciseService;
	
	@Test
	void shouldFindRoutineById(){
		Routine routine = this.routineService.findRoutineById(1);
			
		Boolean notNull = routine != null;
		Boolean sameNameAndNotEmpty = routine.getName().equals("Cardio") && !routine.getName().isEmpty();
		Boolean sameDescAndNotEmpty = routine.getDescription().equals("Augment resistance") && !routine.getDescription().isEmpty();
		Boolean sameRepsAndNotNull = routine.getRepsPerWeek().equals(3) && routine.getRepsPerWeek() != null;
		
		assertThat(notNull && sameNameAndNotEmpty && sameDescAndNotEmpty && sameRepsAndNotNull);
	}
	
	@Test
	void shouldCreateRoutine(){
		
		final int trainingId = 1;
			
		Training training = this.trainingService.findTrainingById(trainingId);
		Collection<Routine> trainingRoutinesbeforeAdding = this.routineService.findAllRoutinesFromTraining(trainingId);
		Collection<Routine> beforeAdding = this.routineService.findAllRoutines();
		
		Routine newRoutine = new Routine();
		newRoutine.setName("Name 1");
		newRoutine.setDescription("Desc 1");
		newRoutine.setRepsPerWeek(4);
		
		training.getRoutines().add(newRoutine);		
		this.trainingService.saveTraining(training);
		
		Collection<Routine> afterAdding = this.routineService.findAllRoutines();
		Collection<Routine> trainingRoutinesAfterAdding = this.routineService.findAllRoutinesFromTraining(trainingId);
		
		ArrayList<Routine> arrList = new ArrayList<>(trainingRoutinesAfterAdding);
		Routine addedRoutine = arrList.get(arrList.size()-1);
			
		Boolean hasBeenAddedToRoutine = beforeAdding.size() < afterAdding.size();
		Boolean hasBeenAddedToTraining = trainingRoutinesbeforeAdding.size() < trainingRoutinesAfterAdding.size();
		Boolean sameName = newRoutine.getName().equals(addedRoutine.getName());
		Boolean sameDescription = newRoutine.getDescription().equals(addedRoutine.getDescription());
		Boolean sameRepetitionsPerWeek = newRoutine.getRepsPerWeek().equals(addedRoutine.getRepsPerWeek());
		
		assertThat(hasBeenAddedToRoutine && hasBeenAddedToTraining && sameName && sameDescription && sameRepetitionsPerWeek);		
	}
	
	@Test
	void shouldDeleteRoutine(){
		
		final int routineId = 1;
		
		Routine routine = this.routineService.findRoutineById(routineId);
		Collection<Routine> beforeDelete = this.routineService.findAllRoutines();
		
		this.routineService.deleteRoutine(routine);
		
		Collection<Routine> afterDelete = this.routineService.findAllRoutines();
		
		Boolean hasBeenDeleted = afterDelete.size() < beforeDelete.size();
		Boolean notExist = this.routineService.findRoutineById(routineId) == null;
		
		assertThat(hasBeenDeleted && notExist);
	}
}
