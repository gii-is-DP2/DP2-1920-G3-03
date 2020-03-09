package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "routines")
public class Routine extends BaseEntity{

	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "description")
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "routine_id")
	private Collection<RoutineLine> routineLine;	
	
}
