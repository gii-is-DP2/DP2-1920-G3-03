package org.springframework.samples.yogogym.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "trainings")
public class Training extends BaseEntity{
	
	@Column(name = "initialDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date initialDate;
	
	@Column(name = "endDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date endDate;
	
	@Column(name = "name")
	@NotBlank
	protected String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "routine_id")
	protected Collection<Routine> routines;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diet_id")
	protected Diet diet;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "client_id")
	protected Client client;
}
