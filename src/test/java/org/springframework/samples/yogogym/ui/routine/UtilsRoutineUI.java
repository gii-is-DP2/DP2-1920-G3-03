package org.springframework.samples.yogogym.ui.routine;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsRoutineUI {
	
	private int port;
	private WebDriver driver;
	private StringBuffer verificationErrors;
	
	private Boolean logged = false;
	
	public UtilsRoutineUI(int port, WebDriver driver) {

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
		this.driver.findElement(By.id("password")).clear();
		this.driver.findElement(By.id("password")).sendKeys(password);
		this.driver.findElement(By.id("username")).clear();
		this.driver.findElement(By.id("username")).sendKeys(username);
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		this.logged = true;
	}

	public void createRoutine(String username, String password, String newRoutineName, String newRoutineDescription, String newRoutineRepsPerWeek, int mode) {
		
		if(!this.logged)
			as(username, password);
		
		newRoutineName = (newRoutineName.equals("{empty}"))?"":newRoutineName;
		newRoutineDescription = (newRoutineDescription.equals("{empty}"))?"":newRoutineDescription;
		newRoutineRepsPerWeek = (newRoutineRepsPerWeek.equals("{empty}"))?"":newRoutineRepsPerWeek;		
		
		this.driver.findElement(By.linkText("Trainer")).click();
		this.driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		this.driver.findElement(By.xpath("(//a[contains(text(),'Add Routine')])[3]")).click();
		this.driver.findElement(By.id("name")).click();
		this.driver.findElement(By.id("name")).clear();
		this.driver.findElement(By.id("name")).sendKeys(newRoutineName);
		this.driver.findElement(By.id("description")).click();
		this.driver.findElement(By.id("description")).clear();
		this.driver.findElement(By.id("description")).sendKeys(newRoutineDescription);
		this.driver.findElement(By.id("repsPerWeek")).click();
		this.driver.findElement(By.id("repsPerWeek")).clear();
		this.driver.findElement(By.id("repsPerWeek")).sendKeys(String.valueOf(newRoutineRepsPerWeek));
		
		String name = "name:" + newRoutineName +" ";
		String description = "description:" + newRoutineDescription + " ";
		String repsPerWeek = "repsPerWeek:" + newRoutineRepsPerWeek + " ";
		
		if(mode == 0)
		{
			this.driver.findElement(By.xpath("//button[@type='submit']")).click();	
			checkParams(name,description,repsPerWeek);			
		}
		else if(mode == 1)
		{
			checkParams(name,description,repsPerWeek);
			this.driver.findElement(By.xpath("//button[@type='submit']")).click();
			checkCorrectCreation(name,description,repsPerWeek);
		}
		else if(mode == 2)
		{
			this.driver.findElement(By.xpath("//button[@type='submit']")).click();
			checkMaxRoutineSurpases();
		}
	}
	
	public void updateRoutine(String routineToEdit, String username, String password, String string)
	{
		if(!this.logged)
			as(username,password);
			
		String trozos[] = string.split(":");
		String param = trozos[0].trim();
		String value = trozos[1].trim();
		
		driver.findElement(By.linkText("Trainer")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		driver.findElement(By.linkText(routineToEdit)).click();
		driver.findElement(By.linkText("Edit Routine")).click();
		
		driver.findElement(By.id(param)).click();
		driver.findElement(By.id(param)).clear();
		driver.findElement(By.id(param)).sendKeys(value);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		if(param.equals("name"))
			assertEquals("Routine Name: " + value, driver.findElement(By.xpath("//body/div/div/p")).getText());
		
		if(param.equals("description"))
			assertEquals("Description: " + value, driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
		
		if(param.equals("repsPerWeek"))
			assertEquals("Repetitions Per Week: " + value, driver.findElement(By.xpath("//body/div/div/p[3]")).getText());
		
	}
	
	public void deleteRoutine(String username, String password, String routineName)
	{
		if(!this.logged)
			as(username,password);
		
		driver.findElement(By.linkText("Trainer")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		driver.findElement(By.linkText(routineName)).click();
		driver.findElement(By.linkText("Delete Routine")).click();
		assertEquals("Success:\n'"+routineName+"' has been deleted succesfully", driver.findElement(By.xpath("//body/div/div/div")).getText());
	}
	
	public void checkParams(String...strings) 
	{
		String trozos[] = {};
		String parameter = "";
		String value = "";
		
		for(String s: strings) 
		{
			trozos = s.split(":");
			parameter = trozos[0];
			value = trozos[1].trim();
			
			assertEquals(value, driver.findElement(By.id(parameter)).getAttribute("value"));
			
			if(value.equals(""))
			{
				if(parameter.equals("name"))
					assertEquals("The name cannot be empty", driver.findElement(By.xpath("//form[@id='routine']/div/div/div/span[2]")).getText());	
				
				if(parameter.equals("description"))
					assertEquals("The description cannot be empty", driver.findElement(By.xpath("//form[@id='routine']/div/div[2]/div/span[2]")).getText());
				
				if(parameter.equals("repsPerWeek"))
					assertEquals("The repetition per week cannot be null", driver.findElement(By.xpath("//form[@id='routine']/div/div[3]/div/span[2]")).getText());
			}
			else
			{
				if(parameter.equals("name"))
					assertEquals(value, driver.findElement(By.id(parameter)).getAttribute("value"));
				
				if(parameter.equals("description"))	
					assertEquals(value, driver.findElement(By.id(parameter)).getAttribute("value"));
				
				if(parameter.equals("repsPerWeek"))
				{
					assertEquals(value, driver.findElement(By.id(parameter)).getAttribute("value"));
					
					if(Integer.valueOf(value) > 20)
						assertEquals("The repetition per week cannot be greater than 20", driver.findElement(By.xpath("//form[@id='routine']/div/div[3]/div/span[2]")).getText());
					else if(Integer.valueOf(value) < 0)
						assertEquals("The repetition per week must be positive", driver.findElement(By.xpath("//form[@id='routine']/div/div[3]/div/span[2]")).getText());
					else if(Integer.valueOf(value) == 0)
						assertEquals("The repetition per week cannot be greater than 20 or less than 1", driver.findElement(By.xpath("//form[@id='routine']/div/div[3]/div/span[2]")).getText());						
				}
			}
		}
	}
	
	public void checkCorrectCreation(String newRoutineName, String newRoutineDescription, String newRoutineRepsPerWeek)
	{
		newRoutineName = newRoutineName.split(":")[1].trim();
		newRoutineDescription = newRoutineDescription.split(":")[1].trim();
		newRoutineRepsPerWeek = newRoutineRepsPerWeek.split(":")[1].trim();
		
		this.driver.get("http://localhost:"+ port +"/trainer/trainer1/routines");
	    this.driver.findElement(By.linkText(newRoutineName)).click();
	    assertEquals("Routine Name: " + newRoutineName, driver.findElement(By.xpath("//body/div/div/p")).getText());
	    assertEquals("Description: " + newRoutineDescription, driver.findElement(By.xpath("//body/div/div/p[2]")).getText());
	    assertEquals("Repetitions Per Week: " + newRoutineRepsPerWeek, driver.findElement(By.xpath("//p[3]")).getText());
	    this.driver.get("http://localhost:"+ port);
	}

	public void checkMaxRoutineSurpases()
	{
		assertEquals("Error:\nYou cannot create more than 10 routines for Antonio Lera, Martin", driver.findElement(By.xpath("//body/div/div/div")).getText());
	}
}
