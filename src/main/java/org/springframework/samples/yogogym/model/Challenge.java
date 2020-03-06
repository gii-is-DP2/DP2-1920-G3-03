package org.springframework.samples.yogogym.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseEntity{
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "reward")
	protected String reward;
	
	@Column(name = "start")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date start;
	
	@Column(name = "end")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected Date end;
	
	@Column(name = "status")
	protected StatusType status;

}
