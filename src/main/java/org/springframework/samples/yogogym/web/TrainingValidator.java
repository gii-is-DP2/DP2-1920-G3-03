package org.springframework.samples.yogogym.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	@SuppressWarnings("unused")
	private TrainingService trainingService;
	
	@Autowired
	public TrainingValidator(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	
	@SuppressWarnings("deprecation")
	public void validate(Object target, Errors errors) {
		
		Training validar = (Training) target;
		
		// Name not empty
		if (validar.getName().trim().isEmpty()) {
			errors.rejectValue("name", null, "The name cannot be empty");
		}
		// Initial Date not empty
		if (validar.getInitialDate() == null) {
			errors.rejectValue("initialDate", null, "The initial date cannot be null");
		}
		// End Date not empty
		if (validar.getEndDate() == null) {
			errors.rejectValue("endDate", null, "The end date cannot be null");
		}
		
		if(validar.getInitialDate() != null && validar.getEndDate() != null) {
			Date initialDate = validar.getInitialDate();
			Date endDate = validar.getEndDate();
			Date now = new Date();
			now = new Date(now.getYear(), now.getMonth(), now.getDate());
			
			// No training starting in the past
			if(validar.isNew() && initialDate.before(now)) {
				errors.rejectValue("initialDate", null, "The initial date cannot be in the past");
			}
			
			// initialDate before endDate
			if(endDate.before(initialDate)) {
				errors.rejectValue("endDate", null, "The end date must be after the initial date");
			}
			
			// Period without other trainings
			List<Training> associatedTrainings = validar.getClient().getTrainings()
				.stream().sorted(Comparator.comparing(Training::getInitialDate)).collect(Collectors.toList());
			Boolean break1 = true;
			Boolean break2 = true;
			Boolean break3 = true;
			
			for(Training t : associatedTrainings) {
				if(t.isNew()||!t.getId().equals(validar.getId())) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date initDateAssoc = t.getInitialDate();
					String initAssoc = dateFormat.format(initDateAssoc);
					Date endDateAssoc = t.getEndDate();
					String endAssoc = dateFormat.format(endDateAssoc);

					Boolean initInPeriod = validar.isNew() && (initialDate.compareTo(initDateAssoc)>=0&&initialDate.compareTo(endDateAssoc)<=0);
					Boolean endInPeriod = endDate.compareTo(initDateAssoc)>=0&&endDate.compareTo(endDateAssoc)<=0;
					Boolean initAssocInPeriod = initDateAssoc.compareTo(initialDate)>=0&&initDateAssoc.compareTo(endDate)<=0;
					Boolean endAssocInPeriod = endDateAssoc.compareTo(initialDate)>=0&&endDateAssoc.compareTo(endDate)<=0;
					
					if(initInPeriod && break1) {
						break1 = false;
						errors.rejectValue("initialDate", null, "The training cannot start in a period "
							+ "with other training (The other training is from " + initAssoc + " to " + endAssoc + ")");
					}
					if(endInPeriod && break2) {
						break2 = false;
						errors.rejectValue("endDate", null, "The training cannot end in a period "
							+ "with other training (The other training is from " + initAssoc + " to " + endAssoc + ")");
					}
					if(initAssocInPeriod && endAssocInPeriod && break3) {
						break3 = false;
						errors.rejectValue("initialDate", null, "The training cannot be in a period "
							+ "which includes another training (The other training is from " + initAssoc + " to " + endAssoc + ")");
						errors.rejectValue("endDate", null, "The training cannot be in a period "
							+ "which includes another training (The other training is from " + initAssoc + " to " + endAssoc + ")");
					}
					if(!break1&&!break2&&!break3) {
						break;
					}
				}
			}
			
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Training.class.isAssignableFrom(clazz);
	}

}
