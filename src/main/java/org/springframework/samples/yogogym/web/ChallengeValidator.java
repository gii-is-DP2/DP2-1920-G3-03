package org.springframework.samples.yogogym.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChallengeValidator implements Validator{

	//@Autowired
	//private ChallengeService challengeService;
	
	private static final String REQUIRED = "required";
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Challenge validar = (Challenge) target;
		
		//Name not empty
		if(!validar.getName().equals("A")) {
			errors.rejectValue("name",REQUIRED, REQUIRED);
		}
		
		//Description not empty
		
		
		/*
		
		Date start = cVal.getInitialDate();
		Date end = cVal.getEndDate();
		String description = cVal.getDescription();
		
		Iterable<Challenge> cs = challengeService.findAll();
		List<Challenge> challenges = new ArrayList<Challenge>();
		cs.forEach(challenges::add);
		List<Challenge> challengesSameWeek = new ArrayList<Challenge>();
		Calendar thisC = Calendar.getInstance();
		thisC.setTime(start);
		int week = thisC.getWeekYear();
		challengesSameWeek = challenges.stream().filter(ch -> sameWeek(ch,week)).collect(Collectors.toList());
		
		// end Date Posterior
		if(end.before(start)) {
			errors.rejectValue("end", "La fecha de finalización debe ser posterior a la de inicio");
		}
		
		// No puede añadirse reto en el pasado
		Date now = new Date();
		if(start.before(now)) {
			errors.rejectValue("start", "La fecha de comienzo debe ser posterior a la actual");
		}
		
		// Reto ya existente esa semana
		if(challengesSameWeek.stream().anyMatch(c -> c.getDescription().equals(description))) {
			errors.rejectValue("description", "Ya existe un reto con esa descripción en la misma semana");
		}
		
		// Ya hay 3 retos
		if(challengesSameWeek.size() > 3) {
			errors.rejectValue("description", "Ya hay 3 retosen la misma semana");
		}*/
		
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Challenge.class.isAssignableFrom(clazz);
	}
	
	private boolean sameWeek(Challenge c, int week) {
		
		Calendar Cal = Calendar.getInstance();
		Cal.setTime(c.getInitialDate());
		
		return week == Cal.getWeekYear();
	}

}
