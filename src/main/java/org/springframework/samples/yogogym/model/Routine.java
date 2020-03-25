package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "routines")
public class Routine extends BaseEntity{

	@Column(name = "name")
	@NotBlank
	protected String name;
	
	@Column(name = "description")
	@NotBlank
	protected String description;

	@Column(name="reps_per_week")
	@NotNull
	@Min(1)
	@Max(20)
	protected Integer repsPerWeek;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "routine_id")
	protected Collection<RoutineLine> routineLine;	
	
}
