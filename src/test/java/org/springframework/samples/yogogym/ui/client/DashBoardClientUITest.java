package org.springframework.samples.yogogym.ui.client;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashBoardClientUITest {

	private static final String CLIENT = "client1";
	private static final String CLIENT_PASSWORD = "client1999";

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	UtilsClientsUI utils;

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsClientsUI(port, driver);
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
	public void testDashboardCompleted() throws Exception {
		utils.init();
		utils.as(CLIENT, CLIENT_PASSWORD);
		utils.dashboardOfMonthAndYear("2020-01");
		driver.findElement(By.xpath("//td")).click();
		try {
			assertEquals("1100", driver.findElement(By.xpath("//b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//body/div")).click();
		try {
			assertEquals("Month: 1 - 2020", driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("canvasBodyParts")).click();
		driver.findElement(By.id("canvasRepititionType")).click();
	}

	@Test
	public void testDashboardEmpty() throws Exception {
		utils.init();
		utils.as(CLIENT, CLIENT_PASSWORD);
		utils.dashboardOfMonthAndYear("2020-05");
		try {
			 assertEquals("No data for this month", driver.findElement(By.xpath("//b")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void testDashboardOtherUser() throws Exception {
		utils.init();
		utils.as(CLIENT, CLIENT_PASSWORD);
		driver.get("http://localhost:" + port + "/client/client3/dashboard");
		try {
			assertEquals("Something happened...", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
