package org.springframework.samples.yogogym.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "forums")
public class Forum extends BaseEntity{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guild_id")
	@Valid
	@NotNull
	protected Guild guild;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "forum_id")
	@Valid
	@NotNull
	protected Collection<Message> messages;

}
