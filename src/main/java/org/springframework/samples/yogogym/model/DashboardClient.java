package org.springframework.samples.yogogym.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "dashboardsClient")
public class DashboardClient extends BaseEntity{

}
