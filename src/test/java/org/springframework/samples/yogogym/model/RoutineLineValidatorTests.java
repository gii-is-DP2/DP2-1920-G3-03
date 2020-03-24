package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

public class RoutineLineValidatorTests extends ValidatorTests{
		
	@Test
	void shouldNotValidateWhenRepLessThanMin()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(0);
		routineLine.setTime(null);
		routineLine.setSeries(2);
		routineLine.setWeight(6.0);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
						
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("reps");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
	}
	
	@Test
	void shouldNotValidateWhenTimeLessThanMin()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(null);
		routineLine.setTime(0.0);
		routineLine.setSeries(2);
		routineLine.setWeight(6.0);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
						
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("time");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
	}
	
	@Test
	void shouldNotValidateWhenSeriesNull()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(5);
		routineLine.setTime(1.0);
		routineLine.setSeries(2);
		routineLine.setWeight(6.0);
		routineLine.setSeries(null);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
						
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("series");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void shouldNotValidateWhenSeriesLessThanMin()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(5);
		routineLine.setTime(1.0);
		routineLine.setSeries(0);
		routineLine.setWeight(6.0);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("series");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
	}
	
	@Test
	void shouldNotValidateWhenWeightNull()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(5);
		routineLine.setTime(1.0);
		routineLine.setSeries(2);
		routineLine.setWeight(null);
		routineLine.setSeries(2);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
						
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("weight");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void shouldNotValidateWhenWeightLessThanMin()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(5);
		routineLine.setTime(1.0);
		routineLine.setSeries(1);
		routineLine.setWeight(-1.0);
		Exercise exercise = new Exercise();
		routineLine.setExercise(exercise);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("weight");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}
	
	@Test
	void shouldNotValidateWhenExerciseNull()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(5);
		routineLine.setTime(1.0);
		routineLine.setSeries(2);
		routineLine.setWeight(2.0);
		routineLine.setSeries(2);
							
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("exercise");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
}
