package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Food;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.model.Enums.FoodType;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.DietService;
import org.springframework.samples.yogogym.service.FoodService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = FoodController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class FoodControllerTests {
	
	private static final Integer DIET2_ID = 2;
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	private static final int CLIENT1_ID = 1;
	private static final Integer TRAINING2_ID = 2;
	private static final String NIF1 = "29639287H";
	

	
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
		void setup() {
		
		// Collection<Food> foods = this.foodService.findAllFoods();
		
		Client client1 = new Client();
		User userClient1 = new User();
		userClient1.setUsername(CLIENT1_USERNAME);
		userClient1.setEnabled(true);
		client1.setUser(userClient1);
		client1.setId(CLIENT1_ID);
		client1.setNif(NIF1);
		client1.setIsPublic(true);

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
		food2.setName("Apple");
		food2.setKcal(57);
		food2.setProtein(10);
		food2.setFat(20);
		food2.setCarb(30);
		food2.setWeight(20);
		food2.setFoodType(FoodType.BREAKFAST);
		Collection<Food> foods = new ArrayList<>();
		foods.add(food1);
		foods.add(food2);

		Training training1 = new Training();
		training1.setId(TRAINING2_ID);
		training1.setName("Training 1");
		Calendar cal =  Calendar.getInstance();
		training1.setInitialDate(cal.getTime());
		Calendar endCal =  Calendar.getInstance();
		endCal.add(Calendar.DAY_OF_MONTH, 12);
		training1.setEndDate(endCal.getTime());
		training1.setEditingPermission(EditingPermission.BOTH);
		training1.setAuthor(TRAINER1_USERNAME);
		training1.setDiet(null);
		training1.setRoutines(new ArrayList<>());

		// Diet diet = this.dietService.findDietById(dietId);
		Diet diet = new Diet();
		diet.setId(1);
		diet.setName("Diet");
		training1.setDiet(diet);

		given(this.clientService.findClientByUsername(CLIENT1_USERNAME)).willReturn(client1);
		given(this.foodService.findAllFoods()).willReturn(foods);
		given(this.trainingService.findTrainingById(TRAINING2_ID)).willReturn(training1);
		given(this.dietService.findDietById(DIET2_ID)).willReturn(diet);	
	}

	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testGetDietDetails() throws Exception
	{
	
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/foods",
		CLIENT1_USERNAME,TRAINING2_ID,DIET2_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("client/foods/foodsList"));
			
	}
	
	void testWrongAuth(int mode,String path,Object... uriVars) throws Exception {
		if(mode == 0) {
			mockMvc.perform(get(path,uriVars))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));			
		}
		else {
			mockMvc.perform(post(path,uriVars))
			.andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testWrongAuthority() throws Exception {
		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/foods",CLIENT2_USERNAME,TRAINING2_ID,DIET2_ID);

	}


}