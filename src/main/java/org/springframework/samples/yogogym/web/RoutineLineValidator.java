
package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
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
		if (validar.getWeight() == null) {
			errors.rejectValue("weight", REQUIRED, "The weight cannot be null");
		}
		else if (validar.getWeight() < 0) {
			errors.rejectValue("weight", REQUIRED, "The weight cannot be less than 0");
		}
		// Series greater than 0
		if (validar.getSeries() == null) {
			errors.rejectValue("series", REQUIRED, "The series cannot be null");
		}
		else if (validar.getSeries() < 1) {
			errors.rejectValue("series", REQUIRED, "The series cannot be less than 0");
		}
		
		if(validar.getReps() == null && validar.getTime() == null)
		{
			errors.rejectValue("reps", REQUIRED, "Reps cannot be null");
			errors.rejectValue("time", REQUIRED, "Time cannot be null");
		}
		else if(validar.getTime() == null && validar.getReps() != null)
		{
			if(validar.getReps() < 0)
				errors.rejectValue("reps", REQUIRED, "Reps cannot lower than 0");
		}
		else if(validar.getReps() == null && validar.getTime() != null)
		{
			if(validar.getTime() < 0)
				errors.rejectValue("time", REQUIRED, "Time cannot lower than 0");
		}
		else
		{
			errors.rejectValue("reps", REQUIRED, "You cannot enter both time and reps. Choose one.");
			errors.rejectValue("time", REQUIRED, "You cannot enter both time and reps. Choose one.");
		}
	}
		
	@Override
	public boolean supports(Class<?> clazz) {
		return RoutineLine.class.isAssignableFrom(clazz);
	}
}
