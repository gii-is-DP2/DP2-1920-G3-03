package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Enums.DietType;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class DietControllerE2ETest {
	
	private static final int testDietId = 1;
	
	private static final String testTrainerUsername = "trainer1";
	
	private static final int testClientId = 1;
	
	private static final int testTrainingId = 1;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar testInitialTrainingDate = Calendar.getInstance();
		
	//Trainer 2
	private static final String testTrainerUsername_t2 = "trainer2";
	
	private static final int testClientId_t2 = 2;
	
	@Autowired
	private DietService dietService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp()
	{
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
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception
	{
		// Wrong client id
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,3,testTrainingId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,3,testTrainingId);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,3,testTrainingId,testDietId);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,3,testTrainingId,testDietId);
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
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingId)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString()))
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

	
	// @WithMockUser(username="trainer1", authorities= {"trainer"})
	// @Test
	// void testProcessCorrectCreateDietForm() throws Exception{
	// 	testProcessCorrectCreateDietForm("diet1", "description 1", DietType.DEFINITION);
	// }

	// @WithMockUser(username="trainer1", authorities= {"trainer"})
	// @Test
	// void testProcessWrongCreateDietForm() throws Exception{
	// 	testProcessWrongCreateDietForm(null, null, DietType.DEFINITION);
	// }
	
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
