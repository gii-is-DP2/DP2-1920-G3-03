package org.springframework.samples.yogogym.web;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.samples.yogogym.configuration.SecurityConfiguration;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Diet;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainerService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
import org.springframework.context.annotation.FilterType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
	
	private static final String NIF1 = "12345678F";
	private static final String NIF2 = "12345678G";
	
	private static final int CLIENT1_TRAINING1_ID = 1;
	private static final int CLIENT1_TRAINING2_ID = 2;
	private static final int CLIENT1_TRAINING3_ID = 3;
	private static final int CLIENT1_TRAINING4_ID = 4;
	private static final int CLIENT1_TRAINING5_ID = 5;
	private static final int CLIENT1_TRAINING6_ID = 6;
	private static final int CLIENT2_TRAINING7_ID = 7;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static Date initialDate = null;
	private static Date endDate = null;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private TrainerService trainerService;
	
	@MockBean
	private TrainingService trainingService;
	
	@MockBean
	private ClientFormatter clientFormatter;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		Calendar initCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		initialDate = initCal.getTime();
		endCal.add(Calendar.DAY_OF_MONTH, 7);
		endDate = endCal.getTime();
		
		//Trainer1
		Collection<Client> clientsTrainer1 = new ArrayList<>();
				
		Client client1 = new Client();
		User userClient1 = new User();
		userClient1.setUsername("client1");
		userClient1.setEnabled(true);
		client1.setUser(userClient1);
		client1.setId(CLIENT1_ID);
		client1.setNif(NIF1);
		client1.setIsPublic(true);
		
		clientsTrainer1.add(client1);
		
		Trainer trainer1 = new Trainer();
		User userTrainer1 = new User();
		userTrainer1.setUsername(TRAINER1_USERNAME);
		userTrainer1.setEnabled(true);
		trainer1.setUser(userTrainer1);
		trainer1.setClients(clientsTrainer1);
		
		Training training1 = new Training();
		training1.setId(CLIENT1_TRAINING1_ID);
		training1.setName("Training 1");
		training1.setClient(client1);
		training1.setInitialDate(initialDate);
		training1.setEndDate(endDate);
		training1.setEditingPermission(EditingPermission.TRAINER);
		training1.setAuthor(TRAINER1_USERNAME);
		training1.setDiet(null);
		training1.setRoutines(new ArrayList<>());

		Training training2 = new Training();
		BeanUtils.copyProperties(training1, training2);
		training2.setId(CLIENT1_TRAINING2_ID);
		training2.setName("Training 2");
		training2.setEditingPermission(EditingPermission.CLIENT);
		training2.setAuthor(client1.getUser().getUsername());
		
		Training training3 = new Training();
		BeanUtils.copyProperties(training1, training3);
		training3.setId(CLIENT1_TRAINING3_ID);
		training3.setName("Training 3");
		training3.setEditingPermission(EditingPermission.BOTH);
		training3.setAuthor(TRAINER1_USERNAME);
		
		Training training4 = new Training();
		training4.setId(CLIENT1_TRAINING4_ID);
		training4.setName("Training 4");
		training4.setClient(client1);
		training4.setInitialDate(initialDate);
		training4.setEndDate(endDate);
		training4.setEditingPermission(EditingPermission.TRAINER);
		training4.setAuthor(TRAINER1_USERNAME);
		Diet diet = new Diet();
		diet.setId(1);
		diet.setName("Diet");
		training4.setDiet(diet);
		Routine routine = new Routine();
		routine.setId(1);
		routine.setName("Routine");
		Collection<Routine> routineList = new ArrayList<>();
		routineList.add(routine);
		training4.setRoutines(routineList);
		
		Training training5 = new Training();
		BeanUtils.copyProperties(training4, training5);
		training5.setId(CLIENT1_TRAINING5_ID);
		training5.setDiet(null);
		
		Training training6 = new Training();
		BeanUtils.copyProperties(training4, training6);
		training6.setId(CLIENT1_TRAINING6_ID);
		training6.setRoutines(new ArrayList<>());
		
		Collection<Training> trainingList = new ArrayList<>();
		trainingList.add(training1);
		trainingList.add(training2);
		trainingList.add(training3);
		trainingList.add(training4);
		trainingList.add(training5);
		trainingList.add(training6);
		Collection<Integer> trainingIdList = new ArrayList<>();
		trainingIdList.add(CLIENT1_TRAINING1_ID);
		trainingIdList.add(CLIENT1_TRAINING2_ID);
		trainingIdList.add(CLIENT1_TRAINING3_ID);
		trainingIdList.add(CLIENT1_TRAINING4_ID);
		trainingIdList.add(CLIENT1_TRAINING5_ID);
		trainingIdList.add(CLIENT1_TRAINING6_ID);
		
		//Trainer2
		Collection<Client> clientsTrainer2 = new ArrayList<>();
		
		Client client2 = new Client();
		User userClient2 = new User();
		userClient2.setUsername("client2");
		userClient2.setEnabled(true);
		client2.setUser(userClient2);
		client2.setId(CLIENT2_ID);
		client2.setNif(NIF2);
		client2.setIsPublic(true);
		
		clientsTrainer2.add(client2);
		
		Trainer trainer2 = new Trainer();
		User userTrainer2 = new User();
		userTrainer2.setUsername(TRAINER2_USERNAME);
		userTrainer2.setEnabled(true);
		trainer2.setUser(userTrainer2);
		trainer2.setClients(clientsTrainer2);
		
		Training training7 = new Training();
		training7.setId(CLIENT2_TRAINING7_ID);
		training7.setName("Training 2");
		training7.setClient(client2);
		training7.setInitialDate(initialDate);
		training7.setEndDate(endDate);
		training7.setEditingPermission(EditingPermission.TRAINER);
		training7.setAuthor(TRAINER2_USERNAME);
		training7.setDiet(null);
		training7.setRoutines(new ArrayList<>());
		
		Collection<Training> trainingList2 = new ArrayList<>();
		trainingList2.add(training7);
		
		given(this.clientService.findClientById(CLIENT1_ID)).willReturn(client1);
		given(this.clientService.findClientById(CLIENT2_ID)).willReturn(client2);
		given(this.trainerService.findTrainer(TRAINER1_USERNAME)).willReturn(trainer1);
		given(this.trainerService.findTrainer(TRAINER2_USERNAME)).willReturn(trainer2);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING1_ID)).willReturn(training1);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING2_ID)).willReturn(training2);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING3_ID)).willReturn(training3);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING4_ID)).willReturn(training4);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING5_ID)).willReturn(training5);
		given(this.trainingService.findTrainingById(CLIENT1_TRAINING6_ID)).willReturn(training6);
		given(this.trainingService.findTrainingFromClient(CLIENT2_ID)).willReturn(trainingList2);
		
		//Copy Training
		given(this.trainingService.findTrainingWithPublicClient()).willReturn(trainingList);
		given(this.trainingService.findTrainingIdFromClient(CLIENT1_ID)).willReturn(trainingIdList);
		given(this.trainingService.findTrainingIdFromClient(CLIENT2_ID)).willReturn(new ArrayList<>());
		try {
			given(this.clientFormatter.parse(NIF1, Locale.ENGLISH)).willReturn(client1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			given(this.clientFormatter.parse(NIF2, Locale.ENGLISH)).willReturn(client2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@WithMockUser(username="client1", authorities= {"client"})
	@Test
	void testWrongAuthority() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongAuthority() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testTrainerWrongClients() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testTrainerNoEditingPermission() throws Exception {
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING2_ID);
		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING2_ID);
		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING2_ID);
	}
	
	//TODO
//	@WithMockUser(username="client1", authorities= {"client"})
//	@Test
//	void testClientNoEditingPermission() throws Exception {
//		
//	}
	
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
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
				.andExpect(model().attributeExists("training"))
				.andExpect(model().attribute("training", hasProperty("name", is("Training 1"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("routines", is(new ArrayList<>()))))
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
			 	.param("editingPermission", EditingPermission.TRAINER.toString())
			 	.param("author", TRAINER2_USERNAME)
			 	.param("client", NIF2))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/"+CLIENT2_TRAINING7_ID));
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsEmptyParameters() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
			 	.with(csrf())
			 	.param("name", "")
			 	.param("initialDate", "")
			 	.param("endDate", "")
			 	.param("editingPermission", "")
			 	.param("author", TRAINER2_USERNAME)
			 	.param("client", NIF2))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().attributeHasFieldErrors("training", "editingPermission"))
				.andExpect(model().errorCount(4))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsPastInitDate() throws Exception {
		
		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(-1,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsPastEnd() throws Exception {
		
		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(0,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsEndBeforeEqualsInit() throws Exception {
		
		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(0,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsLongerThan90Days() throws Exception {
		
		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(0,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsInitInTraining() throws Exception {
		
		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(-1,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsEndInTraining() throws Exception {
		
		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(0,0);
	}
	
	@WithMockUser(username="trainer2", authorities= {"trainer"})
	@Test
	void testProcessTrainingCreationFormHasErrorsPeriodIncludingTraining() throws Exception {
		
		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
		
		performSamplePost(0,0);
	}
	
	@WithMockUser(username="trainer1", authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
	void testInitTrainingUpdateForm(int trainingId) throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
		 		.andExpect(status().isOk())
		  		.andExpect(model().attributeExists("client"))
		  		.andExpect(model().attributeExists("endDateAux"))
		  		.andExpect(model().attributeExists("actualDate"))
		  		.andExpect(model().attributeExists("training"))
		  		.andExpect(model().attribute("training", hasProperty("name", is(trainingId==1?"Training 1":"Training 3"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(trainingId==1?EditingPermission.TRAINER:EditingPermission.BOTH))))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}

    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
	void testProcessTrainingUpdateFormSuccess(int trainingId) throws Exception {   
    	
    	Calendar now = Calendar.getInstance();
    	now.add(Calendar.DAY_OF_MONTH, 14);
    	Date endDateUpdated = now.getTime();
    	
    	mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
    			.with(csrf())
    			.param("name", "Training 1 Updated")
    			.param("initialDate", dateFormat.format(initialDate))
				.param("endDate", dateFormat.format(endDateUpdated))
				.param("editingPermission", EditingPermission.BOTH.toString())
				.param("author", TRAINER1_USERNAME)
				.param("client", NIF1))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}"));
	}

    @WithMockUser(username="trainer1", authorities= {"trainer"})
	@Test
	void testProcessTrainingUpdateFormHasErrorsEmptyParameters() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
				.with(csrf())	
				.param("name", "")
				.param("initialDate", dateFormat.format(initialDate))
				.param("endDate", "")
				.param("editingPermission", "")
				.param("author", TRAINER1_USERNAME)
				.param("client", NIF1))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().errorCount(3))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsPastInitDate() throws Exception {
   		
   		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(-1,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsPastEnd() throws Exception {
   		
   		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(0,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsEndBeforeEqualsInit() throws Exception {
   		
   		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(0,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsLongerThan90Days() throws Exception {
   		
   		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(0,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsInitInTraining() throws Exception {
   		
   		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(-1,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsEndInTraining() throws Exception {
   		
   		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(0,-1);
   	}
   	
    @WithMockUser(username="trainer1", authorities= {"trainer"})
   	@Test
   	void testProcessTrainingUpdateFormHasErrorsPeriodIncludingTraining() throws Exception {
   		
   		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
   		
   		performSamplePost(0,-1);
   	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
	void testProcessTrainingDeleteForm(int trainingId) throws Exception {
    	mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
    			.andExpect(status().is3xxRedirection())
    			.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
    }
    
    //Copy training
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
   	void testGetTrainingListCopyGood(int trainingId) throws Exception {
   		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
   				.andExpect(status().isOk())
   				.andExpect(view().name("trainer/trainings/listCopyTraining"));
   	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING4_ID,CLIENT1_TRAINING5_ID,CLIENT1_TRAINING6_ID})
   	void testGetTrainingListCopyFailed(int trainingId) throws Exception {
   		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
   				.andExpect(status().isOk())
   				.andExpect(view().name("exception"));
   	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
	void testProcessCopyTrainingSuccess(int trainingId) throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
				.with(csrf())
			 	.param("trainingIdToCopy", String.valueOf(CLIENT1_TRAINING4_ID))
			 	.param("trainerUsername", "trainer1"))
				.andExpect(status().is3xxRedirection())
		 		.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
	}
    
    @WithMockUser(username="trainer1", authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT1_TRAINING4_ID,CLIENT1_TRAINING5_ID,CLIENT1_TRAINING6_ID})
	void testProcessCopyTrainingFailed(int trainingId) throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
				.with(csrf())
			 	.param("trainingIdToCopy", String.valueOf(CLIENT1_TRAINING4_ID))
			 	.param("trainerUsername", "trainer1"))
				.andExpect(status().isOk())
		 		.andExpect(view().name("exception"));
	}
    
    /**
     * <p>Performs a post with a sample training which has no errors. It's used to check try/catch in controller tests.</p>
     * @param selectErrorField : Must be greater or equals to 0 for "endDate" and less than 0 for "initialDate"
     * @param selectMode : Must be greater or equals to 0 for "create" and less than 0 for "edit". If the mode selected
     * is create it will be applied for Client2 and if it's edit it will be applied to Client1 and Training1.
     * @throws Exception
     */
    private void performSamplePost(int selectErrorField, int selectMode) throws Exception {
    	
    	String errorField;
    	if(selectErrorField>=0) {
    		errorField = "endDate";
    	}
    	else {
    		errorField = "initialDate";
    	}
    	if(selectMode>=0) {
    		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
    		 	.with(csrf())
    		 	.param("name", "Training 2")
    		 	.param("initialDate", dateFormat.format(initialDate))
    		 	.param("endDate", dateFormat.format(endDate))
    		 	.param("editingPermission", EditingPermission.BOTH.toString())
    		 	.param("author", TRAINER2_USERNAME)
    		 	.param("client", NIF2))
    			.andExpect(status().isOk())
    			.andExpect(model().attributeHasErrors("training"))
    			.andExpect(model().attributeHasFieldErrors("training", errorField))
    			.andExpect(model().errorCount(1))
    			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
    	}
    	else {
    		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
    		 	.with(csrf())
    		 	.param("name", "Training 1 Updated")
    		 	.param("initialDate", dateFormat.format(initialDate))
    		 	.param("endDate", dateFormat.format(endDate))
    		 	.param("editingPermission", EditingPermission.BOTH.toString())
    		 	.param("author", TRAINER1_USERNAME)
    		 	.param("client", NIF1))
    			.andExpect(status().isOk())
    			.andExpect(model().attributeHasErrors("training"))
    			.andExpect(model().attributeHasFieldErrors("training", errorField))
    			.andExpect(model().errorCount(1))
    			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
    	}
    	
    	
    }
    
    private void testWrongAuth(int mode,String path,Object... uriVars) throws Exception {
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
	 
}
