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
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.exceptions.MaxRoutinesException;
import org.springframework.samples.yogogym.service.exceptions.NotEditableException;
import org.springframework.samples.yogogym.service.exceptions.TrainingFinished;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RoutineServiceTest {
	
	@Autowired
	protected ClientService clientService;
	@Autowired
	protected RoutineService routineService;
	@Autowired
	protected TrainingService trainingService;
	@Autowired
	protected RoutineLineService routineLineService;
	@Autowired
	protected ExerciseService exerciseService;
	
	private final String trainerUsername = "trainer1";
	private final String clientUsername = "client1";
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
	
	
	//TRAINER
	@Test
	void shouldCreateRoutine_trainer() 
	{
		//Setting Training not Finished
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date  newEndDate = cal.getTime();
		
		createRoutine(trainerUsername,newEndDate,trainingId);
	}
	
	@Test
	void shouldNotCreateRoutineTrainingFinished_trainer(){
		
		testCreateExceptions(0,trainerUsername,EditingPermission.CLIENT);
	}
	
	@Test
	void shouldNotCreateRoutineNotEditableTraining_trainer(){
		
		testCreateExceptions(1,trainerUsername,EditingPermission.CLIENT);
	}
	
	@Test
	void shouldNotCreateRoutineMaxRoutines_trainer(){
		
		testCreateExceptions(2,trainerUsername,EditingPermission.TRAINER);
	}
	
	@Test
	void shouldDeleteRoutine_trainer(){
		
		//Setting Training not Finished
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date  newEndDate = cal.getTime();
		
		deleteRoutine(trainerUsername,newEndDate,EditingPermission.TRAINER);		
	}
	
	@Test
	void shouldNotDeleteRoutineTrainingFinished_trainer(){
	
		testDeleteExceptions(0,trainerUsername,EditingPermission.CLIENT);		
	}
	
	@Test
	void shouldNotDeleteRoutineNotEditable_trainer(){
	
		testDeleteExceptions(1,trainerUsername,EditingPermission.CLIENT);		
	}
	
	//CLIENT
	
	@Test
	void shouldCreateRoutine_client()
	{
		//Setting Training not Finished
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date  newEndDate = cal.getTime();
				
		createRoutine(clientUsername,newEndDate,trainingId);
	}
	
	@Test
	void shouldDeleteRoutine_client(){
		
		//Setting Training not Finished
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date  newEndDate = cal.getTime();
		
		deleteRoutine(clientUsername,newEndDate,EditingPermission.CLIENT);		
	}
	
	@Test
	void shouldNotDeleteRoutineTrainingFinished_client(){
	
		testDeleteExceptions(0,clientUsername,EditingPermission.TRAINER);		
	}
	
	@Test
	void shouldNotDeleteRoutineNotEditable_client(){
	
		testDeleteExceptions(1,clientUsername,EditingPermission.TRAINER);		
	}
	
	//Base Methods
	
	void createRoutine(String username,Date newEndDate, final int trainingId)
	{
		//Setting future end date
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setEndDate(newEndDate);
		
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
		try
		{
			this.routineService.saveRoutine(newRoutine, username, trainingId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
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
	
	void deleteRoutine(String username, Date newEndDate, EditingPermission editPerm)
	{			
		//Get the specified routine (routineId)
		Routine routine = this.routineService.findRoutineById(routineId);
		//Get the specified training that contained the deleted routine
		Training training = this.trainingService.findTrainingById(trainingId);
		training.setInitialDate(Calendar.getInstance().getTime());
		training.setEndDate(newEndDate);
		training.setEditingPermission(editPerm);
		Client client = this.clientService.findClientByUsername(clientUsername);
		try {
			this.trainingService.saveTraining(training,client);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Bring all routines before deleting
		Collection<Routine> beforeDelete = this.routineService.findAllRoutines();
		
		//Bring all routines before deleting from the training containing the routine
		Collection<Routine>beforeDeleteRoutinesTraining = this.routineService.findAllRoutinesFromTraining(trainingId);
		
		try
		{
			this.routineService.deleteRoutine(routine,username,trainingId);		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
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
	
	void testCreateExceptions(final int caseId, String username, EditingPermission editPerm)
	{
		Routine newRoutine = new Routine();
		newRoutine.setName("Name 1");
		newRoutine.setDescription("Desc 1");
		newRoutine.setRepsPerWeek(4);
		
		switch(caseId)
		{
		//Training Finished
		case 0:
			assertThrows(TrainingFinished.class, ()->{this.routineService.saveRoutine(newRoutine,username,trainingId);});
			break;
		//Training not Editable
		case 1:
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			Date  newEndDate = cal.getTime();
			
			Training training = this.trainingService.findTrainingById(trainingId);
			training.setEditingPermission(editPerm);
			training.setEndDate(newEndDate);
			
			assertThrows(NotEditableException.class, ()->{this.routineService.saveRoutine(newRoutine,username,trainingId);});
			break;
		//Max Routines Reached
		case 2:
			cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			newEndDate = cal.getTime();
			
			training = this.trainingService.findTrainingById(trainingId);
			training.setEditingPermission(editPerm);
			training.setEndDate(newEndDate);
		
			CreateRoutinesForTraining(training,10);
			
			assertThrows(MaxRoutinesException.class, ()->{this.routineService.saveRoutine(newRoutine,username,trainingId);});
				
			break;
		}		
	}
	
	void testDeleteExceptions(final int caseId, String username, EditingPermission editPerm)
	{
		Routine newRoutine = this.routineService.findRoutineById(routineId);
		
		Calendar cal = null;
		Date newEndDate = null;
		Training training = null;
		
		switch(caseId)
		{
		//Training Finished
		case 0:
			assertThrows(TrainingFinished.class, ()->{this.routineService.saveRoutine(newRoutine,username,trainingId);});
			break;
		//Training not Editable
		case 1:
			cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			newEndDate = cal.getTime();
			
			training = this.trainingService.findTrainingById(trainingId);
			training.setEditingPermission(editPerm);
			training.setEndDate(newEndDate);
			
			assertThrows(NotEditableException.class, ()->{this.routineService.saveRoutine(newRoutine,username,trainingId);});
			break;
		}
	}
	
	void CreateRoutinesForTraining(Training training,int limit)
	{		
		for(int i = training.getRoutines().size(); i < limit; i++)
		{
			Routine routine = new Routine();
			routine.setName("Routine " + i);
			routine.setDescription("Desc "+i);
			routine.setRepsPerWeek(5);
			
			training.getRoutines().add(routine);
		}
	}
}
