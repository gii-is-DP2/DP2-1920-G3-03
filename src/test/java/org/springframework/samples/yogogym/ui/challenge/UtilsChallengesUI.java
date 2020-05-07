package org.springframework.samples.yogogym.ui.challenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsChallengesUI {
	
	private int port;
	private WebDriver driver;
	
	public UtilsChallengesUI(int port, WebDriver driver) {

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
	
	public void logout(String username) {

		driver.findElement(By.linkText(username)).click();
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public void createChallenge(String name, String description, String initialDate, String endDate, String reward,
			String points, String reps, String weight) {

		// Click in create Challenge As Admin:
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li/a/span[2]")).click();
		driver.findElement(By.linkText("Create Challenge")).click();

		// Put in the data:
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys(description);
		driver.findElement(By.id("initialDate")).clear();
		driver.findElement(By.id("initialDate")).sendKeys(initialDate);
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.id("endDate")).sendKeys(endDate);
		driver.findElement(By.id("reward")).clear();
		driver.findElement(By.id("reward")).sendKeys(reward);
		driver.findElement(By.id("points")).clear();
		driver.findElement(By.id("points")).sendKeys(points);
		driver.findElement(By.id("reps")).clear();
		driver.findElement(By.id("reps")).sendKeys(reps);
		driver.findElement(By.id("weight")).clear();
		driver.findElement(By.id("weight")).sendKeys(weight);

		// Submit the data
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public void updateChallenge(String challenge, String param, String value) {
		
		driver.findElement(By.linkText("Admin")).click();
	    driver.findElement(By.linkText("Challenges")).click();
	    driver.findElement(By.linkText(challenge)).click();
	    driver.findElement(By.linkText("Edit")).click();
	    driver.findElement(By.id(param)).click();
	    driver.findElement(By.id(param)).clear();
	    driver.findElement(By.id(param)).sendKeys(value);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public void deleteChallenge(String challenge) {
		
		driver.findElement(By.linkText("Admin")).click();
	    driver.findElement(By.linkText("Challenges")).click();
	    driver.findElement(By.linkText(challenge)).click();
	    driver.findElement(By.linkText("Delete")).click();
	}
	
	public void dashboardOfMonth(int month) {
		
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[4]/a/span[2]")).click();
		driver.findElement(By.id("month")).click();
		driver.findElement(By.xpath("//option[@value='" + month + "']")).click();
		driver.findElement(By.xpath("//input[@value='Change']")).click();
	}
	
	public void listNewChallenges() {

		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
	}
	
	public void listMyChallenges() {

		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
	}

	public void showSubmittedChallenge(String challenge) {

		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[2]/a/span[2]")).click();
		driver.findElement(By.linkText(challenge)).click();
	}
	
	public void inscribeChallenge(String challenge) {

		driver.findElement(By.linkText(challenge)).click();
		driver.findElement(By.linkText("Inscribe me!")).click();
	}

	public void submitUrlChallenge(String challenge, String url) {

		driver.findElement(By.linkText(challenge)).click();
		driver.findElement(By.id("url")).click();
		driver.findElement(By.id("url")).clear();
		driver.findElement(By.id("url")).sendKeys(url);
		driver.findElement(By.xpath("//input[@value='Submit!']")).click();
	}

	public void evaluateChallenge(String status) {

		driver.findElement(By.id("status")).click();
	    driver.findElement(By.xpath("//option[@value='" + status + "']")).click();
	    driver.findElement(By.id("status")).click();
		driver.findElement(By.xpath("//input[@value='Evaluate!']")).click();
	}
}
