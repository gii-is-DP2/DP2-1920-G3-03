package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.samples.yogogym.model.Enums.DietType;
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
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"" , "  "})
	void shouldNotValidateWhenTrainingNameEmptyOrBlank(String name) throws ParseException {
		
		Training training = new Training();
		
		training.setName(name);
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		
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
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("editingPermission");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"" , "  "})
	void shouldNotValidateWhenTrainingAuthorEmptyOrBlank(String author) throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor(author);
		
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
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("author");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingRoutinesNotValid() throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		
		List<Routine> routines = new ArrayList<>();
		Routine routine = new Routine();
		routine.setName("");
		routine.setDescription("Routine description");
		routine.setRepsPerWeek(5);
		routines.add(routine);
		
		training.setRoutines(routines);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("routines[0].name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenTrainingDietNotValid() throws ParseException {
		
		Training training = new Training();
		
		training.setName("Training 1");
		training.setInitialDate(dateFormat.parse("2020-01-01"));
		training.setEndDate(dateFormat.parse("2020-01-14"));
		training.setEditingPermission(EditingPermission.BOTH);
		training.setAuthor("trainer1");
		
		Diet diet = new Diet();
		diet.setName("");
		diet.setDescription("Diet description");
		diet.setType(DietType.DEFINITION);
		diet.setKcal(10);
		diet.setProtein(10);
		diet.setFat(10);
		diet.setCarb(10);
		
		training.setDiet(diet);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Training>> constraintViolations = validator.validate(training);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Training> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("diet.name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}

}
