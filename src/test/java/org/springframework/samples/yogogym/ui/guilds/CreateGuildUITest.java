package org.springframework.samples.yogogym.ui.guilds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.samples.yogogym.ui.guilds.UtilsGuildUI;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateGuildUITest {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	UtilsGuildUI utils;

	private static final String CLIENT_USERNAME = "client4";

	private static final String GUILD_DESC = "Creando Guild";

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		utils = new UtilsGuildUI(port, driver);
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = utils.getVerificationError().toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void testListGuildsOtherClientUI() throws Exception {
		utils.as(CLIENT_USERNAME);

		driver.get("http://localhost:" + port + "/client/client2/guilds");

		utils.exceptionView();
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void createGuildUI() throws Exception{
		utils.as(CLIENT_USERNAME);
		utils.createGuild("This is a good Guild", GUILD_DESC, "https://fotos00.lne.es/2019/09/19/690x278/-81-fotos-1-18204983.xml.jpg");
	}

	@Test
	public void testCreateGuildEmptyUI() throws Exception {
		utils.as(CLIENT_USERNAME);

		utils.createGuild(null, null, null);

		try {
			assertEquals("no puede estar vacío",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("no puede estar vacío",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
		try {
			assertEquals("no puede estar vacío",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[4]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}

	}

	@Test
	public void testSameCreatorUI() throws Exception {
		utils.as("client1");
		
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
		
		try {
			assertEquals("There is already a guild created by this creator",
					driver.findElement(By.xpath("//form[@id='guild']/div/div/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

	@Test
	public void testBadURLUI() throws Exception {
		utils.as(CLIENT_USERNAME);
		utils.createGuild("A new Guild", GUILD_DESC, "not an url");

		try {
			assertEquals("The link must start with https://",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testSameNameUI() throws Exception {
		utils.as(CLIENT_USERNAME);
		utils.createGuild("Calisthenics", GUILD_DESC, "https://an_url.png");
	
		try {
			assertEquals("There is already a guild with that name",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[3]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}

}
