package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Enums.DietType;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class DietValidatorTests extends ValidatorTests{
	
	@Test
	void shouldNotValidateWhenDietNameEmpty() {
		Diet d= CreateFilledDiet();
		d.setName("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenDietNameNull() {
		Diet d= CreateFilledDiet();
		d.setName(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenDietDescriptionEmpty() {
		Diet d= CreateFilledDiet();
		d.setDescription("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
	
	@Test
	void shouldNotValidateWhenDietDescriptionNull() {
		Diet d= CreateFilledDiet();
		d.setDescription(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
		
	}
		
	@Test
	void shouldNotValidateWhenKcalNotPositive() {
		Diet d= CreateFilledDiet();
		d.setKcal(-5);;
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("kcal");
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 0");
		
	}
	
	@Test
	void shouldNotValidateWhenProteinNotPositive() {
		Diet d= CreateFilledDiet();
		d.setProtein(-5);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("protein");
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 0");
		
	}
	@Test
	void shouldNotValidateWhenFatNotPositive() {
		Diet d= CreateFilledDiet();
		d.setFat(-5);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("fat");
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 0");
		
	}
	@Test
	void shouldNotValidateWhenCarbNotPositive() {
		Diet d= CreateFilledDiet();
		d.setCarb(-5);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Diet>> constraintViolations = validator.validate(d);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Diet> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("carb");
		assertThat(violation1.getMessage()).isEqualTo("must be greater than or equal to 0");
		
	}

	private Diet CreateFilledDiet() {
		
		Diet d= new Diet();
	
		
		d.setName("DietTest");
		d.setDescription("Test");
		
		d.setType(DietType.MAINTENANCE);
		d.setKcal(10);
		d.setCarb(10);
		d.setProtein(10);
		d.setFat(10);
		return d;
	}
}
