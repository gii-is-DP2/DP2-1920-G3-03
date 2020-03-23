package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class TrainingValidatorTests extends ValidatorTests{
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	void shouldNotValidateWhenTrainingNameEmpty() throws ParseException {
		
		Training training = new Training();
		
		training.setName("");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingNameLongerThan40() throws ParseException {
		
		Training training = new Training();
		
		training.setName("This training name has more than forty characters");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 0 and 40");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingInitDateNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(null);
		training.setEndDate(dateFormat.parse("2020-01-14"));
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("initialDate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingEndDateNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(null);
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("endDate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingClientNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setClient(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("client");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}

}
