
package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "equipments")
public class Equipment extends BaseEntity {

	@Column(name = "name")
	@NotBlank
	protected String	name;

	@Column(name = "location")
	protected String	location;

}
