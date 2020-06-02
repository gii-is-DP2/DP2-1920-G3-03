package org.springframework.samples.yogogym.ui.guilds;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsGuildUI {
	
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors;

	public UtilsGuildUI(int port, WebDriver driver) {
		this.port = port;
		this.driver = driver;
		this.verificationErrors = new StringBuffer();
	}

	public StringBuffer getVerificationError()
	{
		return this.verificationErrors;
	}
	
	public void as(String username) {
		this.driver.get("http://localhost:" + this.port);
		this.driver.findElement(By.linkText("Login")).click();
		this.driver.findElement(By.id("username")).clear();
		this.driver.findElement(By.id("username")).sendKeys(username);
		this.driver.findElement(By.id("password")).clear();
		this.driver.findElement(By.id("password")).sendKeys("client1999");
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			assertEquals(username, driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[2]/li/a/strong")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void listGuilds() {
		
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.linkText("Guilds")).click();
	}
	
	public void showYourGuild() {
		
		driver.findElement(By.linkText("See your Guild")).click();

	}
	
public void createGuild(String name, String description, String logo) {	
		
		listGuilds();
		
		driver.findElement(By.linkText("Create a Guild")).click();
		
		if(name!=null) {
			this.driver.findElement(By.id("name")).clear();
			this.driver.findElement(By.id("name")).sendKeys(name);
		}
		if(description!=null) {
			this.driver.findElement(By.id("description")).clear();
			this.driver.findElement(By.id("description")).sendKeys(description);
		}
		
		if(logo!=null) {
			this.driver.findElement(By.id("logo")).clear();
			this.driver.findElement(By.id("logo")).sendKeys(logo);
		}
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

public void updateGuild(String name, String description, String logo) {	
	
	listGuilds();
	showYourGuild();
	
	driver.findElement(By.linkText("Edit")).click();
	
	if(name!=null) {
		this.driver.findElement(By.id("name")).clear();
		this.driver.findElement(By.id("name")).sendKeys(name);
	}
	if(description!=null) {
		this.driver.findElement(By.id("description")).clear();
		this.driver.findElement(By.id("description")).sendKeys(description);
	}
	
	if(logo!=null) {
		this.driver.findElement(By.id("logo")).clear();
		this.driver.findElement(By.id("logo")).sendKeys(logo);
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
}
