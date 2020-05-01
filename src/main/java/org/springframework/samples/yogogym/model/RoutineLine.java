package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "routines_lines")
public class RoutineLine extends BaseEntity{
	
	@Column(name = "reps")
	@Min(1)
	protected Integer reps;
	
	@Column(name = "weight")
	@Min(0)
	@NotNull
	protected Double weight;
	
	@Column(name = "time")
	@Min(1)
	protected Double time;
	
	@Column(name = "series")
	@NotNull
	@Min(1)
	protected Integer series;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
	@JoinColumn(name = "exercise_id")
	@NotNull
	protected Exercise exercise;
}
