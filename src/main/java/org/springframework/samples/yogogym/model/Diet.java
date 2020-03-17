package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
	// @NotEmpty
	protected DietType type;

	@Column(name = "kcal")
	@Min(0)
	protected Integer kcal;
	
	@Column(name = "protein")
	@Min(0)
	protected Integer protein;
	
	@Column(name = "fat")
	@Min(0)
	protected Integer fat;
	
	@Column(name = "carb")
	@Min(0)
	protected Integer carb;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	protected Collection<Food> foods;

}
