package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
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
	@Range(min=1,max=20)
	protected Integer repsPerWeek;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "routine_id")
	protected Collection<RoutineLine> routineLine;	
	
}
