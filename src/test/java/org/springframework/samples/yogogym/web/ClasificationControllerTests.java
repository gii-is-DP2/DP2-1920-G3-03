package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class ClasificationControllerTests {

	private static final String TEST_USERNAME = "client1";

	private static final String TEST_USERNAME2 = "client2";

	private static final Integer[] POINT = { 10 };

	private static final String[] CLIENT = { "client1" };

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithChallengesTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			Challenge sampleChallege = new Challenge();
			sampleChallege.setName("prueba");
			sampleChallege.setDescription("prueba");
			sampleChallege.setReward("prueba");
			sampleChallege.setPoints(10);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			List<Challenge> listChallenge = new ArrayList<>();
			listChallenge.add(sampleChallege);
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME)).willReturn(listChallenge);
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME)).willReturn(10);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(listClient);
			given(this.clientService.classificationPointDate()).willReturn(listPoint);
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", true))
					.andExpect(model().attribute("hasPositionWeek", true))
					.andExpect(model().attribute("hasPositionAll", true))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("totalPoint", 10)).andExpect(model().attribute("positionWeek", 1))
					.andExpect(model().attribute("positionAll", 1))
					.andExpect(model().attribute("orderPointWeek", POINT))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserWeek", CLIENT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutChallengesTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 2 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME2)).willReturn(null);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(listClient);
			given(this.clientService.classificationPointDate()).willReturn(listPoint);
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("orderPointWeek", POINT))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserWeek", CLIENT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitClasificationOtherUser() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("exception"));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutClassificationTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME)).willReturn(new ArrayList<>());
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME)).willReturn(null);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationNameAll()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointAll()).willReturn(new ArrayList<>());

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", false))
					.andExpect(model().attribute("hasChallengeClasificationAll", false));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutChallengesWeekTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			Challenge sampleChallege = new Challenge();
			sampleChallege.setName("prueba");
			sampleChallege.setDescription("prueba");
			sampleChallege.setReward("prueba");
			sampleChallege.setPoints(10);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			List<Challenge> listChallenge = new ArrayList<>();
			listChallenge.add(sampleChallege);
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME)).willReturn(listChallenge);
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME)).willReturn(10);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", true))
					.andExpect(model().attribute("hasPositionAll", true))
					.andExpect(model().attribute("hasChallengeClasificationWeek", false))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("totalPoint", 10)).andExpect(model().attribute("positionAll", 1))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutChallengesWeekUserTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME2)).willReturn(null);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", false))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutChallengesButPointsTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 2 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME2)).willReturn(new ArrayList<>());
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME2)).willReturn(10);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(listClient);
			given(this.clientService.classificationPointDate()).willReturn(listPoint);
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("orderPointWeek", POINT))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserWeek", CLIENT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutPointsButChallengesTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			Challenge sampleChallege = new Challenge();
			sampleChallege.setName("prueba");
			sampleChallege.setDescription("prueba");
			sampleChallege.setReward("prueba");
			sampleChallege.setPoints(10);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			List<Challenge> listChallenge = new ArrayList<>();
			listChallenge.add(sampleChallege);
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 2 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME2)).willReturn(listChallenge);
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME2)).willReturn(null);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(listClient);
			given(this.clientService.classificationPointDate()).willReturn(listPoint);
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME2, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME2)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true))
					.andExpect(model().attribute("orderPointWeek", POINT))
					.andExpect(model().attribute("orderPointAll", POINT))
					.andExpect(model().attribute("orderUserWeek", CLIENT))
					.andExpect(model().attribute("orderUserAll", CLIENT));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithNameChallengesTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			Challenge sampleChallege = new Challenge();
			sampleChallege.setName("prueba");
			sampleChallege.setDescription("prueba");
			sampleChallege.setReward("prueba");
			sampleChallege.setPoints(10);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			List<Challenge> listChallenge = new ArrayList<>();
			listChallenge.add(sampleChallege);
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME)).willReturn(listChallenge);
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME)).willReturn(10);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(listClient);
			given(this.clientService.classificationPointDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationNameAll()).willReturn(listClient);
			given(this.clientService.classificationPointAll()).willReturn(new ArrayList<>());

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", true))
					.andExpect(model().attribute("hasChallengeClasificationWeek", false))
					.andExpect(model().attribute("hasChallengeClasificationAll", false))
					.andExpect(model().attribute("totalPoint", 10));
		}

	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithPointChallengesTests {

		@MockBean
		private ChallengeService challengeService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			Challenge sampleChallege = new Challenge();
			sampleChallege.setName("prueba");
			sampleChallege.setDescription("prueba");
			sampleChallege.setReward("prueba");
			sampleChallege.setPoints(10);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			List<Challenge> listChallenge = new ArrayList<>();
			listChallenge.add(sampleChallege);
			List<String> listClient = new ArrayList<>();
			listClient.add(TEST_USERNAME);
			List<Integer> listPoint = new ArrayList<>();
			listPoint.add(10);

			// Client 1 Challenge
			given(this.challengeService.findChallengesByUsername(TEST_USERNAME)).willReturn(listChallenge);
			given(this.challengeService.sumPointChallengesByUsername(TEST_USERNAME)).willReturn(10);
			// Clasification
			given(this.clientService.classificationNameDate()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointDate()).willReturn(listPoint);
			given(this.clientService.classificationNameAll()).willReturn(new ArrayList<>());
			given(this.clientService.classificationPointAll()).willReturn(listPoint);

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", true))
					.andExpect(model().attribute("hasChallengeClasificationWeek", false))
					.andExpect(model().attribute("hasChallengeClasificationAll", false))
					.andExpect(model().attribute("totalPoint", 10));
		}

	}

}
