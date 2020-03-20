package org.springframework.samples.yogogym.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
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
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.RoutineService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = RoutineController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class RoutineControllerTest {
	
	private static final int testRoutineId = 1;
	private static final String testTrainerUsername = "trainer1";
	private static final int testClientId = 1;
	private static final int testTrainingId = 1;
	
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
		Client client = new Client();
		User user_client = new User();
		user_client.setUsername("client1");
		user_client.setEnabled(true);
		client.setUser(user_client);
		client.setId(testClientId);
		
		Collection<Client> clients = new ArrayList<>();
		clients.add(client);
		
		Trainer trainer = new Trainer();
		User user_trainer = new User();
		user_trainer.setUsername(testTrainerUsername);
		user_trainer.setEnabled(true);
		trainer.setUser(user_trainer);
		trainer.setClients(clients);
				
		
		given(this.clientService.findClientById(testClientId)).willReturn(client);
		given(this.trainerService.findTrainer(testTrainerUsername)).willReturn(trainer);
	}

	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitGetRoutineDetails() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/routines/{routineId}",testTrainerUsername,testClientId,testTrainingId,testRoutineId))
			.andExpect(status().isOk())
			.andExpect(view().name("trainer/routines/routineDetails"))
			.andDo(print());
	}
	
	//"
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitGetRoutines() throws Exception
	{
		mockMvc.perform(get("/trainer/{trainerUsername}/routines",testTrainerUsername))
			.andExpect(status().isOk())
			.andExpect(view().name("trainer/routines/routinesList"))
			.andDo(print());
	}
	
}
