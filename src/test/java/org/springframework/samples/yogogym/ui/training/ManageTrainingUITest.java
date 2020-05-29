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
  
  	//Globally used
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
	public void testListTrainingsOtherTrainerUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		driver.get("http://localhost:" + port + "/trainer/trainer2/trainings");
		
		utils.exceptionView();
	}
	
	@Test
	public void testListTrainingsUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		utils.accessListTrainings();
		
		this.trainingListSuccessful();
	}
	
	@Test
	public void testShowDetailTrainingClientNotTrainedUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		driver.get("http://localhost:" + port + "/trainer/trainer1/clients/3/trainings/4");
		
		utils.exceptionView();
	}
	
	public void testShowDetailsTrainingClientTrainedUI() throws Exception {
		utils.as(TRAINER1_USERNAME, TRAINER1_PASS);
		
		utils.accessListTrainings();
		
		driver.findElement(By.linkText("Entrenamiento1")).click();
		
		trainingShowSuccessful();
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
	
	private void trainingShowSuccessful() {
		try {
			assertEquals("Name: Entrenamiento1", driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Starts: 2020-01-01 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Ends: 2020-01-14 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
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
			assertEquals("Cardio", driver.findElement(By.linkText("Cardio")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Brazos", driver.findElement(By.linkText("Brazos")).getText());
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
		try {
			assertEquals("Dieta 1", driver.findElement(By.linkText("Dieta 1")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	private void trainingListSuccessful() {
		try {
			assertEquals("All Trainings", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Client Martin Antonio Lera ( marantle@yogogym.com )", driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento1", driver.findElement(By.linkText("Entrenamiento1")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento1 (COMPLETED)", driver.findElement(By.xpath("//div/div/div/ul/li")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento2", driver.findElement(By.linkText("Entrenamiento2")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento2 (COMPLETED)", driver.findElement(By.xpath("//div/div/div/ul/li[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Test", driver.findElement(By.linkText("Test")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Test (ON GOING)", driver.findElement(By.xpath("//div/ul/li[3]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Client Federico Javier Saco ( fejasa@yogogym.com )", driver.findElement(By.xpath("//div[2]/h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento3", driver.findElement(By.linkText("Entrenamiento3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento3 (COMPLETED)", driver.findElement(By.xpath("//div/div/div[2]/ul/li")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Client Carmelina Esteso Rodríguez ( caresro@yogogym.com )", driver.findElement(By.xpath("//div[3]/h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento2", driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento2')])[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento2 (COMPLETED)", driver.findElement(By.xpath("//div[3]/ul/li")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Client Sofia Victoria Obeso ( soviob@yogogym.com )", driver.findElement(By.xpath("//div[4]/h3")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento3", driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento3')])[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento3 (COMPLETED)", driver.findElement(By.xpath("//div[4]/ul/li")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("Entrenamiento1 (ON GOING)", driver.findElement(By.xpath("//div[4]/ul/li[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

}
