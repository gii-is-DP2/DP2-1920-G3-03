package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "foods")
public class Food extends BaseEntity{
	
	@Column(name = "name")
	@NotEmpty
	protected String name;
		
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
	
	@Column(name = "weight")
	@Min(0)
	protected Integer weight;
	
	@Column(name = "food_type")
	@NotNull
	protected FoodType foodType;
}