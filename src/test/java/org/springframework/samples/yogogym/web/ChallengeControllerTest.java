package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ChallengeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ChallengeControllerTest {
	
	
	private static final int testChallengeId = 1;
	private static final int testClientId = 1;
	private static final String testClientUsername = "client1";
	
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final Calendar testInitialTrainingDate = Calendar.getInstance();
	private static final Calendar testEndTrainingDate = Calendar.getInstance();
	
	
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
		//Challenge 1:
		testEndTrainingDate.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = testInitialTrainingDate.getTime();
		Date endDate = testEndTrainingDate.getTime();
		Exercise exercise1 = new Exercise();
		exercise1.setName("Exercise Test");
		
		Challenge challenge = new Challenge();
		challenge.setId(testChallengeId);
		challenge.setName("Challenge Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		challenge.setExercise(exercise1);
		
		/*Client client = new Client();
		User userClient = new User();
		userClient.setUsername("client1");
		userClient.setEnabled(true);
		client.setUser(userClient);
		client.setId(testClientId);*/
		
		given(this.challengeService.findChallengeById(testChallengeId)).willReturn(challenge);
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
		
		//testEndTrainingDate.add(Calendar.DAY_OF_MONTH, 1);
		
		mockMvc.perform(get("/admin/challenges/{challengeId}", testChallengeId)).andExpect(status().isOk())
				.andExpect(model().attribute("challenge", hasProperty("name", is("Challenge Name Test"))))
				.andExpect(model().attribute("challenge", hasProperty("description", is("Challenge Description Test"))))
				.andExpect(model().attribute("challenge", hasProperty("initialDate", is(testInitialTrainingDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("endDate", is(testEndTrainingDate.getTime()))))
				.andExpect(model().attribute("challenge", hasProperty("points", is(100))))
				.andExpect(model().attribute("challenge", hasProperty("id", is(1))))
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
			.param("exerciseId","1"))
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
				.param("exerciseId","1"))
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
				.param("exerciseId","1"))
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
				.param("exerciseId","1"))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("challenge"))
				.andExpect(model().attributeHasFieldErrors("challenge", "endDate"))
				.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testInitUpdateChallengeForm() throws Exception
	{
		mockMvc.perform(get("/admin/challenges/{challengeId}/edit",testChallengeId))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/challenges/challengesCreateOrUpdate"))
		.andExpect(model().attributeExists("challenge"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testProcessUpdateChallengeForm() throws Exception
	{						
		
		mockMvc.perform(post("/admin/challenges/{challengeId}/edit",testChallengeId)
			.with(csrf())
			.param("name", "Test Update")
			.param("description", "Test")
			.param("initialDate", "2090/01/01")
			.param("endDate", "2090/01/02")
			.param("points", "10")
			.param("reward","Reward")
			.param("reps","10")
			.param("weight","10.")
			.param("exerciseId","1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	@WithMockUser(value = "admin1", authorities = {"admin"})
	@Test
	void testDeleteChallenge() throws Exception
	{
		mockMvc.perform(get("admin/challenges/{challengeId}/delete",testChallengeId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/admin/challenges"));
	}
	
	/*
	void testWrongAuth(int mode,String path,Object... uriVars) throws Exception
	{
		if(mode == 0)
		{
			mockMvc.perform(get(path,uriVars))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));			
		}
		else
		{
			mockMvc.perform(post(path,uriVars))
			.andExpect(status().isForbidden());
		}
	}*/
	
	
}
