package org.springframework.samples.yogogym.ui.guilds;

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
public class UpdateGuildUITest {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	UtilsGuildUI utils;
	
	private static final String CLIENT_USERNAME = "client1";

	private static final String GUILD_DESC = "Actualizando Guild";

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

	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void testUpdateGuildUI() throws Exception {

		utils.as(CLIENT_USERNAME);
		utils.updateGuild("AnotherName", GUILD_DESC, "https://loco.png");
		
	}
	
	@Test
	public void testUpdateGuildBadURLUI() throws Exception {
		utils.as(CLIENT_USERNAME);
		utils.updateGuild("Another Name", GUILD_DESC, "bad URL");
		
		try {
			assertEquals("The link must start with https://",
					driver.findElement(By.xpath("//form[@id='guild']/div/div[2]/div/span[2]")).getText());
		} catch (Error e) {
			utils.getVerificationError().append(e.toString());
		}
	}
	
	@Test
	public void testUpdatGuildEmptyUI () throws Exception {
		
		utils.as(CLIENT_USERNAME);
		utils.updateGuild("", "", "");
		
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
	public void updateGuildSameNameUI() throws Exception {
		 utils.as(CLIENT_USERNAME);
		 utils.updateGuild("Weightlifting", GUILD_DESC, "https://updating.jpg");
		 try {
				assertEquals("There is already a guild with that name",
						driver.findElement(By.xpath("//form[@id='guild']/div/div[3]/div/span[2]")).getText());
			} catch (Error e) {
				utils.getVerificationError().append(e.toString());
			}
	}
}
