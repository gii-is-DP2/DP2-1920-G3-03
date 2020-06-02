package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU14 extends Simulation{

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
		
	object Login {
		val login = exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(16)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client7")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(10)
	}

	object GuildList{
		val guildList = exec(http("GuildList")
			.get("/client/client7/guilds")
			.headers(headers_0))
		.pause(8)
	}
	object ShowOneGuild{
		val showOneGuild = exec(http("ShowOneGuild")
			.get("/client/client7/guilds/1")
			.headers(headers_0))
		.pause(17)
	}
	object JoinGuild{
		val joinGuild = exec(http("JoinGuild")
			.get("/client/client7/guilds/1/join")
			.headers(headers_0))
		.pause(4)
	}
	object LeaveNotYourGuild{
		val leaveNotYourGuild = exec(http("Show Not Your Guild")
			.get("/client/client7/guilds/3")
			.headers(headers_0))
		.pause(9)
		.exec(http("Leave Not Your Guild")
			.get("/client/client7/guilds/3/leave")
			.headers(headers_0))
		.pause(11)

	}
	
	val joinAGuildScn = scenario("JoinAGuild").exec(Home.home,
																Login.login,
																GuildList.guildList,
																ShowOneGuild.showOneGuild,
																JoinGuild.joinGuild)
	val leaveAGuildNotYoursScn = scenario("LeaveAGuildNotYours").exec(Home.home,
																Login.login,
																GuildList.guildList,
																ShowOneGuild.showOneGuild,
																JoinGuild.joinGuild,
																LeaveNotYourGuild.leaveNotYourGuild)
	
	setUp(
		joinAGuildScn.inject(rampUsers(7000) during (100 seconds)),
		leaveAGuildNotYoursScn.inject(rampUsers(1500) during (100 seconds))
	).protocols(httpProtocol)
	.assertions(
		global.responseTime.max.lt(5000),
		global.responseTime.mean.lt(1000),
		global.successfulRequests.percent.gt(95)
	)
}