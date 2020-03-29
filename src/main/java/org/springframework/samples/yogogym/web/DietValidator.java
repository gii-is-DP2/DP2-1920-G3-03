package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DietValidator implements Validator {

	@SuppressWarnings("unused")
	private DietService dietService;

	@Autowired
	public DietValidator(DietService dietService) {
		this.dietService = dietService;
	}
	
	
	private static final String REQUIRED = "required: ";
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Diet validar = (Diet) target;
		
		// Name not empty
		if (validar.getName().isEmpty()) {
			errors.rejectValue("name", REQUIRED, "The name can not be empty");
		}
		// Description not empty
		if (validar.getDescription().isEmpty()) {
			errors.rejectValue("description", REQUIRED, "The description can not be empty");
		}
		// Type not null
		// if (validar.getType() == null) {
		// 	errors.rejectValue("type", REQUIRED, "The diet type can not be null");
		// }
		
		// Kcals positive
		if(validar.getKcal() != null) {
			if (validar.getKcal() < 0) {
				errors.rejectValue("Kcal", REQUIRED, "Kcals can not be less than 0");
			}
		}
		// Proteins positive
		if(validar.getProtein() != null) {
			if (validar.getProtein() < 0) {
				errors.rejectValue("protein", REQUIRED, "Proteins can not be less than 0");
			}
		}
		// Fat positive
		if(validar.getFat() != null) {
			if (validar.getFat() < 0) {
				errors.rejectValue("fat", REQUIRED, "Fat can not be less than 0");
			}
		}
		// Carbs positive
		if(validar.getCarb() != null) {
			if (validar.getCarb() < 0) {
				errors.rejectValue("carb", REQUIRED, "Carbs can not be less than 0");
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Diet.class.isAssignableFrom(clazz);
	}
	

}
