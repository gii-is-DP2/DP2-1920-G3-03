package org.springframework.samples.yogogym.ui.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsClientsUI {

	private int port;
	private WebDriver driver;

	public UtilsClientsUI(int port, WebDriver driver) {

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

		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[5]/a/span[2]")).click();
		driver.findElement(By.name("monthAndYear")).click();
		driver.findElement(By.name("monthAndYear")).clear();
		driver.findElement(By.name("monthAndYear")).sendKeys(monthAndYear);
		driver.findElement(By.xpath("//input[@value='Enviar']")).click();
	}

}
