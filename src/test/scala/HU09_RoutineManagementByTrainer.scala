package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU09_RoutineManagementByTrainer extends Simulation {

  val httpProtocol = http
    .baseUrl("http://www.dp2.com")
    .inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.*.woff2"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
    .upgradeInsecureRequestsHeader("1")
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

  object GoToRoutine9 {
    val goToRoutine = exec(http("Go To Routine 9")
      .get("/trainer/trainer1/trainings"))
      .pause(1)
      .exec(http("request_4")
        .get("/trainer/trainer1/clients/1/trainings/9"))
      .pause(3)
      .exec(http("request_5")
        .get("/trainer/trainer1/clients/1/trainings/9/routines/9"))
      .pause(15)
  }

  object UpdateRoutineSuccessful {
    val updateRoutineSuccessful = exec(http("Update Routine Successful")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/9/edit")
      .check(css("input[name=_csrf]", "value").saveAs("stoken"))).pause(21)
      .exec(http("request_7")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/9/edit")
        .headers(headers_2)
        .formParam("name", "Routine 1")
        .formParam("description", "Desc")
        .formParam("repsPerWeek", "2")
        .formParam("_csrf", "${stoken}"))
      .pause(14)
  }

  object DeleteRoutineOtherTrainerError {
    val deleteRoutineOtherTrainerError = exec(http("Delete Routine")
      .get("/trainer/trainer2/clients/3/trainings/4/routines/5/delete"))
      .pause(16)
  }

  val updateRoutineSuccessfulScn = scenario("UpdateRoutineSuccessful").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    UpdateRoutineSuccessful.updateRoutineSuccessful)

  val deleteRoutineOtherTrainerErrorScn = scenario("DeleteRoutineOtherTrainerError").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    DeleteRoutineOtherTrainerError.deleteRoutineOtherTrainerError)

  setUp(
    updateRoutineSuccessfulScn.inject(rampUsers(10) during (1 seconds)),
    deleteRoutineOtherTrainerErrorScn.inject(rampUsers(10) during (1 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}