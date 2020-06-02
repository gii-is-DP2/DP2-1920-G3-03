package org.springframework.samples.yogogym.ui.equipment;

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
public class DashBoardEquipmentAdminUITest {

	private static final String ADMIN = "admin1";
	private static final String ADMIN_PASSWORD = "admin1999";

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	UtilsEquipmentsUI utils;

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsEquipmentsUI(port, driver);
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
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.dashboardOfMonthAndYear("2020-01");

		try {
			assertEquals("Month: 1 - 2020", driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("canvas")).click();
		driver.findElement(By.id("canvas")).click();
		driver.findElement(By.id("canvas")).click();
	}
	
	@Test
	public void testDashboardUncompleted() throws Exception {
		utils.init();
		utils.as(ADMIN, ADMIN_PASSWORD);
		utils.dashboardOfMonthAndYear("2020-05");

		try {
			assertEquals("Month: 5 - 2020", driver.findElement(By.xpath("//h3")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
