package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.TrainingNotFinished;
import org.springframework.samples.yogogym.service.exceptions.TrainingRepAndTimeSetted;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RoutineLineServiceTest {
	
	@Autowired
	protected RoutineService routineService;
	@Autowired
	protected TrainingService trainingService;
	@Autowired
	protected RoutineLineService routineLineService;
	@Autowired
	protected ExerciseService exerciseService;
	
	private final int trainingId = 1;
	private final int routineId = 1;
	private final int exerciseId = 1;
	private final int routineLineId = 1;
	
	
	@Test
	void shouldFindRoutineLineById(){
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(1);
			
		Boolean notNull = routineLine != null;
		Boolean repsOrTimeNotEmptyAndGreaterThanMin = (routineLine.getReps() != null && routineLine.getReps() > 0) || (routineLine.getTime() != null && routineLine.getTime() >= 0);
		Boolean seriesNotNull= routineLine.getSeries() != null && routineLine.getSeries() >= 0; 
		Boolean weightNotNull = routineLine.getWeight() != null && routineLine.getWeight() >= 0;
		
		Boolean sameRepAndTime = routineLine.getReps() == null && routineLine.getTime().equals(2.0);
		Boolean sameSeries = routineLine.getSeries().equals(1);
		Boolean sameWeight = routineLine.getWeight().equals(0.0);
		
		assertThat(notNull && repsOrTimeNotEmptyAndGreaterThanMin && seriesNotNull && weightNotNull && sameRepAndTime && sameSeries && sameWeight);
	}
	
	@Test
	void shouldCreateRoutineLine() throws DataAccessException, TrainingNotFinished{
				
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		this.trainingService.saveTraining(training);
		
		Collection<RoutineLine> beforeAdding = this.routineLineService.findAllRoutinesLines();
		Collection<Routine> routines = new ArrayList<>();

		Routine routine = this.routineService.findRoutineById(routineId);
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		
		routines.add(routine);
				
		int rlFromRoutineBeforeAdding = routine.getRoutineLine().size();
		
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(null);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(exercise);
		
		routine.getRoutineLine().add(routineLine);
		this.routineService.saveRoutine(routine,trainingId);
				
		Collection<RoutineLine> afterAdding = this.routineLineService.findAllRoutinesLines();
		Collection<RoutineLine> rlFromRoutineAfterAdding = this.routineService.findRoutineById(routineId).getRoutineLine();
			
		Boolean hasBeenAdded = beforeAdding.size() < afterAdding.size();
		Boolean hasBeenAddedToRoutine = rlFromRoutineBeforeAdding < rlFromRoutineAfterAdding.size();
		
		RoutineLine addedRoutineLine = new ArrayList<>(afterAdding).get(afterAdding.size()-1);
		
		Boolean sameReps = routineLine.getReps().equals(addedRoutineLine.getReps());
		Boolean sameTime = routineLine.getTime() == addedRoutineLine.getTime();
		Boolean sameSeries = routineLine.getSeries().equals(addedRoutineLine.getSeries());
		Boolean sameWeight = routineLine.getWeight().equals(addedRoutineLine.getWeight());
		Boolean sameExercise = routineLine.getExercise().equals(addedRoutineLine.getExercise());
		
		assertTrue(hasBeenAdded && hasBeenAddedToRoutine && sameReps && sameTime && sameSeries && sameWeight && sameExercise);
	}
	
	@Test
	void shouldNotCreateRoutineLineTrainingFinished() throws DataAccessException, TrainingNotFinished{
				
		Collection<Routine> routines = new ArrayList<>();

		Routine routine = this.routineService.findRoutineById(routineId);
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		routines.add(routine);
		
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(null);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(exercise);
		
		routine.getRoutineLine().add(routineLine);
		
		assertThrows(TrainingNotFinished.class,() -> {this.routineService.saveRoutine(routine,trainingId);});
	}
	
	@Test
	void shouldNotCreateRoutineLineTimeAndRepSetted() throws DataAccessException, TrainingNotFinished, TrainingRepAndTimeSetted{
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		this.trainingService.saveTraining(training);
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(10.0);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(exercise);
		
		assertThrows(TrainingRepAndTimeSetted.class, () -> {this.routineLineService.saveRoutineLine(routineLine, trainingId);});
	}
	
	@Test
	void shouldDeleteRoutineLine(){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		this.trainingService.saveTraining(training);
		
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		Collection<RoutineLine> beforeDelete = this.routineLineService.findAllRoutinesLines();
		
		this.routineLineService.deleteRoutineLine(routineLine);
		
		Collection<RoutineLine> afterDelete = this.routineLineService.findAllRoutinesLines();

		Boolean hasBeenDeleted = beforeDelete.size() > afterDelete.size();
		Boolean notExist = this.routineLineService.findRoutineLineById(routineLineId) == null;
		
		assertTrue(notExist && hasBeenDeleted);
	}
	
	@Test
	void shouldNotDeleteRoutineLineTrainingFinished()
	{		
		Calendar cal = Calendar.getInstance();
		Date actualDate = cal.getTime();
		
		Training training = this.trainingService.findTrainingById(trainingId);
	
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		Collection<RoutineLine> beforeDelete = this.routineLineService.findAllRoutinesLines();
		
		this.routineLineService.deleteRoutineLine(routineLine);
		
		Collection<RoutineLine> afterDelete = this.routineLineService.findAllRoutinesLines();
		
		Boolean hasTrainingNotFinished = training.getEndDate().after(actualDate);
		Boolean hasBeenDeleted = beforeDelete.size() > afterDelete.size();
		Boolean notExist = this.routineLineService.findRoutineLineById(routineLineId) == null;
		
		assertFalse(hasTrainingNotFinished && notExist && hasBeenDeleted);
	}
}
