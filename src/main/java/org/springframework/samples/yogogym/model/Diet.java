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

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getKcal() {
		return this.kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getProtein() {
		return this.protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
	public int getFat() {
		return this.fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public int getCarb() {
		return this.carb;
	}
	public void setCarb(int carb) {
		this.carb = carb;
	}
}
