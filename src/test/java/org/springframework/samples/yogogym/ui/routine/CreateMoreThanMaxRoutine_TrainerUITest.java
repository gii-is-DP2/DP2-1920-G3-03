package org.springframework.samples.yogogym.ui.routine;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateMoreThanMaxRoutine_TrainerUITest {
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
	  public void testCreateMoreThan10Routines() throws Exception {
	    driver.get("http://localhost:8080/trainer/trainer1/clients/1/trainings/9");
	    driver.findElement(By.linkText("Add Routine")).click();
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("Routine 10");
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Desc");
	    driver.findElement(By.id("description")).sendKeys(Keys.DOWN);
	    driver.findElement(By.id("description")).sendKeys(Keys.DOWN);
	    driver.findElement(By.id("description")).sendKeys(Keys.DOWN);
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Routine 10 description");
	    driver.findElement(By.id("repsPerWeek")).click();
	    driver.findElement(By.id("repsPerWeek")).clear();
	    driver.findElement(By.id("repsPerWeek")).sendKeys("10");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Add Routine")).click();
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("Bad Routine");
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Bad Routine");
	    driver.findElement(By.id("repsPerWeek")).clear();
	    driver.findElement(By.id("repsPerWeek")).sendKeys("10");
	    driver.findElement(By.xpath("//body/div/div")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    assertEquals("Error: You cannot create more than 10 trainings for Antonio Lera, Martin", driver.findElement(By.xpath("//body/div/div/div")).getText());
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
