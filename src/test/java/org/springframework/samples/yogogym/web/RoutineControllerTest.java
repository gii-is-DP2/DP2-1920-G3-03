package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

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
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = RoutineController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class RoutineControllerTest {
	
	//Trainer 1
	private static final int testRoutineId = 1;	
	private static final String testTrainerUsername = "trainer1";	
	private static final int testClientId = 1;	
	private static final int testTrainingId = 1;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
	//Trainer 2
	private static final String testTrainerUsername_t2 = "trainer2";	
	private static final int testClientId_t2 = 2;
	
	@MockBean
	private RoutineService routineService;	
	@MockBean
	private ClientService clientService;
	@MockBean
	private TrainerService trainerService;	
	@MockBean
	private TrainingService trainingService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp()
	{
		//Trainer 1
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		
		Routine routine= createRoutine(testRoutineId, routinesLines);
		
		Client client = createClient(testClientId, "client1");
		
		Collection<Client> clients = new ArrayList<>();
		clients.add(client);
		
		Trainer trainer = createTrainer(testTrainerUsername, clients);
								
		Collection<Routine> routines = new ArrayList<>();
		routines.add(routine);
		
		Training training = createTraining(testTrainingId,client,routines,1);
		
		given(this.clientService.findClientById(testClientId)).willReturn(client);
		given(this.trainerService.findTrainer(testTrainerUsername)).willReturn(trainer);
		given(this.trainingService.findTrainingById(testTrainingId)).willReturn(training);
		given(this.routineService.findRoutineById(testRoutineId)).willReturn(routine);
		
		//Trainer 2
		Client client_t2 = createClient(testClientId_t2, "client2");
		
		Collection<Client> clients_t2 = new ArrayList<>();
		clients_t2.add(client_t2);
		
		Trainer trainer_t2 = createTrainer(testTrainerUsername_t2,clients_t2);
		
		given(this.clientService.findClientById(testClientId_t2)).willReturn(client_t2);
		given(this.trainerService.findTrainer(testTrainerUsername_t2)).willReturn(trainer_t2);	
	}
	
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
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testWrongAuthority() throws Exception
	{
		// Authority is not trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/routines",testTrainerUsername);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId_t2,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId_t2,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId_t2,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId_t2,testTrainingId,testRoutineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId_t2,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",testTrainerUsername,testClientId_t2,testTrainingId,testRoutineId);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception
	{
		// Wrong trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/routines",testTrainerUsername);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",testTrainerUsername,testClientId,testTrainingId,testRoutineId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testNotExistingRoutine() throws Exception
	{
		final int badId = 100;
		// Wrong trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId,testTrainingId,badId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,badId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,badId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,badId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,badId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",testTrainerUsername,testClientId,testTrainingId,badId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetRoutines() throws Exception
	{		
		mockMvc.perform(get("/trainer/{trainerUsername}/routines",testTrainerUsername))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesList"));
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetRoutineDetails() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId,testTrainingId,testRoutineId))
			.andExpect(status().isOk())
			.andExpect(view().name("trainer/routines/routineDetails"))
			.andDo(print());
	}
				
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateRoutineForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"))
		.andExpect(model().attributeExists("routine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCreateRoutineForm() throws Exception
	{
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		
		Routine routine= new Routine();
		routine.setName("Routine Test");
		routine.setDescription("Routine Description Test");
		routine.setRepsPerWeek(5);
		routine.setRoutineLine(routinesLines);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/create",testTrainerUsername,testClientId,testTrainingId)
			.with(csrf())
			.param("name", routine.getName())
			.param("description", routine.getDescription())
			.param("repsPerWeek",routine.getRepsPerWeek().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+ testTrainerUsername + "/clients/" + testClientId + "/trainings/"+testTrainingId));
	}
		
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitUpdateRoutineForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesCreateOrUpdate"))
		.andExpect(model().attributeExists("routine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessUpdateRoutineForm() throws Exception
	{
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		
		Routine routine= new Routine();
		routine.setName("New Routine Test");
		routine.setDescription("New Routine Description Test");
		routine.setRepsPerWeek(10);
		routine.setRoutineLine(routinesLines);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/edit",testTrainerUsername,testClientId,testTrainingId,testRoutineId)
			.with(csrf())
			.param("name", routine.getName())
			.param("description", routine.getDescription())
			.param("repsPerWeek",routine.getRepsPerWeek().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername + "/clients/" + testClientId + "/trainings/" + testTrainingId + "/routines/" + testRoutineId));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testDeleteRoutine() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/delete",testTrainerUsername,testClientId,testTrainingId,testRoutineId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername + "/routines"));
	}
	
	//Derivative Methods
	
	protected Exercise createExercise(final int id, RepetitionType type) 
	{
		Exercise exercise = new Exercise();
		exercise.setId(id);
		exercise.setName("Exercise name test");
		exercise.setDescription("Description Exercise test");
		exercise.setKcal(20);
		exercise.setRepetitionType(type);
		exercise.setBodyPart(BodyParts.ALL);

		return exercise;
	}

	protected RoutineLine createRoutineLine(final int id, final Integer reps, final Double time, Exercise exercise) 
	{
		RoutineLine routineLine = new RoutineLine();
		routineLine.setId(id);
		routineLine.setReps(reps);
		routineLine.setTime(time);
		routineLine.setWeight(50.0);
		routineLine.setSeries(5);
		routineLine.setExercise(exercise);

		return routineLine;
	}

	protected Routine createRoutine(final int id, Collection<RoutineLine> routinesLines) 
	{
		Routine routine = new Routine();
		routine.setName("Routine Test");
		routine.setDescription("Routine Description Test");
		routine.setRepsPerWeek(5);
		routine.setRoutineLine(routinesLines);

		return routine;
	}

	protected Client createClient(final int id, final String username) 
	{
		Client client = new Client();
		User user_client = new User();
		user_client.setUsername(username);
		user_client.setEnabled(true);
		client.setUser(user_client);
		client.setId(id);

		return client;
	}

	protected Trainer createTrainer(String trainerUsername, Collection<Client> clients) 
	{
		Trainer trainer = new Trainer();
		User user_trainer = new User();
		user_trainer.setUsername(trainerUsername);
		user_trainer.setEnabled(true);
		trainer.setUser(user_trainer);
		trainer.setClients(clients);

		return trainer;
	}

	protected Training createTraining(final int id, final Client client, Collection<Routine> routines, final int DaysToFinishTraining) 
	{
		Calendar cal = Calendar.getInstance();

		Training training = new Training();
		training.setId(id);
		training.setName("training 1");
		training.setClient(client);
		training.setInitialDate(cal.getTime());

		cal.add(Calendar.DAY_OF_MONTH, DaysToFinishTraining);
		training.setEndDate(cal.getTime());
		training.setDiet(null);
		training.setRoutines(routines);

		return training;
	}
}
