package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU02_DashboardChallenges extends Simulation {

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

	object Login{
		val login = exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(10)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "admin1")
			.formParam("password", "admin1999")
			.formParam("_csrf", "${stoken}")
		).pause(15)
	}

	object ShowDashboardChallenges{
		val showDashboardChallenges = exec(http("Show Dashboard Challenges")
			.get("/admin/dashboardChallenges/0"))
		.pause(13)
	}

	object ShowDashboardCompletedChallenges{
		val showDashboardCompletedChallenges = exec(http("Show Dashboard Completed Challenges")
			.get("/admin/dashboardChallenges/1?month=1"))
		.pause(15)
	}
	
	object ShowDashboardNoCompletedChallenges{
		val showDashboardNoCompletedChallenges = exec(http("Show Dashboard No Completed Challenges")
			.get("/admin/dashboardChallenges/10?month=10"))
		.pause(7)
	}

	val dashboardCompletedChallengesScn = scenario("Dashboard Completed Challenges").exec(
																Home.home,
																Login.login,
																ShowDashboardChallenges.showDashboardChallenges,
																ShowDashboardCompletedChallenges.showDashboardCompletedChallenges)
																
	val dashboardNoCompletedChallengesScn = scenario("Dashboard No Completed Challenges").exec(
																Home.home,
																Login.login,
																ShowDashboardChallenges.showDashboardChallenges,
																ShowDashboardNoCompletedChallenges.showDashboardNoCompletedChallenges)

	setUp(
		dashboardCompletedChallengesScn.inject(rampUsers(7000) during (100 seconds)),    // 7000, 100
		dashboardNoCompletedChallengesScn.inject(rampUsers(7000) during (100 seconds))   // 7000, 100
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)

}