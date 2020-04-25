package org.springframework.samples.yogogym.ui.training;

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
public class CopyTrainingSuccessUITest {

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
	public void testCopyTrainingUI() throws Exception {
		as("trainer1");
		prepareNewTraining();
		copyTraining();
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void as(String string) {
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("trainer1999");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("trainer1");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void prepareNewTraining() {
		driver.findElement(By.linkText("Trainer")).click();
	    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
	    driver.findElement(By.linkText("Add Training")).click();
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("Entrenamiento3");
	    driver.findElement(By.id("initialDate")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.linkText("1")).click();
	    driver.findElement(By.id("endDate")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.linkText("7")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void copyTraining() {
		//driver.findElement(By.linkText("Entrenamiento3")).click();
		driver.findElement(By.linkText("Copy Training")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Entrenamiento3")).click();
	}

}
