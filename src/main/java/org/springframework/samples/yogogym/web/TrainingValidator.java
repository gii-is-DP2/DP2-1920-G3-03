package org.springframework.samples.yogogym.web;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
			Date now = new Date();
			
			// initialDate before endDate
			if(endDate.before(initialDate)) {
				errors.rejectValue("endDate", REQUIRED, "The end date must be after the initial date");
			}

			// No training starting in the past
			if(validar.isNew() && initialDate.before(now)) {
				errors.rejectValue("initialDate", REQUIRED, "The initial date cannot be in the past");
			}
			
			// valid training period
			List<Training> associatedTrainings = validar.getClient().getTrainings()
				.stream().sorted(Comparator.comparing(Training::getInitialDate)).collect(Collectors.toList());
			Boolean break1 = true;
			Boolean break2 = true;
			Boolean break3 = true;
			
			for(Training t : associatedTrainings) {
				Date initDateAssoc = t.getInitialDate();
				Date endDateAssoc = t.getEndDate();

				Boolean initInPeriod = validar.isNew() && (initialDate.compareTo(initDateAssoc)>=0&&initialDate.compareTo(endDateAssoc)<=0);
				Boolean endInPeriod = endDate.compareTo(initDateAssoc)>=0&&endDate.compareTo(endDateAssoc)<=0;
				Boolean initAssocInPeriod = initDateAssoc.compareTo(initialDate)>=0&&initDateAssoc.compareTo(endDate)<=0;
				Boolean endAssocInPeriod = endDateAssoc.compareTo(initialDate)>=0&&endDateAssoc.compareTo(endDate)<=0;
				
				if(initInPeriod && break1) {
					break1 = false;
					errors.rejectValue("initialDate", REQUIRED, "The training cannot start in a period "
						+ "with other training (Other training period: " + initDateAssoc + " to " + endDateAssoc + ")");
				}
				if(endInPeriod && break2) {
					break2 = false;
					errors.rejectValue("endDate", REQUIRED, "The training cannot end in a period "
						+ "with other training (Other training period: " + initDateAssoc + " to " + endDateAssoc + ")");
				}
				if(initAssocInPeriod && endAssocInPeriod && break3) {
					break3 = false;
					errors.rejectValue("initialDate", REQUIRED, "The training cannot be in a period "
						+ "which includes another training (Other training period: " + initDateAssoc + " to " + endDateAssoc + ")");
					errors.rejectValue("endDate", REQUIRED, "The training cannot be in a period "
						+ "which includes another training (Other training period: " + initDateAssoc + " to " + endDateAssoc + ")");
				}
				if(!break1&&!break2&&!break3) {
					break;
				}
				
			}
			
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Training.class.isAssignableFrom(clazz);
	}

}
