package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.service.ChallengeService;
import org.springframework.samples.yogogym.service.ClientService;
import org.springframework.samples.yogogym.service.ExerciseService;
import org.springframework.samples.yogogym.service.InscriptionService;
import org.springframework.samples.yogogym.web.ChallengeController;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeControllerIntegrationTest {
	
	private static final Calendar testInitialTrainingDate = Calendar.getInstance();
	private static final Calendar testEndTrainingDate = Calendar.getInstance();
	
	@Autowired
	private ChallengeController challengeController;
	
	@Autowired
	private ChallengeService challengeService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private InscriptionService inscriptionService;
	
	@Autowired
	private ClientService clientService;
	
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
		challenge1.setId(1);
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
		String view = this.challengeController.processCreationForm(exerciseId, challenge1, bindingResult, modelMap);
		assertEquals(view, "/admin/challenges/challengesCreateOrUpdate");
	}

}
