package org.springframework.samples.yogogym.service.exceptions;

public class InitInTrainingException extends Exception{

	private String initAssoc;
	private String EndAssoc;
	
	public InitInTrainingException(String initAssoc, String endAssoc) {
		super();
		this.initAssoc = initAssoc;
		EndAssoc = endAssoc;
	}
	
	public String getInitAssoc() {
		return initAssoc;
	}
	
	public void setInitAssoc(String initAssoc) {
		this.initAssoc = initAssoc;
	}
	
	public String getEndAssoc() {
		return EndAssoc;
	}
	
	public void setEndAssoc(String endAssoc) {
		EndAssoc = endAssoc;
	}
    
}
