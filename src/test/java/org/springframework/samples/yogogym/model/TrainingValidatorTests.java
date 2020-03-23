package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

class TrainingValidatorTests {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
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
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");		
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
		assertThat(violation.getMessage()).isEqualTo("el tamaño tiene que estar entre 0 y 40");		
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
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");		
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
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");		
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
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");		
	}

}
