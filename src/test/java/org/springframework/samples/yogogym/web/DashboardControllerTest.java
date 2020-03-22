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
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.DashboardService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = DashboardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class DashboardControllerTest {

	private static final Integer[] COUNT = { 1 };

	private static final String[] NAME = { "prueba" };

	@MockBean
	private DashboardService dashboardService;

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

		given(this.dashboardService.equipmentControl()).willReturn(sampleListTraining);
		given(this.dashboardService.listRoutine(sampleTraining.getId())).willReturn(listOne);
		given(this.dashboardService.listRepsRoutine(sampleRoutine.getId())).willReturn(sampleRoutine.getRepsPerWeek());
		given(this.dashboardService.listExercise(sampleRoutine.getId())).willReturn(listOne);
		given(this.dashboardService.listIdEquipment(sampleExercise.getId())).willReturn(1);
		given(this.dashboardService.listNameEquipment(sampleEquipment.getId())).willReturn(sampleEquipment.getName());
	}

	@WithMockUser(username = "admin1", authorities = { "admin" })
	@Test
	void testInitAllDashboardClient() throws Exception {
		mockMvc.perform(get("/admin/dashboard")).andExpect(status().isOk())
				.andExpect(view().name("admin/dashboards/dashboardEquipment"))
				.andExpect(model().attribute("hasEquipmentMonth", true))
				.andExpect(model().attribute("hasEquipmentWeek", true))
				.andExpect(model().attribute("countMonth", COUNT)).andExpect(model().attribute("orderNameMonth", NAME));
	}

}
