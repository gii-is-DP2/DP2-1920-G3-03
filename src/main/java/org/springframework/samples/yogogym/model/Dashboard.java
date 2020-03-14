package org.springframework.samples.yogogym.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "dashboards")
public class Dashboard extends BaseEntity{
	
	@Column(name = "listNameEquipment")
	protected String[] listNameEquipment;
	
	@Column(name = "listCountEquipment")
	protected Integer[] listCountEquipment;

}
