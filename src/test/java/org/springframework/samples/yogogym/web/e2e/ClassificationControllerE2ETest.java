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
public class ClassificationControllerE2ETest {
	
	private static final String TEST_USERNAME_CLIENT1 = "client1";
	
	private static final String TEST_USERNAME_CLIENT3 = "client3";
	
	private static final Integer[] POINT = {20, 20, 10 };

	private static final String[] CLIENT = {"client6","client7","client3"};
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username = TEST_USERNAME_CLIENT3, authorities = { "client" })
	@Test
	void testInitAllClasificationSuccessful() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME_CLIENT3)).andExpect(status().isOk())
				.andExpect(view().name("client/clasifications/clasification"))
				.andExpect(model().attribute("hasChallenge", true))
				.andExpect(model().attribute("hasPositionAll", true))
				.andExpect(model().attribute("hasChallengeClasificationAll", true))
				.andExpect(model().attribute("totalPoint", 10))
				.andExpect(model().attribute("positionAll", 3))
				.andExpect(model().attribute("orderPointAll", POINT))
				.andExpect(model().attribute("orderUserAll", CLIENT));
	}
	
	@WithMockUser(username = TEST_USERNAME_CLIENT1, authorities = { "client" })
	@Test
	void testInitAllClasificationEmptySuccessful() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME_CLIENT1)).andExpect(status().isOk())
				.andExpect(view().name("client/clasifications/clasification"))
				.andExpect(model().attribute("hasChallenge", false))
				.andExpect(model().attribute("hasChallengeClasificationAll", true))
				.andExpect(model().attribute("orderPointAll", POINT))
				.andExpect(model().attribute("orderUserAll", CLIENT));
	}
	
	@WithMockUser(username = TEST_USERNAME_CLIENT1, authorities = { "client" })
	@Test
	void testInitAllClasificationFailedOtherUser() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME_CLIENT3)).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}
}
