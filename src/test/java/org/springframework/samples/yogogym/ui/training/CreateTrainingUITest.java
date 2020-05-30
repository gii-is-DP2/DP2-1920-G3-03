package org.springframework.samples.yogogym.ui.training;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateTrainingUITest {
	
	@LocalServerPort
  	private int port;
  	private WebDriver driver;
  
  	UtilsTrainingUI utils;
  
  	//Globally used
  	private static final String TRAINER1_USERNAME = "trainer1";
  	private static final String TRAINER1_PASS = "trainer1999";
  	private static final String NEW_TRAINING_NAME = "Nuevo Entrenamiento";
	private SimpleDateFormat formatterDetails = new SimpleDateFormat("yyyy-MM-dd");

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

	@Test
	public void accessCreateTrainingClientNotTrained() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		driver.get("http://localhost:" + port + "/trainer/trainer1/clients/3/trainings/create");
		
		utils.exceptionView();
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateTrainingWithoutErrorsUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 7);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, false);
		
		trainingSuccessfullyCreated(calInit, calEnd);
	}
	
	@Test
	public void testCreateTrainingEmptyUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		utils.createTraining(null, null, null, false);
		
		try {
	    	assertEquals("no puede estar vacío", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("no puede ser null", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("no puede ser null", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingInitInPastUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calInit.add(Calendar.DAY_OF_MONTH, -1);
		calEnd.add(Calendar.DAY_OF_MONTH, 7);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, false);
		
		try {
	    	assertEquals("The initial date cannot be in the past", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
		    utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingEndBeforeInitUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, -1);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, false);
		
		try {
			assertEquals("The end date must be after the initial date", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingEndEqualsInitUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		utils.createTraining(NEW_TRAINING_NAME, calInit, calInit, false);
		
		try {
	    	assertEquals("The end date must be after the initial date", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
		    utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingLongerThan90DaysUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 91);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, false);
		
		try {
	    	assertEquals("The training cannot be longer than 90 days", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
		    utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingInitInTrainingUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 8);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, true);
		
		Calendar calAuxInit = Calendar.getInstance();
		Calendar calAuxEnd = Calendar.getInstance();
		calAuxInit.add(Calendar.DAY_OF_MONTH, -7);
		calAuxEnd.add(Calendar.DAY_OF_MONTH, 7);
		try {
	    	assertEquals("The training cannot start in a period with other training (The other training is from " + formatterDetails.format(calAuxInit.getTime()) + " to " + formatterDetails.format(calAuxEnd.getTime()) + ")", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
		    utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingEndInTrainingUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		calInit.add(Calendar.DAY_OF_MONTH, 8);
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 14);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, true);
		
		Calendar calAuxInit = Calendar.getInstance();
		Calendar calAuxEnd = Calendar.getInstance();
		calAuxInit.add(Calendar.DAY_OF_MONTH, 14);
		calAuxEnd.add(Calendar.DAY_OF_MONTH, 21);
		try {
			assertEquals("The training cannot end in a period with other training (The other training is from " + formatterDetails.format(calAuxInit.getTime()) + " to " + formatterDetails.format(calAuxEnd.getTime()) + ")",
				driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testCreateTrainingIncludingTrainingUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calInit = Calendar.getInstance();
		calInit.add(Calendar.DAY_OF_MONTH, 8);
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 22);
		utils.createTraining(NEW_TRAINING_NAME, calInit, calEnd, true);
		
		Calendar calAuxInit = Calendar.getInstance();
		Calendar calAuxEnd = Calendar.getInstance();
		calAuxInit.add(Calendar.DAY_OF_MONTH, 14);
		calAuxEnd.add(Calendar.DAY_OF_MONTH, 21);
		try {
			assertEquals("The training cannot be in a period which includes another training (The other training is from " + formatterDetails.format(calAuxInit.getTime()) + " to " + formatterDetails.format(calAuxEnd.getTime()) + ")",
				driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	private void trainingSuccessfullyCreated(Calendar calInit, Calendar calEnd) {
		try {
	    	assertEquals("Name: " + NEW_TRAINING_NAME, driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Starts: " + formatterDetails.format(calInit.getTime()) + " 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Ends: " + formatterDetails.format(calEnd.getTime()) + " 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Editing Permission: TRAINER", driver.findElement(By.xpath("//p[3]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Edit Training", driver.findElement(By.linkText("Edit Training")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Delete Training", driver.findElement(By.linkText("Delete Training")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Copy Training", driver.findElement(By.linkText("Copy Training")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Routines", driver.findElement(By.xpath("//h3[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Add Routine", driver.findElement(By.linkText("Add Routine")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Diet", driver.findElement(By.xpath("//h3[4]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
		    assertEquals("Add Diet", driver.findElement(By.linkText("Add Diet")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

}
