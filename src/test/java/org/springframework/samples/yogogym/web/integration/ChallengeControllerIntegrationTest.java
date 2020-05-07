package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.web.ChallengeController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeControllerIntegrationTest {
	
	private static final Calendar TEST_INITIAL_DATE_CALENDAR = Calendar.getInstance();
	private static final Calendar TEST_END_DATE_CALENDAR = Calendar.getInstance();
	
	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_4 = 4;
	
	private static final int EXERCISE_ID_1 = 1;
	
	private static final String CLIENT_USERNAME_1 = "client1";
	private static final String CLIENT_USERNAME_2 = "client2";
	
	
	@Autowired
	private ChallengeController challengeController;
	
	//ADMIN
	
	@Transactional
	@Test
	void listChallengesAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.listChallengesAdmin(modelMap);
		assertEquals(view, "admin/challenges/challengesList");
	}
	
	@Transactional
	@Test
	void showChallengeByIdAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.showChallengeByIdAdmin(CHALLENGE_ID_1, modelMap);
		assertEquals(view, "admin/challenges/challengeDetails");
	}
	
	@Transactional
	@Test
	void initCreationForm() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initCreationForm(modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void processCreationFormSuccessful() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setName("Challenge1 Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		String view = this.challengeController.processCreationForm(EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}
	
	@Transactional
	@Test
	void processCreationFormErrorSameName() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setName("Challenge1 Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "There is already a challenge with that name the same week");
		String view = this.challengeController.processCreationForm(EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Transactional
	@Test
	void processCreationFormError3SameWeek() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setName("Challenge1 Name Test");
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("initialDate", "There must not be more than 3 challenges per week");
		String view = this.challengeController.processCreationForm(EXERCISE_ID_1, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Transactional
	@Test
	void processCreationFormErrorNameNull() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setName(null);
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "The name can not be empty");
		String view = this.challengeController.processCreationForm(EXERCISE_ID_1, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Transactional
	@Test
	void initUpdateForm() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initUpdateForm(CHALLENGE_ID_4,modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Transactional
	@Test
	void initUpdateFormWithInscriptionError() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initUpdateForm(CHALLENGE_ID_1,modelMap);
		assertEquals(view, "admin/challenges/challengeDetails");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void processUpdateFormSuccessful() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setId(CHALLENGE_ID_4);
		challenge.setName("Challenge4 Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		String view = this.challengeController.processUpdateForm(CHALLENGE_ID_4, EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void processUpdateFormErrorSameName() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setId(CHALLENGE_ID_4);
		challenge.setName("Challenge1 Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "There is already a challenge with that name the same week");
		String view = this.challengeController.processUpdateForm(CHALLENGE_ID_4, EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void processUpdateFormError3SameWeek() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setId(CHALLENGE_ID_4);
		challenge.setName("Challenge1 Name Test");
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("initialDate", "There must not be more than 3 challenges per week");
		String view = this.challengeController.processUpdateForm(CHALLENGE_ID_4, EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void processUpdateFormErrorNameNull() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		
		Challenge challenge = new Challenge();
		Calendar endDateCal = (Calendar) TEST_END_DATE_CALENDAR.clone();
		Calendar initialDateCal = (Calendar) TEST_INITIAL_DATE_CALENDAR.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge.setId(CHALLENGE_ID_4);
		challenge.setName(null);
		challenge.setDescription("Challenge Description Test");
		challenge.setInitialDate(initialDate);
		challenge.setEndDate(endDate);
		challenge.setPoints(100);
		challenge.setReward("Reward Test");
		challenge.setReps(100);
		challenge.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "The name can not be empty");
		String view = this.challengeController.processUpdateForm(CHALLENGE_ID_4, EXERCISE_ID_1, challenge, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void deleteChallengeInscriptionError() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.deleteChallenge(1,modelMap);
		assertEquals(view, "exception");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void deleteChallenge() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.deleteChallenge(CHALLENGE_ID_4,modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}
	
	
	//CLIENT
	
	@Transactional
	@Test
	void listChallengesClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_1);
		
		String view = this.challengeController.listChallengesClient(CLIENT_USERNAME_1, modelMap);
		assertEquals(view, "client/challenges/challengesList");
	}
	
	@Transactional
	@Test 
	void showChallengeByIdClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_1);
		
		String view = this.challengeController.showChallengeByIdClient(CLIENT_USERNAME_1, CHALLENGE_ID_4, modelMap);
		assertEquals(view, "client/challenges/challengeDetails");
	}
	
	@Transactional
	@Test 
	void showChallengeByIdClientErrorAlreadyInscribed() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_1);
		
		String view = this.challengeController.showChallengeByIdClient(CLIENT_USERNAME_1, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "exception");
	}

	@Transactional
	@Test
	void listMyChallengesClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_1);
		
		String view = this.challengeController.listMyChallengesClient(CLIENT_USERNAME_1, modelMap);
		assertEquals(view, "client/challenges/myChallengesList");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void showAndEditMyChallengeByIdClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_1);
		
		String view = this.challengeController.showAndEditMyChallengeByIdClient(CLIENT_USERNAME_1, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "client/challenges/myChallengeDetailsAndUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional
	@Test
	void showAndEditMyChallengeByIdClientErrorNotHisChallenge() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_2);
		
		String view = this.challengeController.showAndEditMyChallengeByIdClient(CLIENT_USERNAME_2, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "exception");
	}
	
	@Transactional
	@Test
	void TestWrongClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		logInClient(CLIENT_USERNAME_2);
		
		String view = this.challengeController.listChallengesClient(CLIENT_USERNAME_1, modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.showChallengeByIdClient(CLIENT_USERNAME_1, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.listMyChallengesClient(CLIENT_USERNAME_1, modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.showAndEditMyChallengeByIdClient(CLIENT_USERNAME_1, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "exception");
	}
	
	private void logInClient(String clientUsername) {
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(clientUsername, "client1999"));
		SecurityContextHolder.setContext(securityContext);
	}

}
