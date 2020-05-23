package org.springframework.samples.yogogym.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

public class ForumValidatorTests extends ValidatorTests {

	@Test
	void shouldValidate() {
		
		Forum forum = new Forum();
		forum.setGuild(createGuild("valid"));
		forum.setMessages(new ArrayList<>());

		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Forum>> constraintViolations = validator.validate(forum);
		
		assertThat(constraintViolations.size()).isEqualTo(0);	
	}
	
	@Test
	void shouldNotValidateInvalidGuild() {
		
		Forum forum = new Forum();
		forum.setGuild(createGuild("invalid"));
		forum.setMessages(new ArrayList<>());

		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Forum>> constraintViolations = validator.validate(forum);
		
		assertThat(constraintViolations.size()).isEqualTo(4);
		
		for(ConstraintViolation<Forum> v: constraintViolations)
		{
			assertThat(v.getMessage()).isEqualTo("must not be empty");			
		}
	}
	
	@Test
	void shouldNotValidateWhenForumGuildNull() {
		
		Forum forum = new Forum();
		forum.setGuild(null);
		forum.setMessages(new ArrayList<>());

		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Forum>> constraintViolations = validator.validate(forum);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Forum> violation = constraintViolations.iterator().next();

		assertThat(violation.getPropertyPath().toString()).isEqualTo("guild");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void shouldNotValidateWhenForumMessagesNull() {
		
		Forum forum = new Forum();
		forum.setGuild(createGuild("valid"));
		forum.setMessages(null);

		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Forum>> constraintViolations = validator.validate(forum);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Forum> violation = constraintViolations.iterator().next();

		assertThat(violation.getPropertyPath().toString()).isEqualTo("messages");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void shouldNotValidateWhenForumMessagesInvalid() {
		
		Forum forum = new Forum();
		forum.setGuild(createGuild("valid"));
		
		Message m = createMessage("invalid");
		Collection<Message> messages = new ArrayList<>();
		messages.add(m);
		forum.setMessages(messages);

		Validator validator = ForumValidatorTests.createValidator();
		Set<ConstraintViolation<Forum>> constraintViolations = validator.validate(forum);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Forum> violation = constraintViolations.iterator().next();

		assertThat(violation.getPropertyPath().toString()).isEqualTo("messages[0].content");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	Guild createGuild(String s) {
		
		Guild res = new Guild();

		if(s == "valid")
		{
			res.setCreator("client1");
			res.setDescription("description");
			res.setLogo("https");
			res.setName("Name");
		}
		else if(s == "invalid")
		{	
			//Invalid name empty
			res.setCreator("");
			//Invalid Description empty
			res.setDescription("");
			//Invalid Logo Link
			res.setLogo("");
			//Invalid name empty
			res.setName("");
		}
		else
		{
			System.out.println("Parameter must be 'null','valid' or 'invalid'.");
			return null;
		}		

		return res;
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
