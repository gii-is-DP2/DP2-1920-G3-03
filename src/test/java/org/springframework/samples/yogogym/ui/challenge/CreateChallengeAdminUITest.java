package org.springframework.samples.yogogym.ui.challenge;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateChallengeAdminUITest {
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
  public void testCreateChallengeAdminUI() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin1999");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin1");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li/a/span[2]")).click();
    driver.findElement(By.linkText("Create Challenge")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("ChallengeTest");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Test1");
    driver.findElement(By.id("initialDate")).clear();
    driver.findElement(By.id("initialDate")).sendKeys("2030/01/01");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2030/01/05");
    driver.findElement(By.id("reward")).clear();
    driver.findElement(By.id("reward")).sendKeys("Test");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("10");
    driver.findElement(By.id("reps")).clear();
    driver.findElement(By.id("reps")).sendKeys("10");
    driver.findElement(By.id("weight")).clear();
    driver.findElement(By.id("weight")).sendKeys("10");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("ChallengeTest", driver.findElement(By.linkText("ChallengeTest")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Create Challenge")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("ChallengeTest");
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Test2");
    driver.findElement(By.id("initialDate")).clear();
    driver.findElement(By.id("initialDate")).sendKeys("2030/01/01");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2030/01/05");
    driver.findElement(By.id("reward")).clear();
    driver.findElement(By.id("reward")).sendKeys("Test");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("10");
    driver.findElement(By.id("reps")).clear();
    driver.findElement(By.id("reps")).sendKeys("10");
    driver.findElement(By.id("weight")).clear();
    driver.findElement(By.id("weight")).sendKeys("10");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("There is already a challenge with that name the same week", driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div/div/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.linkText("Challenges")).click();
    driver.findElement(By.linkText("Create Challenge")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("ChallengeTest1");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Test");
    driver.findElement(By.id("initialDate")).clear();
    driver.findElement(By.id("initialDate")).sendKeys("2020/10/10");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2020/10/15");
    driver.findElement(By.id("reward")).clear();
    driver.findElement(By.id("reward")).sendKeys("10");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("10");
    driver.findElement(By.id("reps")).clear();
    driver.findElement(By.id("reps")).sendKeys("10");
    driver.findElement(By.id("weight")).clear();
    driver.findElement(By.id("weight")).sendKeys("10");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("ChallengeTest1", driver.findElement(By.linkText("ChallengeTest1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Create Challenge")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("ChallengeTest2");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Test");
    driver.findElement(By.id("initialDate")).clear();
    driver.findElement(By.id("initialDate")).sendKeys("2020/10/10");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2020/10/15");
    driver.findElement(By.id("reward")).clear();
    driver.findElement(By.id("reward")).sendKeys("Test");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("10");
    driver.findElement(By.id("reps")).clear();
    driver.findElement(By.id("reps")).sendKeys("10");
    driver.findElement(By.id("weight")).clear();
    driver.findElement(By.id("weight")).sendKeys("10");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("There must not be more than 3 challenges per week", driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li/a/span[2]")).click();
    driver.findElement(By.linkText("Create Challenge")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("ChallengeTest");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Test");
    driver.findElement(By.id("initialDate")).clear();
    driver.findElement(By.id("initialDate")).sendKeys("2010/01/01");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2010");
    driver.findElement(By.id("endDate")).clear();
    driver.findElement(By.id("endDate")).sendKeys("2010/01/05");
    driver.findElement(By.id("reward")).clear();
    driver.findElement(By.id("reward")).sendKeys("Test");
    driver.findElement(By.id("points")).clear();
    driver.findElement(By.id("points")).sendKeys("10");
    driver.findElement(By.id("reps")).clear();
    driver.findElement(By.id("reps")).sendKeys("10");
    driver.findElement(By.id("weight")).clear();
    driver.findElement(By.id("weight")).sendKeys("10");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    try {
      assertEquals("Starting date must be posterior to the actual date", driver.findElement(By.xpath("//form[@id='challenge']/div/div/div/div[3]/div/span[2]")).getText());
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

