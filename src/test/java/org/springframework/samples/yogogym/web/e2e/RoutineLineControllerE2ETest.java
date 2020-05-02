package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Exercise;
import org.springframework.samples.yogogym.model.RoutineLine;
import org.springframework.samples.yogogym.model.Enums.BodyParts;
import org.springframework.samples.yogogym.model.Enums.RepetitionType;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class RoutineLineControllerE2ETest {
	
	@Autowired
	RoutineService routineService;
	
	@Autowired
	ExerciseService exerciseService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

	}
	
	//TRAINER =================================================================================

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
		
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		String trainerUsername = "trainer1";
		int clientId = 3;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
	}
	
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception
	{
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		// Wrong trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testNotExistingRoutine() throws Exception
	{
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 100;
		int routineLineId = 12;
		
		// Wrong trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername, clientId, trainingId,routineId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testNotExistingRoutineLine() throws Exception
	{
		
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 100;
		
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername, clientId, trainingId,routineId,routineLineId);
	}
	
			
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateRoutineLineForm() throws Exception
	{
		
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername,clientId,trainingId,routineId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCreateRoutineLineForm() throws Exception
	{	
		String trainerUsername = "trainer1";

		int exerciseId = 1;
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		RoutineLine routineLine = createRoutineLine(10, null, 10, 50.0, exercise);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/create",trainerUsername,clientId,trainingId,routineId)
			.with(csrf())
			.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
			.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
			.param("weight", routineLine.getWeight().toString())
			.param("series", routineLine.getSeries().toString())
			.param("exercise.id",String.valueOf(routineLine.getExercise().getId())))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/"
					+ trainingId + "/routines/" + routineId));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitUpdateRoutineLineForm() throws Exception
	{
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername,clientId,trainingId,routineId,routineLineId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessUpdateRoutineLineForm() throws Exception
	{						
		String trainerUsername = "trainer1";

		int exerciseId = 1;
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		RoutineLine routineLine = createRoutineLine(10, null, 10, 50.0, exercise);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",trainerUsername,clientId,trainingId,routineId,routineLineId)
			.with(csrf())
			.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
			.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
			.param("weight", routineLine.getWeight().toString())
			.param("series", routineLine.getSeries().toString())
			.param("exercise.id",String.valueOf(routineLine.getExercise().getId())))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/"
				+ trainingId + "/routines/" + routineId));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testDeleteRoutineLine() throws Exception
	{
		String trainerUsername = "trainer1";
		int clientId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
				
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/delete",trainerUsername,clientId,trainingId,routineId,routineLineId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+ trainerUsername + "/clients/" + clientId + "/trainings/" + trainingId + "/routines/" + routineId));
	}
	
	//CLIENT ============================================================================================================
	
	//Create
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testInitCreateRoutineLine_Client() throws Exception
	{
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId))
		.andExpect(status().isOk())
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLine_Client() throws Exception
	{
		String clientUsername = "client1";
		int exerciseId = 1;
		int trainingId = 9;
		int routineId = 9;
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		RoutineLine routineLine = createRoutineLine(10, null, 5, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id",String.valueOf(routineLine.getExercise().getId())))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}
	
	//Update
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testInitUpdateRoutineLine_Client() throws Exception
	{
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",clientUsername,trainingId,routineId,routineLineId))
		.andExpect(status().isOk())
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"))
		.andExpect(model().attributeExists("routineLine"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessUpdateRoutineLine_Client() throws Exception
	{
		String clientUsername = "client1";
		int exerciseId = 1;
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		Exercise exercise = this.exerciseService.findExerciseById(exerciseId);
		RoutineLine routineLine = createRoutineLine(10, null, 5, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/update",clientUsername,trainingId,routineId,routineLineId)
		.with(csrf())
		.param("routineId", String.valueOf(routineId))
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id",String.valueOf(routineLine.getExercise().getId())))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}
	
	//Delete
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testDeleteRoutineLine_Client() throws Exception
	{
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		int routineLineId = 12;
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/{routineLineId}/delete",clientUsername,trainingId,routineId,routineLineId))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/client/" + clientUsername + "/trainings/" + trainingId));
	}	
	
	//Bad information
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLineEmpty_Client() throws Exception
	{	
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
				
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", "")
		.param("time", "")
		.param("weight","")
		.param("series", "")
		.param("exercise.id","1"))
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLineBadSeries_Client() throws Exception
	{		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
				
		Exercise exercise = createExercise(RepetitionType.REPS);
		RoutineLine routineLine = createRoutineLine(10, null, 50, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id","1"))
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLineBadReps_Client() throws Exception
	{		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
				
		Exercise exercise = createExercise(RepetitionType.TIME);
		RoutineLine routineLine = createRoutineLine(10, null, 50, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id","1"))
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLineBadTime_Client() throws Exception
	{		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Exercise exercise = createExercise(RepetitionType.REPS);
		RoutineLine routineLine = createRoutineLine(null, 10.0, 50, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id","1"))
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"));
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testProcessCreateRoutineLineRepsTimeSetted_Client() throws Exception
	{		
		String clientUsername = "client1";
		int trainingId = 9;
		int routineId = 9;
		
		Exercise exercise = createExercise(RepetitionType.REPS);
		RoutineLine routineLine = createRoutineLine(10, 10.0, 50, 5.0, exercise);
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/routines/{routineId}/routineLine/create",clientUsername,trainingId,routineId)
		.with(csrf())
		.param("reps", (routineLine.getReps()==null)?"":routineLine.getReps().toString())
		.param("time", (routineLine.getTime()==null)?"":routineLine.getTime().toString())
		.param("weight", routineLine.getWeight().toString())
		.param("series", routineLine.getSeries().toString())
		.param("exercise.id","1"))
		.andExpect(view().name("client/routines/routinesLineCreateOrUpdate"));
	}
	
	//Derived Methods
	protected Exercise createExercise(RepetitionType type)
	{
		Exercise exercise = new Exercise();
		exercise.setName("Exercise name test");
		exercise.setDescription("Description Exercise test");
		exercise.setKcal(20);
		exercise.setRepetitionType(type);
		exercise.setBodyPart(BodyParts.ALL);
		
		return exercise;
	}
		
	protected RoutineLine createRoutineLine(final Integer reps, final Double time, final Integer series, final Double weight, Exercise exercise)
	{
		RoutineLine routineLine = new RoutineLine();
		routineLine.setReps(reps);
		routineLine.setTime(time);
		routineLine.setWeight(weight);
		routineLine.setSeries(series);
		routineLine.setExercise(exercise);
		
		return routineLine;
	}
}
