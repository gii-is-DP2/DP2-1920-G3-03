package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.model.Enums.Status;
import org.springframework.samples.yogogym.web.InscriptionController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InscriptionControllerIntegrationTest {
	
	private static final int INSCRIPTION_ID_2 = 2;
	private static final int INSCRIPTION_ID_4 = 4;
	private static final int INSCRIPTION_ID_6 = 6;
	
	private static final int CHALLENGE_ID_1 = 1;
	private static final int CHALLENGE_ID_4 = 4;
	private static final int CHALLENGE_ID_5 = 5;
	
	private static final String CLIENT_USERNAME_1 = "client1";
	private static final String CLIENT_USERNAME_2 = "client2";
	
	@Autowired
	private InscriptionController inscriptionController;
	
	//ADMIN
	
	@Transactional
	@Test
	void listSubmittedInscriptionsAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.inscriptionController.listSubmittedInscriptionsAdmin(modelMap);
		assertEquals(view, "admin/challenges/submittedInscriptionsList");
	}
	
	@Transactional
	@Test
	void showSubmittedInscriptionsAdmin() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		String view = this.inscriptionController.showSubmittedInscriptionAdmin(INSCRIPTION_ID_6, modelMap);
		assertEquals(view, "admin/challenges/submittedChallengeDetails");
	}
	
	@Transactional
	@Test
	void showSubmittedInscriptionsAdminErrorNotSubmitted() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.inscriptionController.showSubmittedInscriptionAdmin(INSCRIPTION_ID_2, modelMap);
		assertEquals(view, "exception");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void evaluateChallengeAdmin() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Inscription inscription = new Inscription();
		inscription.setStatus(Status.SUBMITTED);
		
		String view = this.inscriptionController.evaluateChallengeAdmin(CHALLENGE_ID_5, INSCRIPTION_ID_2, inscription, modelMap);
		assertEquals(view, "redirect:/admin/inscriptions/submitted");
	}
	
	//CLIENT
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void createInscriptionByChallengeIdClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		
		logInClient(CLIENT_USERNAME_1);

		String view = this.inscriptionController.createInscriptionByChallengeId(CLIENT_USERNAME_1, CHALLENGE_ID_5, modelMap);
		assertEquals(view, "redirect:/client/" + CLIENT_USERNAME_1 + "/challenges");
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Transactional 
	@Test
	void submitInscription() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Inscription inscription = new Inscription();
		inscription.setUrl("http://testingthetest.com");
		
		logInClient(CLIENT_USERNAME_2);
		
		String view = this.inscriptionController.submitInscription(CLIENT_USERNAME_2, CHALLENGE_ID_4, INSCRIPTION_ID_4, inscription, modelMap);
		assertEquals(view, "redirect:/client/" + CLIENT_USERNAME_2 +"/challenges/mine/" + CHALLENGE_ID_4);
	}
	
	@Transactional
	@Test
	void TestWrongClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		Inscription inscription = new Inscription();
		inscription.setUrl("http://testingthetest.com");
		
		logInClient(CLIENT_USERNAME_2);
		
		String view = this.inscriptionController.createInscriptionByChallengeId(CLIENT_USERNAME_1, CHALLENGE_ID_1, modelMap);
		assertEquals(view, "exception");
		
		view = this.inscriptionController.submitInscription(CLIENT_USERNAME_1, CHALLENGE_ID_1, INSCRIPTION_ID_4, inscription, modelMap);
		assertEquals(view, "exception");
	}
	
	private void logInClient(String clientUsername) {
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(clientUsername, "client1999"));
		SecurityContextHolder.setContext(securityContext);
	}
	
}
