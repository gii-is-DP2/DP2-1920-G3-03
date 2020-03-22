package org.springframework.samples.yogogym.model.Enums;

import javax.persistence.Table;

@Table(name = "intensity")
public enum Intensity {
	LOW, MODERATED, INTENSE, VERY_INTENSE
}
