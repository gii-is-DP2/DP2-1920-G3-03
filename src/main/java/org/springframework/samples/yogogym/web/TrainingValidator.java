package org.springframework.samples.yogogym.web;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TrainingValidator implements Validator{
	
	private TrainingService trainingService;
	
	private static final String REQUIRED = "required: ";
	
	@Autowired
	public TrainingValidator(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	
	public void validate(Object target, Errors errors) {
		
		Training validar = (Training) target;
		
		// Name not empty
		if (validar.getName().isEmpty()) {
			errors.rejectValue("name", REQUIRED, "The name cannot be empty");
		}
		// Initial Date not empty
		if (validar.getInitialDate() == null) {
			errors.rejectValue("initialDate", REQUIRED, "The initial date cannot be null");
		}
		// End Date not empty
		if (validar.getEndDate() == null) {
			errors.rejectValue("endDate", REQUIRED, "The end date cannot be null");
		}
		
		if(validar.getInitialDate() != null && validar.getEndDate() != null) {
			Date initialDate = validar.getInitialDate();
			Date endDate = validar.getEndDate();
			
			// initialDate before endDate
			if(endDate.before(initialDate)) {
				errors.rejectValue("endDate", REQUIRED, "The end Date must be posterior to the initial Date");
			}
			
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Training.class.isAssignableFrom(clazz);
	}

}
