package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class GuildValidatorTests {
	
	private Validator createValidator() {
		Locale.setDefault(new Locale("en","EN"));
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	
}

	@Test
	void shouldNotValidateWhenGuildNameEmpty() {
		Guild guild = new Guild();
		
		guild.setCreator("CarlosD");
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription("Prueba de empty name");
		guild.setName("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildNameNull() {
		Guild guild = new Guild();
		
		guild.setCreator("CarlosD");
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription("Prueba de empty name");
		guild.setName(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildCreatorEmpty() {
		
		Guild guild = new Guild();
		
		guild.setCreator("");
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription("Prueba de empty name");
		guild.setName("Best Guild");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("creator");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildCreatorNull() {
		
		Guild guild = new Guild();
		
		guild.setCreator(null);
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription("Prueba de empty name");
		guild.setName("Best Guild");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("creator");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildDescriptionEmpty() {
		
		Guild guild = new Guild();
		
		guild.setCreator("JCoboG");
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription("");
		guild.setName("Not the best Guild");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildDescriptionNull() {
		
		Guild guild = new Guild();
		
		guild.setCreator("JCoboG");
		guild.setLogo("https://logoejemplo.png");
		guild.setDescription(null);
		guild.setName("Not the best Guild");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildLogoEmpty() {
		
		Guild guild = new Guild();
		
		guild.setCreator("JCoboG");
		guild.setLogo("");
		guild.setDescription("Una descripcion");
		guild.setName("Not the best Guild");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("logo");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	
	@Test
	void shouldNotValidateWhenGuildLogoNull() {
		
		Guild guild = new Guild();
		
		guild.setCreator("JCoboG");
		guild.setDescription("Una descripcion");
		guild.setName("Not the best Guild");
		guild.setLogo(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Guild>> constraintViolations = validator.validate(guild);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Guild> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("logo");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");	
	}
	

}