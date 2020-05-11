package org.springframework.samples.yogogym.ui.login;

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
public class LoginUITest {

	@LocalServerPort
	private int port;
	
private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get("http://localhost:" + port);
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
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
