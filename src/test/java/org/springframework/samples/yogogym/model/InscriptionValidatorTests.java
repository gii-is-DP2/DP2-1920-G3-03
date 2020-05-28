package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.model.Enums.Status;

class InscriptionValidatorTests extends ValidatorTests{
  
	@Test
	void shouldNotValidateWhenStatusNull() {
		
		Inscription i = CreateFilledInscription();
		i.setStatus(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Inscription>> constraintViolations = validator.validate(i);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Inscription> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("status");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
	void shouldNotValidateWhenUnvalidUrl() {
		
		Inscription i = CreateFilledInscription();
		i.setUrl("not a url");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Inscription>> constraintViolations = validator.validate(i);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Inscription> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("url");
		assertThat(violation.getMessage()).isEqualTo("must be a valid URL");
		
	}
	
	@Test
	void shouldNotValidateWhenChallengeNull() {
		
		Inscription i = CreateFilledInscription();
		i.setChallenge(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Inscription>> constraintViolations = validator.validate(i);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Inscription> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("challenge");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	private Inscription CreateFilledInscription() {
		
		Inscription i = new Inscription();
		Challenge c = CreateFilledChallenge();
		
		i.setChallenge(c);
		i.setStatus(Status.PARTICIPATING);
		i.setUrl("http://test.com");
		
		return i;
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
