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
public class ListTrainingsUITest {
	
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
  public void testListTrainingsUI() throws Exception {
    as("trainer1");
    accessListTrainings();
    listTrainingsRequiredInfoShown();
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
  
  private void accessListTrainings() {
	  driver.findElement(By.linkText("Trainer")).click();
	  driver.findElement(By.linkText("Training Management")).click();
  }
  
  private void listTrainingsRequiredInfoShown() {
	  try {
	      assertEquals("All Trainings", driver.findElement(By.xpath("//h2")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Client Martin Antonio Lera ( marantle@yogogym.com )", driver.findElement(By.xpath("//h3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento1", driver.findElement(By.linkText("Entrenamiento1")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento1 (COMPLETED)", driver.findElement(By.xpath("//div/div/div/ul/li")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento2", driver.findElement(By.linkText("Entrenamiento2")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento2 (COMPLETED)", driver.findElement(By.xpath("//div/div/div/ul/li[2]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Test", driver.findElement(By.linkText("Test")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Test (ON GOING)", driver.findElement(By.xpath("//div/ul/li[3]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Client Federico Javier Saco ( fejasa@yogogym.com )", driver.findElement(By.xpath("//div[2]/h3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento3", driver.findElement(By.linkText("Entrenamiento3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento3 (COMPLETED)", driver.findElement(By.xpath("//div/div/div[2]/ul/li")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Client Carmelina Esteso Rodríguez ( caresro@yogogym.com )", driver.findElement(By.xpath("//div[3]/h3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento2", driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento2')])[2]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento2 (COMPLETED)", driver.findElement(By.xpath("//div[3]/ul/li")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Client Sofia Victoria Obeso ( soviob@yogogym.com )", driver.findElement(By.xpath("//div[4]/h3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento3", driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento3')])[2]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento3 (COMPLETED)", driver.findElement(By.xpath("//div[4]/ul/li")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertEquals("Entrenamiento1 (ON GOING)", driver.findElement(By.xpath("//div[4]/ul/li[2]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
  }
}
