package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class TrainingControllerE2ETest {

	@Autowired
	private MockMvc mockMvc;
	

//	@WithMockUser(username = "trainer1", authorities = { "trainer" })
//	@Test
//	void testListCopyTrainingSuccessful() throws Exception {
//		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
//				"trainer1", String.valueOf(6), String.valueOf(10))).andExpect(status().isOk())
//				.andExpect(view().name("trainer/trainings/listCopyTraining"));
//	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testListCopyTrainingFailedTrainingNotEmpty() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(1), String.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testListCopyTrainingFailedTrainingOtherUser() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingSuccess() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(10)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(1)).param("trainerUsername", "trainer1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingFailedPrivate() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(10)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(3)).param("trainerUsername", "trainer1"))
				.andExpect(status().isOk()).andExpect(view().name("exception"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingFailedTrainingNotEmpty() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(1), String.valueOf(1)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(2)).param("trainerUsername", "trainer1"))
				.andExpect(status().isOk()).andExpect(view().name("exception"));
	}

}
