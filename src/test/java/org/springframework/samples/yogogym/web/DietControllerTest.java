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
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Enums.*;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = DietController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class DietControllerTest {
	
	private static final int testDietId = 1;
	
	private static final String testTrainerUsername = "trainer1";
	
	private static final int testClientId = 1;
	
	private static final int testTrainingId = 1;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar testInitialTrainingDate = Calendar.getInstance();
		
	//Trainer 2
	private static final String testTrainerUsername_t2 = "trainer2";
	
	private static final int testClientId_t2 = 2;
	
	@MockBean
	private DietService dietService;
	
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

		Diet d= new Diet();
		d.setName("DietTest");
		d.setDescription("Test");
		d.setType(DietType.MAINTENANCE);
		d.setKcal(10);
		d.setCarb(10);
		d.setProtein(10);
		d.setFat(10);

		Client client = new Client();
		User user_client = new User();
		user_client.setUsername("client1");
		user_client.setEnabled(true);
		client.setUser(user_client);
		client.setId(testClientId);
		client.setAge(18);
		client.setHeight(180.0);
		client.setWeight(70.0);
		client.setFatPercentage(20.0);
		
		Collection<Client> clients = new ArrayList<>();
		clients.add(client);
		
		Trainer trainer = new Trainer();
		User user_trainer = new User();
		user_trainer.setUsername(testTrainerUsername);
		user_trainer.setEnabled(true);
		trainer.setUser(user_trainer);
		trainer.setClients(clients);
				
		Date initialDate = testInitialTrainingDate.getTime();
		
		testInitialTrainingDate.add(Calendar.DAY_OF_MONTH, 1);
		Date endDate = testInitialTrainingDate.getTime();
		
		Collection<Routine> routines = new ArrayList<>();
		
		Training training = new Training();
		training.setId(testTrainingId);
		training.setName("training 1");
		training.setInitialDate(initialDate);
		training.setEndDate(endDate);
		training.setDiet(null);
		training.setRoutines(routines);
		
		given(this.clientService.findClientById(testClientId)).willReturn(client);
		given(this.trainerService.findTrainer(testTrainerUsername)).willReturn(trainer);
		given(this.trainingService.findTrainingById(testTrainingId)).willReturn(training);
		given(this.dietService.findDietById(testDietId)).willReturn(d);
		
		//Trainer 2
		Client client_t2 = new Client();
		User user_client_t2 = new User();
		user_client_t2.setUsername("client2");
		user_client_t2.setEnabled(true);
		client_t2.setUser(user_client_t2);
		client_t2.setId(testClientId_t2);
		client_t2.setAge(40);
		client_t2.setHeight(160.0);
		client_t2.setWeight(90.0);
		client_t2.setFatPercentage(40.0);
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
		// Authority is not trainer
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,testClientId,testTrainingId,testDietId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId_t2,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId_t2,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId_t2,testTrainingId,testDietId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId_t2,testTrainingId,testDietId);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception
	{
		// Wrong trainer
		// testWrongAuth(0,"/trainer/{trainerUsername}/routines",testTrainerUsername);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,testClientId,testTrainingId,testDietId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId);
		// testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/delete",testTrainerUsername,testClientId,testTrainingId,testDietId);
	}
	

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetDietDetails() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,testClientId,testTrainingId,testDietId))
			.andExpect(status().isOk())
			.andExpect(view().name("trainer/diets/dietsDetails"))
			.andDo(print());
	}
			
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateDietForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"))
		.andExpect(model().attributeExists("diet"));
	}
	
	void testProcessCorrectCreateDietForm(String name, String description, DietType dietType) throws Exception
	{		
		
		Diet diet= new Diet();
		diet.setName(name);
		diet.setDescription(description);
		diet.setType(dietType);
		diet.setKcal(10);
		diet.setCarb(10);
		diet.setProtein(10);
		diet.setFat(10);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("carb", diet.getCarb().toString())
			.param("protein", diet.getProtein().toString())
			.param("kcal", diet.getKcal().toString())
			.param("fat", diet.getFat().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+ testTrainerUsername + "/clients/" + testClientId + "/trainings/"+testTrainingId));
	
	}

	void testProcessWrongCreateDietForm(String name, String description, DietType dietType) throws Exception
	{		
		
		Diet diet= new Diet();
		diet.setName(name);
		diet.setDescription(description);
		diet.setType(dietType);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString()))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"));
	
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitUpdateDietForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"))
		.andExpect(model().attributeExists("diet"));
	}
	
	void testProcessCorrectUpdateDietForm(String name, String description, DietType dietType, 
	Integer kcal, Integer carb, Integer protein, Integer fat) throws Exception
	{
		
		Diet d= new Diet();
		d.setName(name);
		d.setDescription(description);
		d.setType(dietType);

		d.setKcal(kcal);
		d.setCarb(carb);
		d.setProtein(protein);
		d.setFat(fat);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId)
			.with(csrf())
			.param("name", d.getName())
			.param("description", d.getDescription())
			.param("type",d.getType().toString())
			.param("kcal",d.getKcal().toString())
			.param("carb",d.getCarb().toString())
			.param("protein",d.getProtein().toString())
			.param("fat",d.getFat().toString()))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername + "/clients/" + testClientId + "/trainings/" + testTrainingId + "/diets/" + testDietId));
	}

	void testProcessWrongUpdateDietForm(String name, String description, DietType dietType, 
	Integer kcal, Integer carb, Integer protein, Integer fat) throws Exception
	{
		
		Diet d= new Diet();
		d.setName(name);
		d.setDescription(description);
		d.setType(dietType);

		d.setKcal(kcal);
		d.setCarb(carb);
		d.setProtein(protein);
		d.setFat(fat);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId)
			.with(csrf())
			.param("name", d.getName())
			.param("description", d.getDescription())
			.param("type",d.getType().toString())
			.param("kcal",d.getKcal().toString())
			.param("carb",d.getCarb().toString())
			.param("protein",d.getProtein().toString())
			.param("fat",d.getFat().toString()))

		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"));
	}

	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCorrectCreateDietForm() throws Exception{
		testProcessCorrectCreateDietForm("diet1", "description 1", DietType.DEFINITION);
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessWrongCreateDietForm() throws Exception{
		testProcessWrongCreateDietForm(null, null, DietType.DEFINITION);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCorrectUpdateDietForm() throws Exception{
		testProcessCorrectUpdateDietForm("diet1", "description 1", DietType.DEFINITION, 10,10,10,10);
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessWrongtUpdateDietForm() throws Exception{
		testProcessWrongUpdateDietForm("diet1", "description 1", DietType.DEFINITION, -10,-10,-10,-10);
	}
}
