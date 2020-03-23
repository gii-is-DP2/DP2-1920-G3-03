package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Entity
@Data
@Table(name = "guilds")
public class Guild extends BaseEntity{
	
	@Column(name = "logo")
	@NotEmpty
	protected String logo;
	
	@Column(name = "name")
	@NotEmpty
	protected String name;
	
	@Column(name = "description")
	@NotEmpty
	protected String description;
	
	@Column(name = "creator")
	@NotEmpty
	protected String creator;
	
}
