package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU11ChallengeClasification extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.yogogym.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.+.woff2"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_1 = Map("Origin" -> "http://www.yogogym.com")



	val scn = scenario("HU11ChallengeClasification")
		.exec(http("request_0")
			.get("/login"))
		.pause(5)
		.exec(http("request_1")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "admin1")
			.formParam("password", "admin1999")
			.formParam("_csrf", "a89d0671-b209-4dfc-8a1c-7384fde0e740"))
		.pause(3)
		.exec(http("request_2")
			.get("/admin/inscriptions/submitted"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}