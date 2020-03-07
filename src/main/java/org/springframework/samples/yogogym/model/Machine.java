
package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "machines")
public class Machine extends BaseEntity {

	@Column(name = "name")
	@NotBlank
	private String	name;

	@Column(name = "location")
	private String	location;

}
