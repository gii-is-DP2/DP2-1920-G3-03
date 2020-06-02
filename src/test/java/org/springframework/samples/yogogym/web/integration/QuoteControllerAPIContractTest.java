package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

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
