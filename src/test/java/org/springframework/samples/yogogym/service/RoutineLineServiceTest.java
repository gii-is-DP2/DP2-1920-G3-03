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
import org.springframework.samples.yogogym.model.RoutineLine;
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
	
	@Test
	void shouldFindRoutineLineById(){
		RoutineLine routineLine = this.routineLineService.findRoutoneLineById(1);
			
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
	void shouldCreateRoutineLine(){
		
		final int routineId = 1;
		
		Collection<RoutineLine> beforeAdding = this.routineLineService.findAllRoutinesLines();
		Routine routine = this.routineService.findRoutineById(routineId);
							
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(10);
		routineLine.setTime(null);
		routineLine.setSeries(5);
		routineLine.setWeight(5.0);
		routineLine.setExercise(null);
		
		routine.getRoutineLine().add(routineLine);
		this.routineService.saveRoutine(routine);
				
		Collection<RoutineLine> afterAdding = this.routineLineService.findAllRoutinesLines();
		
	
		
	}
}
