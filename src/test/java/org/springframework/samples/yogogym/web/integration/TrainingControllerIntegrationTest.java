package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Training;
import org.springframework.samples.yogogym.model.Enums.EditingPermission;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.TrainingService;
import org.springframework.samples.yogogym.service.exceptions.EndBeforeEqualsInitException;
import org.springframework.samples.yogogym.service.exceptions.EndInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.InitInTrainingException;
import org.springframework.samples.yogogym.service.exceptions.LongerThan90DaysException;
import org.springframework.samples.yogogym.service.exceptions.PastEndException;
import org.springframework.samples.yogogym.service.exceptions.PastInitException;
import org.springframework.samples.yogogym.service.exceptions.PeriodIncludingTrainingException;
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
	@Transactional 
	@Test
	void showListAndCopyTrainingSuccessful() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		logIn(username);
		createTrainingEmpty(3,7);
		
		String view = this.trainingController.getTrainingListCopy(11, 1, username, model);
		assertEquals(view, "trainer/trainings/listCopyTraining");
		String view2 = this.trainingController.processTrainingCopy(1, 11, 1, username, model);
		assertEquals(view2, "redirect:/trainer/{trainerUsername}/trainings");
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void showListAndCopyTrainingFailedOtherClient() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		logIn(username);
		createTrainingEmpty(3,4);
		
		String view = this.trainingController.getTrainingListCopy(11, 2, username, model);
		assertEquals(view, "exception");
		String view2 = this.trainingController.processTrainingCopy(1, 11, 2, username, model);
		assertEquals(view2, "exception");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void showListAndCopyTrainingFailedClientNotPublic() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		logIn(username);
		createTrainingEmpty(3,4);
		
		String view = this.trainingController.getTrainingListCopy(11, 1, username, model);
		assertEquals(view, "trainer/trainings/listCopyTraining");
		String view2 = this.trainingController.processTrainingCopy(3, 11, 1, username, model);
		assertEquals(view2, "exception");
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	void showListAndCopyTrainingFailedTrainingNotEmpty() throws Exception {
		ModelMap model = new ModelMap();
		String username = "trainer1";
		logIn(username);
		
		String view = this.trainingController.getTrainingListCopy(1, 1, username, model);
		assertEquals(view, "exception");
		String view2 = this.trainingController.processTrainingCopy(1, 2, 1, username, model);
		assertEquals(view2, "exception");
	}
	
	private void createTrainingEmpty(int initialDay, int endDay) {
		Client client = this.clientService.findClientById(1);
		Training training = new Training();
		training.setName("Prueba"+initialDay+endDay);
		training.setEditingPermission(EditingPermission.TRAINER);
		training.setAuthor("trainer1");
		Calendar calCopy = (Calendar) cal.clone();
		calCopy.add(Calendar.DAY_OF_MONTH, initialDay);
		training.setInitialDate(calCopy.getTime());
		calCopy.add(Calendar.DAY_OF_MONTH, endDay);
		training.setEndDate(calCopy.getTime());
		training.setRoutines(new ArrayList<>());
		
		try {
			this.trainingService.saveTraining(training, client);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (PastInitException e) {
			e.printStackTrace();
		} catch (EndBeforeEqualsInitException e) {
			e.printStackTrace();
		} catch (InitInTrainingException e) {
			e.printStackTrace();
		} catch (EndInTrainingException e) {
			e.printStackTrace();
		} catch (PeriodIncludingTrainingException e) {
			e.printStackTrace();
		} catch (PastEndException e) {
			e.printStackTrace();
		} catch (LongerThan90DaysException e) {
			e.printStackTrace();
		}
		
	}
	
	private void logIn(String username) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, "trainer1999"));
		SecurityContextHolder.setContext(securityContext);
	}

}
