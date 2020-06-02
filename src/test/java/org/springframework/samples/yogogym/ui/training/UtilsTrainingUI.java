package org.springframework.samples.yogogym.ui.training;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsTrainingUI {
	
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors;

	private SimpleDateFormat formatterInput = new SimpleDateFormat("yyyy/MM/dd");
	
	public UtilsTrainingUI(int port, WebDriver driver) {
		this.port = port;
		this.driver = driver;
		this.verificationErrors = new StringBuffer();
	}

	public StringBuffer getVerificationError()
	{
		return this.verificationErrors;
	}
	
	public void as(String username, String password) {
		this.driver.get("http://localhost:" + this.port);
		this.driver.findElement(By.linkText("Login")).click();
		this.driver.findElement(By.id("username")).clear();
		this.driver.findElement(By.id("username")).sendKeys(username);
		this.driver.findElement(By.id("password")).clear();
		this.driver.findElement(By.id("password")).sendKeys(password);
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			assertEquals(username, driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void accessListTrainings() {
		this.driver.findElement(By.linkText("Trainer")).click();
		this.driver.findElement(By.linkText("Training Management")).click();
	}

	public void createTraining(String newTrainingName, Calendar calInit, Calendar calEnd, Boolean trainingForClient3) {	
		
		accessListTrainings();
		
		if(Boolean.TRUE.equals(trainingForClient3)) {
			this.driver.findElement(By.xpath("(//a[contains(text(),'Add Training')])[3]")).click();
		}
		else {
			this.driver.findElement(By.linkText("Add Training")).click();
		}
		
		if(newTrainingName!=null) {
			this.driver.findElement(By.id("name")).clear();
			this.driver.findElement(By.id("name")).sendKeys(newTrainingName);
		}
		if(calInit!=null) {
			this.driver.findElement(By.id("initialDate")).clear();
			this.driver.findElement(By.id("initialDate")).sendKeys(formatterInput.format(calInit.getTime()));
		}
		if(calEnd!=null) {
			this.driver.findElement(By.id("endDate")).clear();
			this.driver.findElement(By.id("endDate")).sendKeys(formatterInput.format(calEnd.getTime()));
		}
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public void updateTraining(String newTrainingName, Calendar calEnd, Boolean training1OnGoing) {
		this.updateTraining(newTrainingName, formatterInput.format(calEnd.getTime()), training1OnGoing);
	}
	
	public void updateTraining(String newTrainingName, String endFormatted, Boolean training1OnGoing) {

		accessListTrainings();

		if (Boolean.TRUE.equals(training1OnGoing)) {
			this.driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento1')])[3]")).click();
		} else {
			this.driver.findElement(By.xpath("(//a[contains(text(),'Entrenamiento2')])[4]")).click();
		}
		this.driver.findElement(By.linkText("Edit Training")).click();

		if (newTrainingName != null) {
			this.driver.findElement(By.id("name")).clear();
			this.driver.findElement(By.id("name")).sendKeys(newTrainingName);
		}
		
		if (endFormatted != null) {
			this.driver.findElement(By.id("endDate")).clear();
			this.driver.findElement(By.id("endDate")).sendKeys(endFormatted);
		}
		
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public void exceptionView() {
		try {
			assertEquals("Something happened...", driver.findElement(By.xpath("//h2")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void copyTraining(String trainingName) {
		driver.findElement(By.linkText("Copy Training")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText(trainingName)).click();
	}
	
}
