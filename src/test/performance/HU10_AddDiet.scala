package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU10_AddDiet extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.yogogym.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.+.woff2"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_1 = Map("Origin" -> "http://www.yogogym.com")



	object Home {
		val home = exec(http("Home")
			.get("/"))
		.pause(10)
	}
	
	object Login{
		val login = exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(24)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "trainer1")
			.formParam("password", "trainer1999")
			.formParam("_csrf", "${stoken}")
		).pause(12)
	}

	object ListClients{
		val listClients = exec(http("List Clients")
			.get("/trainer/trainer1/clients"))
		.pause(14)
	}
	
	object ShowClient{
		val showClient = exec(http("Show Client")
			.get("/trainer/trainer1/clients/1"))
		.pause(9)
	}

	object ShowTraining{
		val showTraining = exec(http("Show Training")
			.get("/trainer/trainer1/clients/1/trainings/9"))
		.pause(10)
	}	

	object NewDietSuccessful{
		val newDietSuccessful = exec(http("New Diet Successful")
			.get("/trainer/trainer1/clients/1/trainings/9/diets/create")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(17)
		.exec(http("Save Diet Successful")
			.post("/trainer/trainer1/clients/1/trainings/9/diets/create")
			.headers(headers_1)
			.formParam("name", "Dieta 1")
			.formParam("description", "Descripcion dieta 1")
			.formParam("type", "VOLUME")
			.formParam("carb", "0")
			.formParam("fat", "0")
			.formParam("kcal", "0")
			.formParam("protein", "0")
			.formParam("_csrf", "${stoken}"))
		.pause(18)
	}		

	object NewDietError{
		val newDietError = exec(http("New Diet Error")
			.get("/trainer/trainer1/clients/1/trainings/9/diets/create")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(9)
		.exec(http("Save Diet Error")
			.post("/trainer/trainer1/clients/1/trainings/9/diets/create")
			.headers(headers_1)
			.formParam("name", "Dieta 1")
			.formParam("description", "")
			.formParam("type", "VOLUME")
			.formParam("carb", "0")
			.formParam("fat", "0")
			.formParam("kcal", "0")
			.formParam("protein", "0")
			.formParam("_csrf", "${stoken}"))
		.pause(9)
	}


	val createDietSuccessfulScn = scenario("Create Challenge Successful").exec(
																Home.home,
																Login.login,
																ListClients.listClients,
																ShowClient.showClient,
																ShowTraining.showTraining,
																NewDietSuccessful.newDietSuccessful)
																
	val createDietErrorScn = scenario("Create Challenge Error").exec(
																Home.home,
																Login.login,
																ListClients.listClients,
																ShowClient.showClient,
																ShowTraining.showTraining,
																NewDietError.newDietError)
	

	setUp(
		createDietSuccessfulScn.inject(rampUsers(2500) during (100 seconds)), // 7000, 100
		createDietErrorScn.inject(rampUsers(1500) during (100 seconds))       // 4000, 80
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}