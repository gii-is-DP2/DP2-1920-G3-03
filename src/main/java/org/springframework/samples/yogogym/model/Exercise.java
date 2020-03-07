package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity{

	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "description")
	@NotBlank
	private String description;
	
	@Column(name = "kcal")
	@Min(0)
	private int kcal;
	
	@Column(name = "intensity")
	private Intensity intensity;
	
	
	//Relations:
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private Machine machine;

	
	// Getters, Setters, Equals, HashCode, ToString:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((intensity == null) ? 0 : intensity.hashCode());
		result = prime * result + kcal;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Exercise other = (Exercise) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (intensity != other.intensity)
			return false;
		if (kcal != other.kcal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exercise [name=" + name + ", description=" + description + ", kcal=" + kcal + ", intensity=" + intensity
				+ "]";
	}
	
}