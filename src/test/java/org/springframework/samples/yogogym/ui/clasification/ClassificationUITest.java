package org.springframework.samples.yogogym.ui.clasification;

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
public class ClassificationUITest {

	private static final String CLIENT1 = "client1";
	private static final String CLIENT3 = "client3";
	private static final String CLIENT_PASSWORD = "client1999";

	@LocalServerPort
	private int port;
	private WebDriver driver;
	UtilsClassificationUI utils;

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsClassificationUI(port, driver);
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
	public void testClassificationWithChallenges() throws Exception {
		utils.init();
		utils.as(CLIENT3, CLIENT_PASSWORD);
		utils.showClassification();
		driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr/td")).click();
		try {
			assertEquals("Challenge1",
					driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr/td")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr/td[3]")).click();
		try {
			assertEquals("10", driver.findElement(By.xpath("//table[@id='challengesTable']/tbody/tr/td[3]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

	@Test
	public void testClassificationWithOutChallenges() throws Exception {
		utils.init();
		utils.as(CLIENT1, CLIENT_PASSWORD);
		utils.showClassification();
		driver.findElement(By.id("canvasClasificationAll")).click();
	}

	@Test
	public void testClassificationOtherUser() throws Exception {
		utils.init();
		utils.as(CLIENT3, CLIENT_PASSWORD);
		driver.get("http://localhost:" + port + "/client/client1/clasification");
		utils.exceptionViewShown();
	}

}
