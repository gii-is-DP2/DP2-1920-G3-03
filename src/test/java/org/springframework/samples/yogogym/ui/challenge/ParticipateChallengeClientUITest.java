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
public class ParticipateChallengeClientUITest {

	private static final String CHALLENGE_1 = "Challenge1";
	private static final String CHALLENGE_4 = "Challenge4";
	private static final String CHALLENGE_5 = "Challenge5";
	private static final String URL = "https://test.com";
	private static final String CLIENT_1 = "client1";
	private static final String CLIENT_2 = "client2";
	private static final String ADMIN = "admin1";
	private static final String COMPLETED = "COMPLETED";
	private static final String PARTICIPATING = "PARTICIPATING";
	private static final String FAILED = "FAILED";
	private static final String SUBMITTED = "SUBMITTED";
	private static final boolean INITIAL = true;
	private static final boolean NOT_INITIAL = false;
	
	
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
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
	@Test
	public void clientListNewChallenges() {

		as(CLIENT_1, INITIAL);
		listNewChallenges();

		// There is the challenge 4 to inscribe
		try {
			assertEquals(CHALLENGE_4, driver.findElement(By.linkText(CHALLENGE_4)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void listInscribedChallenges() {

		as(CLIENT_1, INITIAL);
		listMyChallenges();

		// There is the Challenge 1 in my challenges and its status is Failed
		try {
			assertEquals(CHALLENGE_1, driver.findElement(By.linkText(CHALLENGE_1)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(FAILED,
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void participateInChallenge() {
		
		as(CLIENT_2, INITIAL);
		listNewChallenges();
		inscribeChallenge(CHALLENGE_4);
		listMyChallenges();

		// There is the Challenge 4 in my challenges and its status is participating
		try {
			assertEquals(CHALLENGE_4, driver.findElement(By.linkText(CHALLENGE_4)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(PARTICIPATING,
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[2]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void completeChallenge() {
	
		as(CLIENT_1, INITIAL);
		listMyChallenges();
		submitUrlChallenge(CHALLENGE_5, URL);

		// The status changes to submitted
		try {
			assertEquals(SUBMITTED,
					driver.findElement(By.xpath("//table[@id='inscriptionTable']/tbody/tr/td/b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		logout(CLIENT_1);
		as(ADMIN, NOT_INITIAL);
		showSubmittedChallenge();

		// The submitted challenge has the previus url
		try {
			assertEquals(URL, driver.findElement(By.linkText(URL)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		evaluateChallenge();
		logout(ADMIN);
		as(CLIENT_1, NOT_INITIAL);
		listMyChallenges();

		// The status changes to completed
		try {
			assertEquals(COMPLETED,
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[4]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	

	private void listNewChallenges() {

		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
	}
	
	private void listMyChallenges() {

		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
	}

	private void showSubmittedChallenge() {

		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		driver.findElement(By.linkText("Challenge5")).click();
	}
	
	private void inscribeChallenge(String challenge) {

		driver.findElement(By.linkText(challenge)).click();
		driver.findElement(By.linkText("Inscribe me!")).click();
	}

	private void submitUrlChallenge(String challenge, String url) {

		driver.findElement(By.linkText(challenge)).click();
		driver.findElement(By.id("url")).click();
		driver.findElement(By.id("url")).clear();
		driver.findElement(By.id("url")).sendKeys(url);
		driver.findElement(By.xpath("//input[@value='Submit!']")).click();
	}

	private void evaluateChallenge() {

		driver.findElement(By.xpath("//input[@value='Evaluate!']")).click();
	}

	private void as(String username, boolean initial) {

		if (initial)
			driver.get("http://localhost:" + port);

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();

		if (username.contains("client")) {
			driver.findElement(By.id("password")).sendKeys("client1999");
		} else {
			driver.findElement(By.id("password")).sendKeys("admin1999");
		}

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void logout(String username) {

		driver.findElement(By.linkText(username)).click();
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
}
