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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ClientControllerE2ETest {

	private static final String TRAINER1_USERNAME = "trainer1";
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT3_ID = 3;
	
	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testFindMyClientsTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients", TRAINER1_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("trainer"))
		.andExpect(view().name("trainer/clients/clientsList"));
	}

	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testShowMyClientTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}", TRAINER1_USERNAME, CLIENT1_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("client"))
		.andExpect(view().name("trainer/clients/clientsDetails"));
	}
	
	@WithMockUser(value = "trainer1", authorities = {"trainer"})
	@Test
	void testShouldNotShowNotMyClientTrainer() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}", TRAINER1_USERNAME, CLIENT3_ID)).andExpect(status().isOk())
		.andExpect(model().attributeDoesNotExist("client"))
		.andExpect(view().name("exception"));
	}

}
