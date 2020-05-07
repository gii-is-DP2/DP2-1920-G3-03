package org.springframework.samples.yogogym.ui.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CRUDChallengesAdminUITest {

	private static final String ADMIN = "admin1";
	private static final String ADMIN_PASSWORD = "admin1999";

	
	@LocalServerPort
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	UtilsChallengesUI utils;

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsChallengesUI(port, driver);
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
	@Test
	public void createChallengeSuccessfully() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Name", "Challenge Desc", "2030/01/01", "2030/01/05", "Reward", "10", "12", "20");

		// Check there is a challenge created with that name in the list of challenges
		try {
			assertEquals("Challenge Name", driver.findElement(By.linkText("Challenge Name")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void createChallengeErrorSameName() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Same Name", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "10", "12", "20");
		utils.createChallenge("Challenge Same Name", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "100", "10", "10");

		// Check there is the error of the same name in the form
		try {
			assertEquals("There is already a challenge with that name the same week",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void createChallengeErrorMore3ChallengeSameWeek() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Test 1", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 2", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 3", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 4", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");

		// Check there is the error of more than 3 challenges same week in the form
		try {
			assertEquals("There must not be more than 3 challenges per week",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void createChallengeErrorPastDate() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Name", "Challenge Desc", "2010/01/01", "2010/01/05", "Reward", "10", "12", "20");

		// Check there is the error of past date in the form
		try {
			assertEquals("Starting date must be posterior to the actual date",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void createChallengeErrorsInForm() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("", "", "", "", "", "", "", "");

		// Check that those params cannot be empty
		try {
			assertEquals("The name can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("The description can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("The Initial Date can not be null",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("The End Date can not be null",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[4]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("The Reward can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[5]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Points can not be null or less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[6]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		utils.createChallenge("Challenge Name", "Challenge Desc", "2010/01/01", "2010/01/05", "Reward", "-1", "-10", "");

		// Points and repetitions cannot be less than 0
		try {
			assertEquals("Points can not be null or less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[6]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Repetitions can not be less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[7]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		utils.createChallenge("Challenge Name", "Challenge Desc", "2025/02/10", "2025/02/05", "Reward", "10", "10", "10");

		// The initial date can not be after the end date
		try {
			assertEquals("The end Date must be posterior to the initial Date",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[4]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void UpdateChallengeSuccesfully(){
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.updateChallenge("Challenge4","reps","5");
		
		try {
			driver.findElement(By.linkText("Challenge4")).click();
			assertEquals("5", driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[7]/td")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void UpdateChallengeErrorSameName() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Same Name 1", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "10", "12", "20");
		utils.createChallenge("Challenge Same Name 2", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "100", "10", "10");
		utils.updateChallenge("Challenge Same Name 2","name","Challenge Same Name 1");
		
		// Check there is the error of the same name in the form
		try {
			assertEquals("There is already a challenge with that name the same week",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void updateChallengeErrorMore3ChallengeSameWeek() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Test 1", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 2", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 3", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		utils.createChallenge("Challenge Test 4", "Challenge Desc", "2030/03/10", "2030/03/25", "Reward", "100", "12", "20");
		utils.updateChallenge("Challenge Test 4","initialDate","2030/03/01");

		// Check there is the error of more than 3 challenges same week in the form
		try {
			assertEquals("There must not be more than 3 challenges per week",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void updateChallengeErrorPastDate() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Past Date", "Challenge Desc", "2030/01/01", "2030/01/05", "Reward", "10", "12", "20");
		utils.updateChallenge("Challenge Past Date","initialDate","2010/03/01");

		// Check there is the error of past date in the form
		try {
			assertEquals("Starting date must be posterior to the actual date",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void updateChallengeErrorsInForm() {

		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge Update Test", "Challenge Desc", "2070/01/01", "2070/01/05", "Reward", "10", "12", "20");
		
		// Check that those params cannot be empty
		utils.updateChallenge("Challenge Update Test","name","");
		try {
			assertEquals("The name can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","description","");
		try {
			assertEquals("The description can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","initialDate","");
		try {
			assertEquals("The Initial Date can not be null",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","endDate","");
		try {
			assertEquals("The End Date can not be null",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[4]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","reward","");
		try {
			assertEquals("The Reward can not be empty",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[5]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","points","");
		try {
			assertEquals("Points can not be null or less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[6]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// Points and repetitions cannot be less than 0
		utils.updateChallenge("Challenge Update Test","points","-5");		
		try {
			assertEquals("Points can not be null or less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[6]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		utils.updateChallenge("Challenge Update Test","reps","-10");
		try {
			assertEquals("Repetitions can not be less than 0",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[7]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		utils.updateChallenge("Challenge Update Test","initialDate","2075/01/01");	
		// The initial date can not be after the end date
		try {
			assertEquals("The end Date must be posterior to the initial Date",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[4]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void deleteChallenge() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.createChallenge("Challenge to be deleted", "Challenge Desc", "2075/01/01", "2075/01/05", "Reward", "10", "12", "20");
		utils.deleteChallenge("Challenge to be deleted");
	}
	
	

}
