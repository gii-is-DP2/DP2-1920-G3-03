/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.yogogym.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "clients")
public class Client extends Person {
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	protected User user;
	
	@Column(name="weight")
	@Min(0)
	protected Double weight;
	
	@Column(name="height")
	@Min(0)
	protected Double height;

	@Column(name="age")
	@Min(0)
	protected Integer age;

	@Column(name="fatPercentage")
	@Min(0)
	protected Double fatPercentage;
	
	@Column(name="isPublic")
	protected Boolean isPublic;

	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL)
	protected Collection<Training> trainings;

	@ManyToOne(cascade = CascadeType.ALL)
	protected Guild guild;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<Inscription> inscriptions;
	
	
	protected List<Inscription> getInscriptionsInternal() {
		if (this.inscriptions == null) {
			this.inscriptions = new ArrayList<>();
		}
		return this.inscriptions;
	}

	public void addInscription(Inscription inscription) {
		List<Inscription> l = getInscriptionsInternal();
		l.add(inscription);
	}	
}
