package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.samples.yogogym.model.Enums.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "inscriptions", indexes = { @Index(columnList = "status")})
public class Inscription extends BaseEntity{

	@NotNull
	@Column(name = "status")
	protected Status status;
	
	@Column(name = "url")
	@URL
	protected String url;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	protected Challenge challenge;	
}
