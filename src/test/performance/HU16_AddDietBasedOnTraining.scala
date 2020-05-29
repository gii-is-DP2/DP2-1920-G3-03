package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU16_AddDietBasedOnTraining extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Origin" -> "http://www.dp2.com",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_6 = Map(
		"Accept" -> "text/html, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(7)
	}
	object Login{
		val login = exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(24)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(12)
	}

	object ListDiets{
		val listDiets = exec(http("List Diets")
			.get("/client/client1/diets")
			.headers(headers_0))
		.pause(2)
	}
	
	object NewDietSuccessful{
		val newDietSuccessful = exec(http("New Diet Successful")
			.get("/client/client1/trainings/9/diets/create")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(17)
		.exec(http("Save Diet Successful")
		.post("/client/client1/trainings/9/diets/create")
			.headers(headers_2)
			.formParam("name", "algo")
			.formParam("description", "asd")
			.formParam("type", "DEFINITION")
			.formParam("carb", "0")
			.formParam("fat", "0")
			.formParam("kcal", "0")
			.formParam("protein", "0")
			.formParam("_csrf", "${stoken}"))
		.pause(18)

	}

	object NewDietError{
		val newDietError = exec(http("New Diet Error")
			.get("/client/client1/trainings/9/diets/create")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(9)
		.exec(http("Save Diet Error")
			.post("/client/client1/trainings/9/diets/create")
			.headers(headers_2)
			.formParam("name", "Dieta 1")
			.formParam("description", "")
			.formParam("type", "DEFINITION")
			.formParam("carb", "0")
			.formParam("fat", "0")
			.formParam("kcal", "0")
			.formParam("protein", "0")
			.formParam("_csrf", "${stoken}"))
		.pause(9)
	}


	val createDietSuccessfulScn = scenario("Create Diet Successful").exec(
																Home.home,
																Login.login,
																ListDiets.listDiets,
																NewDietSuccessful.newDietSuccessful)
																
	val createDietErrorScn = scenario("Create Diet Error").exec(
																Home.home,
																Login.login,
																ListDiets.listDiets,
																NewDietError.newDietError)
	

	setUp(
		createDietSuccessfulScn.inject(rampUsers(11000) during (100 seconds)), // 7000, 100
		createDietErrorScn.inject(rampUsers(11000) during (100 seconds))       // 4000, 80
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}