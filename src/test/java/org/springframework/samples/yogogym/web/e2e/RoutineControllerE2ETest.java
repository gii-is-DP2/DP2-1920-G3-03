package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class RoutineControllerE2ETest {

	@Autowired
	RoutineService routineService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

	}

	// TRAINER============================================================================

	void testWrongAuth(int mode, String path, Object... uriVars) throws Exception {
		
		if (mode == 0) {
			mockMvc.perform(get(path, uriVars)).andExpect(status().isOk()).andExpect(view().name("exception"));
		} else {
			mockMvc.perform(post(path, uriVars)).andExpect(status().isForbidden());
		}
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testTrainerWrongClients() throws Exception {

		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 3;
		int routineId = 9;

		// Wrong client id
		testWrongAuth(0, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(0, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId);
		testWrongAuth(1, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(1,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",
				trainerUsername, clientId, trainingId, routineId);
	}

	@WithMockUser(username = "trainer2", authorities = { "trainer" })
	@Test
	void testTrainerWrongAuthority() throws Exception {

		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 9;

		// Wrong trainer
		testWrongAuth(0, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(0, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId);
		testWrongAuth(1, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(1,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",
				trainerUsername, clientId, trainingId, routineId);
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testNotExistingRoutine() throws Exception {

		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 100;

		testWrongAuth(0, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(1, "/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(1,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
				trainerUsername, clientId, trainingId, routineId);
		testWrongAuth(0,
				"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",
				trainerUsername, clientId, trainingId, routineId);
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testGetRoutines() throws Exception {

		String trainerUsername = "trainer1";

		mockMvc.perform(get("/trainer/{trainerUsername}/routines", trainerUsername)).andExpect(status().isOk())
				.andExpect(view().name("trainer/routines/routinesList"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testGetRoutineDetails() throws Exception {

		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 9;

		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",
				trainerUsername, clientId, trainingId, routineId)).andExpect(status().isOk())
				.andExpect(view().name("trainer/routines/routineDetails")).andDo(print());
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testInitCreateRoutineForm() throws Exception {
		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;

		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId)).andExpect(status().isOk())
				.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"))
				.andExpect(model().attributeExists("routine"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testProcessCreateRoutineForm() throws Exception {
		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;

		Collection<RoutineLine> routinesLines = new ArrayList<>();

		Routine routine = createRoutine(routinesLines);

		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",
				trainerUsername, clientId, trainingId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(status().is3xxRedirection()).andExpect(view().name(
						"redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/" + trainingId));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testInitUpdateRoutineForm() throws Exception {
		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 9;

		mockMvc.perform(
				get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
						trainerUsername, clientId, trainingId, routineId))
				.andExpect(status().isOk()).andExpect(view().name("trainer/routines/routinesCreateOrUpdate"))
				.andExpect(model().attributeExists("routine"));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testProcessUpdateRoutineForm() throws Exception {
		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 9;

		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);

		mockMvc.perform(
				post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",
						trainerUsername, clientId, trainingId, routineId).with(csrf()).param("name", routine.getName())
								.param("description", routine.getDescription())
								.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/trainer/" + trainerUsername
						+ "/clients/" + clientId + "/trainings/" + trainingId + "/routines/" + routineId));
	}

	@WithMockUser(username = "trainer1", authorities = { "trainer" })
	@Test
	void testDeleteRoutine() throws Exception {
		String trainerUsername = "trainer1";
		int trainingId = 9;
		int clientId = 1;
		int routineId = 9;

		mockMvc.perform(
				get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",
						trainerUsername, clientId, trainingId, routineId))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/" + trainerUsername + "/routines"));
	}

	// CLIENT
	// ======================================================================================================

	// createRoutine

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testInitCreateRoutineForm_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routine/create", clientUsername,
				trainingId)).andExpect(status().isOk())
				.andExpect(view().name("client/routines/routinesCreateOrUpdate"))
				.andExpect(model().attributeExists("routine"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessCreateRoutineForm_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/create", clientUsername,
				trainingId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}

	// Update Routine
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testInitUpdateRoutineForm_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update",
				clientUsername, trainingId, routineId)).andExpect(status().isOk())
				.andExpect(view().name("client/routines/routinesCreateOrUpdate"))
				.andExpect(model().attributeExists("routine"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessUpdateRoutineForm_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update",
				clientUsername, trainingId, routineId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}

	// Delete Routine

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testDeleteRoutineForm_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/delete",
				clientUsername, trainingId, routineId)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}

	// shouldNotCreateRoutineBadInput

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessCreateRoutineFormBadName_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
			
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setName("");

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/create", clientUsername,
				trainingId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessCreateRoutineFormBadDesc_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setDescription("");

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/create", clientUsername,
				trainingId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessCreateRoutineFormBadReps_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setRepsPerWeek(50);

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/create", clientUsername,
				trainingId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"));
	}

	// shouldNotUpdateRoutineBadInput

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessUpdateRoutineFormBadName_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setName("");

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update",
				clientUsername, trainingId, routineId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("client/routines/routinesCreateOrUpdate"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessUpdateRoutineFormBadDesc_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setDescription("");

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update",
				clientUsername, trainingId, routineId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("client/routines/routinesCreateOrUpdate"));
	}

	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testProcessUpdateRoutineFormBadReps_Client() throws Exception {
		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		Routine routine = createRoutine(routinesLines);
		routine.setRepsPerWeek(50);

		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routine/{routineId}/update",
				clientUsername, trainingId, routineId).with(csrf()).param("name", routine.getName())
						.param("description", routine.getDescription())
						.param("repsPerWeek", routine.getRepsPerWeek().toString()))
				.andExpect(view().name("client/routines/routinesCreateOrUpdate"));
	}
	
	// Derivative Methods

	protected Exercise createExercise(final int id, RepetitionType type) {
		Exercise exercise = new Exercise();
		exercise.setId(id);
		exercise.setName("Exercise name test");
		exercise.setDescription("Description Exercise test");
		exercise.setKcal(20);
		exercise.setRepetitionType(type);
		exercise.setBodyPart(BodyParts.ALL);

		return exercise;
	}

	protected RoutineLine createRoutineLine(final int id, final Integer reps, final Double time, Exercise exercise) {
		RoutineLine routineLine = new RoutineLine();
		routineLine.setId(id);
		routineLine.setReps(reps);
		routineLine.setTime(time);
		routineLine.setWeight(50.0);
		routineLine.setSeries(5);
		routineLine.setExercise(exercise);

		return routineLine;
	}

	protected Routine createRoutine(Collection<RoutineLine> routinesLines) {
		Routine routine = new Routine();
		routine.setName("Routine Test");
		routine.setDescription("Routine Description Test");
		routine.setRepsPerWeek(5);
		routine.setRoutineLine(routinesLines);

		return routine;
	}

	protected Client createClient(final String username) {
		Client client = new Client();
		User user_client = new User();
		user_client.setUsername(username);
		user_client.setEnabled(true);
		client.setUser(user_client);

		return client;
	}

	protected Trainer createTrainer(String trainerUsername, Collection<Client> clients) {
		Trainer trainer = new Trainer();
		User user_trainer = new User();
		user_trainer.setUsername(trainerUsername);
		user_trainer.setEnabled(true);
		trainer.setUser(user_trainer);
		trainer.setClients(clients);

		return trainer;
	}

	protected Training createTraining(final int id, final Client client, Collection<Routine> routines,
			final int DaysToFinishTraining) {
		Calendar cal = Calendar.getInstance();

		Training training = new Training();
		training.setId(id);
		training.setName("training 1");
		training.setInitialDate(cal.getTime());

		cal.add(Calendar.DAY_OF_MONTH, DaysToFinishTraining);
		training.setEndDate(cal.getTime());
		training.setDiet(null);
		training.setRoutines(routines);

		return training;
	}

}