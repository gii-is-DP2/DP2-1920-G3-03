package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU11_DietManagement extends Simulation {

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
		.pause(3)
	}
	
	object Login{
		val loginTrainer1 = exec(http("Login Trainer 1")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(3)
		.exec(http("Logged Trainer 1")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "trainer1")
			.formParam("password", "trainer1999")
			.formParam("_csrf", "${stoken}")
		).pause(13)

		val loginTrainer2 = exec(http("Login Trainer 2")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(3)
		.exec(http("Logged Trainer 2")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "trainer2")
			.formParam("password", "trainer1999")
			.formParam("_csrf", "${stoken}")
		).pause(13)
	}

	object ListDietManagement{
		val listDietManagementT1 = exec(http("List Diet Management T1")
			.get("/trainer/trainer1/diets")
			.headers(headers_0))
		.pause(30)

		val listDietManagementT2 = exec(http("List Diet Management T2")
			.get("/trainer/trainer2/diets")
			.headers(headers_0))
		.pause(20)
	}

	object ShowDiet{
		val showDietT1 = exec(http("Show Diet T1")
			.get("/trainer/trainer1/clients/1/trainings/1/diets/1")
			.headers(headers_0))
		.pause(7)

		val showDietT2Error = exec(http("Show Diet T2")
			.get("/trainer/trainer2/clients/1/trainings/1/diets/1")
			.headers(headers_0))
		.pause(7)
	}
	
	object UpdateDiet{
		val updateDiet = exec(http("Update Diet")
			.get("/trainer/trainer1/clients/1/trainings/1/diets/1/edit")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(16)
		.exec(http("Save Updated Diet")
			.post("/trainer/trainer1/clients/1/trainings/1/diets/1/edit")
			.headers(headers_2)
			.formParam("name", "Dieta 1")
			.formParam("description", "Desc 1")
			.formParam("type", "VOLUME")
			.formParam("kcal", "100")
			.formParam("protein", "1")
			.formParam("carb", "1")
			.formParam("fat", "1")
			.formParam("_csrf", "${stoken}")
		).pause(22)
	}

	val manageDietT1Scn = scenario("Manage Diet T1").exec(
														Home.home,
														Login.loginTrainer1,
														ListDietManagement.listDietManagementT1,
														ShowDiet.showDietT1,
														UpdateDiet.updateDiet)
																
	val manageDietT2NotHisClientScn = scenario("Manage Diet T2 Not His Client").exec(
																				Home.home,
																				Login.loginTrainer1,
																				ListDietManagement.listDietManagementT2,
																				ShowDiet.showDietT2Error)
	

	setUp(
		manageDietT1Scn.inject(rampUsers(35) during (1 seconds)),               // 7000, 100
		manageDietT2NotHisClientScn.inject(rampUsers(10) during (1 seconds))    // 7000, 100
		).protocols(httpProtocol)
		 .assertions(
					global.responseTime.max.lt(5000),    
					global.responseTime.mean.lt(1000),
					global.successfulRequests.percent.gt(95)
					)	


}