package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU08_AddRoutineByTrainer extends Simulation {

  val httpProtocol = http
    .baseUrl("http://www.dp2.com")
    .inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.*.woff2"""), WhiteList())
    .acceptEncodingHeader("gzip,deflate")
    .userAgentHeader("Apache-HttpClient/4.5.6 (Java/1.8.0_221)")

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

  object Login {
    val login = exec(http("Login")
      .get("/login")
      .headers(headers_0)
      .check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(24)
      .exec(http("Logged")
        .post("/login")
        .headers(headers_2)
        .formParam("username", "trainer1")
        .formParam("password", "trainer1999")
        .formParam("_csrf", "${stoken}")).pause(15)
  }

  object GoToTraining9 {
    val goToTraining = exec(http("Training Management")
      .get("/trainer/trainer1/trainings")
      .headers(headers_0))
      .pause(10)
      .exec(http("Training 9")
        .get("/trainer/trainer1/clients/1/trainings/9")
        .headers(headers_0))
      .pause(27)
  }

  object AddRoutineSuccessful {
    val addRoutineSuccessful = exec(http("Add Routine Successful")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/create")
      .headers(headers_0)
      .check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(24)
      .exec(http("request_20")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/create")
        .headers(headers_2)
        .formParam("name", "Prueba")
        .formParam("description", "Prueba")
        .formParam("repsPerWeek", "3")
        .formParam("_csrf", "${stoken}"))
      .pause(7)
  }

  object AddRoutineError {
    val addRoutineError = exec(http("Add Routine Error")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/create")
      .check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(13)
      .exec(http("request_6")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/create")
        .headers(headers_2)
        .formParam("name", "")
        .formParam("description", "")
        .formParam("repsPerWeek", "")
        .formParam("_csrf", "${stoken}"))
      .pause(8)
  }

  val addRoutineSuccessfulScn = scenario("AddRoutineSuccessful").exec(
    Home.home,
    Login.login,
    GoToTraining9.goToTraining,
    AddRoutineSuccessful.addRoutineSuccessful)

  val addRoutineErrorScn = scenario("AddRoutineError").exec(
    Home.home,
    Login.login,
    GoToTraining9.goToTraining,
    AddRoutineError.addRoutineError)

  setUp(
    addRoutineSuccessfulScn.inject(rampUsers(6500) during (100 seconds)),
    addRoutineErrorScn.inject(rampUsers(1500) during (100 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}