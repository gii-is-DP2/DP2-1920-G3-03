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
public class ParticipateChallengesClientUITest {

	private static final String CHALLENGE_1 = "Challenge1";
	private static final String CHALLENGE_3 = "Challenge3";
	private static final String CHALLENGE_4 = "Challenge4";
	private static final String CLIENT_1 = "client1";
	private static final String CLIENT_2 = "client2";
	private static final String CLIENT_PASSWORD = "client1999";
	private static final String ADMIN = "admin1";
	private static final String ADMIN_PASSWORD = "admin1999";
	private static final String COMPLETED = "COMPLETED";
	private static final String PARTICIPATING = "PARTICIPATING";
	private static final String FAILED = "FAILED";
	private static final String SUBMITTED = "SUBMITTED";
	
	
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
	public void clientListNewChallenges() {

		utils.init();
		utils.as(CLIENT_1, CLIENT_PASSWORD);
		utils.listNewChallenges();

		// There is the challenge 4 to inscribe
		try {
			assertEquals(CHALLENGE_4, driver.findElement(By.linkText(CHALLENGE_4)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void listInscribedChallenges() {

		utils.init();
		utils.as(CLIENT_1, CLIENT_PASSWORD);
		utils.listMyChallenges();

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
		
		utils.init();
		utils.as(CLIENT_2, CLIENT_PASSWORD);
		utils.listNewChallenges();
		utils.inscribeChallenge(CHALLENGE_4);
		utils.listMyChallenges();

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
	
		String url = "https://test.com";
		
		utils.init();
		utils.as(CLIENT_1, CLIENT_PASSWORD);
		utils.listMyChallenges();
		utils.submitUrlChallenge(CHALLENGE_3, url);

		// The status changes to submitted
		try {
			assertEquals(SUBMITTED,
					driver.findElement(By.xpath("//table[@id='inscriptionTable']/tbody/tr/td/b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		utils.logout(CLIENT_1);
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.showSubmittedChallenge(CHALLENGE_3);

		// The submitted challenge has the previus url
		try {
			assertEquals(url, driver.findElement(By.linkText(url)).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		utils.evaluateChallenge(COMPLETED);
		utils.logout(ADMIN);
		utils.as(CLIENT_1, CLIENT_PASSWORD);
		utils.listMyChallenges();

		// The status changes to completed
		try {
			assertEquals(COMPLETED,
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[3]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void submitChallengeBadUrl() {
	
		String unvalidUrl = "unvalidUrl";
		
		utils.init();
		utils.as(CLIENT_1, CLIENT_PASSWORD);
		utils.listMyChallenges();
		utils.submitUrlChallenge(CHALLENGE_3, unvalidUrl);

		// It was not submitted
		assertEquals(unvalidUrl, driver.findElement(By.id("url")).getAttribute("value"));
	}
	
}
