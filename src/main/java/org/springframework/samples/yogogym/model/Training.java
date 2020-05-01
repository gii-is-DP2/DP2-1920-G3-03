package org.springframework.samples.yogogym.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "trainings")
public class Training extends BaseEntity{
	
	@Column(name = "name")
	@NotBlank
	@Size(max=40)
	protected String name;
	
	@Column(name = "initialDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date initialDate;
	
	@Column(name = "endDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date endDate;
	
	@Column(name = "editingPermission")
	@NotNull
	protected EditingPermission editingPermission;
	
	@Column(name = "author")
	@NotBlank
	protected String author;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_id")
	protected Collection<Routine> routines;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diet_id")
	protected Diet diet;
}
