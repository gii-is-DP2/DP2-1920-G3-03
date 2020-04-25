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

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testParticipateChallengeClientUI() throws Exception {
		driver.get("http://localhost:" + port);
		as("client1");
		inscribeChallengeFourSuccessful();
		submitChallengeFour();
		logout("client1");
		as("admin1");
		evaluateChallengeFour();
		logout("admin1");
		as("client1");
		statusOfMyChallenges();
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void as(String username) {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		if (username.equals("client1")) {
			driver.findElement(By.id("password")).sendKeys("client1999");
		} else {
			driver.findElement(By.id("password")).sendKeys("admin1999");
		}
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void inscribeChallengeFourSuccessful() {
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		try {
			assertEquals("Challenge4", driver.findElement(By.linkText("Challenge4")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Challenge4")).click();
		try {
			assertEquals("Inscribe me!", driver.findElement(By.linkText("Inscribe me!")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Inscribe me!")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		try {
			assertEquals("Challenge4", driver.findElement(By.linkText("Challenge4")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("PARTICIPATING",
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[5]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	private void submitChallengeFour() {
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		driver.findElement(By.linkText("Challenge4")).click();
		driver.findElement(By.id("url")).click();
		driver.findElement(By.id("url")).clear();
		driver.findElement(By.id("url")).sendKeys("https://test.com");
		driver.findElement(By.xpath("//input[@value='Submit!']")).click();
		try {
			assertEquals("https://test.com", driver.findElement(By.linkText("https://test.com")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	private void logout(String username) {
		if (username.equals("client1")) {
			driver.findElement(By.linkText(username)).click();
		}
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void evaluateChallengeFour() {
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		try {
			assertEquals("Challenge4", driver.findElement(By.linkText("Challenge4")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Challenge4")).click();
		driver.findElement(By.xpath("//input[@value='Evaluate!']")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).click();
	}

	private void statusOfMyChallenges() {
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		try {
			assertEquals("Challenge4", driver.findElement(By.linkText("Challenge4")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("COMPLETED",
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[5]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Challenge5", driver.findElement(By.linkText("Challenge5")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("PARTICIPATING",
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr[4]/td[6]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
