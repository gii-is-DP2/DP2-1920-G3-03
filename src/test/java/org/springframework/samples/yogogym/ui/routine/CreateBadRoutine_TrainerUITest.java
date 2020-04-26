package org.springframework.samples.yogogym.ui.routine;

import static org.junit.Assert.assertEquals;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateBadRoutine_TrainerUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateEmptyRoutine() throws Exception {
		
		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "{empty}";
		String newRoutineDescription = "{empty}";
		String newRoutineRepsPerWeek = "{empty}";
		
		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateNameEmptyRoutine() throws Exception {
		
		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "{empty}";
		String newRoutineDescription = "Routine Description New";
		String newRoutineRepsPerWeek = "30";
		
		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateDescriptionEmptyRoutine() throws Exception {
		
		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "Routine";
		String newRoutineDescription = "{empty}";
		String newRoutineRepsPerWeek = "30";
		
		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateRepsPerWeekEmptyRoutine() throws Exception {
		
		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "Routine";
		String newRoutineDescription = "Description";
		String newRoutineRepsPerWeek = "{empty}";
		
		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateRepsPerWeekRoutineGreaterThanMax() throws Exception {

		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "Routine New";
		String newRoutineDescription = "Routine Description New";
		String newRoutineRepsPerWeek = "30";

		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateRepsPerWeekRoutineNegative() throws Exception {

		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "Routine New";
		String newRoutineDescription = "Routine Description New";
		String newRoutineRepsPerWeek = "-5";

		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testCreateRepsPerWeekRoutineCero() throws Exception {

		String username = "trainer1";
		String password = "trainer1999";
		
		String newRoutineName = "Routine New";
		String newRoutineDescription = "Routine Description New";
		String newRoutineRepsPerWeek = "0";

		createRoutine(username,password,newRoutineName, newRoutineDescription, newRoutineRepsPerWeek);
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	// Derived Methods
	public void as(String username, String password, int port) {
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public void createRoutine(String username, String password, String newRoutineName, String newRoutineDescription, String newRoutineRepsPerWeek) {
		
		as(username, password, port);
		
		newRoutineName = (newRoutineName.equals("{empty}"))?"":newRoutineName;
		newRoutineDescription = (newRoutineDescription.equals("{empty}"))?"":newRoutineDescription;
		newRoutineRepsPerWeek = (newRoutineRepsPerWeek.equals("{empty}"))?"":newRoutineRepsPerWeek;		
		
		driver.findElement(By.linkText("Trainer")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[3]/a/span[2]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Add Routine')])[3]")).click();
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(newRoutineName);
		driver.findElement(By.id("description")).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys(newRoutineDescription);
		driver.findElement(By.id("repsPerWeek")).click();
		driver.findElement(By.id("repsPerWeek")).clear();
		driver.findElement(By.id("repsPerWeek")).sendKeys(String.valueOf(newRoutineRepsPerWeek));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String name = "name:" + newRoutineName +" ";
		String description = "description:" + newRoutineDescription + " ";
		String repsPerWeek = "repsPerWeek:" + newRoutineRepsPerWeek + " ";
		
		checkParams(name,description,repsPerWeek);
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
}
