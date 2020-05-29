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
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Enums.DietType;
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
	
	private static final int testClientId3 = 3;
	
	private static final int testTrainingId = 1;
	
	private static final int testTrainingIdActive = 9;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar testInitialTrainingDate = Calendar.getInstance();
		
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setUp(){
		
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
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}",testTrainerUsername,testClientId3);

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
	
	//Create Diet
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitCreateDietForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingIdActive))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"))
		.andExpect(model().attributeExists("diet"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCorrectCreateDietForm() throws Exception{		
		
		Diet diet= createDiet();
	
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingIdActive)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("kcal",diet.getKcal().toString())
			.param("carb",diet.getCarb().toString())
			.param("protein",diet.getProtein().toString())
			.param("fat",diet.getFat().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/"+testTrainerUsername+"/diets"));

	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessWrongtCreateDietForm() throws Exception{		
		
		Diet diet= createDiet();
		diet.setCarb(-10);
	
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/create",testTrainerUsername,testClientId,testTrainingIdActive)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("kcal",diet.getKcal().toString())
			.param("carb",diet.getCarb().toString())
			.param("protein",diet.getProtein().toString())
			.param("fat",diet.getFat().toString()))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"));

	}
	
	
	//update
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitUpdateDietForm() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId))
		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"))
		.andExpect(model().attributeExists("diet"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessCorrectUpdateDietForm() throws Exception{
		
	Diet diet = createDiet();
	
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("kcal",diet.getKcal().toString())
			.param("carb",diet.getCarb().toString())
			.param("protein",diet.getProtein().toString())
			.param("fat",diet.getFat().toString()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trainer/" + testTrainerUsername + "/clients/" + testClientId + "/trainings/" + testTrainingId + "/diets/" + testDietId));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessWrongUpdateDietForm() throws Exception
	{
		
		Diet diet = createDiet();
		diet.setCarb(-10);
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/diets/{dietId}/edit",testTrainerUsername,testClientId,testTrainingId,testDietId)
			.with(csrf())
			.param("name", diet.getName())
			.param("description", diet.getDescription())
			.param("type",diet.getType().toString())
			.param("kcal",diet.getKcal().toString())
			.param("carb",diet.getCarb().toString())
			.param("protein",diet.getProtein().toString())
			.param("fat",diet.getFat().toString()))

		.andExpect(status().isOk())
		.andExpect(view().name("trainer/diets/dietsCreateOrUpdate"));
	}

	//Derivated Methods
	
	protected Diet createDiet() {
		
		Diet diet= new Diet();
		diet.setName("diet1");
		diet.setDescription("description1");
		diet.setType(DietType.DEFINITION);
		diet.setCarb(10);
		diet.setFat(10);
		diet.setKcal(10);
		diet.setProtein(10);
		
		return diet;
	}
	
}
