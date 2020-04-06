package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;

class TrainingValidatorTests extends ValidatorTests{
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	void shouldValidate() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@Test
	void shouldNotValidateWhenTrainingNameEmpty() throws ParseException {
		
		Training training = new Training();
		
		training.setName("");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
	void shouldNotValidateWhenTrainingNameBlank() throws ParseException {
		
		Training training = new Training();
		
		training.setName("   ");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
	void shouldNotValidateWhenTrainingNameNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName(null);
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
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
	void shouldNotValidateWhenEditingPermissionNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(null);
		training.setAuthor("trainer1");
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("editingPermission");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingAuthorEmpty() throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("");
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("author");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingAuthorBlank() throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("   ");
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("author");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingAuthorNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor(null);
		Client client = new Client();
		training.setClient(client);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("author");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingClientNull() throws ParseException {
		
		Training training = new Training();
		
		training.setName("New Training");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		training.setClient(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("client");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}

}
