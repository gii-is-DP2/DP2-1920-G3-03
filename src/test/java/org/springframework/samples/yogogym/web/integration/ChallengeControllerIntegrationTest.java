package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.web.ChallengeController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeControllerIntegrationTest {
	
	private static final Calendar testInitialTrainingDate = Calendar.getInstance();
	private static final Calendar testEndTrainingDate = Calendar.getInstance();
	
	@Autowired
	private ChallengeController challengeController;
	
	//ADMIN
	
	@Test
	void listChallengesAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.listChallengesAdmin(modelMap);
		assertEquals(view, "admin/challenges/challengesList");
	}
	
	@Test
	void showChallengeByIdAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		String view = this.challengeController.showChallengeByIdAdmin(challengeId, modelMap);
		assertEquals(view, "admin/challenges/challengeDetails");
	}
	
	@Test
	void initCreationForm() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initCreationForm(modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void processCreationFormSuccessful() throws Exception{
		ModelMap modelMap = new ModelMap();
		int exerciseId = 1;
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(6);
		challenge1.setName("Challenge1 Name Test");
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		String view = this.challengeController.processCreationForm(exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}
	
	@Test
	void processCreationFormErrorSameName() throws Exception{
		ModelMap modelMap = new ModelMap();
		int exerciseId = 1;
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(7);
		challenge1.setName("Challenge1 Name Test");
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "There is already a challenge with that name the same week");
		String view = this.challengeController.processCreationForm(exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void processCreationFormError3SameWeek() throws Exception{
		ModelMap modelMap = new ModelMap();
		int exerciseId = 1;
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(8);
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
		String view = this.challengeController.processCreationForm(exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void processCreationFormErrorNameNull() throws Exception{
		ModelMap modelMap = new ModelMap();
		int exerciseId = 1;
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(9);
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
		String view = this.challengeController.processCreationForm(exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void initUpdateForm() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initUpdateForm(6,modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void initUpdateFormWithInscriptionError() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.initUpdateForm(1,modelMap);
		assertEquals(view, "admin/challenges/challengeDetails");
	}
	
	@Test
	void processUpdateFormSuccessful() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 6;
		int exerciseId = 4;
		
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(1);
		challenge1.setName("Challenge4 Name Test");
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		String view = this.challengeController.processUpdateForm(challengeId, exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}
	
	@Test
	void processUpdateFormErrorSameName() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 6;
		int exerciseId = 1;
		
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(2);
		challenge1.setName("Challenge1 Name Test");
		challenge1.setDescription("Challenge Description Test");
		challenge1.setInitialDate(initialDate);
		challenge1.setEndDate(endDate);
		challenge1.setPoints(100);
		challenge1.setReward("Reward Test");
		challenge1.setReps(100);
		challenge1.setWeight(100.);
		
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("name", "There is already a challenge with that name the same week");
		String view = this.challengeController.processUpdateForm(challengeId, exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void processUpdateFormError3SameWeek() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 6;
		int exerciseId = 1;
		
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(3);
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
		String view = this.challengeController.processUpdateForm(challengeId, exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void processUpdateFormErrorNameNull() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 6;
		int exerciseId = 1;
		
		Challenge challenge1 = new Challenge();
		Calendar endDateCal = (Calendar) testEndTrainingDate.clone();
		Calendar initialDateCal = (Calendar) testInitialTrainingDate.clone();
		endDateCal.add(Calendar.DAY_OF_MONTH, 1);
		Date initialDate = initialDateCal.getTime();
		Date endDate = endDateCal.getTime();
		challenge1.setId(4);
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
		String view = this.challengeController.processUpdateForm(challengeId, exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}
	
	@Test
	void deleteChallengeInscriptionError() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.deleteChallenge(1,modelMap);
		assertEquals(view, "exception");
	}
	
	/*@After
	void deleteChallenge() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		String view = this.challengeController.deleteChallenge(6,modelMap);
		assertEquals(view, "redirect:/admin/challenges");
	}*/
	
	
	//CLIENT
	
	@Test
	void listChallengesClient() throws Exception{
		ModelMap modelMap = new ModelMap();
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.listChallengesClient("client1", modelMap);
		assertEquals(view, "client/challenges/challengesList");
	}
	
	@Test 
	void showChallengeByIdClient() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 4;
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.showChallengeByIdClient("client1", challengeId, modelMap);
		assertEquals(view, "client/challenges/challengeDetails");
	}

	@Test 
	void showChallengeByIdClientErrorAlreadyInscribed() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.showChallengeByIdClient("client1", challengeId, modelMap);
		assertEquals(view, "exception");
	}

	@Test
	void listMyChallengesClient() throws Exception{
		ModelMap modelMap = new ModelMap();
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.listMyChallengesClient("client1", modelMap);
		assertEquals(view, "client/challenges/myChallengesList");
	}
	
	@Test
	void showAndEditMyChallengeByIdClient() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.showAndEditMyChallengeByIdClient("client1", challengeId, modelMap);
		assertEquals(view, "client/challenges/myChallengeDetailsAndUpdate");
	}
	
	@Test
	void showAndEditMyChallengeByIdClientErrorNotHisChallenge() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client2", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.showAndEditMyChallengeByIdClient("client2", challengeId, modelMap);
		assertEquals(view, "exception");
	}
	
	@Test
	void TestWrongClient() throws Exception{
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client2", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.challengeController.listChallengesClient("client1", modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.showChallengeByIdClient("client1", challengeId, modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.listMyChallengesClient("client1", modelMap);
		assertEquals(view, "exception");
		view = this.challengeController.showAndEditMyChallengeByIdClient("client1", challengeId, modelMap);
		assertEquals(view, "exception");
	}

}
