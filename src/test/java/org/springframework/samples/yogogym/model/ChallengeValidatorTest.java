package org.springframework.samples.yogogym.model;

public class ChallengeValidatorTest {

	/*
	@Ignore
	void shouldNotValidateWhenEndDateBeforeIntialDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		
		Date end = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date initial = cal.getTime();
		dateFormat.format(initial);
		
		c.setInitialDate(initial);
		c.setEndDate(end);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("endDate");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenInitialDateBeforeTodays() {
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		Date initial = new Date();
		Date end = new Date();
				
		cal.set(2019, 1, 15);
		initial = cal.getTime();
		cal.set(2021, 1, 10);
		end = cal.getTime();
		c.setInitialDate(initial);
		c.setEndDate(end);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("initialDate");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenNameExistsSameWeek() {
		Challenge c = CreateFilledChallenge();
		Calendar cal = Calendar.getInstance();
		Date initial = new Date();
				
		cal.set(2020, 10, 15);
		initial = cal.getTime();
		c.setInitialDate(initial);
		c.setName("Challenge3");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	@Ignore
	void shouldNotValidateWhenMore3ChallengeSameWeek() {
		Challenge c1 = CreateFilledChallenge();
		Challenge c2 = CreateFilledChallenge();
		this.challengeService.saveChallenge(c1);
		this.challengeService.saveChallenge(c2);
		Challenge c3 = CreateFilledChallenge();
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Challenge>> constraintViolations = validator.validate(c3);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Challenge> violation1 = constraintViolations.iterator().next();
		
		assertThat(violation1.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation1.getMessage()).isEqualTo("tiene que ser mayor o igual que 1");
		
	}
	
	private Challenge CreateFilledChallenge() {
		
		Challenge c = new Challenge();
		Date initialDate = new Date();
		Date endDate = new Date();
		
		c.setName("ChallengeTest");
		c.setDescription("Test");
		c.setInitialDate(initialDate);
		c.setEndDate(endDate);
		c.setPoints(10);
		c.setReps(10);
		c.setReward("Test");
		c.setWeight(10.);
		
		return c;
	}
	*/
	
}
