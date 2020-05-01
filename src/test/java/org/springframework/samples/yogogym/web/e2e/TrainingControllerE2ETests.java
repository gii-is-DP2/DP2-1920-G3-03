package org.springframework.samples.yogogym.web.e2e;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class TrainingControllerE2ETests {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String TRAINER2_USERNAME = "trainer2";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT2_ID = 2;
	private static final int CLIENT5_ID = 5;
	
	private static final String NIF1 = "12345678F";
	private static final String NIF2 = "12345678G";
	
	private static final int CLIENT1_TRAINING1_ID = 1;
	private static final int CLIENT1_TRAINING2_ID = 2;
	private static final int CLIENT1_TRAINING3_ID = 3;
	private static final int CLIENT1_TRAINING4_ID = 4;
	private static final int CLIENT1_TRAINING5_ID = 5;
	private static final int CLIENT1_TRAINING6_ID = 6;
	private static final int CLIENT1_TRAINING8_ID = 8;
	private static final int CLIENT2_TRAINING7_ID = 7;
	private static final int CLIENT5_TRAINING1_ID = 6;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat detailsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private static Date initialDate = null;
	private static Date endDate = null;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		Calendar initCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		initialDate = initCal.getTime();
		endCal.add(Calendar.DAY_OF_MONTH, 7);
		endDate = endCal.getTime();
	}
	
	//TRAINER
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/client/{clientUsername}/trainings",
		"/client/{clientUsername}/trainings/{trainingId}","/client/{clientUsername}/trainings/create",
		"/client/{clientUsername}/trainings/{trainingId}/edit","/client/{clientUsername}/trainings/{trainingId}/delete"})
	void testTrainerPerformGetClientSectionError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(get(path,CLIENT1_USERNAME,CLIENT1_TRAINING1_ID))
				.andExpect(status().isForbidden());
		}
		else {
			mockMvc.perform(get(path,CLIENT1_USERNAME))
				.andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/client/{clientUsername}/trainings/create",
		"/client/{clientUsername}/trainings/{trainingId}/edit"})
	void testTrainerPerformPostClientSectionError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(post(path,CLIENT1_USERNAME,CLIENT1_TRAINING1_ID)
				.with(csrf()))
				.andExpect(status().isForbidden());
		}
		else {
			mockMvc.perform(post(path,CLIENT1_USERNAME)
				.with(csrf()))
				.andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/trainings",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete"})
	void testTrainerPerformGetWrongUsernameError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(get(path,TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else if(path.contains("clientId")) {
			mockMvc.perform(get(path,TRAINER1_USERNAME,CLIENT1_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(get(path,TRAINER1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit"})
	void testTrainerPerformPostWrongUsernameError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(post(path,TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(post(path,TRAINER1_USERNAME,CLIENT1_ID)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete"})
	void testTrainerPerformGetWrongClientError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(get(path,TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(get(path,TRAINER2_USERNAME,CLIENT1_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit"})
	void testTrainerPerformPostWrongClientError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(post(path,TRAINER2_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(post(path,TRAINER2_USERNAME,CLIENT1_ID)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerPerformGetNoEditingPermissionError() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT5_ID,CLIENT5_TRAINING1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerPerformPostNoEditingPermissionError() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT5_ID,CLIENT5_TRAINING1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerDeleteNoAuthor() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING8_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
//	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerTrainingDetails() throws Exception {
//		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
//		 		.andExpect(status().isOk())
//		 		.andExpect(model().attributeExists("client"))
//				.andExpect(model().attributeExists("training"))
//				.andExpect(model().attribute("training", hasProperty("name", is("Entrenamiento 1"))))
//				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(detailsFormatter.parse("2020-01-01 00:00:00.0")))))
//				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(detailsFormatter.parse("2020-01-14 00:00:00.0")))))
//				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
//				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(EditingPermission.TRAINER))))
//				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name", is("Cardio"))))))
//				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name", is("Brazos"))))))
//				.andExpect(model().attribute("training", hasProperty("diet", hasProperty("name", is("Dieta 1")))))
//				.andExpect(view().name("trainer/trainings/trainingsDetails"));
//	}
//
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerInitTrainingCreationForm() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
		 		.andExpect(model().attributeExists("training"))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//				.with(csrf())	
//			 	.param("name", "Training 2")
//			 	.param("initialDate", dateFormat.format(initialDate))
//			 	.param("endDate", dateFormat.format(endDate))
//			 	.param("editingPermission", EditingPermission.TRAINER.toString())
//			 	.param("author", TRAINER2_USERNAME)
//			 	.param("client", NIF2))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/"+CLIENT2_TRAINING7_ID));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsEmptyParameters() throws Exception {
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//			 	.with(csrf())
//			 	.param("name", "")
//			 	.param("initialDate", "")
//			 	.param("endDate", "")
//			 	.param("editingPermission", "")
//			 	.param("author", TRAINER2_USERNAME)
//			 	.param("client", NIF2))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("training"))
//				.andExpect(model().attributeHasFieldErrors("training", "name"))
//				.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//				.andExpect(model().attributeHasFieldErrors("training", "editingPermission"))
//				.andExpect(model().errorCount(4))
//				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsPastInitDate() throws Exception {
//		
//		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsPastEnd() throws Exception {
//		
//		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsEndBeforeEqualsInit() throws Exception {
//		
//		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsLongerThan90Days() throws Exception {
//		
//		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsInitInTraining() throws Exception {
//		
//		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsEndInTraining() throws Exception {
//		
//		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=TRAINER2_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingCreationFormHasErrorsPeriodIncludingTraining() throws Exception {
//		
//		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER2_USERNAME,CLIENT2_ID)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//
//	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//	@ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
//	void testTrainerInitTrainingUpdateForm(int trainingId) throws Exception {
//		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
//		 		.andExpect(status().isOk())
//		  		.andExpect(model().attributeExists("client"))
//		  		.andExpect(model().attributeExists("endDateAux"))
//		  		.andExpect(model().attributeExists("actualDate"))
//		  		.andExpect(model().attributeExists("training"))
//		  		.andExpect(model().attribute("training", hasProperty("name", is(trainingId==1?"Training 1":"Training 3"))))
//				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
//				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
//				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
//				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(trainingId==1?EditingPermission.TRAINER:EditingPermission.BOTH))))
//				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
//	void testTrainerProcessTrainingUpdateFormSuccess(int trainingId) throws Exception {   
//    	
//    	Calendar now = Calendar.getInstance();
//    	now.add(Calendar.DAY_OF_MONTH, 14);
//    	Date endDateUpdated = now.getTime();
//    	
//    	mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
//    			.with(csrf())
//    			.param("name", "Training 1 Updated")
//    			.param("initialDate", dateFormat.format(initialDate))
//				.param("endDate", dateFormat.format(endDateUpdated))
//				.param("editingPermission", EditingPermission.BOTH.toString())
//				.param("author", TRAINER1_USERNAME)
//				.param("client", NIF1))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}"));
//	}
//
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//	@Test
//	void testTrainerProcessTrainingUpdateFormHasErrorsEmptyParameters() throws Exception {
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//				.with(csrf())	
//				.param("name", "")
//				.param("initialDate", dateFormat.format(initialDate))
//				.param("endDate", "")
//				.param("editingPermission", "")
//				.param("author", TRAINER1_USERNAME)
//				.param("client", NIF1))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("training"))
//				.andExpect(model().attributeHasFieldErrors("training", "name"))
//				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//				.andExpect(model().errorCount(3))
//				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsPastInitDate() throws Exception {
//   		
//   		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsPastEnd() throws Exception {
//   		
//   		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsEndBeforeEqualsInit() throws Exception {
//   		
//   		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsLongerThan90Days() throws Exception {
//   		
//   		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsInitInTraining() throws Exception {
//   		
//   		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsEndInTraining() throws Exception {
//   		
//   		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//   	@Test
//   	void testTrainerProcessTrainingUpdateFormHasErrorsPeriodIncludingTraining() throws Exception {
//   		
//   		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(true)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
//   	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
//	void testTrainerProcessTrainingDeleteForm(int trainingId) throws Exception {
//    	mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
//    			.andExpect(status().is3xxRedirection())
//    			.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
//    }
//    
//    //Copy training
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
//   	void testGetTrainingListCopyGood(int trainingId) throws Exception {
//   		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
//   				.andExpect(status().isOk())
//   				.andExpect(view().name("trainer/trainings/listCopyTraining"));
//   	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING4_ID,CLIENT1_TRAINING5_ID,CLIENT1_TRAINING6_ID})
//   	void testGetTrainingListCopyFailed(int trainingId) throws Exception {
//   		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId))
//   				.andExpect(status().isOk())
//   				.andExpect(view().name("exception"));
//   	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING1_ID,CLIENT1_TRAINING3_ID})
//	void testProcessCopyTrainingSuccess(int trainingId) throws Exception {
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
//				.with(csrf())
//			 	.param("trainingIdToCopy", String.valueOf(CLIENT1_TRAINING4_ID))
//			 	.param("trainerUsername", "trainer1"))
//				.andExpect(status().is3xxRedirection())
//		 		.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
//	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//    @ParameterizedTest
//	@ValueSource(ints = {CLIENT1_TRAINING4_ID,CLIENT1_TRAINING5_ID,CLIENT1_TRAINING6_ID})
//	void testProcessCopyTrainingFailed(int trainingId) throws Exception {
//		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining", TRAINER1_USERNAME,CLIENT1_ID,trainingId)
//				.with(csrf())
//			 	.param("trainingIdToCopy", String.valueOf(CLIENT1_TRAINING4_ID))
//			 	.param("trainerUsername", "trainer1"))
//				.andExpect(status().isOk())
//		 		.andExpect(view().name("exception"));
//	}
//    
//    @WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
//	@Test
//	void testClientTrainingList() throws Exception {
//		mockMvc.perform(get("/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME)).andExpect(status().isOk())
//		.andExpect(model().attributeExists("trainer"))
//		.andExpect(model().attributeExists("actualDate"))
//		.andExpect(view().name("trainer/trainings/trainingsList"));
//	}
//    
//    //CLIENT
//    
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@Test
//	void testClientWrongAccess() throws Exception {
//		testWrongAuth(0,"/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME);
//		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
//		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
//		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT1_ID);
//		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
//		testWrongAuth(1,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
//		testWrongAuth(0,"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID);
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientWrongUsername() throws Exception {
//		testWrongAuth(0,"/client/{clientUsername}/trainings",CLIENT1_USERNAME);
//		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}",CLIENT1_USERNAME,CLIENT1_TRAINING2_ID);
//		testWrongAuth(0,"/client/{clientUsername}/trainings/create",CLIENT1_USERNAME);
//		testWrongAuth(1,"/client/{clientUsername}/trainings/create",CLIENT1_USERNAME);
//		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING2_ID);
//		testWrongAuth(1,"/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING2_ID);
//		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}/delete",CLIENT1_USERNAME,CLIENT1_TRAINING2_ID);
//	}
//	
//	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@Test
//	void testClientNoEditingPermission() throws Exception {
//		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID);
//		testWrongAuth(1,"/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID);
//	}
//	
//	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@Test
//	void testClientDeleteNoAuthor() throws Exception {
//		testWrongAuth(0,"/client/{clientUsername}/trainings/{trainingId}/delete",CLIENT1_USERNAME,CLIENT1_TRAINING3_ID);
//	}
//	
//	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@Test
//	void testClientTrainingDetails() throws Exception {
//		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID))
//		 		.andExpect(status().isOk())
//		 		.andExpect(model().attributeExists("client"))
//				.andExpect(model().attributeExists("training"))
//				.andExpect(model().attribute("training", hasProperty("name", is("Training 1"))))
//				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
//				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
//				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
//				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(EditingPermission.TRAINER))))
//				.andExpect(model().attribute("training", hasProperty("routines", is(new ArrayList<>()))))
//				.andExpect(model().attribute("training", hasProperty("diet", nullValue())))
//				.andExpect(view().name("client/trainings/trainingsDetails"));
//	}
//
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientInitTrainingCreationForm() throws Exception {
//		mockMvc.perform(get("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME))
//		 		.andExpect(status().isOk())
//		 		.andExpect(model().attributeExists("client"))
//		 		.andExpect(model().attributeExists("training"))
//				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//				.with(csrf())	
//			 	.param("name", "Training 2")
//			 	.param("initialDate", dateFormat.format(initialDate))
//			 	.param("endDate", dateFormat.format(endDate))
//			 	.param("editingPermission", EditingPermission.CLIENT.toString())
//			 	.param("author", CLIENT2_USERNAME)
//			 	.param("client", NIF2))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/client/{clientUsername}/trainings/"+CLIENT2_TRAINING7_ID));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsEmptyParameters() throws Exception {
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//			 	.with(csrf())
//			 	.param("name", "")
//			 	.param("initialDate", "")
//			 	.param("endDate", "")
//			 	.param("editingPermission", "")
//			 	.param("author", CLIENT2_USERNAME)
//			 	.param("client", NIF2))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("training"))
//				.andExpect(model().attributeHasFieldErrors("training", "name"))
//				.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//				.andExpect(model().attributeHasFieldErrors("training", "editingPermission"))
//				.andExpect(model().errorCount(4))
//				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsPastInitDate() throws Exception {
//		
//		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsPastEnd() throws Exception {
//		
//		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsEndBeforeEqualsInit() throws Exception {
//		
//		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsLongerThan90Days() throws Exception {
//		
//		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsInitInTraining() throws Exception {
//		
//		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsEndInTraining() throws Exception {
//		
//		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//	
//	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingCreationFormHasErrorsPeriodIncludingTraining() throws Exception {
//		
//		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//		
//		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
//		 	.with(csrf())
//		 	.params(createTrainingClient2(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//
//	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@ParameterizedTest
//   	@ValueSource(ints = {CLIENT1_TRAINING2_ID,CLIENT1_TRAINING3_ID})
//	void testClientInitTrainingUpdateForm(int trainingId) throws Exception {
//		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,trainingId))
//		 		.andExpect(status().isOk())
//		  		.andExpect(model().attributeExists("client"))
//		  		.andExpect(model().attributeExists("endDateAux"))
//		  		.andExpect(model().attributeExists("actualDate"))
//		  		.andExpect(model().attributeExists("training"))
//		  		.andExpect(model().attribute("training", hasProperty("name", is(trainingId==2?"Training 2":"Training 3"))))
//				.andExpect(model().attribute("training", hasProperty("initialDate", equalTo(initialDate))))
//				.andExpect(model().attribute("training", hasProperty("endDate", equalTo(endDate))))
//				.andExpect(model().attribute("training", hasProperty("author", is(trainingId==2?CLIENT1_USERNAME:TRAINER1_USERNAME))))
//				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(trainingId==2?EditingPermission.CLIENT:EditingPermission.BOTH))))
//				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//    @ParameterizedTest
//   	@ValueSource(ints = {CLIENT1_TRAINING2_ID,CLIENT1_TRAINING3_ID})
//	void testClientProcessTrainingUpdateFormSuccess(int trainingId) throws Exception {   
//    	
//    	Calendar now = Calendar.getInstance();
//    	now.add(Calendar.DAY_OF_MONTH, 14);
//    	Date endDateUpdated = now.getTime();
//    	
//    	mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,trainingId)
//    			.with(csrf())
//    			.param("name", "Training 1 Updated")
//    			.param("initialDate", dateFormat.format(initialDate))
//				.param("endDate", dateFormat.format(endDateUpdated))
//				.param("editingPermission", EditingPermission.BOTH.toString())
//				.param("author", trainingId==2 ? CLIENT1_USERNAME : TRAINER1_USERNAME)
//				.param("client", NIF1))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/client/{clientUsername}/trainings/{trainingId}"));
//	}
//
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	@Test
//	void testClientProcessTrainingUpdateFormHasErrorsEmptyParameters() throws Exception {
//		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//				.with(csrf())	
//				.param("name", "")
//				.param("initialDate", dateFormat.format(initialDate))
//				.param("endDate", "")
//				.param("editingPermission", "")
//				.param("author", CLIENT1_USERNAME)
//				.param("client", NIF1))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("training"))
//				.andExpect(model().attributeHasFieldErrors("training", "name"))
//				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//				.andExpect(model().errorCount(3))
//				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//	}
//    
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsPastInitDate() throws Exception {
//   		
//   		doThrow(new PastInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsPastEnd() throws Exception {
//   		
//   		doThrow(new PastEndException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsEndBeforeEqualsInit() throws Exception {
//   		
//   		doThrow(new EndBeforeEqualsInitException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsLongerThan90Days() throws Exception {
//   		
//   		doThrow(new LongerThan90DaysException()).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsInitInTraining() throws Exception {
//   		
//   		doThrow(new InitInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsEndInTraining() throws Exception {
//   		
//   		doThrow(new EndInTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//   	
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//   	@Test
//   	void testClientProcessTrainingUpdateFormHasErrorsPeriodIncludingTraining() throws Exception {
//   		
//   		doThrow(new PeriodIncludingTrainingException("","")).when(this.trainingService).saveTraining(Mockito.any(Training.class));
//   		
//   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
//		 	.with(csrf())
//		 	.params(updateTrainingClient1(false)))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("training"))
//			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
//			.andExpect(model().errorCount(1))
//			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
//   	}
//    
//    @WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
//	void testClientProcessTrainingDeleteForm() throws Exception {
//    	mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/delete", CLIENT1_USERNAME,CLIENT1_TRAINING2_ID))
//    			.andExpect(status().is3xxRedirection())
//    			.andExpect(view().name("redirect:/client/{clientUsername}/trainings"));
//    }
    
	private MultiValueMap<String,String> createTrainingClient2(boolean isPrincipalTrainer){
		
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		
		params.add("name", "Training 2");
		params.add("initialDate", dateFormat.format(initialDate));
		params.add("endDate", dateFormat.format(endDate));
		params.add("editingPermission", EditingPermission.BOTH.toString());
		params.add("author", isPrincipalTrainer?TRAINER2_USERNAME:CLIENT2_USERNAME);
		params.add("client", NIF2);
		
		return params;
	}
	
	private MultiValueMap<String,String> updateTrainingClient1(boolean isTraining1){
		
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		
		params.add("name", "Training 1 Updated");
		params.add("initialDate", dateFormat.format(initialDate));
		params.add("endDate", dateFormat.format(endDate));
		params.add("editingPermission", EditingPermission.BOTH.toString());
		params.add("author", isTraining1?TRAINER1_USERNAME:CLIENT1_USERNAME);
		params.add("client", NIF1);
		
		return params;
	}
    
    private void testWrongAuth(int mode,String path,Object... uriVars) throws Exception {
		if(mode == 0) {
			mockMvc.perform(get(path,uriVars))
			.andExpect(status().isForbidden());			
		}
		else {
			mockMvc.perform(post(path,uriVars))
			.andExpect(status().isForbidden());
		}
	}
	 
}