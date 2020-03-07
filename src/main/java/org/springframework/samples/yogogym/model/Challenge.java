package org.springframework.samples.yogogym.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "challenges")
public class Challenge extends BaseEntity{
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "reward")
	protected String reward;
	
	@Column(name = "points")
	protected String points;
	
	@Column(name = "reps")
	protected String reps;
	
	@Column(name = "weight")
	protected String weight;
	
	@Column(name = "start")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date start;
	
	@Column(name = "end")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date end;
	
	// Relations
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;

	/*
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getReps() {
		return reps;
	}

	public void setReps(String reps) {
		this.reps = reps;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((reps == null) ? 0 : reps.hashCode());
		result = prime * result + ((reward == null) ? 0 : reward.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Challenge other = (Challenge) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (reps == null) {
			if (other.reps != null)
				return false;
		} else if (!reps.equals(other.reps))
			return false;
		if (reward == null) {
			if (other.reward != null)
				return false;
		} else if (!reward.equals(other.reward))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
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
		return "Challenge [description=" + description + ", reward=" + reward + ", points=" + points + ", reps=" + reps
				+ ", weight=" + weight + ", start=" + start + ", end=" + end + ", exercise=" + exercise + "]";
	}
	*/
	

}
