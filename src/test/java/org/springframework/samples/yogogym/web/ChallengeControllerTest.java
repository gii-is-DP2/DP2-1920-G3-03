package org.springframework.samples.yogogym.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.samples.yogogym.service.exceptions.ChallengeMore3Exception;
import org.springframework.samples.yogogym.service.exceptions.ChallengeSameNameException;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ChallengeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ChallengeControllerTest {
	
	
	private static final int testChallengeId1 = 1;
	private static final int testChallengeId2 = 2;
	private static final int testInscriptionId1 = 1;
	private static final int testClientId1 = 1;
	private static final String testClientUsername1 = "client1";
	
	private static final Calendar testInitialDate = Calendar.getInstance();
	private static final Calendar testEndDate = Calendar.getInstance();
	
	
	@MockBean
	private ChallengeService challengeService;
	
	@MockBean
	private ExerciseService exerciseService;
	
	@MockBean
	private InscriptionService inscriptionService;
	
	@MockBean
	private ClientService clientService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp()
	{	
		
		testEndDate.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = testInitialDate.getTime();
		Date endDate = testEndDate.getTime();
		
		//Challenge 1:
		Challenge challenge1 = createChallenge(testChallengeId1, "Challenge1 Name Test", initialDate, endDate);
		
		given(this.challengeService.findChallengeById(testChallengeId1)).willReturn(challenge1);
		
		// Challenge 2 (With Inscription):
		Challenge challenge2 = createChallenge(testChallengeId2, "Challenge2 Name Test", initialDate, endDate);
		
		Inscription inscription1 = new Inscription();
		inscription1.setChallenge(challenge2);
		inscription1.setId(testInscriptionId1);
		inscription1.setStatus(Status.PARTICIPATING);
		
		Client client1 = new Client();
		User userClient1 = new User();
		userClient1.setUsername(testClientUsername1);
		userClient1.setEnabled(true);
		client1.setUser(userClient1);
		client1.setId(testClientId1);
		client1.addInscription(inscription1);
		
		List<Inscription> inscriptions = new ArrayList<Inscription>();
		inscriptions.add(inscription1);
		
		given(this.challengeService.findChallengeById(testChallengeId2)).willReturn(challenge2);
		given(this.inscriptionService.findInscriptionByInscriptionId(testInscriptionId1)).willReturn(inscription1);
		given(this.inscriptionService.findInscriptionByClientAndChallenge(client1, challenge2)).willReturn(inscription1);
		given(this.inscriptionService.findInscriptionsByChallengeId(testChallengeId2)).willReturn(inscriptions);
		given(this.clientService.findClientById(testClientId1)).willReturn(client1);
		given(this.clientService.findClientByUsername(testClientUsername1)).willReturn(client1);
	}
	
	// ADMIN:
	
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testListChallengesAdmin() throws Exception {
		mockMvc.perform(get("/admin/challenges")).andExpect(status().isOk()).andExpect(model().attributeExists("challenges"))
			.andExpect(view().name("admin/challenges/challengesList"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testShowChallengeByIdAdmin() throws Exception {
		
		mockMvc.perform(get("/admin/challenges/{challengeId}", testChallengeId1)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge1 Name Test"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Challenge Description Test"))))
				.andExpect(model().attribute("challenge", hasProperty("initialDate", is(testInitialDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("endDate", is(testEndDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(1))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward Test"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(100.))))
				.andExpect(model().attribute("isModifiable", true))
				.andExpect(view().name("admin/challenges/challengeDetails"));
	}
	
	@WithMockUser(value = "admin1", authorities = { "admin" })
	@Test
	void testShowChallengeByIdAdminWithInscription() throws Exception {

		mockMvc.perform(get("/admin/challenges/{challengeId}", testChallengeId2)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge2 Name Test"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Challenge Description Test"))))
				.andExpect(model().attribute("challenge",
						hasProperty("initialDate", is(testInitialDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("endDate", is(testEndDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(2))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward Test"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(100.))))
				.andExpect(view().name("admin/challenges/challengeDetails"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testInitCreateChallengeForm() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"))
		.andExpect(model().attributeExists("challenge"));
	}

	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessCreateChallengeForm() throws Exception
	{						
		
		mockMvc.perform(post("/admin/challenges/new")
			.with(csrf())
			.param("name", "Test")
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
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessCreateChallengeHasErrorEmptyParameters() throws Exception {
		mockMvc.perform(post("/admin/challenges/new")
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
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testProcessChallengeCreationFormHasErrorsSameName() throws Exception {
		
		doThrow(new ChallengeSameNameException()).when(this.challengeService).saveChallenge(Mockito.any(Challenge.class));
		
		mockMvc.perform(post("/admin/challenges/new")
				.with(csrf())
				.param("name", "Test")
				.param("description", "Test")
				.param("initialDate", "2090/01/01")
				.param("endDate", "2090/01/02")
				.param("points", "10")
				.param("reward","Reward")
				.param("reps","10")
				.param("weight","10.")
				.param("exercise.id","1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("challenge", "name"))
			.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
		
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testProcessChallengeCreationFormHasErrorsMore3() throws Exception {
		
		doThrow(new ChallengeMore3Exception()).when(this.challengeService).saveChallenge(Mockito.any(Challenge.class));
		
		mockMvc.perform(post("/admin/challenges/new")
				.with(csrf())
				.param("name", "Test")
				.param("description", "Test")
				.param("initialDate", "2090/01/01")
				.param("endDate", "2090/01/02")
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
	void testInitUpdateChallengeForm() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/edit",testChallengeId1))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"))
		.andExpect(model().attributeExists("challenge"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testInitUpdateChallengeFormWithInscriptions() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/edit",testChallengeId2))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/challenges/challengeDetails"))
		.andExpect(model().attributeExists("challenge"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessUpdateChallengeForm() throws Exception
	{						
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",testChallengeId1)
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
		
		doThrow(new ChallengeSameNameException()).when(this.challengeService).saveChallenge(Mockito.any(Challenge.class));
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",testChallengeId1)
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
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("challenge", "name"))
			.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
		
	}
	
	@WithMockUser(username="admin1", authorities= {"admin"})
	@Test
	void testProcessUpdateChallengeFormHasErrorsMore3() throws Exception {
		
		doThrow(new ChallengeMore3Exception()).when(this.challengeService).saveChallenge(Mockito.any(Challenge.class));
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",testChallengeId1)
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
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("challenge", "initialDate"))
			.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
		
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessUpdateChallengeHasErrorEmptyParameters() throws Exception {
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",testChallengeId1)
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
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testDeleteChallenge() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/delete",testChallengeId1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	
	// CLIENT:
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testListChallengesClient() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/challenges", testClientUsername1)).andExpect(status().isOk()).andExpect(model().attributeExists("challenges"))
			.andExpect(view().name("client/challenges/challengesList"));
	}
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testShowChallengeByIdClient() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}",testClientUsername1, testChallengeId1)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge1 Name Test"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Challenge Description Test"))))
				.andExpect(model().attribute("challenge", hasProperty("initialDate", is(testInitialDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("endDate", is(testEndDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(1))))
				.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward Test"))))
				.andExpect(model().attribute("challenge", hasProperty("reps", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("weight", is(100.))))
				.andExpect(view().name("client/challenges/challengeDetails"));
	}
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testShowChallengeByIdClientAlreadyInscribed() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/{challengeId}",testClientUsername1, testChallengeId2)).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testListMyChallengesClient() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/challenges/mine", testClientUsername1)).andExpect(status().isOk()).andExpect(model().attributeExists("inscriptions"))
			.andExpect(view().name("client/challenges/myChallengesList"));
	}
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testShowAndEditMyChallengeByIdClient() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/mine/{challengeId}",testClientUsername1, testChallengeId2)).andExpect(status().isOk())
		.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge2 Name Test"))))
		.andExpect(model().attribute("challenge", hasProperty("description", is("Challenge Description Test"))))
		.andExpect(model().attribute("challenge", hasProperty("initialDate", is(testInitialDate.getTime()))))
		.andExpect(model().attribute("challenge", hasProperty("endDate", is(testEndDate.getTime()))))
		.andExpect(model().attribute("challenge", hasProperty("points", is(100))))
		.andExpect(model().attribute("challenge", hasProperty("id", is(2))))
		.andExpect(model().attribute("challenge", hasProperty("reward", is("Reward Test"))))
		.andExpect(model().attribute("challenge", hasProperty("reps", is(100))))
		.andExpect(model().attribute("challenge", hasProperty("weight", is(100.))))
		.andExpect(view().name("client/challenges/myChallengeDetailsAndUpdate"));
	}
	
	@WithMockUser(value = "client1", authorities = {"client"})
	@Test
	void testShowAndEditNotHisChallengeByIdClient() throws Exception {

		mockMvc.perform(get("/client/{clientUsername}/challenges/mine/{challengeId}",testClientUsername1, testChallengeId1)).andExpect(status().isOk())
		.andExpect(view().name("exception"));
	}
	
	@WithMockUser(value = "client2", authorities = {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"/client/{clientUsername}/challenges","/client/{clientUsername}/challenges/{challengeId}",
							"/client/{clientUsername}/challenges/mine","/client/{clientUsername}/challenges/mine/{challengeId}"})
	void testWrongClient(String path) throws Exception {
		
		if(path.contains("challengeId")) {
			mockMvc.perform(get(path,testClientUsername1, testChallengeId2)).andExpect(status().isOk())
			.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(get(path, testClientUsername1)).andExpect(status().isOk())
			.andExpect(view().name("exception"));
		}
	}
	
	// UTILS
	private Challenge createChallenge(int id, String name, Date initialDate, Date endDate) {
		
		Exercise exercise1 = new Exercise();
		exercise1.setName("Exercise Test");
		
		Challenge c = new Challenge();
		c.setId(id);
		c.setName(name);
		c.setDescription("Challenge Description Test");
		c.setInitialDate(initialDate);
		c.setEndDate(endDate);
		c.setPoints(100);
		c.setReward("Reward Test");
		c.setReps(100);
		c.setWeight(100.);
		c.setExercise(exercise1);
		
		return c;
	}
	
}
