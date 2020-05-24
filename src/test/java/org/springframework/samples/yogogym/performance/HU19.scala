package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU19_PersonalizarMiEntrenamiento extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.yogogym.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.*.woff2"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_17 = Map(
		"Origin" -> "http://www.yogogym.com",
		"Upgrade-Insecure-Requests" -> "1")

	object Home
	{
		val home = exec(http("Home")
				.get("/")
				.headers(headers_0))
			.pause(9)
	}

	object Login
	{
		val login = exec(http("Login")
			.get("/login")
				.headers(headers_0)
				.check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(24)
				.exec(http("Logged")
					.post("/login")
					.headers(headers_17)
					.formParam("username", "client10")
					.formParam("password", "client1999")
					.formParam("_csrf", "${stoken}"))
				.pause(15)
	}

	object TrainingList
	{
		val trainingList = exec(http("Training List")
				.get("/client/client10/trainings")
				.headers(headers_0))
			.pause(13)
	}

	object TrainingDetails_notEditable
	{
		val trainingDetails_notEditable = exec(http("Training Details Not Editable")
				.get("/client/client10/trainings/14")
				.headers(headers_0))
			.pause(70)
	}

	object WrongAuthEditTraining
	{
		val wrongAuthEditTraining = exec(http("WrongAuthEditTraining")
				.get("/client/client10/trainings/14/edit")
				.headers(headers_0))			
			.pause(17)
	}

	object UpdateTraining
	{
		val updateTaining = exec(http("Init Update Training")
				.get("/client/client10/trainings/15/edit")
				.headers(headers_0)
				.check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(24)
				.exec(http("Process Update Training")
					.post("/client/client10/trainings/15/edit")
					.headers(headers_17)
					.formParam("id", "15")
					.formParam("author", "client10")
					.formParam("name", "Test Performance Prueba")
					.formParam("initialDate", "2020/12/19")
					.formParam("endDate", "2020/12/28")
					.formParam("editingPermission", "CLIENT")
					.formParam("_csrf", "${stoken}"))
				.pause(9)
	}

	val editTrainingSuccesfulScn = scenario("Edit Training Succesful Scenary").exec(
		Home.home,
		Login.login,
		TrainingList.trainingList,
		UpdateTraining.updateTaining
	)
	
	val editTrainingUnsuccesfulScn = scenario("Edit Training Unsuccesful Scenary").exec(
		Home.home,
		Login.login,
		TrainingList.trainingList,
		TrainingDetails_notEditable.trainingDetails_notEditable,
		WrongAuthEditTraining.wrongAuthEditTraining
	)

	setUp(
		editTrainingSuccesfulScn.inject(rampUsers(5000) during (60 seconds)),
		editTrainingUnsuccesfulScn.inject(rampUsers(3000) during (80 seconds))).protocols(httpProtocol)
		.assertions(
			global.responseTime.max.lt(5000),
			global.responseTime.mean.lt(1000),
			global.successfulRequests.percent.gt(95)
	)
}