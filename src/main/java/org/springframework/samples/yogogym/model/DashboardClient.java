package org.springframework.samples.yogogym.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "dashboardsClient")
public class DashboardClient extends BaseEntity{

}
