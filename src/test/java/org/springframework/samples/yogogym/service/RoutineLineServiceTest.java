package org.springframework.samples.yogogym.service;

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
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
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
		//Finds specified RoutineLine
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(1);
		
		//Check it fetchs something from the Database
		Boolean notNull = routineLine != null;
		//Check for time or reps to be valid ( > 0 && != null)
		Boolean repsOrTimeNotEmptyAndGreaterThanMin = (routineLine.getReps() != null && routineLine.getReps() > 0) || (routineLine.getTime() != null && routineLine.getTime() >= 0);
		//Check for series to be valid ( > 0 && != null)
		Boolean seriesNotNull= routineLine.getSeries() != null && routineLine.getSeries() >= 0;
		//Check for weight to be valid ( > 0 && != null)		
		Boolean weightNotNull = routineLine.getWeight() != null && routineLine.getWeight() >= 0;
		
		//Check the fetched routine is the expected one
		Boolean sameRepAndTime = routineLine.getReps() == null && routineLine.getTime().equals(2.0);
		Boolean sameSeries = routineLine.getSeries().equals(1);
		Boolean sameWeight = routineLine.getWeight().equals(0.0);
		
		assertTrue(notNull && repsOrTimeNotEmptyAndGreaterThanMin && seriesNotNull && weightNotNull && sameRepAndTime && sameSeries && sameWeight);
	}
	
	@Test
	void shouldCreateRoutineLine(){
		
		//Set training a valid end date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		try {
			this.trainingService.saveTraining(training);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		//Get all of routineLine before adding
		Collection<RoutineLine> beforeAdding = this.routineLineService.findAllRoutinesLines();
		//Create routine collection of training
		Collection<Routine> routines = new ArrayList<>();
		//Fetch routine that will contain the routineLine
		Routine routine = this.routineService.findRoutineById(routineId);
		//Fetch exercise RoutineLine will have
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		//Add routine
		routines.add(routine);
		
		//Check routineLine size of the routine we are adding the routineLine
		int rlFromRoutineBeforeAdding = routine.getRoutineLine().size();
		
		//Creating the RoutineLine
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(null);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(exercise);
		
		//Add routineLIne to the routine created before
		routine.getRoutineLine().add(routineLine);
		//Save the routine
		try
		{
			this.routineLineService.saveRoutineLine(routineLine,trainingId);			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Get of all routineLines after creation
		Collection<RoutineLine> afterAdding = this.routineLineService.findAllRoutinesLines();
		//Get all routineLines of the routine we said we were adding the RoutineLine
		Collection<RoutineLine> rlFromRoutineAfterAdding = this.routineService.findRoutineById(routineId).getRoutineLine();
			
		//Check if size of all routineLine before creation < after creation
		Boolean hasBeenAdded = beforeAdding.size() < afterAdding.size();
		//Check if size of all routineLine before creation < after creation in the specified routine
		Boolean hasBeenAddedToRoutine = rlFromRoutineBeforeAdding < rlFromRoutineAfterAdding.size();
		
		//Get the added routine
		RoutineLine addedRoutineLine = new ArrayList<>(afterAdding).get(afterAdding.size()-1);
		
		//Check it contains what we declared before
		Boolean sameReps = routineLine.getReps().equals(addedRoutineLine.getReps());
		Boolean sameTime = routineLine.getTime() == addedRoutineLine.getTime();
		Boolean sameSeries = routineLine.getSeries().equals(addedRoutineLine.getSeries());
		Boolean sameWeight = routineLine.getWeight().equals(addedRoutineLine.getWeight());
		Boolean sameExercise = routineLine.getExercise().equals(addedRoutineLine.getExercise());
		
		assertTrue(hasBeenAdded && hasBeenAddedToRoutine && sameReps && sameTime && sameSeries && sameWeight && sameExercise);
	}
	
	@Test
	void shouldNotCreateRoutineLineTrainingFinished(){

		//Fetch exercise used by RoutineLine
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		
		//Create the routineLine
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(null);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(exercise);
				
		assertThrows(TrainingFinished.class,() -> {this.routineLineService.saveRoutineLine(routineLine,trainingId);});
	}
	
	@Test
	void shouldNotCreateRoutineLineTimeAndRepSetted(){
		
		//Create new EndDate for Training
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		
		//Update Training
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		try {
			this.trainingService.saveTraining(training);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Fetch exercise used by RoutineLine
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		
		//Create RoutineLine
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
		
		//Create new EndDate for Training
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date newEndDate = cal.getTime();
		
		//Update training
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		try {
			this.trainingService.saveTraining(training);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Fetch specified routineLine
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);
		
		try
		{
			this.routineLineService.deleteRoutineLine(routineLine,trainingId);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//Check that the routineLine do not exist
		Boolean notExist = this.routineLineService.findRoutineLineById(routineLineId) == null;
	
		assertTrue(notExist);
	}
	
	@Test
	void shouldNotDeleteRoutineLineTrainingFinished()
	{		
		//Fetch routineLine to be deleted
		RoutineLine routineLine = this.routineLineService.findRoutineLineById(routineLineId);

		assertThrows(TrainingFinished.class,()->{this.routineLineService.deleteRoutineLine(routineLine,trainingId);});
			
	}
}
