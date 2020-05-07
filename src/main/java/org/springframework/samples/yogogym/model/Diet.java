package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.yogogym.model.Enums.DietType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "diets")
public class Diet extends BaseEntity{
	
	@Column(name = "name")
	@NotBlank
	protected String name;
	
	@Column(name = "description")
	@NotBlank
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
	@Min(0)
	protected Integer protein;
	
	@Column(name = "fat")
	@NotNull
	@Min(0)
	protected Integer fat;
	
	@Column(name = "carb")
	@NotNull
	@Min(0)
	protected Integer carb;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	protected Collection<Food> foods;

}
