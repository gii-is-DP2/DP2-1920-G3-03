package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "diet")
public class Diet extends BaseEntity{
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	private String type;
	
	@Min(0)
	private int kcal;
	
	@Min(0)
	private int proteins;
	
	@Min(0)
	private int fat;
	
	@Min(0)
	private int carbs;

	
	// Getters, Setters, Equals, HashCode, ToString:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carbs;
		result = prime * result + fat;
		result = prime * result + kcal;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + proteins;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diet other = (Diet) obj;
		if (carbs != other.carbs)
			return false;
		if (fat != other.fat)
			return false;
		if (kcal != other.kcal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proteins != other.proteins)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Diet [name=" + name + ", type=" + type + ", kcal=" + kcal + ", proteins=" + proteins + ", fat=" + fat
				+ ", carbs=" + carbs + "]";
	}
	
}
