package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EquipmentValidatorTests extends ValidatorTests{
	
	@Test
	void shouldValidateWithLocation() throws ParseException {
		
		Equipment equipment = new Equipment();
		
		equipment.setName("New Equipment");
		equipment.setLocation("New Location");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Equipment>> constraintViolations = validator.validate(equipment);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@Test
	void shouldValidateNoLocation() throws ParseException {
		
		Equipment equipment = new Equipment();
		
		equipment.setName("New Equipment");
		equipment.setLocation(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Equipment>> constraintViolations = validator.validate(equipment);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"" , "  "})
	void shouldNotValidateWhenEquipmentNameEmptyOrBlank(String name) throws ParseException {
		
		Equipment equipment = new Equipment();
		
		equipment.setName(name);
		equipment.setLocation("New Location");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Equipment>> constraintViolations = validator.validate(equipment);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Equipment> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenEquipmentNameNull() throws ParseException {
		
		Equipment equipment = new Equipment();
		
		equipment.setName(null);
		equipment.setLocation("New Location");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Equipment>> constraintViolations = validator.validate(equipment);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Equipment> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
}
