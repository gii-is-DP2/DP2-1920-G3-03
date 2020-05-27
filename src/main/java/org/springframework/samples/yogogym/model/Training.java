package org.springframework.samples.yogogym.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "trainings")
public class Training extends BaseEntity{
	
	@Column(name = "name")
	@NotBlank
	@Size(max=40)
	protected String name;
	
	@Column(name = "initialDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date initialDate;
	
	@Column(name = "endDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	protected Date endDate;
	
	@Column(name = "editingPermission")
	@NotNull
	protected EditingPermission editingPermission;
	
	@Column(name = "author")
	@NotBlank
	protected String author;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_id")
	@Valid
	protected Collection<Routine> routines;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diet_id")
	@Valid
	protected Diet diet;
	
	public void copyTrainingInfo(Training trainingToCopy) {
		if(trainingToCopy.getDiet()!=null) {
			this.setDiet(trainingToCopy.getDiet());
		}
		if(trainingToCopy.getRoutines()!=null) {
			Collection<Routine> routines = new ArrayList<>();
			for(Routine r : trainingToCopy.getRoutines()) {
				Routine nueva = new Routine();
				if(r.getRoutineLine()!=null) {
					Collection<RoutineLine> routinesLines = new ArrayList<>();
					for(RoutineLine rl : r.getRoutineLine()) {
						RoutineLine nuevaRl = new RoutineLine();
						nuevaRl.setExercise(rl.getExercise());
						nuevaRl.setReps(rl.getReps());
						nuevaRl.setSeries(rl.getSeries());
						nuevaRl.setTime(rl.getTime());
						nuevaRl.setWeight(rl.getWeight());
						routinesLines.add(nuevaRl);
					}
					nueva.setRoutineLine(routinesLines);
				}
				nueva.setDescription(r.getDescription());
				nueva.setName(r.getName());
				nueva.setRepsPerWeek(r.getRepsPerWeek());
				routines.add(nueva);
			}
			this.setRoutines(routines);
		}

	}
	
	public Boolean isEmpty() {
		return this.getDiet()==null && (this.getRoutines().isEmpty()||this.getRoutines()==null);
	}
}
