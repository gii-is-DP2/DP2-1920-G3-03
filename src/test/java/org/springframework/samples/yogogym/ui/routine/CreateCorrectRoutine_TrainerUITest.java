package org.springframework.samples.yogogym.ui.routine;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCorrectRoutine_TrainerUITest {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	
	UtilsRoutineUI utils;
	
	//Globally used
	String username = "trainer1";
	String password = "trainer1999";

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsRoutineUI(port,driver);
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateRoutineCorrect() throws Exception {
				
		String newRoutineName = "Routine New";
		String newRoutineDescription = "Routine Description New";
		//No poner un numero superior a 20, si no saltaría excepción
		String newRoutineRepsPerWeek = "15";		
	
		utils.createRoutine(username, password, newRoutineName, newRoutineDescription, newRoutineRepsPerWeek,1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = utils.getVerificationError().toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
