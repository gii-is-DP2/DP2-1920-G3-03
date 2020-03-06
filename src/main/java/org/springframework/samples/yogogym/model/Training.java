package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "trainings")
public class Training extends BaseEntity{
	
	@Column(name = "name")
	protected String name;
	
	@Column(name = "description")
	protected String description;
	
}
