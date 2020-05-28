package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class DashboardAdminControllerE2ETest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testInitAllDashboardEquipmentSuccessful() throws Exception {
		mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardEquipment"))
				.andExpect(model().attributeExists("hasEquipmentWeek", "hasEquipmentMonth"));
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testInitAllDashboardEquipmentFailedAuthority() throws Exception {
		mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().is4xxClientError());
	}
	
	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testInitDashboardChallengesExists() throws Exception {
		mockMvc.perform(get("/admin/dashboardChallenges?monthAndYear=2020-01")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardChallenges"))
				.andExpect(model().attribute("ChallengesExists", true))
				.andExpect(model().attribute("client", "Sofia Victoria Obeso"))
				.andExpect(model().attribute("guild", "Gym for Dummies"));
	}
	
	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testInitDashboardChallengesDontExist() throws Exception {
		mockMvc.perform(get("/admin/dashboardChallenges?monthAndYear=2020-02")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardChallenges"))
				.andExpect(model().attribute("ChallengesExists", false));
	}
	
	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testInitDashboardChallengesInscriptionsDontExist() throws Exception {
		mockMvc.perform(get("/admin/dashboardChallenges?monthAndYear=2020-10")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardChallenges"))
				.andExpect(model().attribute("ChallengesExists", true))
				.andExpect(model().attribute("NoCompletedChallenges", true));
	}

}
