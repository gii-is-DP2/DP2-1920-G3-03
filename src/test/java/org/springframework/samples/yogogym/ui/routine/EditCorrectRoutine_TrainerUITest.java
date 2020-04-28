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
public class EditCorrectRoutine_TrainerUITest {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	
	UtilsRoutineUI utils;
	
	//Globally used
	
	String routineToEdit = "Routine 1";
	String username = "trainer1";
	String password = "trainer1999";
	
	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		utils = new UtilsRoutineUI(port, driver);
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testEditNameRoutineCorrect() throws Exception {

		String newRoutineName = "name:Edit Name";
		utils.updateRoutine(routineToEdit, username, password, newRoutineName);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testEditDescriptionRoutineCorrect() throws Exception{
		
		String newRoutineDescription = "description:Edited Description";
		utils.updateRoutine(routineToEdit, username, password, newRoutineDescription);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testEditRepsPerWeekRoutineCorrect() throws Exception
	{
		// No mayor de 20 si no, no se puede actualizar la rutina
		String newRoutineRepsPerWeek = "repsPerWeek:15";
		utils.updateRoutine(routineToEdit, username, password, newRoutineRepsPerWeek);
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
