package org.springframework.samples.yogogym.ui.guilds;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateGuildSameCreatorUITest {

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
	public void testGuildSameCreator() throws Exception {

		as("client1");
		createSameName();
		showErrors();

	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void as(String username) {
		driver.get("http://localhost:" + port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("client1999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.linkText("Guilds")).click();

	}

	private void createSameName() {
		driver.get("http://localhost:" + port + "/client/client1/guilds/create");
		driver.findElement(By.id("logo")).click();
		driver.findElement(By.id("logo")).clear();
		driver.findElement(By.id("logo")).sendKeys("https://trucoslondres.com/wp-content/uploads/2017/04/sports.jpg");
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Sports Club");
		driver.findElement(By.id("description")).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("Give me an error with the creator");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void showErrors() {
		try {
			assertEquals("There is already a guild created by this creator",
					driver.findElement(By.xpath("//form[@id='guild']/div/div/div/span[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
