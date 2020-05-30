package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU5 extends Simulation {

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
			.formParam("username", "client4")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(10)
	}

	object GuildList{
		val guildList = exec(http("GuildList")
			.get("/client/client4/guilds")
			.headers(headers_0))
		.pause(8)
	}
	object NewGuildSuccessful{

		val guildsCreateForm = exec(http("GuildsCreateForm")
			.get("/client/client4/guilds/create")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(22)
		.exec(http("Save Guild Successfull")
			.post("/client/client4/guilds/create")
			.headers(headers_2)
			.formParam("creator", "client4")
			.formParam("logo", "https://imagenExample.png")
			.formParam("name", "This is an example")
			.formParam("description", "This is an example")
			.formParam("_csrf", "${stoken}"))
		.pause(12)
	}
	object ShowMyGuild{
		val showMyGuild = exec(http("ShowMyGuild")
			.get("/client/client4/guilds/6")
			.headers(headers_0))
		.pause(17)
	}
	object BadUpdateGuild{
		val badUpdateGuild = exec(http("BadUpdateGuildRequest")
			.get("/client/client4/guilds/6/edit")
			.headers(headers_0))
		.pause(26)
		.exec(http("Do not update guild")
			.post("/client/client4/guilds/6/edit")
			.headers(headers_2)
			.formParam("creator", "client4")
			.formParam("logo", "imagensinhttps://.png")
			.formParam("name", "This is an example")
			.formParam("description", "This is an example")
			.formParam("_csrf", "${stoken}"))
		.pause(13)
	}

	val createNewGuildScn = scenario("CreateNewGuild").exec(Home.home,
																Login.login,
																GuildList.guildList,
																NewGuildSuccessful.guildsCreateForm)
	val updateBadURLGuildScn = scenario("UpdateBadURLGuild").exec(Home.home,
																Login.login,
																GuildList.guildList,														NewGuildSuccessful.guildsCreateForm,
																ShowMyGuild.showMyGuild,
																BadUpdateGuild.badUpdateGuild)
	setUp(
		createNewGuildScn.inject(rampUsers(3900) during (100 seconds)),
		updateBadURLGuildScn.inject(rampUsers(3900) during (100 seconds))
	).protocols(httpProtocol)
	.assertions(
		global.responseTime.max.lt(5000),
		global.responseTime.mean.lt(1000),
		global.successfulRequests.percent.gt(95)
	)

	
}