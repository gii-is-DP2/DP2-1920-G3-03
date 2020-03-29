package org.springframework.samples.yogogym.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity{
	String username;
	String authority;
}
