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
public class DashboardClientControllerE2ETest {
	
	private static final String TEST_USERNAME = "client1";

	private static final String TEST_USERNAME2 = "client2";

	private static final Integer[] COUNT_BODY_PART_ALL = { 1, 4, 6 };

	private static final String[] NAME_BODY_PART_ALL = { "UPPER_TRUNK", "ARMS", "ALL" };

	private static final Integer[] COUNT_REPETITION_TYPE_ALL = { 6, 5 };

	private static final String[] NAME_REPETITION_TYPE_ALL = { "TIME", "TIME_AND_REPS" };
	
	private static final Integer SUM_KCAL_ALL = 1100;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
	@Test
	void testInitAllDashboardClientSuccessful() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-01", TEST_USERNAME)).andExpect(status().isOk())
				.andExpect(view().name("client/dashboards/dashboard"))
				.andExpect(model().attribute("hasBodyParts", true))
				.andExpect(model().attribute("hasRepetitionType", true))
				.andExpect(model().attribute("hasKcal", true))
				.andExpect(model().attribute("orderBodyParts", NAME_BODY_PART_ALL))
				.andExpect(model().attribute("countBodyParts", COUNT_BODY_PART_ALL))
				.andExpect(model().attribute("orderRepetitionType", NAME_REPETITION_TYPE_ALL))
				.andExpect(model().attribute("countRepetitionType", COUNT_REPETITION_TYPE_ALL))
				.andExpect(model().attribute("kcal", SUM_KCAL_ALL));
	}
	
	@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
	@Test
	void testInitEmptyDashboardClientSuccessful() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/dashboard?monthAndYear=2020-01", TEST_USERNAME2)).andExpect(status().isOk())
				.andExpect(view().name("client/dashboards/dashboard"))
				.andExpect(model().attribute("hasBodyParts", false))
				.andExpect(model().attribute("hasRepetitionType", false))
				.andExpect(model().attribute("hasKcal", false));
	}
	
	@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
	@Test
	void testInitAllDashboardClientFailedUser() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}

}
