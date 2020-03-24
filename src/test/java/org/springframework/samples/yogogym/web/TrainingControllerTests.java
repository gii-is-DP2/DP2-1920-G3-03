package org.springframework.samples.yogogym.web;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@WebMvcTest(value = TrainingController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class TrainingControllerTests {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String TRAINER2_USERNAME = "trainer2";
	
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT2_ID = 2;
	
	private static final int CLIENT1_TRAINING_ID = 1;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private final static Calendar INITIAL_DATE_CAL = Calendar.getInstance();
	private static Calendar endDateCal = null;
	private static Date initialDate = null;
	private static Date endDate = null;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private TrainerService trainerService;
	
	@MockBean
	private TrainingService trainingService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		initialDate = INITIAL_DATE_CAL.getTime();
		endDateCal = (Calendar) INITIAL_DATE_CAL.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 7);
		endDate = endDateCal.getTime();
		
		//Trainer1
		Collection<Client> clientsTrainer1 = new ArrayList<>();
				
		Client client1 = new Client();
		User userClient1 = new User();
		userClient1.setUsername("client1");
		userClient1.setEnabled(true);
		client1.setUser(userClient1);
		client1.setId(CLIENT1_ID);
		
		clientsTrainer1.add(client1);
		
		Trainer trainer1 = new Trainer();
		User userTrainer1 = new User();
		userTrainer1.setUsername(TRAINER1_USERNAME);
		userTrainer1.setEnabled(true);
		trainer1.setUser(userTrainer1);
		trainer1.setClients(clientsTrainer1);
		
		Training training1 = new Training();
		training1.setId(CLIENT1_TRAINING_ID);
		training1.setName("Training 1");
		training1.setClient(client1);
		training1.setInitialDate(initialDate);
		training1.setEndDate(endDate);
		training1.setDiet(null);
		training1.setRoutines(null);
		
		//Trainer2
		Collection<Client> clientsTrainer2 = new ArrayList<>();
		
		Client client2 = new Client();
		User userClient2 = new User();
		userClient2.setUsername("client2");
		userClient2.setEnabled(true);
		client2.setUser(userClient2);
		client2.setId(CLIENT2_ID);
		
		clientsTrainer2.add(client2);
		
		Trainer trainer2 = new Trainer();
		User userTrainer2 = new User();
		userTrainer2.setUsername(TRAINER2_USERNAME);
		userTrainer2.setEnabled(true);
		trainer2.setUser(userTrainer2);
		trainer2.setClients(clientsTrainer2);
		
		given(this.clientService.findClientById(CLIENT1_ID)).willReturn(client1);
		given(this.clientService.findClientById(CLIENT2_ID)).willReturn(client2);
		given(this.trainerService.findTrainer(TRAINER1_USERNAME)).willReturn(trainer1);
		given(this.trainerService.findTrainer(TRAINER2_USERNAME)).willReturn(trainer2);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING_ID)).willReturn(training1);
		
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
		testWrongAuth(0,"/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testClientTrainingList() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("trainer"))
		.andExpect(model().attributeExists("actualDate"))
		.andExpect(view().name("trainer/trainings/trainingsList"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testClientTrainingDetails() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID))
		 		.andExpect(status().isOk())
				.andExpect(model().attributeExists("client"))
				.andExpect(model().attributeExists("training"))
				.andExpect(model().attribute("training", hasProperty("name", is("Training 1"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
				.andExpect(model().attribute("training", hasProperty("routines", nullValue())))
				.andExpect(model().attribute("training", hasProperty("diet", nullValue())))
				.andExpect(view().name("trainer/trainings/trainingsDetails"));
	}

	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testInitTrainingCreationForm() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
		 		.andExpect(model().attributeExists("training"))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	 
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
				.with(csrf())	
			 	.param("name", "Training 2")
			 	.param("initialDate", dateFormat.format(initialDate))
			 	.param("endDate", dateFormat.format(endDate))
			 	.param("client", "client2,"+CLIENT2_ID))
				.andExpect(status().is3xxRedirection())
		 		.andExpect(view().name("redirect:/trainer/"+TRAINER2_USERNAME+"/trainings"));
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
			 	.with(csrf())
			 	.param("name", "")
			 	.param("initialDate", dateFormat.format(initialDate))
			 	.param("endDate", dateFormat.format(endDate))
			 	.param("client", "client2,"+CLIENT2_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testInitTrainingUpdateForm() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID))
		 		.andExpect(status().isOk())
		  		.andExpect(model().attributeExists("client"))
		  		.andExpect(model().attributeExists("endDateAux"))
		  		.andExpect(model().attributeExists("actualDate"))
		  		.andExpect(model().attributeExists("training"))
		  		.andExpect(model().attribute("training", hasProperty("name", is("Training 1"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}

    @WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessTrainingUpdateFormSuccess() throws Exception {   
    	
    	Calendar aux = (Calendar) INITIAL_DATE_CAL.clone();
    	aux.add(Calendar.DAY_OF_MONTH, 14);
    	Date endDateUpdated = aux.getTime();
    	
    	mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID)
    			.with(csrf())
    			.param("name", "Training 1 Updated")
    			.param("initialDate", dateFormat.format(initialDate))
				.param("endDate", dateFormat.format(endDateUpdated))
				.param("client", "client1,"+CLIENT1_ID))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}"));
	}

    @WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessTrainingUpdateFormHasErrorsName() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID)
				.with(csrf())	
				.param("name", "")
				.param("initialDate", dateFormat.format(initialDate))
				.param("endDate", dateFormat.format(endDate))
				.param("client", "client1,"+CLIENT1_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessTrainingDeleteForm() throws Exception {
    	mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING_ID))
    			.andExpect(status().is3xxRedirection())
    			.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
    }
	 
}
