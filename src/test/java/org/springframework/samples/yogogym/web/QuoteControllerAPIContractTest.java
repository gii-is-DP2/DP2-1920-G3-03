package org.springframework.samples.yogogym.web;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.concurrent.TimeUnit;

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
	      		.body("quote", notNull());
		
	}	
}
