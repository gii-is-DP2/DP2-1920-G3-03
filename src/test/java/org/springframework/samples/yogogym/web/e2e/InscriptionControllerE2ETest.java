package org.springframework.samples.yogogym.web.e2e;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class InscriptionControllerE2ETest {

	private static final int INSCRIPTION_ID_1 = 1;
	private static final int INSCRIPTION_ID_2 = 2;
	private static final int INSCRIPTION_ID_4 = 4;
	private static final int INSCRIPTION_ID_6 = 6;
	
	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_4 = 4;
	
	private static final String CLIENT_USERNAME_1 = "client1";
	private static final String CLIENT_USERNAME_2 = "client2";
	
	
	@Autowired
	private MockMvc mockMvc;

	// Admin

	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testListSubmittedInscriptionsAdmin() throws Exception {
		mockMvc.perform(get("/admin/inscriptions/submitted")).andExpect(status().isOk())
			.andExpect(model().attributeExists("clients"))
			.andExpect(view().name("admin/challenges/submittedInscriptionsList"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testShowSubmittedInscriptionByIdAdmin() throws Exception {
		
		mockMvc.perform(get("/admin/inscriptions/submitted/{inscriptionId}", INSCRIPTION_ID_6)).andExpect(status().isOk())
				.andExpect(model().attribute("inscription", hasProperty("id", is(INSCRIPTION_ID_6))))
				.andExpect(model().attribute("inscription", hasProperty("status", is(Status.SUBMITTED))))
				.andExpect(view().name("admin/challenges/submittedChallengeDetails"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testShowSubmittedInscriptionByIdAdminNotSubmitted() throws Exception {
		
		mockMvc.perform(get("/admin/inscriptions/submitted/{inscriptionId}", INSCRIPTION_ID_1)).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testEvaluateChallengeAdmin() throws Exception
	{						
		
		mockMvc.perform(post("/admin/challenges/submitted/{challengeId}/inscription/{inscriptionId}/evaluate",CHALLENGE_ID_4,INSCRIPTION_ID_4)
			.with(csrf())
			.param("status", String.valueOf(Status.COMPLETED)))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/admin/inscriptions/submitted"));
	}
	
	// CLIENT:

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "client2", authorities = { "client" })
	@Test
	void testcreateInscriptionByChallengeId() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}/inscription/create", CLIENT_USERNAME_2,CHALLENGE_ID_4))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/client/" + CLIENT_USERNAME_2 + "/challenges"));
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "client2", authorities = { "client" })
	@Test
	void testsubmitInscription() throws Exception {

		mockMvc.perform(post("/client/{clientUsername}/challenges/{challengeId}/inscription/{inscriptionId}/submit",
				CLIENT_USERNAME_2, CHALLENGE_ID_4, INSCRIPTION_ID_2).with(csrf()).param("url", "https://test.com"))
				.andExpect(status().is3xxRedirection()).andExpect(view()
				.name("redirect:/client/" + CLIENT_USERNAME_2 + "/challenges/mine/" + 4));
	}

	@WithMockUser(value = "client2", authorities = { "client" })
	@Test
	void testWrongClient() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}/inscription/create", CLIENT_USERNAME_1,CHALLENGE_ID_1))
			.andExpect(view().name("exception"));

		mockMvc.perform(post("/client/{clientUsername}/challenges/{challengeId}/inscription/{inscriptionId}/submit",
				CLIENT_USERNAME_1, CHALLENGE_ID_1, INSCRIPTION_ID_1).with(csrf()).param("url", "https://test.com"))
				.andExpect(view().name("exception"));
	}

}
