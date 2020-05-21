package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU01_CreateChallenge extends Simulation {

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

	val headers_5 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "gzip",
		"Accept-Language" -> "en-US,en;q=0.5",
		"Connection" -> "close",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36 AceStream/3.1.16.3")

    val uri2 = "http://ts.acestream.net/init"

	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(8)
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
			.formParam("username", "admin1")
			.formParam("password", "admin1999")
			.formParam("_csrf", "${stoken}")
		).pause(15)
	}
	
	object ListChallenges{
		val listChallenges = exec(http("List Challenges")
			.get("/admin/challenges")
			.headers(headers_0))
		.pause(14)
	}
	
	object NewChallengeSuccessful{
		val newChallengeSuccessful = exec(http("New Challenge Successful")
			.get("/admin/challenges/new")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(42)
		.exec(http("Save Challenge Successfull")
			.post("/admin/challenges/new")
			.headers(headers_2)
			.formParam("id", "")
			.formParam("name", "Challenge Prueba")
			.formParam("description", "Descripcion prueba")
			.formParam("initialDate", "2021/04/01")
			.formParam("endDate", "2021/04/06")
			.formParam("reward", "Texto")
			.formParam("points", "1")
			.formParam("reps", "2")
			.formParam("weight", "3")
			.formParam("exercise.id", "1")
			.formParam("_csrf", "${stoken}"))
		.pause(40)
	}
	
	object NewChallengeError{
		val newChallengeError = exec(http("New Challenge Error")
			.get("/admin/challenges/new")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(42)
		.exec(http("Save Challenge Error")
			.post("/admin/challenges/new")
			.headers(headers_2)
			.formParam("id", "")
			.formParam("name", "Challenge2")
			.formParam("description", "Descripcion prueba")
			.formParam("initialDate", "2020/10/01")
			.formParam("endDate", "2020/10/02")
			.formParam("reward", "Texto")
			.formParam("points", "1")
			.formParam("reps", "2")
			.formParam("weight", "3")
			.formParam("exercise.id", "1")
			.formParam("_csrf", "${stoken}"))
		.pause(40)
	}

	val createChallengeSuccessfulScn = scenario("CreateChallengeSuccessful").exec(
																Home.home,
																Login.login,
																ListChallenges.listChallenges,
																NewChallengeSuccessful.newChallengeSuccessful)
																
	val createChallengeErrorScn = scenario("CreateChallengeError").exec(
																Home.home,
																Login.login,
																ListChallenges.listChallenges,
																NewChallengeError.newChallengeError)
	

	setUp(
		createChallengeSuccessfulScn.inject(rampUsers(100000) during (10 seconds)),
		createChallengeErrorScn.inject(rampUsers(100000) during (10 seconds))
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)
}