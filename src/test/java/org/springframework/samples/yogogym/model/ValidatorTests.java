package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	/* ------------------------ ROUTINE --------------------------------------------------------------------*/
	@Test
	void shouldNotValidateWhenRoutineNameEmpty()
	{
		Routine routine = new Routine();
		
		routine.setName("");
		routine.setDescription("Desc 1");
		routine.setRepsPerWeek(1);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Routine>> constraintViolations = validator.validate(routine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Routine> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");		
	}
	
	@Test
	void shouldNotValidateWhenRoutineDescriptionEmpty()
	{
		Routine routine = new Routine();
		
		routine.setName("Name 1");
		routine.setDescription("");
		routine.setRepsPerWeek(1);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Routine>> constraintViolations = validator.validate(routine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Routine> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");		
	}
	
	@Test
	void shouldNotValidateWhenRoutineRepetitionPerWeekNull()
	{
		Routine routine = new Routine();
		
		routine.setName("Name 1");
		routine.setDescription("Desc 1");
		routine.setRepsPerWeek(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Routine>> constraintViolations = validator.validate(routine);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Routine> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("repsPerWeek");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");		
	}
	
	/* ------------------------ ROUTINE LINE---------------------------------------------------------------*/
	
	@Ignore
	void shouldNotValidateWhenRoutineLineRepsOrTimeEmpty()
	{
		RoutineLine routineLine = new RoutineLine();
		
		routineLine.setReps(null);
		routineLine.setTime(null);
		routineLine.setSeries(2);
		routineLine.setWeight(6.0);
						
		Validator validator = createValidator();
		Set<ConstraintViolation<RoutineLine>> constraintViolations = validator.validate(routineLine);
		
		assertThat(constraintViolations.size()).isEqualTo(2);
		ConstraintViolation<RoutineLine> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("reps");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("time");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");	
	}

}
