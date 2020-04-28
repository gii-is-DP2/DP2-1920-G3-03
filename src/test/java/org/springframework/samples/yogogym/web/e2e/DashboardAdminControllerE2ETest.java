package org.springframework.samples.yogogym.web.e2e;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
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
	void testInitAllDashboardChallenges() throws Exception {
		mockMvc.perform(get("/admin/dashboardChallenges/0")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardChallenges"))
				.andExpect(model().attributeExists("ChallengesExists"));
	}	

}
