package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;


@Log
public class QuoteControllerAPIContractTest {


	@Test
	public void testDefaultEvent() {		
		when()
			.get("https://api.kanye.rest/")			
		.then()			
			.statusCode(200)
		.and()
		  	.assertThat()
	      		.body("quote", notNullValue());
		
	}	
}
