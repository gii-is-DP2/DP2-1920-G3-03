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
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.model.Enums.*;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.FoodService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = DietController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class DietControllerTest {
	
	private static final String testTrainerUsername = "trainer1";	
	private static final int CLIENT1_ID = 1;	
	private static final Integer DIET1_ID = 1;
	private static final String CLIENT1_USERNAME = "client1";
	private static final Integer TRAINING1_ID = 1;
		
	//Trainer 2
	private static final String testTrainerUsername_t2 = "trainer2";
	
	private static final int CLIENT1_ID_t2 = 2;
	
	@MockBean
	private DietService dietService;
	
	@MockBean
	private ClientService clientService;

	@MockBean
	private TrainerService trainerService;
	
	@MockBean
	private TrainingService trainingService;
	
	@MockBean
	private FoodService foodService;

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

		Food food1 = new Food();
		food1.setId(1);
		food1.setName("Bread");
		food1.setKcal(80);
		food1.setProtein(20);
		food1.setFat(20);
		food1.setCarb(30);
		food1.setWeight(20);
		food1.setFoodType(FoodType.BRANCH);

		Food food2 = new Food();
		food2.setId(2);
		food2.setName("Bread");
		food2.setKcal(80);
		food2.setProtein(20);
		food2.setFat(20);
		food2.setCarb(30);
		food2.setWeight(20);
		food2.setFoodType(FoodType.BRANCH);

		Collection<Food> foods_d = new ArrayList<>();
		foods_d.add(food1);
		foods_d.add(food2);
		d.setFoods(foods_d);

		Client client = new Client();
		User user_client = new User();
		user_client.setUsername("client1");
		user_client.setEnabled(true);
		client.setUser(user_client);
		client.setId(CLIENT1_ID);
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
				
		Calendar testInitialTrainingDate = Calendar.getInstance();

		Date initialDate = testInitialTrainingDate.getTime();
		testInitialTrainingDate.add(Calendar.DAY_OF_MONTH, 1);
		Date endDate = testInitialTrainingDate.getTime();
		
		Collection<Routine> routines = new ArrayList<>();
				
		Training training = new Training();
		training.setId(TRAINING1_ID);
		training.setName("training 1");
		training.setInitialDate(initialDate);
		training.setEndDate(endDate);
		training.setDiet(d);
		training.setRoutines(routines);
		
		given(this.clientService.findClientById(CLIENT1_ID)).willReturn(client);
		given(this.trainerService.findTrainer(testTrainerUsername)).willReturn(trainer);
		given(this.trainingService.findTrainingById(TRAINING1_ID)).willReturn(training);
		given(this.dietService.findDietById(DIET1_ID)).willReturn(d);
		
		//Trainer 2
		Client client_t2 = new Client();
		User user_client_t2 = new User();
		user_client_t2.setUsername("client2");
		user_client_t2.setEnabled(true);
		client_t2.setUser(user_client_t2);
		client_t2.setId(CLIENT1_ID_t2);
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
		
		given(this.clientService.findClientById(CLIENT1_ID_t2)).willReturn(client_t2);
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
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID_t2,TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID_t2,TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID_t2,TRAINING1_ID,DIET1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID_t2,TRAINING1_ID,DIET1_ID);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception
	{
		// Wrong trainer
		// testWrongAuth(0,"/trainer/{trainerUsername}/routines",testTrainerUsername);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
		// testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/delete",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID);
	}
	

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testGetDietDetails() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("trainer/diets/dietsDetails"))
			.andDo(print());
	}
			
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateDietForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID))
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
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("carb", diet.getCarb().toString())
			.param("protein", diet.getProtein().toString())
			.param("kcal", diet.getKcal().toString())
			.param("fat", diet.getFat().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+testTrainerUsername+"/diets"));
	
	}

	void testProcessWrongCreateDietForm(String name, String description, DietType dietType) throws Exception
	{		
		
		Diet diet= new Diet();
		diet.setName(name);
		diet.setDescription(description);
		diet.setType(dietType);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,CLIENT1_ID,TRAINING1_ID)
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
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID))
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
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID)
			.with(csrf())
			.param("name", d.getName())
			.param("description", d.getDescription())
			.param("type",d.getType().toString())
			.param("kcal",d.getKcal().toString())
			.param("carb",d.getCarb().toString())
			.param("protein",d.getProtein().toString())
			.param("fat",d.getFat().toString()))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername + "/clients/" + CLIENT1_ID + "/trainings/" + TRAINING1_ID + "/diets/" + DIET1_ID));
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
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,CLIENT1_ID,TRAINING1_ID,DIET1_ID)
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
	//FOODS
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testShowFoods() throws Exception
	{
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/showFoods",
		CLIENT1_USERNAME,TRAINING1_ID,DIET1_ID))
			.andExpect(status().isOk())
			// .andExpect(view().name("client/foods/foodsOnDiet"))
			.andDo(print());
	}

	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testAddFood() throws Exception
	{

		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/food/{foodId}/addFood",
		CLIENT1_USERNAME,TRAINING1_ID,DIET1_ID,4))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(
				"redirect:/client/"+CLIENT1_USERNAME+"/trainings/"+TRAINING1_ID+"/diets/"+DIET1_ID));
	}

	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testDeleteFoods() throws Exception
	{

		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/showFoods/delete",
		CLIENT1_USERNAME,TRAINING1_ID,DIET1_ID))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(
				"redirect:/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/showFoods"))
				.andDo(print());
	}

	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testDeleteFood() throws Exception
	{

		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/food/{foodId}/deleteFood",
		CLIENT1_USERNAME,TRAINING1_ID,DIET1_ID,1))
			.andExpect(status().isOk())
			.andExpect(view().name(
				"redirect:/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/showFoods"))
				.andDo(print());
	} 

}