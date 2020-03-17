
package org.springframework.samples.yogogym.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;

import lombok.Data;

@Entity
@Data
@Table(name = "exercises")
public class Exercise extends BaseEntity {

	@Column(name = "name")
	@NotBlank
	protected String		name;

	@Column(name = "description")
	@NotBlank
	protected  String		description;

	@Column(name = "kcal")
	@Min(0)
	protected  Integer			kcal;

	@Column(name = "intensity")
	protected  Intensity	intensity;
	
	@Column(name = "body_part")
	protected BodyParts bodyPart;

	@Column(name = "repetition_type")
	protected RepetitionType repetitionType;
	
	//Relations:
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipment_id")
	protected Equipment equipment;
	
}
