package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DashboardClientService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class DashboardClientControllerTests {

	private static final String TEST_USERNAME = "client1";

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client13Tests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise = new Exercise();
			sampleExercise.setId(1);
			sampleExercise.setName("prueba");
			sampleExercise.setDescription("prueba");
			sampleExercise.setBodyPart(BodyParts.ALL);
			sampleExercise.setKcal(1000);
			sampleExercise.setRepetitionType(RepetitionType.REPS);
			List<Exercise> listExercise = new ArrayList<Exercise>();
			listExercise.add(sampleExercise);
			RoutineLine sampleRoutineLine = new RoutineLine();
			sampleRoutineLine.setId(1);
			sampleRoutineLine.setReps(3);
			sampleRoutineLine.setExercise(sampleExercise);
			Routine sampleRoutine = new Routine();
			sampleRoutine.setId(1);
			sampleRoutine.setName("prueba");
			sampleRoutine.setRepsPerWeek(1);
			sampleRoutine.setDescription("prueba");
			List<RoutineLine> sampleListRoutineLine = new ArrayList<RoutineLine>();
			sampleListRoutineLine.add(sampleRoutineLine);
			sampleRoutine.setRoutineLine(sampleListRoutineLine);
			Training sampleTraining = new Training();
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -2);
			Date sampleIntialDate = now.getTime();
			Calendar now2 = Calendar.getInstance();
			now2.add(Calendar.DAY_OF_MONTH, -1);
			Date sampleEndDate = now.getTime();
			sampleTraining.setInitialDate(sampleIntialDate);
			sampleTraining.setEndDate(sampleEndDate);
			sampleTraining.setName("prueba");
			List<Routine> sampleListRoutine = new ArrayList<Routine>();
			sampleListRoutine.add(sampleRoutine);
			sampleTraining.setRoutines(sampleListRoutine);
			sampleTraining.setId(1);
			Client sampleClient = new Client();
			User sampleUser = new User();
			sampleUser.setUsername(TEST_USERNAME);
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listRoutineByTraining = new ArrayList<Integer>();
			listRoutineByTraining.add(1);

			given(this.clientService.findClientByUsername(TEST_USERNAME)).willReturn(sampleClient);
			given(this.dashboardClientService.listTrainingByClient(sampleClient.getId()))
					.willReturn(sampleListTraining);
			given(this.dashboardClientService.listRoutineByTraining(sampleTraining.getId()))
					.willReturn(listRoutineByTraining);
			given(this.dashboardClientService.listRepsRoutineByRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardClientService.listExerciseByRoutine(sampleRoutine.getId())).willReturn(listExercise);

		}

		@WithMockUser(username = TEST_USERNAME, authorities = { "client" })
		@Test
		void testInitAllDashboardClient() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", true))
					.andExpect(model().attribute("hasExerciseAll", true));
		}

		@WithMockUser(username = "client3", authorities = { "client" })
		@Test
		void testInitDashboardClientWithoutExercises() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", "client3")).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", false))
					.andExpect(model().attribute("hasExerciseAll", false));
		}

		@WithMockUser(username = "client3", authorities = { "client" })
		@Test
		void testWrongUsername() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", TEST_USERNAME)).andExpect(status().isOk())
					.andExpect(view().name("exception"));
		}
	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client2Tests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Exercise sampleExercise2 = new Exercise();
			sampleExercise2.setId(2);
			sampleExercise2.setName("prueba");
			sampleExercise2.setDescription("prueba");
			sampleExercise2.setBodyPart(BodyParts.ALL);
			sampleExercise2.setKcal(1000);
			sampleExercise2.setRepetitionType(RepetitionType.REPS);
			List<Exercise> listExercise2 = new ArrayList<Exercise>();
			listExercise2.add(sampleExercise2);
			RoutineLine sampleRoutineLine2 = new RoutineLine();
			sampleRoutineLine2.setId(2);
			sampleRoutineLine2.setReps(3);
			sampleRoutineLine2.setExercise(sampleExercise2);
			Routine sampleRoutine2 = new Routine();
			sampleRoutine2.setId(2);
			sampleRoutine2.setName("prueba");
			sampleRoutine2.setRepsPerWeek(1);
			sampleRoutine2.setDescription("prueba");
			List<RoutineLine> sampleListRoutineLine2 = new ArrayList<RoutineLine>();
			sampleListRoutineLine2.add(sampleRoutineLine2);
			sampleRoutine2.setRoutineLine(sampleListRoutineLine2);
			Training sampleTraining2 = new Training();
			Calendar now3 = Calendar.getInstance();
			now3.add(Calendar.DAY_OF_MONTH, -60);
			Date sampleIntialDate2 = now3.getTime();
			Calendar now4 = Calendar.getInstance();
			now4.add(Calendar.DAY_OF_MONTH, -55);
			Date sampleEndDate2 = now4.getTime();
			sampleTraining2.setInitialDate(sampleIntialDate2);
			sampleTraining2.setEndDate(sampleEndDate2);
			sampleTraining2.setName("prueba");
			List<Routine> sampleListRoutine2 = new ArrayList<Routine>();
			sampleListRoutine2.add(sampleRoutine2);
			sampleTraining2.setRoutines(sampleListRoutine2);
			sampleTraining2.setId(2);
			Client sampleClient2 = new Client();
			User sampleUser2 = new User();
			sampleUser2.setUsername("client2");
			sampleUser2.setEnabled(true);
			sampleClient2.setUser(sampleUser2);
			sampleClient2.setId(1);
			sampleTraining2.setClient(sampleClient2);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining2);
			List<Integer> listRoutineByTraining = new ArrayList<Integer>();
			listRoutineByTraining.add(2);

			given(this.clientService.findClientByUsername("client2")).willReturn(sampleClient2);
			given(this.dashboardClientService.listTrainingByClient(sampleClient2.getId()))
					.willReturn(sampleListTraining);
			given(this.dashboardClientService.listRoutineByTraining(sampleTraining2.getId()))
					.willReturn(listRoutineByTraining);
			given(this.dashboardClientService.listRepsRoutineByRoutine(sampleRoutine2.getId()))
					.willReturn(sampleRoutine2.getRepsPerWeek());
			given(this.dashboardClientService.listExerciseByRoutine(sampleRoutine2.getId())).willReturn(listExercise2);

		}

		@WithMockUser(username = "client2", authorities = { "client" })
		@Test
		void testInitDashboardClientHisto() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", "client2")).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", false))
					.andExpect(model().attribute("hasExerciseAll", true));
		}

	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client2WithoutExercisesTests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			RoutineLine sampleRoutineLine2 = new RoutineLine();
			sampleRoutineLine2.setId(2);
			sampleRoutineLine2.setReps(3);
			Routine sampleRoutine2 = new Routine();
			sampleRoutine2.setId(2);
			sampleRoutine2.setName("prueba");
			sampleRoutine2.setRepsPerWeek(1);
			sampleRoutine2.setDescription("prueba");
			List<RoutineLine> sampleListRoutineLine2 = new ArrayList<RoutineLine>();
			sampleListRoutineLine2.add(sampleRoutineLine2);
			sampleRoutine2.setRoutineLine(sampleListRoutineLine2);
			Training sampleTraining2 = new Training();
			Calendar now3 = Calendar.getInstance();
			now3.add(Calendar.DAY_OF_MONTH, -60);
			Date sampleIntialDate2 = now3.getTime();
			Calendar now4 = Calendar.getInstance();
			now4.add(Calendar.DAY_OF_MONTH, -55);
			Date sampleEndDate2 = now4.getTime();
			sampleTraining2.setInitialDate(sampleIntialDate2);
			sampleTraining2.setEndDate(sampleEndDate2);
			sampleTraining2.setName("prueba");
			List<Routine> sampleListRoutine2 = new ArrayList<Routine>();
			sampleListRoutine2.add(sampleRoutine2);
			sampleTraining2.setRoutines(sampleListRoutine2);
			sampleTraining2.setId(2);
			Client sampleClient2 = new Client();
			User sampleUser2 = new User();
			sampleUser2.setUsername("client2");
			sampleUser2.setEnabled(true);
			sampleClient2.setUser(sampleUser2);
			sampleClient2.setId(1);
			sampleTraining2.setClient(sampleClient2);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining2);
			List<Integer> listRoutineByTraining = new ArrayList<Integer>();
			listRoutineByTraining.add(2);

			given(this.clientService.findClientByUsername("client2")).willReturn(sampleClient2);
			given(this.dashboardClientService.listTrainingByClient(sampleClient2.getId()))
					.willReturn(sampleListTraining);
			given(this.dashboardClientService.listRoutineByTraining(sampleTraining2.getId()))
					.willReturn(listRoutineByTraining);
			given(this.dashboardClientService.listRepsRoutineByRoutine(sampleRoutine2.getId()))
					.willReturn(sampleRoutine2.getRepsPerWeek());
			given(this.dashboardClientService.listExerciseByRoutine(sampleRoutine2.getId()))
					.willReturn(new ArrayList<>());

		}

		@WithMockUser(username = "client2", authorities = { "client" })
		@Test
		void testInitDashboardClientWithoutExercises() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", "client2")).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", false))
					.andExpect(model().attribute("hasExerciseAll", false));
		}
	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client2WithoutRoutineLinesTests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Routine sampleRoutine2 = new Routine();
			sampleRoutine2.setId(2);
			sampleRoutine2.setName("prueba");
			sampleRoutine2.setRepsPerWeek(1);
			sampleRoutine2.setDescription("prueba");
			Training sampleTraining2 = new Training();
			Calendar now3 = Calendar.getInstance();
			now3.add(Calendar.DAY_OF_MONTH, -60);
			Date sampleIntialDate2 = now3.getTime();
			Calendar now4 = Calendar.getInstance();
			now4.add(Calendar.DAY_OF_MONTH, -55);
			Date sampleEndDate2 = now4.getTime();
			sampleTraining2.setInitialDate(sampleIntialDate2);
			sampleTraining2.setEndDate(sampleEndDate2);
			sampleTraining2.setName("prueba");
			List<Routine> sampleListRoutine2 = new ArrayList<Routine>();
			sampleListRoutine2.add(sampleRoutine2);
			sampleTraining2.setRoutines(sampleListRoutine2);
			sampleTraining2.setId(2);
			Client sampleClient2 = new Client();
			User sampleUser2 = new User();
			sampleUser2.setUsername("client2");
			sampleUser2.setEnabled(true);
			sampleClient2.setUser(sampleUser2);
			sampleClient2.setId(1);
			sampleTraining2.setClient(sampleClient2);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining2);
			List<Integer> listRoutineByTraining = new ArrayList<Integer>();
			listRoutineByTraining.add(2);

			given(this.clientService.findClientByUsername("client2")).willReturn(sampleClient2);
			given(this.dashboardClientService.listTrainingByClient(sampleClient2.getId()))
					.willReturn(sampleListTraining);
			given(this.dashboardClientService.listRoutineByTraining(sampleTraining2.getId()))
					.willReturn(listRoutineByTraining);
			given(this.dashboardClientService.listRepsRoutineByRoutine(sampleRoutine2.getId()))
					.willReturn(sampleRoutine2.getRepsPerWeek());
		}

		@WithMockUser(username = "client2", authorities = { "client" })
		@Test
		void testInitDashboardClientWithoutRoutineLines() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", "client2")).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", false))
					.andExpect(model().attribute("hasExerciseAll", false));
		}
	}

	@Nested
	@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class Client2WithoutRoutineTests {

		@MockBean
		private DashboardClientService dashboardClientService;

		@MockBean
		private ClientService clientService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Training sampleTraining2 = new Training();
			Calendar now3 = Calendar.getInstance();
			now3.add(Calendar.DAY_OF_MONTH, -60);
			Date sampleIntialDate2 = now3.getTime();
			Calendar now4 = Calendar.getInstance();
			now4.add(Calendar.DAY_OF_MONTH, -55);
			Date sampleEndDate2 = now4.getTime();
			sampleTraining2.setInitialDate(sampleIntialDate2);
			sampleTraining2.setEndDate(sampleEndDate2);
			sampleTraining2.setName("prueba");
			sampleTraining2.setId(2);
			Client sampleClient2 = new Client();
			User sampleUser2 = new User();
			sampleUser2.setUsername("client2");
			sampleUser2.setEnabled(true);
			sampleClient2.setUser(sampleUser2);
			sampleClient2.setId(1);
			sampleTraining2.setClient(sampleClient2);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining2);
			List<Integer> listRoutineByTraining = new ArrayList<Integer>();
			listRoutineByTraining.add(2);

			given(this.clientService.findClientByUsername("client2")).willReturn(sampleClient2);
			given(this.dashboardClientService.listTrainingByClient(sampleClient2.getId()))
					.willReturn(sampleListTraining);
			given(this.dashboardClientService.listRoutineByTraining(sampleTraining2.getId()))
					.willReturn(new ArrayList<>());
		}

		@WithMockUser(username = "client2", authorities = { "client" })
		@Test
		void testInitDashboardClientWithoutRoutines() throws Exception {
			mockMvc.perform(get("/client/{clientUsername}/dashboard", "client2")).andExpect(status().isOk())
					.andExpect(view().name("client/dashboards/dashboard"))
					.andExpect(model().attribute("hasExerciseMonth", false))
					.andExpect(model().attribute("hasExerciseAll", false));
		}
	}

}
