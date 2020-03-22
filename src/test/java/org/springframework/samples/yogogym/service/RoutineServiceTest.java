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
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.exceptions.TrainingNotFinished;
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
	
	private final int trainingId = 1;
	private final int routineId = 1;
	
	@Test
	void shouldFindRoutineById(){
		Routine routine = this.routineService.findRoutineById(1);
		
		//Check that we get some routine from the method
		Boolean notNull = routine != null;
		
		//Check that the fetched routine has the same data that we introduced
		Boolean sameNameAndNotEmpty = routine.getName().equals("Cardio") && !routine.getName().isEmpty();
		Boolean sameDescAndNotEmpty = routine.getDescription().equals("Augment resistance") && !routine.getDescription().isEmpty();
		Boolean sameRepsAndNotNull = routine.getRepsPerWeek().equals(3) && routine.getRepsPerWeek() != null;
		
		assertTrue(notNull && sameNameAndNotEmpty && sameDescAndNotEmpty && sameRepsAndNotNull);
	}
	
	@Test
	void shouldCreateRoutine(){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date futureDate = cal.getTime();
		
		//Setting future end date
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(futureDate);
		
		//Check all routine and all of a specific training before adding
		Collection<Routine> trainingRoutinesbeforeAdding = this.routineService.findAllRoutinesFromTraining(trainingId);
		Collection<Routine> beforeAdding = this.routineService.findAllRoutines();
		
		//Create the routine to be added
		Routine newRoutine = new Routine();
		newRoutine.setName("Name 1");
		newRoutine.setDescription("Desc 1");
		newRoutine.setRepsPerWeek(4);
		
		//Add routine to training
		training.getRoutines().add(newRoutine);	
		
		//Update Training
		this.trainingService.saveTraining(training);
		
		//Check all routine and all of a specific training after adding
		Collection<Routine> afterAdding = this.routineService.findAllRoutines();
		Collection<Routine> trainingRoutinesAfterAdding = this.routineService.findAllRoutinesFromTraining(trainingId);
		
		//Get last added Routine == to the one we created upwards
		ArrayList<Routine> arrList = new ArrayList<>(trainingRoutinesAfterAdding);
		Routine addedRoutine = arrList.get(arrList.size()-1);
		
		//Check that the size of all persisted routines has increased after creation
		Boolean hasBeenAddedToRoutine = beforeAdding.size() < afterAdding.size();
		
		//Check that all the size of all the routines of the specified training has increased
		Boolean hasBeenAddedToTraining = trainingRoutinesbeforeAdding.size() < trainingRoutinesAfterAdding.size();
		
		//Check that the added routine has the same data that we introduced
		Boolean sameName = newRoutine.getName().equals(addedRoutine.getName());
		Boolean sameDescription = newRoutine.getDescription().equals(addedRoutine.getDescription());
		Boolean sameRepetitionsPerWeek = newRoutine.getRepsPerWeek().equals(addedRoutine.getRepsPerWeek());
		
		assertTrue(hasBeenAddedToRoutine && hasBeenAddedToTraining && sameName && sameDescription && sameRepetitionsPerWeek);		
	}
	
	@Test
	void shouldNotCreateRoutineTrainingFinished() throws DataAccessException, TrainingNotFinished{
		
		//Create the routine to be added
		Routine newRoutine = new Routine();
		newRoutine.setName("Name 1");
		newRoutine.setDescription("Desc 1");
		newRoutine.setRepsPerWeek(4);
		
		//Update Training
		assertThrows(TrainingNotFinished.class, ()->{this.routineService.saveRoutine(newRoutine, trainingId);});
	}
	
	@Test
	void shouldDeleteRoutine() throws DataAccessException, TrainingNotFinished{
		
		final int routineId = 1;
		final int trainingId = 1;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date  newEndDate = cal.getTime();
		
		//Get the specified routine (routineId)
		Routine routine = this.routineService.findRoutineById(routineId);
		//Get the specified training that contained the deleted routine
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		this.trainingService.saveTraining(training);
		
		//Bring all routines before deleting
		Collection<Routine> beforeDelete = this.routineService.findAllRoutines();
		
		//Bring all routines before deleting from the training containing the routine
		Collection<Routine>beforeDeleteRoutinesTraining = this.routineService.findAllRoutinesFromTraining(trainingId);
		
		this.routineService.deleteRoutine(routine,trainingId);		
		
		//Bring all routines after deleting
		Collection<Routine> afterDelete = this.routineService.findAllRoutines();
		
		//Bring all routines from specified training after deleting
		Collection<Routine>afterDeleteRoutinesTraining = this.routineService.findAllRoutinesFromTraining(trainingId);
		
		//Check that the size of all routine has decreased after deleting
		Boolean hasBeenDeleted = afterDelete.size() < beforeDelete.size();
		
		//Check that the size of all routine has decreased from specific training after deleting
		Boolean hasBeenDeletedFromTraining = beforeDeleteRoutinesTraining.size() > afterDeleteRoutinesTraining.size();
		
		//Check that the specified routine now do not exist == null
		Boolean notExist = this.routineService.findRoutineById(routineId) == null;
		
		assertTrue(hasBeenDeletedFromTraining && hasBeenDeleted && notExist);
	}
	
	@Test
	void shouldNotDeleteRoutine() throws DataAccessException, TrainingNotFinished{
				
		//Get the specified routine (routineId)
		Routine routine = this.routineService.findRoutineById(routineId);
		assertThrows(TrainingNotFinished.class, ()->{this.routineService.saveRoutine(routine, trainingId);});
	}
}
