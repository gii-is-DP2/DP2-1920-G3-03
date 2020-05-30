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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ChallengeControllerE2ETest {

	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_2 = 2;
	private static final int CHALLENGE_ID_4 = 4;
	private static final int CHALLENGE_ID_6 = 6;
	
	private static final String CLIENT_USERNAME_1 = "client1";
	private static final String EMPTY_STRING = "";
	private static final String EXCEPTION = "exception";
	
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
		
		mockMvc.perform(get("/admin/challenges/{challengeId}", CHALLENGE_ID_4)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge4"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 4"))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(30))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(CHALLENGE_ID_4))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward2"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(4))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(40.0))))
				.andExpect(model().attribute("isModifiable", true))
				.andExpect(view().name("admin/challenges/challengeDetails"));
	}

	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testShowChallengeByIdAdminNotModifiableWithInscription() throws Exception {

		mockMvc.perform(get("/admin/challenges/{challengeId}", CHALLENGE_ID_2)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge2"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 2"))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(20))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(CHALLENGE_ID_2))))
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

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testProcessCreateChallengeForm() throws Exception {

		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test").param("description", "Test")
				.param("initialDate", "2090/01/01").param("endDate", "2090/01/02").param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessCreateChallengeHasErrorEmptyParameters() throws Exception {
		mockMvc.perform(post("/admin/challenges/new")
				.with(csrf())
				.param("name", EMPTY_STRING)
				.param("description", EMPTY_STRING)
				.param("initialDate", EMPTY_STRING)
				.param("endDate", EMPTY_STRING)
				.param("points", EMPTY_STRING)
				.param("reward",EMPTY_STRING)
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("challenge"))
				.andExpect(model().attributeHasFieldErrors("challenge", "name"))
				.andExpect(model().attributeHasFieldErrors("challenge", "description"))
				.andExpect(model().attributeHasFieldErrors("challenge", "initialDate"))
				.andExpect(model().attributeHasFieldErrors("challenge", "endDate"))
				.andExpect(model().attributeHasFieldErrors("challenge", "points"))
				.andExpect(model().attributeHasFieldErrors("challenge", "reward"))
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessCreateChallengeHasErrorInitialInPast() throws Exception {
		
		mockMvc.perform(post("/admin/challenges/new")
				.with(csrf())
				.param("name", "Test")
				.param("description", "Test")
				.param("initialDate", "2010/01/02")
				.param("endDate", "2090/01/01")
				.param("points", "10")
				.param("reward","Reward")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("challenge"))
				.andExpect(model().attributeHasFieldErrors("challenge", "initialDate"))
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessCreateChallengeHasErrorEndBeforeInitial() throws Exception {
		
		mockMvc.perform(post("/admin/challenges/new")
				.with(csrf())
				.param("name", "Test")
				.param("description", "Test")
				.param("initialDate", "2090/01/02")
				.param("endDate", "2090/01/01")
				.param("points", "10")
				.param("reward","Reward")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("challenge"))
				.andExpect(model().attributeHasFieldErrors("challenge", "endDate"))
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testProcessCreateChallengeFormErrorSameName() throws Exception {

		String name = "TestSameName";
		String intialDate = "2080/01/01";
		String endDate = "2080/01/02";
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", name).param("description", "Test")
				.param("initialDate", intialDate).param("endDate", endDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", name).param("description", "Test")
				.param("initialDate", intialDate).param("endDate", endDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().isOk()).andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testProcessCreateChallengeFormErrorMore3SameWeek() throws Exception {
		
		String sameInitialDate = "2070/01/01";
		String sameEndDate = "2070/01/02";
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 1").param("description", "Test")
				.param("initialDate", sameInitialDate).param("endDate", sameEndDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 2").param("description", "Test")
				.param("initialDate", sameInitialDate).param("endDate", sameEndDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 3").param("description", "Test")
				.param("initialDate", sameInitialDate).param("endDate", sameEndDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 4").param("description", "Test")
				.param("initialDate", sameInitialDate).param("endDate", sameEndDate).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().isOk()).andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testInitUpdateChallengeForm() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_4))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"))
		.andExpect(model().attributeExists("challenge"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testInitUpdateChallengeFormWithInscriptions() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_1))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/challenges/challengeDetails"))
		.andExpect(model().attributeExists("challenge"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessUpdateChallengeForm() throws Exception
	{						
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_6)
			.with(csrf())
			.param("name", "Test Update")
			.param("description", "Test")
			.param("initialDate", "2090/01/01")
			.param("endDate", "2090/01/02")
			.param("points", "10")
			.param("reward","Reward")
			.param("reps","10")
			.param("weight","10.")
			.param("exercise.id","1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testProcessUpdateChallengeFormHasErrorsSameName() throws Exception {
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_4)
				.with(csrf())
				.param("name", "Challenge3")
				.param("description", "Test")
				.param("initialDate", "2020/10/10")
				.param("endDate", "2020/10/15")
				.param("points", "10")
				.param("reward","Reward")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("challenge", "name"))
			.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
		
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testProcessUpdateChallengeFormHasErrorsMore3() throws Exception {
		
		String sameDateInitial = "2075/01/01";
		String sameDateEnd = "2075/01/02";
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 1").param("description", "Test")
				.param("initialDate", sameDateInitial).param("endDate", sameDateEnd).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 2").param("description", "Test")
				.param("initialDate", sameDateInitial).param("endDate", sameDateEnd).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/new").with(csrf()).param("name", "Test More 3 3").param("description", "Test")
				.param("initialDate", sameDateInitial).param("endDate", sameDateEnd).param("points", "10")
				.param("reward", "Reward").param("reps", "10").param("weight", "10.").param("exercise.id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/challenges"));
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_4)
				.with(csrf())
				.param("name", "Test More 3 4")
				.param("description", "Test")
				.param("initialDate", sameDateEnd)
				.param("endDate", sameDateEnd)
				.param("points", "10")
				.param("reward","Reward")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("challenge", "initialDate"))
			.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
		
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessUpdateChallengeHasErrorEmptyParameters() throws Exception {
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",CHALLENGE_ID_4)
				.with(csrf())
				.param("name", "")
				.param("description", "")
				.param("initialDate", "")
				.param("endDate", "")
				.param("points", "")
				.param("reward","")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("challenge"))
				.andExpect(model().attributeHasFieldErrors("challenge", "name"))
				.andExpect(model().attributeHasFieldErrors("challenge", "description"))
				.andExpect(model().attributeHasFieldErrors("challenge", "initialDate"))
				.andExpect(model().attributeHasFieldErrors("challenge", "endDate"))
				.andExpect(model().attributeHasFieldErrors("challenge", "points"))
				.andExpect(model().attributeHasFieldErrors("challenge", "reward"))
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testDeleteChallenge() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/delete",CHALLENGE_ID_4))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testDeleteChallengeErrorInscriptionsExist() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/delete",CHALLENGE_ID_1))
		.andExpect(status().isOk())
		.andExpect(view().name(EXCEPTION));
	}
	
	
	// CLIENT:
	
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testListChallengesClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/challenges", CLIENT_USERNAME_1)).andExpect(status().isOk()).andExpect(model().attributeExists("challenges"))
				.andExpect(view().name("client/challenges/challengesList"));
		}
		
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testShowChallengeByIdClient() throws Exception {

			mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}",CLIENT_USERNAME_1, CHALLENGE_ID_4)).andExpect(status().isOk())
					.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge4"))))
					.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 4"))))
					.andExpect(model().attribute("challenge", hasProperty("points", is(30))))
					.andExpect(model().attribute("challenge", hasProperty("id", is(CHALLENGE_ID_4))))
					.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward2"))))
					.andExpect(model().attribute("challenge", hasProperty("reps", is(4))))
					.andExpect(model().attribute("challenge", hasProperty("weight", is(40.))))
					.andExpect(view().name("client/challenges/challengeDetails"));
		}
		
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testShowChallengeByIdClientAlreadyInscribed() throws Exception {

			mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}",CLIENT_USERNAME_1, CHALLENGE_ID_1)).andExpect(status().isOk())
					.andExpect(view().name(EXCEPTION));
		}
		
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testListMyChallengesClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/challenges/mine", CLIENT_USERNAME_1)).andExpect(status().isOk()).andExpect(model().attributeExists("inscriptions"))
				.andExpect(view().name("client/challenges/myChallengesList"));
		}
		
		@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testShowAndEditMyChallengeByIdClient() throws Exception {

			mockMvc.perform(get("/client/{clientUsername}/challenges/mine/{challengeId}",CLIENT_USERNAME_1, CHALLENGE_ID_1)).andExpect(status().isOk())
			.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge1"))))
			.andExpect(model().attribute("challenge", hasProperty("description", is("Desc challenge 1"))))
			.andExpect(model().attribute("challenge", hasProperty("points", is(10))))
			.andExpect(model().attribute("challenge", hasProperty("id", is(CHALLENGE_ID_1))))
			.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward1"))))
			.andExpect(model().attribute("challenge", hasProperty("reps", is(10))))
			.andExpect(model().attribute("challenge", hasProperty("weight", is(10.))))
			.andExpect(view().name("client/challenges/myChallengeDetailsAndUpdate"));
		}
		
		@WithMockUser(value = "client1", authorities = {"client"})
		@Test
		void testShowAndEditNotHisChallengeByIdClient() throws Exception {

			mockMvc.perform(get("/client/{clientUsername}/challenges/mine/{challengeId}",CLIENT_USERNAME_1, CHALLENGE_ID_4)).andExpect(status().isOk())
			.andExpect(view().name(EXCEPTION));
		}
		
		@WithMockUser(value = "client2", authorities = {"client"})
		@ParameterizedTest
		@ValueSource(strings = {"/client/{clientUsername}/challenges", "/client/{clientUsername}/challenges/{challengeId}", 
								"/client/{clientUsername}/challenges/mine", "/client/{clientUsername}/challenges/mine/{challengeId}"})
		void testWrongClient(String path) throws Exception {
			
			if(path.contains("challengeId")) {
				mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}",CLIENT_USERNAME_1, CHALLENGE_ID_1)).andExpect(status().isOk())
				.andExpect(view().name(EXCEPTION));
			}
			else {
				mockMvc.perform(get(path, CLIENT_USERNAME_1)).andExpect(status().isOk())
				.andExpect(view().name(EXCEPTION));
			}
		}

}
