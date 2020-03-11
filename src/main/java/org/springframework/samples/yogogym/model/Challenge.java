package org.springframework.samples.yogogym.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "challenges")
public class Challenge extends BaseEntity{
	
	@Column(name = "name")
	@NotEmpty
	protected String name;
	
	@Column(name = "description")
	@NotEmpty
	protected String description;
	
	@Column(name = "reward")
	protected String reward;
	
	@Column(name = "points")
	@Min(1)
	protected Integer points;
	
	@Column(name = "reps")
	@Min(1)
	protected Integer reps;
	
	@Column(name = "weight")
	@Min(0)
	protected Double weight;
	
	@Column(name = "initial_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date initialDate;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date endDate;
	
	// Relations
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exercise_id")
	protected Exercise exercise;
}
