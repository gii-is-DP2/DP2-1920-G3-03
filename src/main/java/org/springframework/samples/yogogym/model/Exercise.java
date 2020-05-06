
package org.springframework.samples.yogogym.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.Intensity;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
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
	@NotNull
	protected  Intensity	intensity;
	
	@Column(name = "body_part")
	@NotNull
	protected BodyParts bodyPart;

	@Column(name = "repetition_type")
	@NotNull
	protected RepetitionType repetitionType;
	
	//Relations:
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipment_id")
	@Valid
	protected Equipment equipment;
	
}
