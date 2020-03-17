package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {
  
  @Autowired
	protected ChallengeService challengeService;

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
		
	/* ------------------------ CHALLENGE -----------------------------------------------------------------*/	
	@Test
	void shouldNotValidateWhenChallengeNameEmpty() {
		Challenge c = CreateFilledChallenge();
		c.setName("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("no puede estar vacío");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeDescriptionEmpty() {
		Challenge c = CreateFilledChallenge();
		c.setDescription("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation1.getMessage()).isEqualTo("no puede estar vacío");
		
	}
	
	@Test
	void shouldNotValidateWhenRepetitionsNotPositive() {
		Challenge c = CreateFilledChallenge();
		c.setReps(-5);;
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("reps");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenEndDateBeforeIntialDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		
		Date end = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date initial = cal.getTime();
		dateFormat.format(initial);
		
		c.setInitialDate(initial);
		c.setEndDate(end);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("endDate");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenInitialDateBeforeTodays() {
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		Date initial = new Date();
		Date end = new Date();
				
		cal.set(2019, 1, 15);
		initial = cal.getTime();
		cal.set(2021, 1, 10);
		end = cal.getTime();
		c.setInitialDate(initial);
		c.setEndDate(end);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("initialDate");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenNameExistsSameWeek() {
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		Date initial = new Date();
				
		cal.set(2020, 10, 15);
		initial = cal.getTime();
		c.setInitialDate(initial);
		c.setName("Challenge3");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenMore3ChallengeSameWeek() {
		Challenge c1 = CreateFilledChallenge();
		Challenge c2 = CreateFilledChallenge();
		this.challengeService.saveChallenge(c1);
		this.challengeService.saveChallenge(c2);
		Challenge c3 = CreateFilledChallenge();
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c3);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}

	private Challenge CreateFilledChallenge() {
		
		Challenge c = new Challenge();
		Date initialDate = new Date();
		Date endDate = new Date();
		
		c.setName("ChallengeTest");
		c.setDescription("Test");
		c.setInitialDate(initialDate);
		c.setEndDate(endDate);
		c.setPoints(10);
		c.setReps(10);
		c.setReward("Test");
		c.setWeight(10.);
		
		return c;
	}
}
