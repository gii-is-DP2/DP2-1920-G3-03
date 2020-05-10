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
public class DashboardChallengesAdminUITest {

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
	public void DashboardWithChallenges() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.dashboardOfMonth(1);
		
		// Check there is data of the User and Guild with more points
		try {
			assertEquals("Julio Enrique Guerrero", driver.findElement(By.xpath("//b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Gym for Dummies", driver.findElement(By.xpath("//h5[2]/b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void DashboardWithoutCompletedChallenges() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.dashboardOfMonth(10);
		
		// Check there is a message telling that there are not completed challenges
		try {
			assertEquals("No challenge is completed", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void DashboardWithoutChallenges() {
		
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.dashboardOfMonth(2);
		
		// Check there is a message telling that there are no challenges and the option to create one
		try {
			assertEquals("There are no challenges ending this month. Create one!",
					driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Create Challenge", driver.findElement(By.linkText("Create Challenge")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
