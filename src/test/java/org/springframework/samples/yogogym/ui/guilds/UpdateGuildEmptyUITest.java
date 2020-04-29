package org.springframework.samples.yogogym.ui.guilds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateGuildEmptyUITest {

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
	  public void testUntitledTestCase() throws Exception {
	    driver.get("http://localhost:"+port);
	    driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("client2");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("client1999");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Client")).click();
	    driver.findElement(By.linkText("Guilds")).click();
	    driver.findElement(By.linkText("See your Guild")).click();
	    driver.findElement(By.linkText("Edit")).click();
	    driver.findElement(By.id("logo")).click();
	    driver.findElement(By.id("logo")).clear();
	    driver.findElement(By.id("logo")).sendKeys("");
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("");
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("");
	    driver.findElement(By.xpath("//body/div/div")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    assertEquals("no puede estar vacío", driver.findElement(By.xpath("//form[@id='guild']/div/div[2]/div/span[2]")).getText());
	    assertEquals("no puede estar vacío", driver.findElement(By.xpath("//form[@id='guild']/div/div[3]/div/span[2]")).getText());
	    assertEquals("no puede estar vacío", driver.findElement(By.xpath("//form[@id='guild']/div/div[4]/div/span[2]")).getText());
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
