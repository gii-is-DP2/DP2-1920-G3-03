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

@WebMvcTest(value = DashboardClientController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class DashboardClientControllerTests {

	private static final String TEST_USERNAME = "client1";

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
		given(this.dashboardClientService.listTrainingByClient(sampleClient.getId())).willReturn(sampleListTraining);
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
	void testInitHistoricalDashboardClient() throws Exception {
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
