package org.springframework.samples.yogogym.ui.equipment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsEquipmentsUI {
	
	private int port;
	private WebDriver driver;
	
	public UtilsEquipmentsUI(int port, WebDriver driver) {

		this.port = port;
		this.driver = driver;
	}
		
	public void init() {
		driver.get("http://localhost:" + port);
	}
	
	public void as(String username, String password) {

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public void dashboardOfMonthAndYear(String monthAndYear) {
		
		driver.findElement(By.linkText("Admin")).click();
	    driver.findElement(By.linkText("Equipment Dashboard")).click();
	    driver.findElement(By.name("monthAndYear")).click();
	    driver.findElement(By.name("monthAndYear")).clear();
	    driver.findElement(By.name("monthAndYear")).sendKeys(monthAndYear);
	    driver.findElement(By.xpath("//input[@value='Enviar']")).click();
	    driver.findElement(By.xpath("//body/div")).click();
	}
	
}
