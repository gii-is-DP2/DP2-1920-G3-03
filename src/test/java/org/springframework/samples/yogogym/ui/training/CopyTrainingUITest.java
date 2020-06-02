package org.springframework.samples.yogogym.ui.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CopyTrainingUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	UtilsTrainingUI utils;

	// Globally used
	private static final String TRAINER1_USERNAME = "trainer1";
	private static final String TRAINER1_PASS = "trainer1999";
	private static final String NEW_TRAINING_NAME = "Nuevo Entrenamiento";

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsTrainingUI(port, driver);
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = utils.getVerificationError().toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCopyTrainingUISuccessful() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);

		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 7);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, false);

		utils.copyTraining(NEW_TRAINING_NAME);
		try {
			assertEquals("Cardio", driver.findElement(By.linkText("Cardio")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

	@Test
	public void testCopyTrainingUIWrong() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);

		driver.get("http://localhost:" + port + "/trainer/trainer1/clients/1/trainings/1/copyTraining");

		utils.exceptionView();
	}

}
