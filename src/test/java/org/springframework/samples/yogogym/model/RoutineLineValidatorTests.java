package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Ignore;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class RoutineLineValidatorTests {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
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
