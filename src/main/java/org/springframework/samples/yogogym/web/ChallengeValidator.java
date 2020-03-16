package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChallengeValidator implements Validator{

	private ChallengeService challengeService;
	
	@Autowired
	public ChallengeValidator(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}
	
	
	private static final String REQUIRED = "required: ";
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Challenge validar = (Challenge) target;
		
		// Name not empty
		if (validar.getName().isEmpty()) {
			errors.rejectValue("name", REQUIRED, "The name can not be empty");
		}
		// Description not empty
		if (validar.getDescription().isEmpty()) {
			errors.rejectValue("description", REQUIRED, "The description can not be empty");
		}
		// Initial Date not empty
		if (validar.getInitialDate() == null) {
			errors.rejectValue("initialDate", REQUIRED, "The Initial Date can not be null");
		}
		// End Date not empty
		if (validar.getEndDate() == null) {
			errors.rejectValue("endDate", REQUIRED, "The End Date can not be null");
		}
		// Reward not empty
		if (validar.getReward().isEmpty()) {
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
		
		
		if(validar.getInitialDate() != null && validar.getEndDate() != null) {
			
			Date initialDateVal = validar.getInitialDate();
			Date endDateVal = validar.getEndDate();
			Date now = new Date();
			
			// All the challenges in the Database
			List<Challenge> challenges = new ArrayList<Challenge>();
			challengeService.findAll().forEach(challenges::add);
			
			// All the challenges in the same week as the one we are validating
			Calendar challengeCalendar = Calendar.getInstance();
			challengeCalendar.setTime(initialDateVal);
			int week = challengeCalendar.get(GregorianCalendar.WEEK_OF_YEAR);
			List<Challenge> challengesSameWeek = new ArrayList<Challenge>();
			challengesSameWeek = challenges.stream().filter(ch -> sameWeek(ch,week)).collect(Collectors.toList());
			//Quitar challenge que estamos editando
			if(validar.getId() != null) {
				int id = validar.getId();
				
				for(Challenge c : challengesSameWeek) {
					if(c.getId() == id) {
						challengesSameWeek.remove(c);
						break;
					}
				}
			}
			
			// initialDate before endDate
			if(endDateVal.before(initialDateVal)) {
				errors.rejectValue("endDate", REQUIRED, "The end Date must be posterior to the initial Date");
			}
			
			// No challenge starting in the past
			if(initialDateVal.before(now)) {
				errors.rejectValue("initialDate", REQUIRED, "Starting date must be posterior to the actual date");
			}
			
			// Challenge with that name already exist this week
			if(challengesSameWeek.stream().anyMatch(c -> c.getName().equals(validar.getName()))) {
				errors.rejectValue("name", REQUIRED, "There is already a challenge with that name the same week");
			}
			
			// No more than 3 challenges the same week
			if(challengesSameWeek.size() > 2) {
				errors.rejectValue("initialDate", REQUIRED, "There are already 3 challenges the same week");
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Challenge.class.isAssignableFrom(clazz);
	}
	
	private boolean sameWeek(Challenge c, int week) {
		
		Calendar Cal = Calendar.getInstance();
		Cal.setTime(c.getInitialDate());
		int week2 = Cal.get(GregorianCalendar.WEEK_OF_YEAR);
		
		return week == week2;
	}

}
