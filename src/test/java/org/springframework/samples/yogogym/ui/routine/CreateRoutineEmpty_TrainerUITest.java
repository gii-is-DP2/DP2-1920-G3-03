package org.springframework.samples.yogogym.ui.routine;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateRoutineEmpty_TrainerUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCreateRoutineAllEmpty() throws Exception {
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("trainer1999");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("trainer1");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Trainer")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Add Routine')])[3]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		assertEquals("The name cannot be empty",
				driver.findElement(By.xpath("//form[@id='routine']/div/div/div/span[2]")).getText());
		assertEquals("The description cannot be empty",
				driver.findElement(By.xpath("//form[@id='routine']/div/div[2]/div/span[2]")).getText());
		assertEquals("The repetition per week cannot be null",
				driver.findElement(By.xpath("//form[@id='routine']/div/div[3]/div/span[2]")).getText());
		assertEquals("", driver.findElement(By.id("name")).getAttribute("value"));
		assertEquals("", driver.findElement(By.id("description")).getAttribute("value"));
		assertEquals("", driver.findElement(By.id("repsPerWeek")).getAttribute("value"));
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
