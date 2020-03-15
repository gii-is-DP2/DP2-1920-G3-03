package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.URL;
import org.springframework.samples.yogogym.model.Enums.Status;

import lombok.Data;

@Entity
@Data
@Table(name = "inscriptions")
public class Inscription extends BaseEntity{

	@Column(name = "status")
	protected Status status;
	
	@Column(name = "url")
	@URL
	protected String url;
	
	@Column(name = "submitted")
	protected boolean submitted;
	

	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	protected Challenge challenge;	
}
