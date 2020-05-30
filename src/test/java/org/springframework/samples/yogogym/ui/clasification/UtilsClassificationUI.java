package org.springframework.samples.yogogym.ui.clasification;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsClassificationUI {
	
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors;
	
	public UtilsClassificationUI(int port, WebDriver driver) {

		this.port = port;
		this.driver = driver;
		this.verificationErrors = new StringBuffer();
	}
		
	public void init() {
		driver.get("http://localhost:" + port);
	}
	
	public StringBuffer getVerificationError()
	{
		return this.verificationErrors;
	}
	
	public void as(String username, String password) {

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			assertEquals(username, driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void showClassification() {
		driver.findElement(By.linkText("Client")).click();
	    driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[4]/a/span[2]")).click();
	}
	
	public void exceptionViewShown() {
		try {
			assertEquals("Something happened...", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
}
