package org.springframework.samples.yogogym.model;

import java.util.Locale;

import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidatorTests {
	
	protected static Validator createValidator() {
		Locale.setDefault(new Locale("en","EN"));
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

}
