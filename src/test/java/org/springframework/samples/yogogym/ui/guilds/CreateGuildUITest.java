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
public class CreateGuildUITest {

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
	public void testCreateGuild() throws Exception {

		as("client4");
		createGuild();

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

	}

	private void createGuild() {

		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[6]/a/span[2]")).click();
		driver.findElement(By.linkText("Create a Guild")).click();
		driver.findElement(By.id("logo")).click();
		driver.findElement(By.id("logo")).clear();
		driver.findElement(By.id("logo"))
				.sendKeys("https://fotos00.lne.es/2019/09/19/690x278/-81-fotos-1-18204983.xml.jpg");
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Running4ever");
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("Runners");
		driver.findElement(By.id("description")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
