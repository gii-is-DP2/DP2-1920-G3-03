package org.springframework.samples.yogogym.ui.training;

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
public class ManageTrainingClientNotTrainedUITest {
	
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
  public void testCreateTrainingClientNotTrainedUI() throws Exception {
    as("trainer1");
    accessManageTrainingClientNotTrained();
    exceptionViewShown();
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
  
  private void as(String username) {
	  driver.get("http://localhost:" + port);
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(username);
	  driver.findElement(By.id("password")).clear();
	  String pass = username.replaceAll("\\d", "");
	  driver.findElement(By.id("password")).sendKeys(pass+"1999");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  try {
		  assertEquals(username, driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).getText());
	  } catch (Error e) {
		  verificationErrors.append(e.toString());
	  }
  }
  
  private void accessManageTrainingClientNotTrained() {
	  driver.get("http://localhost:" + port + "/trainer/trainer1/clients/3/trainings/4");
  }
  
  private void exceptionViewShown() {
	  try {
	      assertEquals("Something happened...", driver.findElement(By.xpath("//h2")).getText());
	  } catch (Error e) {
	      verificationErrors.append(e.toString());
	  }
  }
}
