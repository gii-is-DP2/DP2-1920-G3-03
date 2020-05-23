package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU20_CreateAnswer extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.png""", """.*.jpg""", """.*.ico"""), WhiteList())
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
		.pause(12)
	}
	
	object Login {
		val login = exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(11)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}"))
		.pause(9)
	}

	object GuildList {
		val guildList = exec(http("GuildList")
			.get("/client/client1/guilds")
			.headers(headers_0))
		.pause(6)
	}

	object ShowMyGuild {
		val showMyGuild = exec(http("ShowMyGuild")
			.get("/client/client1/guilds/1")
			.headers(headers_0))
		.pause(7)
	}

	object ShowMyGuildForum {
		val showMyGuildForum = exec(http("ShowMyGuildForum")
			.get("/client/client1/guilds/1/forums/1")
			.headers(headers_0))
		.pause(9)
	}

	object NewAnswer {
		val newAnswer = exec(http("NewAnswerForm")
			.get("/client/forums/messagesAnswerForm")
			.headers(headers_6)
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(27)
		.exec(http("NewAnswerPost")
			.post("/client/client1/guilds/1/forums/1/messages/10")
			.headers(headers_2)
			.formParam("content", "Estoy haciendo una respuesta de prueba en el Calisthenics Forum.")
			.formParam("_csrf", "${stoken}"))
		.pause(7)
	}

	object NewEmptyAnswer {
		val newEmptyAnswer = exec(http("NewAnswerForm")
			.get("/client/forums/messagesAnswerForm")
			.headers(headers_6)
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(27)
		.exec(http("NewEmptyAnswerPost")
			.post("/client/client1/guilds/1/forums/1/messages/10")
			.headers(headers_2)
			.formParam("content", "")
			.formParam("_csrf", "${stoken}"))
		.pause(7)
	}

	val createNewAnswerScn = scenario("CreateNewAnswer").exec(Home.home,
																Login.login,
																GuildList.guildList,
																ShowMyGuild.showMyGuild,
																ShowMyGuildForum.showMyGuildForum,
																NewAnswer.newAnswer)

	val createNewEmptyAnswerScn = scenario("CreateNewEmptyAnswer").exec(Home.home,
																Login.login,
																GuildList.guildList,
																ShowMyGuild.showMyGuild,
																ShowMyGuildForum.showMyGuildForum,
																NewEmptyAnswer.newEmptyAnswer)

																

	setUp(
		createNewAnswerScn.inject(rampUsers(1000) during (60 seconds)),
		createNewEmptyAnswerScn.inject(rampUsers(1000) during (60 seconds))
		).protocols(httpProtocol)
}