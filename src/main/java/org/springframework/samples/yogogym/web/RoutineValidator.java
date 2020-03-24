
package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoutineValidator implements Validator{

	private final RoutineService routineService;
	
	private static final String REQUIRED = "required: ";
	
	@Autowired
	public RoutineValidator(final RoutineService routineService) {
		this.routineService = routineService;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Routine validar = (Routine) target;
				
		// Name not empty and not null
		if (validar.getName() == null || validar.getName().trim().isEmpty()) {
			errors.rejectValue("name", REQUIRED, "The name cannot be empty");
		}
		// Description not empty
		if (validar.getDescription() == null || validar.getDescription().trim().isEmpty()) {
			errors.rejectValue("description", REQUIRED, "The description cannot be empty");
		}
		// Reps per week not empty
		if (validar.getRepsPerWeek() == null) {
			errors.rejectValue("repsPerWeek", REQUIRED, "The repetition per week cannot be null");
		}
		// Reps per week positive
		else if(validar.getRepsPerWeek() < 0) {
			errors.rejectValue("repsPerWeek", REQUIRED, "The repetition per week must be positive");
		}
		// Reps per week less than 20
		else if(validar.getRepsPerWeek() > 20) {
			errors.rejectValue("repsPerWeek", REQUIRED, "The repetition per week cannot be greater than 20");
		}
		
	}
		
	@Override
	public boolean supports(Class<?> clazz) {
		return Routine.class.isAssignableFrom(clazz);
	}
}
