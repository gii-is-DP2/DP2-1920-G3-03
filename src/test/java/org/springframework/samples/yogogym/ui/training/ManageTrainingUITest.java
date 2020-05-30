package org.springframework.samples.yogogym.ui.training;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class ManageTrainingUITest {
	
	@LocalServerPort
  	private int port;
  	private WebDriver driver;
  
  	UtilsTrainingUI utils;
  
  	private static final String TRAINER1_USERNAME = "trainer1";
  	private static final String TRAINER1_PASS = "trainer1999";
  	private static final String UPDATED_TRAINING_NAME = "Entrenamiento 1 Actualizado";
	private SimpleDateFormat formatterDetails = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private SimpleDateFormat formatterError = new SimpleDateFormat("yyyy-MM-dd");

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
	public void testUpdateTrainingWithoutEditingPermissionUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		driver.get("http://localhost:" + port + "/trainer/trainer1/clients/5/trainings/6/edit");
		
		utils.exceptionView();
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testUpdateTrainingWithEditingPermissionUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calEnd = Calendar.getInstance();
		
		String endDateNoTime = formatterError.format(calEnd.getTime());
		Date enDate = formatterError.parse(endDateNoTime);
		
		utils.updateTraining(UPDATED_TRAINING_NAME, calEnd, true);
		
		try {
			assertEquals("Name: " + UPDATED_TRAINING_NAME, driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Ends: " + formatterDetails.format(enDate), driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		
	}
	
	@Test
	public void testUpdateTrainingEmptyUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		utils.updateTraining("", "", true);
		
		try {
	    	assertEquals("no puede estar vacío", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div/div/span[2]")).getText());
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
	public void testUpdateTrainingEndInPastUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, -1);
		utils.updateTraining(null, calEnd, true);
		
		try {
	    	assertEquals("The end date cannot be in the past", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testUpdateTrainingLongerThan90DaysUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 105);
		
		utils.updateTraining(null, calEnd, false);
		
		try {
			assertEquals("The training cannot be longer than 90 days", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testUpdateTrainingEndInTrainingUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 14);
		utils.updateTraining(null, calEnd, true);
		
		Calendar calInitAux = Calendar.getInstance();
		calInitAux.add(Calendar.DAY_OF_MONTH, 14);
		Calendar calEndAux = Calendar.getInstance();
		calEndAux.add(Calendar.DAY_OF_MONTH, 21);
		
		try {
			assertEquals("The training cannot end in a period with other training (The other training is from " + formatterError.format(calInitAux.getTime()) + " to " + formatterError.format(calEndAux.getTime()) + ")",
				driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testUpdateTrainingIncludingOtherTrainingUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.DAY_OF_MONTH, 22);
		utils.updateTraining(null, calEnd, true);
		
		Calendar calInitAux = Calendar.getInstance();
		calInitAux.add(Calendar.DAY_OF_MONTH, 14);
		Calendar calEndAux = Calendar.getInstance();
		calEndAux.add(Calendar.DAY_OF_MONTH, 21);
		
		try {
			assertEquals("The training cannot be in a period which includes another training (The other training is from " + formatterError.format(calInitAux.getTime()) + " to " + formatterError.format(calEndAux.getTime()) + ")", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testDeleteTrainingNotBeingAuthorUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);

		driver.get("http://localhost:" + port + "/trainer/trainer1/clients/6/trainings/11/delete");

		utils.exceptionView();
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testDeleteTrainingBeingAuthorUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		utils.accessListTrainings();
		driver.findElement(By.linkText("Entrenamiento1")).click();
		
		driver.findElement(By.linkText("Delete Training")).click();
		try {
			assertEquals("The training was deleted successfully", driver.findElement(By.xpath("//body/div/div/div/p")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
}
