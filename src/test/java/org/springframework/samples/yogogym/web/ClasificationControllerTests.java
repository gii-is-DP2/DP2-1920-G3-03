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
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class ClasificationControllerTests {

	private static final String TEST_USERNAME = "client3";

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithChallengesTests {

		@MockBean
		private InscriptionService inscriptionService;

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
			sampleChallege.setPoints(3);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			Inscription sampleInscription = new Inscription();
			sampleInscription.setStatus(Status.COMPLETED);
			sampleInscription.setChallenge(sampleChallege);
			Client sampleClient = new Client();
			User sampleUser = new User();
			sampleUser.setUsername("client3");
			sampleUser.setEnabled(true);
			List<Inscription> listSample = new ArrayList<Inscription>();
			listSample.add(sampleInscription);
			sampleClient.setInscriptions(listSample);
			sampleClient.setUser(sampleUser);
			List<Client> listSampleClient = new ArrayList<Client>();
			listSampleClient.add(sampleClient);

			given(this.inscriptionService.findInscriptionsByUsername(TEST_USERNAME)).willReturn(listSample);
			given(this.clientService.findClientsWithCompletedInscriptions()).willReturn(listSampleClient);
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllClasification() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", true))
					.andExpect(model().attribute("hasPositionWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true));
		}

	}
	
	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithChallengesTests2 {

		@MockBean
		private InscriptionService inscriptionService;

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
			sampleChallege.setPoints(3);
			sampleChallege.setInitialDate(new Date());
			sampleChallege.setExercise(sampleExercise);
			Inscription sampleInscription = new Inscription();
			sampleInscription.setStatus(Status.COMPLETED);
			sampleInscription.setChallenge(sampleChallege);
			Client sampleClient = new Client();
			User sampleUser = new User();
			sampleUser.setUsername("client3");
			sampleUser.setEnabled(true);
			List<Inscription> listSample = new ArrayList<Inscription>();
			listSample.add(sampleInscription);
			sampleClient.setInscriptions(listSample);
			sampleClient.setUser(sampleUser);
			List<Client> listSampleClient = new ArrayList<Client>();
			listSampleClient.add(sampleClient);

			given(this.inscriptionService.findInscriptionsByUsername(TEST_USERNAME)).willReturn(listSample);
			given(this.clientService.findClientsWithCompletedInscriptions()).willReturn(listSampleClient);
		}


		@WithMockUser(username = "client1", authorities = { "client" })
		@Test
		void testInitClasificationWithoutMyChallenges() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", "client1")).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasificationWeek", true))
					.andExpect(model().attribute("hasChallengeClasificationAll", true));
		}

		@WithMockUser(username = "client1", authorities = { "client" })
		@Test
		void testWrongUsername() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("exception"));
		}
	}

	@Nested
	@WebMvcTest(value = ClasificationController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class ClasificationWithoutChallengesTests {

		@MockBean
		private InscriptionService inscriptionService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			given(this.inscriptionService.findInscriptionsByUsername(TEST_USERNAME))
					.willReturn(new ArrayList<Inscription>());
			given(this.clientService.findClientsWithCompletedInscriptions()).willReturn(new ArrayList<Client>());
		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitClasificationWithoutChallenges() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/clasification", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/clasifications/clasification"))
					.andExpect(model().attribute("hasChallenge", false))
					.andExpect(model().attribute("hasChallengeClasification", false));
		}
	}

}
