package org.springframework.samples.yogogym.web.e2e;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ChallengeControllerE2ETest {

	private static final int testChallengeId1 = 1;
	private static final int testChallengeId2 = 2;
	private static final int testInscriptionId1 = 1;
	private static final int testClientId1 = 1;
	private static final String testClientUsername1 = "client1";

	@Autowired
	private MockMvc mockMvc;

	// Admin

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testListChallengesAdmin() throws Exception {
		mockMvc.perform(get("/admin/challenges")).andExpect(status().isOk())
				.andExpect(model().attributeExists("challenges"))
				.andExpect(view().name("admin/challenges/challengesList"));
	}

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testShowChallengeByIdAdminModifiable() throws Exception {
		mockMvc.perform(get("/admin/challenges/{challengeId}", 4)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge4"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 4"))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(30))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(4))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward2"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(4))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(40.0))))
				.andExpect(model().attribute("isModifiable", true))
				.andExpect(view().name("admin/challenges/challengeDetails"));
	}

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testShowChallengeByIdAdminNotModifiableWithInscription() throws Exception {

		mockMvc.perform(get("/admin/challenges/{challengeId}", testChallengeId2)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge2"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 2"))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(20))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(2))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward2"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(5))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(20.))))
				.andExpect(model().attributeDoesNotExist("isModifiable"))
				.andExpect(view().name("admin/challenges/challengeDetails"));
	}

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testInitCreateChallengeForm() throws Exception {
		mockMvc.perform(get("/admin/challenges/new")).andExpect(status().isOk())
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"))
				.andExpect(model().attributeExists("challenge"));
	}

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testProcessCreateChallengeForm() throws Exception {

		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test").param("description", "Test")
				.param("initialDate", "2090/01/01").param("endDate", "2090/01/02").param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
	}

}
