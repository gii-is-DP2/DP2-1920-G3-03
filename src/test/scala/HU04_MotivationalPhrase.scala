package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU04_MotivationalPhrase extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.yogogym.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.+.woff2"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_2 = Map("Origin" -> "http://www.yogogym.com")


	object Home {
		val home = exec(http("Home")
			.get("/"))
		.pause(8)
	}


	object LoginC1{
		val loginC1 = exec(http("Login C1")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(10)
		.exec(http("Logged C1")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(15)
	}

	object LoginC2{
		val loginC2 = exec(http("Login C2")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(10)
		.exec(http("Logged C2")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client2")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(15)
	}

	
	val motivationalPhraseOkClient1Scn = scenario("Motivational Phrase Ok Client1").exec(
																Home.home,
																LoginC1.loginC1)
																
	val motivationalPhraseOkClient2Scn = scenario("Motivational Phrase Ok Client2").exec(
																Home.home,
																LoginC2.loginC2)


	setUp(
		motivationalPhraseOkClient1Scn.inject(rampUsers(7) during (1 seconds)), // 7000, 100
		motivationalPhraseOkClient2Scn.inject(rampUsers(7) during (1 seconds))       // 7000, 100
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}