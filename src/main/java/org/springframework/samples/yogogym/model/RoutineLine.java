package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

@Entity
@Table(name = "routine_line")
public class RoutineLine extends BaseEntity{
	
	@Column(name = "reps")
	@Min(1)
	private int reps;
	
	@Column(name = "weight")
	@Min(0)
	private Double weight;
	
	
	//Relations:
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "exercise_id")
	@NotNull
	private Collection<Exercise> exercises;
	
	
	// Getters, Setters, Equals, HashCode, ToString:
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reps;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		RoutineLine other = (RoutineLine) obj;
		if (reps != other.reps)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoutineLine [reps=" + reps + ", weight=" + weight + "]";
	}
	
}
