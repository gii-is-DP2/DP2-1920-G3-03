package org.springframework.samples.yogogym.model;

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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "messages")
public class Message extends BaseEntity{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@Valid
	protected User user;
	
	@Column(name = "content")
	@NotBlank
	@Size(max=256)
	protected String content;
	
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	protected Date createdAt;
	
	@Column(name = "edited")
	protected Boolean edited;
	
	@Column(name = "isParent")
	protected Boolean isParent;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "message_id")
	@Valid
	protected Collection<Message> answers;
	
}
