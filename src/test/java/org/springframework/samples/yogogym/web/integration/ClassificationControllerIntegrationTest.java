package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.web.ClasificationController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassificationControllerIntegrationTest {

	@Autowired
	private ClasificationController classificationController;

	@Test
	void showClassificationChallengeSuccessful() throws Exception {
		ModelMap model = new ModelMap();
		String username = "client3";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client3", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		String view = this.classificationController.getClasification(username, model);
		assertEquals(view, "client/clasifications/clasification");
	}

	@Test
	void showClassificationChallengeEmptySuccessful() throws Exception {
		ModelMap model = new ModelMap();
		String username = "client1";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		String view = this.classificationController.getClasification(username, model);
		assertEquals(view, "client/clasifications/clasification");
	}

	@Test
	void showClassificationChallengeFailed() throws Exception {
		ModelMap model = new ModelMap();
		String username = "client3";
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("client1", "client1999"));
		SecurityContextHolder.setContext(securityContext);
		String view = this.classificationController.getClasification(username, model);
		assertEquals(view, "exception");
	}

}
