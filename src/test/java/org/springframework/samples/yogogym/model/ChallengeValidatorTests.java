package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ChallengeValidatorTests extends ValidatorTests{
  
	@Test
	void shouldNotValidateWhenChallengeNameEmpty() {
		Challenge c = CreateFilledChallenge();
		c.setName("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeNameNull() {
		Challenge c = CreateFilledChallenge();
		c.setName(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeDescriptionEmpty() {
		Challenge c = CreateFilledChallenge();
		c.setDescription("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeDescriptionNull() {
		Challenge c = CreateFilledChallenge();
		c.setDescription(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeRewardEmpty() {
		Challenge c = CreateFilledChallenge();
		c.setReward("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("reward");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeRewardNull() {
		Challenge c = CreateFilledChallenge();
		c.setReward(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("reward");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengePointsNull() {
		Challenge c = CreateFilledChallenge();
		c.setPoints(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("points");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengePointsLess1() {
		Challenge c = CreateFilledChallenge();
		c.setPoints(0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("points");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
		
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
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 1");
		
	}
	
	@Test
	void shouldNotValidateWhenWeightNotPositive() {
		Challenge c = CreateFilledChallenge();
		c.setWeight(-5.);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("weight");
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 0");
		
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
