package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU07 extends Simulation {

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
			.formParam("username", "client1")
			.formParam("password", "client1999")
			.formParam("_csrf", "${stoken}")
		).pause(10)
	}
	object DietList {
		val dietList = exec(http("Diet List")
			.get("/client/client1/diets")
			.headers(headers_0))
		.pause(9)
	}
	object ShowDiet{
		val showDiet = exec(http("Show Diet")
			.get("/client/client1/trainings/9/diets/5")
			.headers(headers_0))
		.pause(8)
	}
		
	object AddFoodSuccessful{
		var addFoodSuccessful = exec(http("Show All Foods")
			.get("/client/client1/trainings/9/diets/5/foods")
			.headers(headers_0))
		.pause(8)
		.exec(http("Add Food Successful")
			.get("/client/client1/trainings/9/diets/5/food/3/addFood")
			.headers(headers_0))
		.pause(9)
	}	
		
	object AddFoodWrong {
 		var addFoodWrong = exec(http("Show All Foods")
			.get("/client/client1/trainings/9/diets/5/foods")
			.headers(headers_0))
		.pause(5)
		.exec(http("Add Food Wrong")
			.get("/client/client1/trainings/9/diets/5/food/3/addFood")
			.headers(headers_0))
		.pause(8)
	}
		
	val addFoodSuccessfulScn = scenario("AddFoodSuccessful").exec(Home.home,
																Login.login,
																DietList.dietList,
																ShowDiet.showDiet,
																AddFoodSuccessful.addFoodSuccessful)
	val addFoodWrongScn = scenario("AddFoodWrong").exec(Home.home,
																Login.login,
																DietList.dietList,
																ShowDiet.showDiet,
																AddFoodWrong.addFoodWrong)

	setUp(
		addFoodSuccessfulScn.inject(rampUsers(3500) during (100 seconds)),
		addFoodWrongScn.inject(rampUsers(3500) during (100 seconds))
	).protocols(httpProtocol)
	.assertions(
		global.responseTime.max.lt(5000),
		global.responseTime.mean.lt(1000),
		global.successfulRequests.percent.gt(95)
	)
}