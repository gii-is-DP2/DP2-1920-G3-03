package org.springframework.samples.yogogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "phrases")
public class Phrase extends BaseEntity{

	@Column(name = "text")
	@NotBlank
	protected String text;
	
	@Column(name = "author")
	@NotBlank
	protected String author;
	
}
