package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU15_ShowExcercises extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.yogogym.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.+.woff2"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Origin" -> "http://www.yogogym.com",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map("Accept" -> "image/webp,*/*")


	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(9)
	}

	object Login{
		val loginClient1 = exec(http("Login Client 1")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(4)
		.exec(http("Logged Client 1")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(11)

		val loginClient2 = exec(http("Login Client 2")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(4)
		.exec(http("Logged Client 2")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client2")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(11)
	}
	
	object ListExercises{
		val listExercises = exec(http("List Exercises")
			.get("/mainMenu/exercises")
			.headers(headers_0))
		.pause(23)
	}	

	object ShowExercise{
		val showExercise = exec(http("Show Exercise")
			.get("/mainMenu/exercises/6")
			.headers(headers_0))
		.pause(15)

		val showExerciseNotExisting = exec(http("Show Exercise Not Existing")
			.get("/mainMenu/exercises/1000")
			.headers(headers_0))
		.pause(15)
	}
		
	val showExercisesScn = scenario("Show Exercises").exec(
																Home.home,
																Login.loginClient1,
																ListExercises.listExercises,
																ShowExercise.showExercise)
																
	val showExerciseNotExistingScn = scenario("Show Exercise Not Existing").exec(
																Home.home,
																Login.loginClient2,
																ListExercises.listExercises,
																ShowExercise.showExerciseNotExisting)
	

	setUp(
		showExercisesScn.inject(rampUsers(8000) during (100 seconds)),         // 12000, 100
		showExerciseNotExistingScn.inject(rampUsers(300) during (20 seconds))   // 500, 20
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}