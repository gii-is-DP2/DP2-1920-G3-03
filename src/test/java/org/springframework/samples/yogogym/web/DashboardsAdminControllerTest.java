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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.DashboardsAdminService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

public class DashboardsAdminControllerTest {

	private static final Integer[] COUNT = { 1 };

	private static final String[] NAME = { "prueba" };

	@Nested
	@DisplayName("Admin test with exercises")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestCompleted {

		@MockBean
		private DashboardsAdminService dashboardsAdminService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Equipment sampleEquipment = new Equipment();
			sampleEquipment.setId(1);
			sampleEquipment.setName("prueba");
			sampleEquipment.setLocation("aqui");
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardsAdminService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardsAdminService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardsAdminService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardsAdminService.listExercise(sampleRoutine.getId())).willReturn(listOne);
			given(this.dashboardsAdminService.listIdEquipment(sampleExercise.getId())).willReturn(1);
			given(this.dashboardsAdminService.listNameEquipment(sampleEquipment.getId()))
					.willReturn(sampleEquipment.getName());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitAllDashboard() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", true))
					.andExpect(model().attribute("hasEquipmentWeek", true))
					.andExpect(model().attribute("countMonth", COUNT))
					.andExpect(model().attribute("orderNameMonth", NAME))
					.andExpect(model().attribute("countWeek", COUNT))
					.andExpect(model().attribute("orderNameWeek", NAME));
		}
	}

	@Nested
	@DisplayName("Admin test without week")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutWeek {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Equipment sampleEquipment = new Equipment();
			sampleEquipment.setId(1);
			sampleEquipment.setName("prueba");
			sampleEquipment.setLocation("aqui");
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
			now.add(Calendar.DAY_OF_MONTH, -14);
			Date sampleIntialDate = now.getTime();
			Calendar now2 = Calendar.getInstance();
			now2.add(Calendar.DAY_OF_MONTH, -10);
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(listOne);
			given(this.dashboardService.listIdEquipment(sampleExercise.getId())).willReturn(1);
			given(this.dashboardService.listNameEquipment(sampleEquipment.getId()))
					.willReturn(sampleEquipment.getName());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardWithoutWeek() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", true))
					.andExpect(model().attribute("hasEquipmentWeek", false))
					.andExpect(model().attribute("countMonth", COUNT))
					.andExpect(model().attribute("orderNameMonth", NAME));
		}
	}

	@Nested
	@DisplayName("Admin test without trainings")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutTrainings {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			given(this.dashboardService.equipmentControl()).willReturn(new ArrayList<Training>());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardWithoutWeek() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}

	@Nested
	@DisplayName("Admin test without equipment machine")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutEquipment {

		@MockBean
		private DashboardsAdminService dashboardService;

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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(listOne);
			given(this.dashboardService.listIdEquipment(sampleExercise.getId())).willReturn(null);
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitWithoutEquipment() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}

	@Nested
	@DisplayName("Admin test without exercises")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutExercises {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			RoutineLine sampleRoutineLine = new RoutineLine();
			sampleRoutineLine.setId(1);
			sampleRoutineLine.setReps(3);
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(new ArrayList<>());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitWithoutExercise() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}

	@Nested
	@DisplayName("Admin test without routine lines")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutRoutinesLines {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Routine sampleRoutine = new Routine();
			sampleRoutine.setId(1);
			sampleRoutine.setName("prueba");
			sampleRoutine.setRepsPerWeek(1);
			sampleRoutine.setDescription("prueba");
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitWithoutRoutineLines() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}

	@Nested
	@DisplayName("Admin test without routines")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestWithoutRoutines {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
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
			sampleTraining.setId(1);
			Client sampleClient = new Client();
			User sampleUser = new User();
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(new ArrayList<>());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitWithoutRoutines() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}
	
	@Nested
	@DisplayName("Admin test with past date")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestPastDate {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Equipment sampleEquipment = new Equipment();
			sampleEquipment.setId(1);
			sampleEquipment.setName("prueba");
			sampleEquipment.setLocation("aqui");
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
			now.add(Calendar.DAY_OF_MONTH, -60);
			Date sampleIntialDate = now.getTime();
			Calendar now2 = Calendar.getInstance();
			now2.add(Calendar.DAY_OF_MONTH, -50);
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(listOne);
			given(this.dashboardService.listIdEquipment(sampleExercise.getId())).willReturn(1);
			given(this.dashboardService.listNameEquipment(sampleEquipment.getId()))
					.willReturn(sampleEquipment.getName());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardPastDate() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}
	
	@Nested
	@DisplayName("Admin test with post date")
	@WebMvcTest(value = DashboardsAdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
	public class AdminTestPostDate {

		@MockBean
		private DashboardsAdminService dashboardService;

		@Autowired
		private MockMvc mockMvc;

		@BeforeEach
		void setup() {
			Equipment sampleEquipment = new Equipment();
			sampleEquipment.setId(1);
			sampleEquipment.setName("prueba");
			sampleEquipment.setLocation("aqui");
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
			now.add(Calendar.DAY_OF_MONTH, +2);
			Date sampleIntialDate = now.getTime();
			Calendar now2 = Calendar.getInstance();
			now2.add(Calendar.DAY_OF_MONTH, +3);
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
			sampleUser.setUsername("client1");
			sampleUser.setEnabled(true);
			sampleClient.setUser(sampleUser);
			sampleClient.setId(1);
			sampleTraining.setClient(sampleClient);
			List<Training> sampleListTraining = new ArrayList<Training>();
			sampleListTraining.add(sampleTraining);
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(1);

			given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
			given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
			given(this.dashboardService.listRepsRoutine(sampleRoutine.getId()))
					.willReturn(sampleRoutine.getRepsPerWeek());
			given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(listOne);
			given(this.dashboardService.listIdEquipment(sampleExercise.getId())).willReturn(1);
			given(this.dashboardService.listNameEquipment(sampleEquipment.getId()))
					.willReturn(sampleEquipment.getName());
		}

		@WithMockUser(username = "admin1", authorities = { "admin" })
		@Test
		void testInitDashboardPostDate() throws Exception {
			mockMvc.perform(get("/admin/dashboardEquipment")).andExpect(status().isOk())
					.andExpect(view().name("admin/dashboards/dashboardEquipment"))
					.andExpect(model().attribute("hasEquipmentMonth", false))
					.andExpect(model().attribute("hasEquipmentWeek", false));
		}
	}

}
