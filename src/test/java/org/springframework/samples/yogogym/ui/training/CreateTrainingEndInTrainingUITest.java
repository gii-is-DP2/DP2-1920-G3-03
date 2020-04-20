package org.springframework.samples.yogogym.ui.training;

import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateTrainingEndInTrainingUITest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private Calendar cal = Calendar.getInstance();
  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateTrainingInitInTrainingUI() throws Exception {
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
    driver.findElement(By.linkText("Add Training")).click();
    try {
      assertEquals("New Training for Martin Antonio Lera", driver.findElement(By.xpath("//h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Entrenamiento viejo");
    driver.findElement(By.id("initialDate")).click();
    cal.add(Calendar.DAY_OF_MONTH, 1);
    String initString = formatter.format(cal.getTime());
    int init = cal.get(Calendar.DAY_OF_MONTH);
    driver.findElement(By.linkText(String.valueOf(init))).click();
    driver.findElement(By.id("endDate")).click();
    cal.add(Calendar.DAY_OF_MONTH, 6);
    String endString = formatter.format(cal.getTime());
    int end = cal.get(Calendar.DAY_OF_MONTH);
    driver.findElement(By.linkText(String.valueOf(end))).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Trainer")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
    driver.findElement(By.linkText("Add Training")).click();
    try {
      assertEquals("New Training for Martin Antonio Lera", driver.findElement(By.xpath("//h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Entrenamiento nuevo");
    driver.findElement(By.id("initialDate")).click();
    cal.add(Calendar.DAY_OF_MONTH, -7);
    int init2 = cal.get(Calendar.DAY_OF_MONTH);
    driver.findElement(By.linkText(String.valueOf(init2))).click();
    driver.findElement(By.id("endDate")).click();
    driver.findElement(By.linkText(String.valueOf(end))).click();;
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("The training cannot end in a period with other training (The other training is from " + initString + " to " + endString + ")", driver.findElement(By.xpath("//form[@id='trainingForm']/div/div[3]/div/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Trainer")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
    driver.findElement(By.linkText("Entrenamiento viejo")).click();
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
