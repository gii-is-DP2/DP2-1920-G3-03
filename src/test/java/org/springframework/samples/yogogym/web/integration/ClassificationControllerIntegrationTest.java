package org.springframework.samples.yogogym.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.yogogym.web.ClasificationController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassificationControllerIntegrationTest {

	@Autowired
	private ClasificationController classificationController;

	@WithMockUser(username = "client3", authorities = { "client" })
	@Test
	void showClassificationChallengeSuccessful() throws Exception {
		ModelMap model = new ModelMap();
		String username = "client3";
		String view = this.classificationController.getClasification(username, model);
		assertEquals(view, "client/clasifications/clasification");
	}
	
	@WithMockUser(username = "client1", authorities = { "client" })
	@Test
	void showClassificationChallengeFailed() throws Exception {
		ModelMap model = new ModelMap();
		String username = "client3";
		String view = this.classificationController.getClasification(username, model);
		assertEquals(view, "exception");
	}

}
