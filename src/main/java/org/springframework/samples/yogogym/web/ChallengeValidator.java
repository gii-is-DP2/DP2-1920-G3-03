package org.springframework.samples.yogogym.web;

import java.util.Date;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChallengeValidator implements Validator{

	private static final String REQUIRED = "required: ";
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Challenge validar = (Challenge) target;
		Date initialDate = validar.getInitialDate();
		Date endDate = validar.getEndDate();
		Date now = new Date();
		
		// Name not empty
		if (validar.getName() == null || validar.getName().trim().isEmpty()) {
			errors.rejectValue("name", REQUIRED, "The name can not be empty");
		}
		// Description not empty
		if (validar.getDescription() == null || validar.getDescription().trim().isEmpty()) {
			errors.rejectValue("description", REQUIRED, "The description can not be empty");
		}
		// Initial Date not empty
		if (initialDate == null) {
			errors.rejectValue("initialDate", REQUIRED, "The Initial Date can not be null");
		}
		// End Date not empty
		if (validar.getEndDate() == null) {
			errors.rejectValue("endDate", REQUIRED, "The End Date can not be null");
		}
		// Reward not empty
		if (validar.getReward() == null || validar.getReward().trim().isEmpty()) {
			errors.rejectValue("reward", REQUIRED, "The Reward can not be empty");
		}
		// Points not empty and positive
		if (validar.getPoints() == null || validar.getPoints() < 0) {
			errors.rejectValue("points", REQUIRED, "Points can not be null or less than 0");
		}
		// Repetitions positive
		if(validar.getReps() != null) {
			if (validar.getReps() < 0) {
				errors.rejectValue("reps", REQUIRED, "Repetitions can not be less than 0");
			}
		}
		// Weight positive
		if(validar.getWeight() != null) {
			if (validar.getWeight() < 0) {
				errors.rejectValue("weight", REQUIRED, "Weight can not be less than 0");
			}
		}
			
		if(initialDate != null && endDate != null) {
			
			// initialDate before endDate
			if(endDate.before(initialDate)) {
				errors.rejectValue("endDate", REQUIRED, "The end Date must be posterior to the initial Date");
			}
			
			// No challenge starting in the past
			if(initialDate.before(now)) {
				errors.rejectValue("initialDate", REQUIRED, "Starting date must be posterior to the actual date");
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Challenge.class.isAssignableFrom(clazz);
	}
	
}
