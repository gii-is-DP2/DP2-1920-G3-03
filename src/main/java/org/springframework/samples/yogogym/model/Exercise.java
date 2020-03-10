
package org.springframework.samples.yogogym.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "exercises")
public class Exercise extends BaseEntity {

	@Column(name = "name")
	@NotBlank
	private String		name;

	@Column(name = "description")
	@NotBlank
	private String		description;

	@Column(name = "kcal")
	@Min(0)
	private int			kcal;

	@Column(name = "intensity")
	private Intensity	intensity;

	//Relations:
	@ManyToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "machine_id")
  private Machine machine;
