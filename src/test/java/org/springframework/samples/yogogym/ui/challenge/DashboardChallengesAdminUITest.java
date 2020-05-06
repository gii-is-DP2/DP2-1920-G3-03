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
	public void DashboardWithChallenges() {
		
		as(ADMIN);
		dashboardOfMonth(1);
		
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
		
		as(ADMIN);
		dashboardOfMonth(10);
		
		// Check there is a message telling that there are not completed challenges
		try {
			assertEquals("No challenge is completed", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void DashboardWithoutChallenges() {
		
		as(ADMIN);
		dashboardOfMonth(2);
		
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

	
	

	private void as(String username) {
		
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin1999");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	private void dashboardOfMonth(int month) {
		
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[4]/a/span[2]")).click();
		driver.findElement(By.id("month")).click();
		driver.findElement(By.xpath("//option[@value='" + month + "']")).click();
		driver.findElement(By.xpath("//input[@value='Change']")).click();
	}

}
