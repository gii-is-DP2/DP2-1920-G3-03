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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateChallengeAdminUITest {

	private static final String ADMIN = "admin1";
	
	
	@LocalServerPort
	private int port;

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterEach
	@Sql(scripts = "drop-tables.sql")
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
	
	@Test
	public void createChallengeSuccessfully() {
		
		as(ADMIN);
		createChallenge("Challenge Name", "Challenge Desc", "2030/01/01", "2030/01/05", "Reward", "10", "12", "20");
		
		// Check there is a challenge created with that name in the list of challenges
		try {
			assertEquals("Challenge Name", driver.findElement(By.linkText("Challenge Name")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void createChallengeErrorSameName() {
		
		as(ADMIN);
		createChallenge("Challenge Same Name", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "10", "12", "20");
		createChallenge("Challenge Same Name", "Challenge Desc", "2030/02/01", "2030/02/05", "Reward", "100", "10", "10");
		
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
		
		as("admin1");
		createChallenge("Challenge 1", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		createChallenge("Challenge 2", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		createChallenge("Challenge 3", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		createChallenge("Challenge 4", "Challenge Desc", "2030/03/01", "2030/03/05", "Reward", "100", "12", "20");
		
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
		
		as(ADMIN);
		createChallenge("Challenge Name", "Challenge Desc", "2010/01/01", "2010/01/05", "Reward", "10", "12", "20");
		
		// Check there is the error of past date in the form
		try {
			assertEquals("Starting date must be posterior to the actual date",
					driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	
	
	private void as(String username) {
		
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin1999");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	private void createChallenge(String name, String description, String initialDate, String endDate, String reward, String points, String reps, String weight) {
		
		// Click in create Challenge As Admin:
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li/a/span[2]")).click();
		driver.findElement(By.linkText("Create Challenge")).click();
		
		// Put in the data:
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys(description);
		driver.findElement(By.id("initialDate")).clear();
		driver.findElement(By.id("initialDate")).sendKeys(initialDate);
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.id("endDate")).sendKeys(endDate);
		driver.findElement(By.id("reward")).clear();
		driver.findElement(By.id("reward")).sendKeys(reward);
		driver.findElement(By.id("points")).clear();
		driver.findElement(By.id("points")).sendKeys(points);
		driver.findElement(By.id("reps")).clear();
		driver.findElement(By.id("reps")).sendKeys(reps);
		driver.findElement(By.id("weight")).clear();
		driver.findElement(By.id("weight")).sendKeys(weight);
		
		// Submit the data
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
}
