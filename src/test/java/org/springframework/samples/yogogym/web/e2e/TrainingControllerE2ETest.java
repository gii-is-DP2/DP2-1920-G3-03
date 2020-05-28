package org.springframework.samples.yogogym.web.e2e;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class TrainingControllerE2ETest {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String TRAINER2_USERNAME = "trainer2";
	private static final String CLIENT1_USERNAME = "client1";
	private static final String CLIENT2_USERNAME = "client2";
	private static final String CLIENT5_USERNAME = "client5";
	private static final String CLIENT6_USERNAME = "client6";
	
	private static final int CLIENT1_ID = 1;
	private static final int CLIENT2_ID = 2;
	private static final int CLIENT6_ID = 6;
	
	private static final int CLIENT1_TRAINING1_ID = 1;
	private static final int CLIENT1_TRAINING2_ID = 2;
	private static final int CLIENT5_TRAINING2_ID = 12;
	private static final int CLIENT5_TRAINING3_ID = 13;
	private static final int CLIENT6_TRAINING1_ID = 7;
	private static final int CLIENT6_TRAINING2_ID = 10;
	private static final int CLIENT6_TRAINING3_ID = 11;
	private static final int NEW_TRAINING_ID = 16;
	
	private SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat detailsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	private static Date initialDate;
	private static Date endDate;
	
	@Autowired
	TrainingService trainingService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Calendar initCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		endCal.add(Calendar.DAY_OF_MONTH, 7);
		initialDate = initCal.getTime();
		endDate = endCal.getTime();
	}

	//COPY TRAINING
	
	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testListCopyTrainingSuccessful() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(10))).andExpect(status().isOk())
				.andExpect(view().name("trainer/trainings/listCopyTraining"));
	}

	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testListCopyTrainingFailedTrainingNotEmpty() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(1), String.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}

	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testListCopyTrainingFailedTrainingOtherUser() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("exception"));
	}

	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingSuccess() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(10)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(1)).param("trainerUsername", "trainer1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
	}

	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingFailedPrivate() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(6), String.valueOf(10)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(3)).param("trainerUsername", "trainer1"))
				.andExpect(status().isOk()).andExpect(view().name("exception"));
	}

	@WithMockUser(username = TRAINER1_USERNAME, authorities = { "trainer" })
	@Test
	void testProcessCopyTrainingFailedTrainingNotEmpty() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/copyTraining",
				"trainer1", String.valueOf(1), String.valueOf(1)).with(csrf())
						.param("trainingIdToCopy", String.valueOf(2)).param("trainerUsername", "trainer1"))
				.andExpect(status().isOk()).andExpect(view().name("exception"));
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
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerPerformPostNoEditingPermissionError() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerDeleteNoAuthor() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete",TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerTrainingList() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/trainings",TRAINER1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("trainer"))
				.andExpect(model().attributeExists("actualDate"))
				.andExpect(model().attribute("trainer", hasProperty("clients", hasSize(4))))
				.andExpect(model().attribute("trainer", hasProperty("clients", hasItem(allOf(hasProperty("id", equalTo(CLIENT1_ID)),hasProperty("trainings", hasSize(3)))))))
				.andExpect(model().attribute("trainer", hasProperty("clients", hasItem(allOf(hasProperty("id", equalTo(CLIENT1_ID)),hasProperty("trainings", hasItem(allOf(
					hasProperty("id", equalTo(CLIENT1_TRAINING1_ID)),hasProperty("name", is("Entrenamiento1")),
					hasProperty("initialDate", hasToString("2020-01-01 00:00:00.0")),hasProperty("endDate", hasToString("2020-01-14 00:00:00.0")),
					hasProperty("editingPermission", equalTo(EditingPermission.TRAINER)),hasProperty("author", is(TRAINER1_USERNAME)),
					hasProperty("diet", hasProperty("name",is("Dieta 1"))),
					hasProperty("routines", allOf(hasItem(hasProperty("name",is("Cardio"))),hasItem(hasProperty("name",is("Brazos")))))))))))))
				.andExpect(view().name("trainer/trainings/trainingsList"));
	}	

	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerTrainingDetails() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
				.andExpect(model().attributeExists("training"))
				.andExpect(model().attribute("training", hasProperty("name", is("Entrenamiento1"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", hasToString("2020-01-01 00:00:00.0"))))
				.andExpect(model().attribute("training", hasProperty("endDate", hasToString("2020-01-14 00:00:00.0"))))
				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(EditingPermission.TRAINER))))
				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name",is("Cardio"))))))
				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name",is("Brazos"))))))
				.andExpect(model().attribute("training", hasProperty("diet", hasProperty("name",is("Dieta 1")))))
				.andExpect(view().name("trainer/trainings/trainingsDetails"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerInitTrainingCreationForm() throws Exception {
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
		 		.andExpect(model().attributeExists("training"))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID)
				.with(csrf())	
			 	.param("name", "Entrenamiento")
			 	.param("initialDate", inputFormatter.format(initialDate))
			 	.param("endDate", inputFormatter.format(endDate))
			 	.param("editingPermission", EditingPermission.TRAINER.toString())
			 	.param("author", TRAINER1_USERNAME))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/"+NEW_TRAINING_ID));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsEmptyParameters() throws Exception {
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID)
			 	.with(csrf())
			 	.param("name", "")
			 	.param("initialDate", "")
			 	.param("endDate", "")
			 	.param("editingPermission", "")
			 	.param("author", TRAINER1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().attributeHasFieldErrors("training", "editingPermission"))
				.andExpect(model().errorCount(4))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsPastInitDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date initialDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsEndBeforeEqualsInit() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsLongerThan90Days() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 91);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsInitInTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 8);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT6_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsEndInTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 8);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT6_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingCreationFormHasErrorsPeriodIncludingTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 13);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 9);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/create",TRAINER1_USERNAME,CLIENT6_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.TRAINER.toString())
		 	.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@ParameterizedTest
	@ValueSource(ints = {CLIENT6_TRAINING2_ID,CLIENT6_TRAINING3_ID})
	void testTrainerInitTrainingUpdateForm(int trainingId) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT6_TRAINING2_ID?-7:14);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT6_TRAINING2_ID?14:7);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,trainingId))
		 		.andExpect(status().isOk())
		  		.andExpect(model().attributeExists("client"))
		  		.andExpect(model().attributeExists("endDateAux"))
		  		.andExpect(model().attributeExists("actualDate"))
		  		.andExpect(model().attributeExists("training"))
		  		.andExpect(model().attribute("training", hasProperty("name", is(trainingId==CLIENT6_TRAINING2_ID?"Entrenamiento1":"Entrenamiento2"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", hasToString(detailsFormatter.format(initialDate)))))
				.andExpect(model().attribute("training", hasProperty("endDate", hasToString(detailsFormatter.format(endDate)))))
				.andExpect(model().attribute("training", hasProperty("author", is(trainingId==CLIENT6_TRAINING2_ID?TRAINER1_USERNAME:CLIENT6_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(trainingId==CLIENT6_TRAINING2_ID?EditingPermission.TRAINER:EditingPermission.BOTH))))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
    @ParameterizedTest
	@ValueSource(ints = {CLIENT6_TRAINING2_ID,CLIENT6_TRAINING3_ID})
	void testTrainerProcessTrainingUpdateFormSuccess(int trainingId) throws Exception {   
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT6_TRAINING2_ID?-7:14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT6_TRAINING2_ID?15:8);
    	Date endDateUpdated = calendar.getTime();
    	
    	mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,trainingId)
    			.with(csrf())
    			.param("name", "Entrenamiento Actualizado")
    			.param("initialDate", inputFormatter.format(initialDate))
				.param("endDate", inputFormatter.format(endDateUpdated))
				.param("editingPermission", EditingPermission.BOTH.toString())
				.param("author", trainingId==CLIENT6_TRAINING2_ID?TRAINER1_USERNAME:CLIENT6_USERNAME))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
	@Test
	void testTrainerProcessTrainingUpdateFormHasErrorsEmptyParameters() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING2_ID)
				.with(csrf())	
				.param("name", "")
				.param("initialDate", inputFormatter.format(initialDate))
				.param("endDate", "")
				.param("editingPermission", EditingPermission.BOTH.toString())
				.param("author", TRAINER1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().errorCount(2))
				.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
   	@Test
   	void testTrainerProcessTrainingUpdateFormHasErrorsPastEnd() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 1);
    	Date endDateUpdated = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.TRAINER.toString())
			.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
   	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
   	@Test
   	void testTrainerProcessTrainingUpdateFormHasErrorsEndBeforeEqualsInit() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, 14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	Date endDateUpdated = calendar.getTime();
   		
   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING3_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.BOTH.toString())
			.param("author", CLIENT6_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
   	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
   	@Test
   	void testTrainerProcessTrainingUpdateFormHasErrorsLongerThan90Days() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, 14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 91);
    	Date endDateUpdated = calendar.getTime();
		
   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING3_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.BOTH.toString())
			.param("author", CLIENT6_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
   	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
   	@Test
   	void testTrainerProcessTrainingUpdateFormHasErrorsEndInTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7+15);
    	Date endDateUpdated = calendar.getTime();
		
		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.TRAINER.toString())
			.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
   	}
	
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
   	@Test
   	void testTrainerProcessTrainingUpdateFormHasErrorsPeriodIncludingTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7+22);
    	Date endDateUpdated = calendar.getTime();
		
   		mockMvc.perform(post("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.TRAINER.toString())
			.param("author", TRAINER1_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("trainer/trainings/trainingCreateOrUpdate"));
   	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(username=TRAINER1_USERNAME, authorities= {"trainer"})
    @Test
	void testTrainerProcessTrainingDeleteForm() throws Exception {
    	mockMvc.perform(get("/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete", TRAINER1_USERNAME,CLIENT6_ID,CLIENT6_TRAINING2_ID))
    			.andExpect(status().is3xxRedirection())
    			.andExpect(view().name("redirect:/trainer/{trainerUsername}/trainings"));
    }
	
	//CLIENT
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/trainings",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/delete"})
	void testClientPerformGetTrainerSectionError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(get(path,TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID))
				.andExpect(status().isForbidden());
		}
		else if(path.contains("clientId")) {
			mockMvc.perform(get(path,TRAINER1_USERNAME,CLIENT1_ID))
				.andExpect(status().isForbidden());
		}
		else {
			mockMvc.perform(get(path,TRAINER1_USERNAME))
				.andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"/trainer/{trainerUsername}/clients/{clientId}/trainings/create",
		"/trainer/{trainerUsername}/clients/{clientId}/trainings/{trainingId}/edit"})
	void testClientPerformPostTrainerSectionError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(post(path,TRAINER1_USERNAME,CLIENT1_ID,CLIENT1_TRAINING1_ID)
				.with(csrf()))
				.andExpect(status().isForbidden());
		}
		else {
			mockMvc.perform(post(path,TRAINER1_USERNAME,CLIENT1_ID)
				.with(csrf()))
				.andExpect(status().isForbidden());
		}
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"/client/{clientUsername}/trainings",
		"/client/{clientUsername}/trainings/{trainingId}",
		"/client/{clientUsername}/trainings/create",
		"/client/{clientUsername}/trainings/{trainingId}/edit",
		"/client/{clientUsername}/trainings/{trainingId}/delete"})
	void testClientPerformGetWrongUsernameError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(get(path,CLIENT1_USERNAME,CLIENT1_TRAINING2_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(get(path,CLIENT1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@ParameterizedTest
	@ValueSource(strings = {"/client/{clientUsername}/trainings/create",
		"/client/{clientUsername}/trainings/{trainingId}/edit"})
	void testClientPerformPostWrongUsernameError(String path) throws Exception {
		if(path.contains("trainingId")) {
			mockMvc.perform(post(path,CLIENT1_USERNAME,CLIENT1_TRAINING2_ID)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
		else {
			mockMvc.perform(post(path,CLIENT1_USERNAME)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("exception"));
		}
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientPerformGetNoEditingPermissionError() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientPerformPostNoEditingPermissionError() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientDeleteNoAuthor() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/delete",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientTrainingList() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/trainings",CLIENT1_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("trainings"))
				.andExpect(model().attribute("trainings", hasSize(3)))
				.andExpect(model().attribute("trainings", hasItem(allOf(hasProperty("id", equalTo(CLIENT1_TRAINING1_ID)),
					hasProperty("name", is("Entrenamiento1")),hasProperty("initialDate", hasToString("2020-01-01 00:00:00.0")),
					hasProperty("endDate", hasToString("2020-01-14 00:00:00.0")),
					hasProperty("editingPermission", equalTo(EditingPermission.TRAINER)),hasProperty("author", is(TRAINER1_USERNAME)),
					hasProperty("diet", hasProperty("name",is("Dieta 1"))),
					hasProperty("routines", allOf(hasItem(hasProperty("name",is("Cardio"))),hasItem(hasProperty("name",is("Brazos")))))))))
				.andExpect(view().name("client/trainings/trainingsList"));
	}	
	
	@WithMockUser(username=CLIENT1_USERNAME, authorities= {"client"})
	@Test
	void testClientTrainingDetails() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}",CLIENT1_USERNAME,CLIENT1_TRAINING1_ID))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attribute("training", hasProperty("name", is("Entrenamiento1"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", hasToString("2020-01-01 00:00:00.0"))))
				.andExpect(model().attribute("training", hasProperty("endDate", hasToString("2020-01-14 00:00:00.0"))))
				.andExpect(model().attribute("training", hasProperty("author", is(TRAINER1_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(EditingPermission.TRAINER))))
				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name",is("Cardio"))))))
				.andExpect(model().attribute("training", hasProperty("routines", hasItem(hasProperty("name",is("Brazos"))))))
				.andExpect(model().attribute("training", hasProperty("diet", hasProperty("name",is("Dieta 1")))))
				.andExpect(view().name("client/trainings/trainingsDetails"));
	}

	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientInitTrainingCreationForm() throws Exception {
		mockMvc.perform(get("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME))
		 		.andExpect(status().isOk())
		 		.andExpect(model().attributeExists("client"))
		 		.andExpect(model().attributeExists("training"))
				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
				.with(csrf())	
				.param("name", "Entrenamiento")
			 	.param("initialDate", inputFormatter.format(initialDate))
			 	.param("endDate", inputFormatter.format(endDate))
			 	.param("editingPermission", EditingPermission.CLIENT.toString())
			 	.param("author", CLIENT2_USERNAME))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/{clientUsername}/trainings/"+NEW_TRAINING_ID));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsEmptyParameters() throws Exception {
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
			 	.with(csrf())
			 	.param("name", "")
			 	.param("initialDate", "")
			 	.param("endDate", "")
			 	.param("editingPermission", EditingPermission.CLIENT.toString())
			 	.param("author", CLIENT2_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().errorCount(3))
				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsPastInitDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date initialDate = calendar.getTime();
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
		 	.param("author", CLIENT2_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}	
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsEndBeforeEqualsInit() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = calendar.getTime();

		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
		 	.param("author", CLIENT2_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=CLIENT2_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsLongerThan90Days() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 91);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT2_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
		 	.param("author", CLIENT2_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=CLIENT6_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsInitInTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 8);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT6_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
			.param("author", CLIENT6_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "initialDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=CLIENT6_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsEndInTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 8);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT6_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
		 	.param("author", CLIENT6_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
	
	@WithMockUser(username=CLIENT6_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingCreationFormHasErrorsPeriodIncludingTraining() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 13);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 9);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(post("/client/{clientUsername}/trainings/create",CLIENT6_USERNAME)
		 	.with(csrf())
		 	.param("name", "Entrenamiento")
		 	.param("initialDate", inputFormatter.format(initialDate))
		 	.param("endDate", inputFormatter.format(endDate))
		 	.param("editingPermission", EditingPermission.CLIENT.toString())
		 	.param("author", CLIENT6_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}

	@WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
	@ParameterizedTest
   	@ValueSource(ints = {CLIENT5_TRAINING2_ID,CLIENT5_TRAINING3_ID})
	void testClientInitTrainingUpdateForm(int trainingId) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT5_TRAINING2_ID?-7:14);
		Date initialDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT5_TRAINING2_ID?14:7);
		Date endDate = calendar.getTime();
		
		mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,trainingId))
		 		.andExpect(status().isOk())
		  		.andExpect(model().attributeExists("client"))
		  		.andExpect(model().attributeExists("endDateAux"))
		  		.andExpect(model().attributeExists("actualDate"))
		  		.andExpect(model().attributeExists("training"))
		  		.andExpect(model().attribute("training", hasProperty("name", is(trainingId==CLIENT5_TRAINING2_ID?"Entrenamiento1":"Entrenamiento2"))))
				.andExpect(model().attribute("training", hasProperty("initialDate", hasToString(detailsFormatter.format(initialDate)))))
				.andExpect(model().attribute("training", hasProperty("endDate", hasToString(detailsFormatter.format(endDate)))))
				.andExpect(model().attribute("training", hasProperty("author", is(trainingId==CLIENT5_TRAINING2_ID?CLIENT5_USERNAME:TRAINER1_USERNAME))))
				.andExpect(model().attribute("training", hasProperty("editingPermission", equalTo(trainingId==CLIENT5_TRAINING2_ID?EditingPermission.CLIENT:EditingPermission.BOTH))))
				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
    @ParameterizedTest
   	@ValueSource(ints = {CLIENT5_TRAINING2_ID,CLIENT5_TRAINING3_ID})
	void testClientProcessTrainingUpdateFormSuccess(int trainingId) throws Exception {   
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT5_TRAINING2_ID?-7:14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, trainingId==CLIENT5_TRAINING2_ID?15:8);
    	Date endDateUpdated = calendar.getTime(); 	
    	
    	mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,trainingId)
    			.with(csrf())
    			.param("name", "Entrenamiento Actualizado")
    			.param("initialDate", inputFormatter.format(initialDate))
    			.param("endDate", inputFormatter.format(endDateUpdated))
    			.param("editingPermission", trainingId==CLIENT5_TRAINING2_ID?EditingPermission.CLIENT.toString():EditingPermission.BOTH.toString())
    			.param("author", trainingId==CLIENT5_TRAINING2_ID?CLIENT5_USERNAME:TRAINER1_USERNAME))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/client/{clientUsername}/trainings/{trainingId}"));
	}

    @WithMockUser(username=CLIENT6_USERNAME, authorities= {"client"})
	@Test
	void testClientProcessTrainingUpdateFormHasErrorsEmptyParameters() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, 14);
    	Date initialDate = calendar.getTime();
    	
    	mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT6_USERNAME,CLIENT6_TRAINING3_ID)
				.with(csrf())	
				.param("name", "")
				.param("initialDate", inputFormatter.format(initialDate))
				.param("endDate", "")
				.param("editingPermission", EditingPermission.BOTH.toString())
				.param("author", CLIENT6_USERNAME))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("training"))
				.andExpect(model().attributeHasFieldErrors("training", "name"))
				.andExpect(model().attributeHasFieldErrors("training", "endDate"))
				.andExpect(model().errorCount(2))
				.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
	}
    
   	
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
   	@Test
   	void testClientProcessTrainingUpdateFormHasErrorsPastEnd() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 1);
    	Date endDateUpdated = calendar.getTime();
    	
    	mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,CLIENT5_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.CLIENT.toString())
			.param("author", CLIENT5_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
   	}
   	
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
   	@Test
   	void testClientProcessTrainingUpdateFormHasErrorsEndBeforeEqualsInit() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, 14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	Date endDateUpdated = calendar.getTime();

   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,CLIENT5_TRAINING3_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.BOTH.toString())
			.param("author", CLIENT5_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
   	}
   	
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
   	@Test
   	void testClientProcessTrainingUpdateFormHasErrorsLongerThan90Days() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, 14);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 91);
    	Date endDateUpdated = calendar.getTime();
    	
   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,CLIENT5_TRAINING3_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.BOTH.toString())
			.param("author", CLIENT5_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
   	}
   	 	
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
   	@Test
   	void testClientProcessTrainingUpdateFormHasErrorsEndInTraining() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7+15);
    	Date endDateUpdated = calendar.getTime();
    
   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,CLIENT5_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.CLIENT.toString())
			.param("author", CLIENT5_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
   	}
   	
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
   	@Test
   	void testClientProcessTrainingUpdateFormHasErrorsPeriodIncludingTraining() throws Exception {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -7);
    	Date initialDate = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7+22);
    	Date endDateUpdated = calendar.getTime();
    	
   		mockMvc.perform(post("/client/{clientUsername}/trainings/{trainingId}/edit", CLIENT5_USERNAME,CLIENT5_TRAINING2_ID)
		 	.with(csrf())
		 	.param("name", "Entrenamiento Actualizado")
			.param("initialDate", inputFormatter.format(initialDate))
			.param("endDate", inputFormatter.format(endDateUpdated))
			.param("editingPermission", EditingPermission.CLIENT.toString())
			.param("author", CLIENT5_USERNAME))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("training"))
			.andExpect(model().attributeHasFieldErrors("training", "endDate"))
			.andExpect(model().errorCount(1))
			.andExpect(view().name("client/trainings/trainingCreateOrUpdate"));
   	}
    
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    @WithMockUser(username=CLIENT5_USERNAME, authorities= {"client"})
	void testClientProcessTrainingDeleteForm() throws Exception {
    	mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/delete", CLIENT5_USERNAME,CLIENT5_TRAINING2_ID))
    			.andExpect(status().is3xxRedirection())
    			.andExpect(view().name("redirect:/client/{clientUsername}/trainings"));
    }
}
