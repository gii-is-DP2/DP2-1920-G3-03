package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DashboardsAdminServiceTest {

	@Autowired
	protected DashboardsAdminService dashboardService;

	@Test
	void shouldCountEquipment() {
		List<Integer> count = this.dashboardService.countEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}

	@Test
	void shouldNameEquipment() {
		List<String> count = this.dashboardService.nameEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}

}
