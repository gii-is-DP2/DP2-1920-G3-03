
package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.service.RoutineLineService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoutineLineValidator implements Validator{

	private final RoutineLineService routineLineService;
	
	private static final String REQUIRED = "required: ";
	
	@Autowired
	public RoutineLineValidator(final RoutineLineService routineLineService) {
		this.routineLineService = routineLineService;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		RoutineLine validar = (RoutineLine) target;
		
		// Weight greater than 0
		if (validar.getWeight() < 0) {
			errors.rejectValue("reps", REQUIRED, "The weight cannot be less than 0");
		}
		// Series greater than 0
		if (validar.getSeries() < 1) {
			errors.rejectValue("reps", REQUIRED, "The series cannot be less than 0");
		}
		// Only time or reps setted
		//Priority to reps
		if(validar.getReps() > 1)
		{
			if(validar.getTime() > 0)
				errors.rejectValue("time", REQUIRED, "You can only set one, or repetition or time, not both");
			
			//Time reps be greater than 0
			if(validar.getReps() < 0)
				errors.rejectValue("reps", REQUIRED, "Repetition cannot be less than 0");
		}
		else
		{
			if(validar.getReps() > 0)
				errors.rejectValue("reps", REQUIRED, "You can only set one, or repetition or time, not both");
			//Time must be greater than 0
			if(validar.getTime() < 0)
				errors.rejectValue("time", REQUIRED, "Time cannot be less than 0");
		}				
	}
		
	@Override
	public boolean supports(Class<?> clazz) {
		return Routine.class.isAssignableFrom(clazz);
	}
}
