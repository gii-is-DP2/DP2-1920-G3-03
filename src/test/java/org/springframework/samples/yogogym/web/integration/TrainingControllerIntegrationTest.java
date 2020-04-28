package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.web.TrainingController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingControllerIntegrationTest {

	private static final Calendar cal = Calendar.getInstance();

	@Autowired
	private TrainingController trainingController;

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private ClientService clientService;

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void showListAndCopyTrainingSuccessful() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, "trainer1999"));
		SecurityContextHolder.setContext(securityContext);
		Client client = this.clientService.findClientById(1);
		Training training = new Training();
		training.setName("Prueba1");
		training.setEditingPermission(EditingPermission.TRAINER);
		training.setAuthor("trainer1");
		Calendar calCopy = (Calendar) cal.clone();
		calCopy.add(Calendar.DAY_OF_MONTH, 9);
		training.setInitialDate(calCopy.getTime());
		calCopy.add(Calendar.DAY_OF_MONTH, 15);
		training.setEndDate(calCopy.getTime());
		training.setClient(client);
		this.trainingService.saveTraining(training);
		String view = this.trainingController.getTrainingListCopy(11, 1, username, model);
		assertEquals(view, "trainer/trainings/listCopyTraining");
		String view2 = this.trainingController.processTrainingCopy(1, 11, 1, username, model);
		assertEquals(view2, "redirect:/trainer/{trainerUsername}/trainings");
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void showListAndCopyTrainingFailedTrainingEmpty() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, "trainer1999"));
		SecurityContextHolder.setContext(securityContext);
		Client client = this.clientService.findClientById(1);
		Training training = new Training();
		training.setName("Prueba");
		training.setEditingPermission(EditingPermission.TRAINER);
		training.setAuthor("trainer1");
		Calendar calCopy = (Calendar) cal.clone();
		calCopy.add(Calendar.DAY_OF_MONTH, 3);
		training.setInitialDate(calCopy.getTime());
		calCopy.add(Calendar.DAY_OF_MONTH, 7);
		training.setEndDate(calCopy.getTime());
		training.setClient(client);
		this.trainingService.saveTraining(training);
		String view = this.trainingController.getTrainingListCopy(11, 1, username, model);
		assertEquals(view, "trainer/trainings/listCopyTraining");
		String view2 = this.trainingController.processTrainingCopy(1, 11, 1, username, model);
		assertEquals(view2, "redirect:/trainer/{trainerUsername}/trainings");
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void showListAndCopyTrainingFailedTrainingNotEmpty() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, "trainer1999"));
		SecurityContextHolder.setContext(securityContext);
		String view = this.trainingController.getTrainingListCopy(1, 1, username, model);
		assertEquals(view, "exception");
		String view2 = this.trainingController.processTrainingCopy(1, 2, 1, username, model);
		assertEquals(view2, "exception");
	}

}
