package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU22_ChallengeClasification extends Simulation {

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



	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(8)
	}
	
	object Login{
		val loginClient1 = exec(http("Login Client 1")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(3)
		.exec(http("Logged Client 1")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(10)

		val loginClient3 = exec(http("Login Client 3")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(3)
		.exec(http("Logged Client 3")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client3")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(10)
	}

	object ShowDashboardClassification{
		val showDashboardClassificationNoCompleted = exec(http("Show Dashboard Classification No Completed")
			.get("/client/client1/clasification")
			.headers(headers_0))
		.pause(17)

		val showDashboardClassificationCompleted = exec(http("Show Dashboard Classification Completed")
			.get("/client/client3/clasification")
			.headers(headers_0))
		.pause(17)
	}


	val challengeClasificationNoCompletedScn = scenario("Challenge Clasification No Completed").exec(
																								Home.home,
																								Login.loginClient1,
																								ShowDashboardClassification.showDashboardClassificationNoCompleted)
																
	val challengeClasificationCompletedScn = scenario("Challenge Clasification Completed").exec(
																							Home.home,
																							Login.loginClient3,
																							ShowDashboardClassification.showDashboardClassificationCompleted)
	

	setUp(
		challengeClasificationNoCompletedScn.inject(rampUsers(35) during (1 seconds)), // 7000, 100
		challengeClasificationCompletedScn.inject(rampUsers(35) during (1 seconds))    // 7000, 100
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}