package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Routine;
import org.springframework.samples.yogogym.web.RoutineController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoutineControllerIntegrationTest {

	@Autowired
	private RoutineController routineController;
	
	//TRAINER============================================================================
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testWrongAuthority_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";		
		setAuthority(trainerUsername,trainerPassword);
		
		//Check that trainer 1 is not able to see routines of the trainers
		String wrongTrainerUsername = "trainer2";
		ModelMap model = new ModelMap();
		
		String view = routineController.routinesList(wrongTrainerUsername, model);
		assertEquals(view, "exception");
	}
	
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testWrongClient_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";		
		setAuthority(trainerUsername,trainerPassword);
		
		//Check that trainer 1 is not able to see routines of clients which he/she do not train
		
		int clientId = 4;
		int routineId = 5;
		int trainingId = 4;
		ModelMap model = new ModelMap();
		
		String view = routineController.clientRoutineDetails(trainerUsername, clientId, routineId, trainingId, model);
		assertEquals(view, "exception");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testInitCreateForm_Trainer() throws Exception {
	
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";
		setAuthority(trainerUsername,trainerPassword);
			
		int clientId = 1;
		int trainingId = 9;
		ModelMap model = new ModelMap();
		
		String view = routineController.initRoutineCreateForm(trainingId, clientId, trainerUsername, model);
		assertEquals(view, "trainer/routines/routinesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testProcessCreateForm_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";
		setAuthority(trainerUsername,trainerPassword);
				
		int clientId = 1;
		int trainingId = 9;
		BindingResult result = new MapBindingResult(Collections.emptyMap(), "");
		RedirectAttributes redirectAttrs = null;
		ModelMap model = new ModelMap();
		
		Routine routine = createRoutine("Routine Name","Routine Description",10);
		
		String view = routineController.processRoutineCreationForm(routine,result,trainerUsername,trainingId,clientId,model,redirectAttrs);
		assertEquals(view, "redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/" + trainingId);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testInitUpdateForm_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";
		setAuthority(trainerUsername,trainerPassword);
				
		int clientId = 1;
		int routineId = 9;
		int trainingId = 9;
		ModelMap model = new ModelMap();
		
		String view = routineController.initEditRoutine(routineId, clientId, trainingId, trainerUsername, model);
		assertEquals(view, "trainer/routines/routinesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testProcessUpdateForm_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";
		setAuthority(trainerUsername,trainerPassword);
				
		int clientId = 1;
		int routineId = 9;
		int trainingId = 9;
		BindingResult result = new MapBindingResult(Collections.emptyMap(), "");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		ModelMap model = new ModelMap();
		
		Routine routine = createRoutine("Routine Name","Routine Description",10);
		
		String view = routineController.processRoutineEditForm(routine,result,trainerUsername,trainingId,clientId,routineId,model,redirectAttrs);
		assertEquals(view, "redirect:/trainer/" + trainerUsername + "/clients/" + clientId + "/trainings/" + trainingId + "/routines/" + routineId);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testDeleteRoutine_Trainer() throws Exception {
		
		String trainerUsername = "trainer1";
		String trainerPassword = "trainer1999";
		setAuthority(trainerUsername,trainerPassword);
				
		int clientId = 1;
		int routineId = 9;
		int trainingId = 9;
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		
		String view = routineController.deleteRoutine(routineId, clientId, trainingId, trainerUsername, redirectAttrs);
		assertEquals(view, "redirect:/trainer/" + trainerUsername + "/routines");
	}
	
	//CLIENT==================================================================================
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testInitCreateForm_Client() throws Exception {
	
		String clientUsername = "client1";
		String clientPassword = "client1999";
		setAuthority(clientUsername,clientPassword);
			
		int trainingId = 9;
		ModelMap model = new ModelMap();
		
		String view = routineController.initProcessForm(trainingId, clientUsername, model);
		assertEquals(view, "client/routines/routinesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testProcessCreateForm_Client() throws Exception {
	
		String clientUsername = "client1";
		String clientPassword = "client1999";
		setAuthority(clientUsername,clientPassword);
		
		int trainingId = 9;
		
		BindingResult result = new MapBindingResult(Collections.emptyMap(), "");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		ModelMap model = new ModelMap();
		
		Routine routine = createRoutine("Routine Name","Routine Description",10);

		String view = routineController.postProcessForm(routine, result, trainingId, clientUsername, model, redirectAttrs);
		assertEquals(view, "redirect:/client/" + clientUsername + "/trainings/" + trainingId);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testInitUpdateForm_Client() throws Exception {
	
		String clientUsername = "client1";
		String clientPassword = "client1999";
		setAuthority(clientUsername,clientPassword);
			
		int trainingId = 9;
		int routineId = 9;
		ModelMap model = new ModelMap();
		
		String view = routineController.initUpdateForm(trainingId, routineId, clientUsername, model);
		assertEquals(view, "client/routines/routinesCreateOrUpdate");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void testProcessUpdateForm_Client() throws Exception {
	
		String clientUsername = "client1";
		String clientPassword = "client1999";
		setAuthority(clientUsername,clientPassword);
			
		int trainingId = 9;
		int routineId = 9;
		
		BindingResult result = new MapBindingResult(Collections.emptyMap(), "");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		ModelMap model = new ModelMap();
		
		Routine routine = createRoutine("Routine Name","Routine Description",10);
		
		String view = routineController.postUpdateForm(routine, result, trainingId, routineId, clientUsername, model, redirectAttrs);
		assertEquals(view, "redirect:/client/" + clientUsername + "/trainings/" + trainingId);
	}
	
	//Derived Methods
	public void setAuthority(String username, String password)
	{
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.setContext(securityContext);
	}
	
	public Routine createRoutine(String name, String description, int repsPerWeek)
	{
		Routine res = new Routine();
		
		res.setName(name);
		res.setDescription(description);
		res.setRepsPerWeek(repsPerWeek);
		
		return res;
	}
	
}
