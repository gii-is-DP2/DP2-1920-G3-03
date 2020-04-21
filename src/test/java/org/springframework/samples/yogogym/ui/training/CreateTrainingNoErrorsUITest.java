package org.springframework.samples.yogogym.ui.training;

import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateTrainingNoErrorsUITest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private Calendar cal = Calendar.getInstance();

  @BeforeEach
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateTrainingNoErrorsUI() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("trainer1");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("trainer1999");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("trainer1", driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Trainer")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
    try {
      assertEquals("Client Martin Antonio Lera ( marantle@yogogym.com )", driver.findElement(By.xpath("//h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Martin Antonio Lera", driver.findElement(By.linkText("Martin Antonio Lera")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Martin Antonio Lera", driver.findElement(By.linkText("Martin Antonio Lera")).getText());
    driver.findElement(By.linkText("Add Training")).click();
    try {
      assertEquals("New Training for Martin Antonio Lera", driver.findElement(By.xpath("//h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Entrenamiento nuevo");
    driver.findElement(By.id("initialDate")).click();
    int init = cal.get(Calendar.DAY_OF_MONTH);
    driver.findElement(By.linkText(String.valueOf(init))).click();
    driver.findElement(By.id("endDate")).click();
    cal.add(Calendar.DAY_OF_MONTH, 7);
    int end = cal.get(Calendar.DAY_OF_MONTH);
    driver.findElement(By.linkText(String.valueOf(end))).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("Trainer: Training Details of Martin Antonio Lera", driver.findElement(By.xpath("//h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Name: Entrenamiento nuevo", driver.findElement(By.xpath("//h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Starts: 2020-04-20 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Ends: 2020-04-27 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Edit Training", driver.findElement(By.linkText("Edit Training")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Delete Training", driver.findElement(By.linkText("Delete Training")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Copy Training", driver.findElement(By.linkText("Copy Training")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Routines", driver.findElement(By.xpath("//h3[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Add Routine", driver.findElement(By.linkText("Add Routine")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Diet", driver.findElement(By.xpath("//h3[4]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Add Diet", driver.findElement(By.linkText("Add Diet")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Delete Training")).click();
    try {
      assertEquals("The training was deleted successfully", driver.findElement(By.xpath("//body/div/div/div/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
