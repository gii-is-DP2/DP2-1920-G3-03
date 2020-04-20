package org.springframework.samples.yogogym.ui.client;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DashBoardClientUITest {
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
  public void testDashBoardClientUI() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("client1999");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("client1");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Client")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[5]/a/span[2]")).click();
    driver.findElement(By.id("canvasBodyPartsMonth")).click();
    driver.findElement(By.id("canvasBodyPartsMonth")).click();
    driver.findElement(By.id("canvasRepititionTypeMonth")).click();
    driver.findElement(By.id("canvasRepititionTypeMonth")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=canvasRepititionTypeMonth | ]]
    driver.findElement(By.id("canvasBodyPartsAll")).click();
    driver.findElement(By.id("canvasBodyPartsAll")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=canvasBodyPartsAll | ]]
    driver.findElement(By.id("canvasRepititionTypeAll")).click();
    driver.findElement(By.id("canvasRepititionTypeAll")).click();
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

