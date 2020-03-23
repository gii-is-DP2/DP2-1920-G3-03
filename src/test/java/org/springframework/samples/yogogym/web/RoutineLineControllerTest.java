package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.RoutineLineService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = RoutineLineController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class RoutineLineControllerTest {
	
	//Trainer 1
	private static final int testExerciseId_t1 = 1;
	
	private static final int testRoutineLineId_t1 = 1;
	
	private static final int testRoutineId_t1 = 1;
	
	private static final String testTrainerUsername_t1 = "trainer1";
	
	private static final int testClientId_t1 = 1;
	
	private static final int testTrainingId_t1 = 1;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar testInitialTrainingDate = Calendar.getInstance();
	Calendar testEndTrainingDate = testInitialTrainingDate;
	
	//Trainer 2
	private static final int testClientId_t2 = 2;
	private static final String testTrainerUsername_t2 = "trainer2";
	
	@MockBean
	private RoutineService routineService;
	
	@MockBean
	private ExerciseService exerciseService;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private RoutineLineService routineLineService;
	
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
		Exercise exercise = new Exercise();
		exercise.setId(testExerciseId_t1);
		exercise.setName("Exercise name test");
		exercise.setDescription("Description Exercise test");
		exercise.setKcal(20);
		exercise.setRepetitionType(RepetitionType.REPS);
		exercise.setBodyPart(BodyParts.ALL);
		
		Collection<Exercise> allExercises = new ArrayList<>();
		allExercises.add(exercise);
		
		RoutineLine routineLine = new RoutineLine();
		routineLine.setId(testRoutineLineId_t1);
		routineLine.setReps(5);
		routineLine.setTime(null);
		routineLine.setWeight(50.0);
		routineLine.setSeries(5);
		routineLine.setExercise(exercise);
				
		Collection<RoutineLine> routinesLines = new ArrayList<>();
		routinesLines.add(routineLine);
		
		Routine routine= new Routine();
		routine.setName("Routine Test");
		routine.setDescription("Routine Description Test");
		routine.setRepsPerWeek(5);
		routine.setRoutineLine(routinesLines);
		
		Client client = new Client();
		User user_client = new User();
		user_client.setUsername("client1");
		user_client.setEnabled(true);
		client.setUser(user_client);
		client.setId(testClientId_t1);
		
		Collection<Client> clients = new ArrayList<>();
		clients.add(client);
		
		Trainer trainer = new Trainer();
		User user_trainer = new User();
		user_trainer.setUsername(testTrainerUsername_t1);
		user_trainer.setEnabled(true);
		trainer.setUser(user_trainer);
		trainer.setClients(clients);
				
		Date initialDate = testInitialTrainingDate.getTime();
		
		testEndTrainingDate.add(Calendar.DAY_OF_MONTH, 1);
		Date endDate = testEndTrainingDate.getTime();
		
		Collection<Routine> routines = new ArrayList<>();
		
		Training training = new Training();
		training.setId(testTrainingId_t1);
		training.setName("training 1");
		training.setClient(client);
		training.setInitialDate(initialDate);
		training.setEndDate(endDate);
		training.setDiet(null);
		training.setRoutines(routines);
		
		given(this.clientService.findClientById(testClientId_t1)).willReturn(client);
		given(this.trainerService.findTrainer(testTrainerUsername_t1)).willReturn(trainer);
		given(this.exerciseService.findExerciseById(testExerciseId_t1)).willReturn(exercise);
		given(this.exerciseService.findAllExercise()).willReturn(allExercises);
		given(this.routineService.findRoutineById(testRoutineId_t1)).willReturn(routine);
		given(this.routineLineService.findRoutineLineById(testRoutineLineId_t1)).willReturn(routineLine);
		given(this.trainingService.findTrainingById(testTrainingId_t1)).willReturn(training);
		
		//Trainer 2
		Client client_t2 = new Client();
		User user_client_t2 = new User();
		user_client_t2.setUsername("client2");
		user_client_t2.setEnabled(true);
		client_t2.setUser(user_client_t2);
		client_t2.setId(testClientId_t2);
		
		Collection<Client> clients_t2 = new ArrayList<>();
		clients_t2.add(client_t2);
		
		Trainer trainer_t2 = new Trainer();
		User user_trainer_t2 = new User();
		user_trainer_t2.setUsername(testTrainerUsername_t2);
		user_trainer_t2.setEnabled(true);
		trainer_t2.setUser(user_trainer_t2);
		trainer_t2.setClients(clients_t2);
		
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
		//Authority is not trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t2,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t2,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t2,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t2,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
	}
	
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception
	{
		// Wrong trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1);
	}
	
			
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateRoutineLineForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCreateRoutineLineForm() throws Exception
	{						
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(5);
		routineLine.setWeight(50.0);
		routineLine.setSeries(5);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1)
			.with(csrf())
			.param("routineId", String.valueOf(testRoutineId_t1))
			.param("reps", routineLine.getReps().toString())
			.param("time", "")
			.param("weight", routineLine.getWeight().toString())
			.param("series", routineLine.getSeries().toString())
			.param("exercise.id",String.valueOf(testExerciseId_t1)))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername_t1 + "/clients/" + testClientId_t1 + "/trainings/"
				+ testTrainingId_t1 + "/routines/" + testRoutineId_t1));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitUpdateRoutineLineForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessUpdateRoutineLineForm() throws Exception
	{						
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(5);
		routineLine.setWeight(50.0);
		routineLine.setSeries(5);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1)
			.with(csrf())
			.param("routineId", String.valueOf(testRoutineId_t1))
			.param("reps", routineLine.getReps().toString())
			.param("time", "")
			.param("weight", routineLine.getWeight().toString())
			.param("series", routineLine.getSeries().toString())
			.param("exercise.id",String.valueOf(testExerciseId_t1)))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername_t1 + "/clients/" + testClientId_t1 + "/trainings/"
				+ testTrainingId_t1 + "/routines/" + testRoutineId_t1));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testDeleteRoutineLine() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/delete",testTrainerUsername_t1,testClientId_t1,testTrainingId_t1,testRoutineId_t1,testRoutineLineId_t1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+ testTrainerUsername_t1 + "/clients/" + testClientId_t1 + "/trainings/" + testTrainingId_t1 + "/routines/" + testRoutineId_t1));
	}
	
}
