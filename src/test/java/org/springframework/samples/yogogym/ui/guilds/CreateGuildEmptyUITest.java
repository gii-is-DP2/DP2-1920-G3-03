package org.springframework.samples.yogogym.ui.guilds;

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
public class CreateGuildEmptyUITest {

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
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("client8");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("client1999");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Client")).click();
	    driver.findElement(By.linkText("Guilds")).click();
	    driver.findElement(By.linkText("Create a Guild")).click();
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
