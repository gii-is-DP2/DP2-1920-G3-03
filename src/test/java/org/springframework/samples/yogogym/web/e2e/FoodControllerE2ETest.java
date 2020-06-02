package org.springframework.samples.yogogym.web.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.service.FoodService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class FoodControllerE2ETest {

	@Autowired
	FoodService foodService;

	@Autowired
	private MockMvc mockMvc;

	private static final String CLIENT2_USERNAME = "client2";
	
	@BeforeEach
	void setup() {

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
	void testWrongClients() throws Exception
	{
	
		// Wrong client USERNAME
		testWrongAuth(0,"/client/{clientUsername}/diets",CLIENT2_USERNAME);

	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void testGetFoods() throws Exception {
		String clientUsername = "client1";
		Integer trainingId = 9;
		Integer dietId = 5;
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/diets/{dietId}/foods", clientUsername,trainingId,dietId)).andExpect(status().isOk())
		.andExpect(view().name("client/foods/foodsList"));
	}
}
