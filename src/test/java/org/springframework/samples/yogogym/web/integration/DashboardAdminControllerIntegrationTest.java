package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.web.DashboardsAdminController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashboardAdminControllerIntegrationTest {

	@Autowired
	private DashboardsAdminController dashboardsAdminController;

	@Test
	void InitEquipmentDashboard() throws Exception {
		ModelMap model = new ModelMap();
		String view = this.dashboardsAdminController.getDashboardEquipment(model);
		assertEquals(view, "admin/dashboards/dashboardEquipment");
	}

	@Test
	void InitChallengeDashboard() throws Exception {
		int month = 1;
		ModelMap model = new ModelMap();
		String view = this.dashboardsAdminController.getDashboardChallenges(month, model);
		assertEquals(view, "admin/dashboards/dashboardChallenges");
	}

	@Test
	void InitGeneralDashboard() throws Exception {
		ModelMap model = new ModelMap();
		String view = this.dashboardsAdminController.getDashboardGeneral(model);
		assertEquals(view, "admin/dashboards/dashboardGeneral");
	}

}
