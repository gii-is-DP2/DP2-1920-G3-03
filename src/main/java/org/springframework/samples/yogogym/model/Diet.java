package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.yogogym.model.Enums.DietType;

import lombok.Data;

@Entity
@Data
@Table(name = "diets")
public class Diet extends BaseEntity{
	
	@Column(name = "name")
	@NotEmpty
	protected String name;
	
	@Column(name = "description")
	@NotEmpty
	protected String description;
	
	@Column(name = "type")
	// @NotNull
	protected DietType type;

	@Column(name = "kcal")
	@NotNull
	@Min(0)
	protected Integer kcal;
	
	@Column(name = "protein")
	@NotNull
	protected Integer protein;
	
	@Column(name = "fat")
	@NotNull
	protected Integer fat;
	
	@Column(name = "carb")
	@NotNull
	protected Integer carb;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	protected Collection<Food> foods;

}
