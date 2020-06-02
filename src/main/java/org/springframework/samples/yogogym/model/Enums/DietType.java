package org.springframework.samples.yogogym.model.Enums;

import javax.persistence.Table;

@Table(name = "dietType")
public enum DietType {
	VOLUME, DEFINITION, EXTREME_DEFINITION, MAINTENANCE, AUTO_ASSIGN
}
