package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU18_DashBoardClient extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Origin" -> "http://www.dp2.com",
		"Upgrade-Insecure-Requests" -> "1")


	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(8)
	}
	
	object Login1{
		val login1 = exec(http("Login")
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
		).pause(15)
	}
	
	object Login2{
		val login2 = exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(24)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client3")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(15)
	}
		
	object ShowDashBoardClient{
		val showDashboardClient = exec(http("ShowDashBoardClient")
			.get("/client/client1/dashboard")
			.headers(headers_0))
		.pause(19)
	}
	
	val dashboardCompletedScn = scenario("DashBoardCompleted").exec(
																Home.home,
																Login1.login1,
																ShowDashBoardClient.showDashboardClient)
																
	val dashboardWithoutExerciseScn = scenario("DashBoardWithoutExercise").exec(
																Home.home,
																Login2.login2,
																ShowDashBoardClient.showDashboardClient)
	

	setUp(
		dashboardCompletedScn.inject(rampUsers(10) during (1 seconds)),
		dashboardWithoutExerciseScn.inject(rampUsers(10) during (1 seconds))
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}