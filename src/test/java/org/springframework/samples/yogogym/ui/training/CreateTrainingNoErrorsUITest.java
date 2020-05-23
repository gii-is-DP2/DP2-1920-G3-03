package org.springframework.samples.yogogym.ui.training;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateTrainingNoErrorsUITest {
	
  @LocalServerPort
  private int port;
	  
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  private Calendar calInit = Calendar.getInstance();
  private Calendar calEnd = Calendar.getInstance();
  private SimpleDateFormat formatterDetails = new SimpleDateFormat("yyyy-MM-dd");
  private SimpleDateFormat formatterInput = new SimpleDateFormat("yyyy/MM/dd");

  @BeforeEach
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  @Test
  public void testCreateTrainingNoErrorsUI() throws Exception {
    as("trainer1");
    createTrainingWithoutErrors();
    try {
    	assertEquals("Name: Entrenamiento nuevo", driver.findElement(By.xpath("//h3")).getText());
	} catch (Error e) {
	    verificationErrors.append(e.toString());
	}
	try {
	    assertEquals("Starts: " + formatterDetails.format(calInit.getTime()) + " 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p")).getText());
	} catch (Error e) {
	    verificationErrors.append(e.toString());
	}
	try {
	    assertEquals("Ends: " + formatterDetails.format(calEnd.getTime()) + " 00:00:00.0", driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
	} catch (Error e) {
	    verificationErrors.append(e.toString());
	}
	try {
	    assertEquals("Editing Permission: TRAINER", driver.findElement(By.xpath("//p[3]")).getText());
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
	};
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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
  
  private void createTrainingWithoutErrors() {
	  driver.findElement(By.linkText("Trainer")).click();
	  driver.findElement(By.linkText("Training Management")).click();
	  driver.findElement(By.linkText("Add Training")).click();
	  driver.findElement(By.id("name")).clear();
	  driver.findElement(By.id("name")).sendKeys("Entrenamiento nuevo");
	  driver.findElement(By.id("initialDate")).clear();
	  driver.findElement(By.id("initialDate")).sendKeys(formatterInput.format(calInit.getTime()));
	  calEnd.add(Calendar.DAY_OF_MONTH, 7);
	  driver.findElement(By.id("endDate")).clear();
	  driver.findElement(By.id("endDate")).sendKeys(formatterInput.format(calEnd.getTime()));
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
 
}
