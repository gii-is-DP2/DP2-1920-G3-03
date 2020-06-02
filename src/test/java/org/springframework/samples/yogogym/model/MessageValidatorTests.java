package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

public class MessageValidatorTests extends  ValidatorTests{
		
	@Test
	void shouldValidate()
	{
		Message m = createMessage("valid");
		
		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Message>> constraintViolations = validator.validate(m);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@Test
	void shouldNotValidateInvalidate()
	{
		Message m = createMessage("invalid");
		
		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Message>> constraintViolations = validator.validate(m);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Message> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("content");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void shouldNotValidateNull()
	{
		Message m = createMessage("null");
		
		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Message>> constraintViolations = validator.validate(m);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Message> violation = constraintViolations.iterator().next();
		
		assertThat(violation.getPropertyPath().toString()).isEqualTo("content");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	
	Message createMessage(String s) {
		
		Message res = new Message();

		if(s == "valid")
		{
			res.setAnswers(new ArrayList<>());
			res.setContent("Content");
			res.setCreatedAt(Calendar.getInstance().getTime());
			res.setEdited(false);
			res.setIsParent(true);
			res.setUser(createUser());
		}
		else if(s == "invalid")
		{	
			res.setContent("");
		}
		else if(s == "null")
		{	
			res.setContent(null);
		}
		else
		{
			System.out.println("Parameter must be 'null','valid' or 'invalid'.");
			return null;
		}		

		return res;
	}
	
	User createUser()
	{
		User user = new User();
		
		user.setEnabled(true);
		user.setPassword("password");
		user.setUsername("user");
		
		return user;
	}

}
