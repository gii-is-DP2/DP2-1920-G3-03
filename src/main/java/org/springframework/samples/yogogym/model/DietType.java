package org.springframework.samples.yogogym.model;

import javax.persistence.Table;

@Table(name = "dietType")
public enum DietType {
	VOLUME, DEFINITION, EXTREME_DEFINITION, MAINTENANCE
}
