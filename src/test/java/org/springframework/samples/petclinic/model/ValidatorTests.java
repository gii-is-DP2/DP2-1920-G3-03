package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.model.Challenge;
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

	
	@Test
	void shouldNotValidateWhenChallengeEmpty() {
		Challenge c = new Challenge();
		Date initialDate = new Date();
		Date endDate = new Date();
		c.setName("");
		c.setDescription("");
		c.setInitialDate(initialDate);
		c.setEndDate(endDate);
		c.setPoints(10);
		c.setReps(10);
		c.setReward("Test");
		c.setWeight(10.);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(2);
		
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("no puede estar vacío");
		
		/*ConstraintViolation<Challenge> violation2 = constraintViolations.iterator().next();
		assertThat(violation2.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation2.getMessage()).isEqualTo("no puede estar vacío");*/
		
	}

	/*private Challenge CreateFilledChallenge() {
		
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
	}*/
}
