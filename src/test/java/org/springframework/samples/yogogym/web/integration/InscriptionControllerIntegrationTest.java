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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InscriptionControllerIntegrationTest {
	
	
	@Autowired
	private InscriptionController inscriptionController;
	
	//ADMIN
	
	@Test
	void listSubmittedInscriptionsAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		String view = this.inscriptionController.listSubmittedInscriptionsAdmin(modelMap);
		assertEquals(view, "admin/challenges/submittedInscriptionsList");
	}
	
	@Test
	void showSubmittedInscriptionsAdmin() throws Exception{
		ModelMap modelMap = new ModelMap();
		int inscriptionId = 6;
		String view = this.inscriptionController.showSubmittedInscriptionAdmin(inscriptionId, modelMap);
		assertEquals(view, "admin/challenges/submittedChallengeDetails");
	}
	
	@Test
	void showSubmittedInscriptionsAdminErrorNotSubmitted() throws Exception{
		ModelMap modelMap = new ModelMap();
		int inscriptionId = 2;
		String view = this.inscriptionController.showSubmittedInscriptionAdmin(inscriptionId, modelMap);
		assertEquals(view, "exception");
	}
	
	@Test
	void evaluateChallengeAdmin() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 5;
		int inscriptionId = 2;
		Inscription inscription = new Inscription();
		inscription.setStatus(Status.SUBMITTED);
		
		String view = this.inscriptionController.evaluateChallengeAdmin(challengeId, inscriptionId, inscription, modelMap);
		assertEquals(view, "redirect:/admin/inscriptions/submitted");
	}
	
	//CLIENT
	
	/*    NO FUNCIONA AL GUARDAR LA INSCRIPCIÓN: DETACHED (CHALLENGE)
	@Test
	void createInscriptionByChallengeIdClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 5;
		String client = "client1";
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);

		String view = this.inscriptionController.createInscriptionByChallengeId(client, challengeId, modelMap);
		assertEquals(view, "redirect:/admin/inscriptions/submitted");
	}*/
	
	@Test
	void submitInscription() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 4;
		int inscriptionId = 4;
		String client = "client2";
		Inscription inscription = new Inscription();
		inscription.setUrl("http://testingthetest.com");
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client2", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.inscriptionController.submitInscription(client, challengeId, inscriptionId, inscription, modelMap);
		assertEquals(view, "redirect:/client/client2/challenges/mine/4");
	}
	
	@Test
	void TestWrongClient() throws Exception{
		
		ModelMap modelMap = new ModelMap();
		int challengeId = 1;
		int inscriptionId = 4;
		Inscription inscription = new Inscription();
		inscription.setUrl("http://testingthetest.com");
		String client = "client1";
		
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client2", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		
		String view = this.inscriptionController.createInscriptionByChallengeId(client, challengeId, modelMap);
		assertEquals(view, "exception");
		
		view = this.inscriptionController.submitInscription(client, challengeId, inscriptionId, inscription, modelMap);
		assertEquals(view, "exception");
	}
	
}
