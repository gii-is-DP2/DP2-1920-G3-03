package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Guild;
import org.springframework.samples.yogogym.service.GuildService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GuildValidator implements Validator{

	private final GuildService guildService;
	
	private static final String REQUIRED = "required: ";
	
	@Autowired
	public GuildValidator(final GuildService guildService) {
		this.guildService = guildService;
	}
	
	
	public void validate(Object target, Errors errors) {
		
		Guild validar = (Guild) target;
	
	//creator not empty
	if(validar.getCreator() == "") {
		errors.rejectValue("creator", REQUIRED, "The creator cannot be null");
	}
	
	if(validar.getLogo() == "") {
		errors.rejectValue("logo", REQUIRED, "Must provide an image for your Guild");
	}else if(!validar.getLogo().startsWith("https://")) {
		errors.rejectValue("logo", REQUIRED, "Must provide an URL starting with 'https://'");
	}
	
	if(validar.getDescription() == "") {
		errors.rejectValue("description", REQUIRED, "The Guild must have a description");
	}
	
	if(validar.getName() == "") {
		errors.rejectValue("name", REQUIRED, "The Guild name cannot be null");
	}
		
	}
	
	public boolean supports(Class<?> clazz) {
		
		return Guild.class.isAssignableFrom(clazz);
	}
}
