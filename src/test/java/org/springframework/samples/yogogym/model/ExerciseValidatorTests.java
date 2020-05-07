package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;

class ExerciseValidatorTests extends ValidatorTests{
	
	@Test
	void shouldValidate() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void shouldNotValidateWhenExerciseNameEmptyOrBlank(String name) {
		
		Exercise exercise = new Exercise();
		
		exercise.setName(name);
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");	
	}
	
	@Test
	void shouldNotValidateWhenExerciseNameNull() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName(null);
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void shouldNotValidateWhenExerciseDescriptionEmptyOrBlank(String description) {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription(description);
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");	
	}
	
	@Test
	void shouldNotValidateWhenExerciseDescriptionNull() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription(null);
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
	
	@Test
	void shouldNotValidateWhenExerciseKcalLessThanZero() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(-1);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("kcal");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");		
	}
	
	@Test
	void shouldNotValidateWhenExerciseIntensityNull() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(null);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("intensity");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenExerciseBodyPartNull() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(null);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("bodyPart");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenExerciseRepetitionTypeNull() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(null);
		exercise.setEquipment(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("repetitionType");
		assertThat(violation.getMessage()).isEqualTo("must not be null");		
	}
	
	@Test
	void shouldNotValidateWhenExerciseEquipmentNotValid() {
		
		Exercise exercise = new Exercise();
		
		exercise.setName("New Exercise");
		exercise.setDescription("Exercise description");
		exercise.setKcal(10);
		exercise.setIntensity(Intensity.INTENSE);
		exercise.setBodyPart(BodyParts.ALL);
		exercise.setRepetitionType(RepetitionType.REPS);
		Equipment equipment = new Equipment();
		equipment.setName("");
		equipment.setLocation("Location");
		exercise.setEquipment(equipment);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Exercise>> constraintViolations = validator.validate(exercise);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Exercise> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("equipment.name");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");		
	}
}
