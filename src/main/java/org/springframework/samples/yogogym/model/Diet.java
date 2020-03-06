package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diets")
public class Diet extends BaseEntity{
	
	@Column(name = "type")
	protected String type;
	
	@Column(name = "kcal")
	protected int kcal;
	
	@Column(name = "protein")
	protected int protein;
	
	@Column(name = "fat")
	protected int fat;
	
	@Column(name = "carb")
	protected int carb;

}
