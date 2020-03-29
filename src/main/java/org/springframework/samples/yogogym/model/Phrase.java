package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "phrases")
public class Phrase extends BaseEntity{

	@Column(name = "text")
	@NotBlank
	protected String text;
	
	@Column(name = "author")
	@NotBlank
	protected String author;
	
}
