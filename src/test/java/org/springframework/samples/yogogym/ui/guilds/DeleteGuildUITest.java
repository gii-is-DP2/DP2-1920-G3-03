package org.springframework.samples.yogogym.ui.guilds;

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
public class DeleteGuildUITest {


	@LocalServerPort
	private int port;
	private WebDriver driver;
	UtilsGuildUI utils;
	
	private static final String CLIENT_USERNAME = "client1";

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
	public void testDeleteGuildUI() throws Exception {
		
		utils.as(CLIENT_USERNAME);
		utils.listGuilds();
		utils.showYourGuild();
		driver.findElement(By.linkText("Delete")).click();

	}
	
	@Test
	public void testDeleteNotYourGuild() throws Exception {
		utils.as(CLIENT_USERNAME);
		utils.listGuilds();
		driver.findElement(By.linkText("Calisthenics")).click();
		driver.get("http://localhost:" + port + "/client/client4/guilds/1/delete");
		utils.exceptionView();
	}

}
