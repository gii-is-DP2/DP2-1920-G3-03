package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "inscriptions")
public class Inscription extends BaseEntity{

	@Column(name = "status")
	protected Status status;
	
	@Column(name = "url")
	@NotEmpty
	protected String url;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	protected Challenge challenge;	
}
