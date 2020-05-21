package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU25_RoutineLineManagementByTrainer extends Simulation {

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
    val goToRoutine = exec(http("Training Management")
      .get("/trainer/trainer1/trainings"))
      .pause(2)
      .exec(http("Training 9")
        .get("/trainer/trainer1/clients/1/trainings/9"))
      .pause(2)
      .exec(http("Routine 9 from Training 9")
        .get("/trainer/trainer1/clients/1/trainings/9/routines/9"))
      .pause(15)
  }

  object UpdateRoutineLineByTrainerSuccessful {
    val updateRoutineLineByTrainerSuccessful = exec(http("Update Routine Line Form")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/12/update")
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(21)
      .exec(http("Update Routine Line by Trainer Successful")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/12/update")
        .headers(headers_2)
        .formParam("exercise.id", "15")
        .formParam("routineId", "9")
        .formParam("reps", "15")
        .formParam("time", "")
        .formParam("weight", "30.0")
        .formParam("series", "3")
        .formParam("_csrf", "${stoken}"))
      .pause(17)
  }

  object UpdateRoutineLineByTrainerError {
    val updateRoutineLineByTrainerError = exec(http("Update Routine Line Form")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/12/update")
      .headers(headers_0)
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(8)
      .exec(http("Update Routine Line by Trainer Successful")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/12/update")
        .headers(headers_2)
        .formParam("exercise.id", "15")
        .formParam("routineId", "9")
        .formParam("reps", "15")
        .formParam("time", "12")
        .formParam("weight", "30.0")
        .formParam("series", "3")
        .formParam("_csrf", "${stoken}"))
      .pause(12)
  }

  val UpdateRoutineLineByTrainerSuccessfulScn = scenario("UpdateRoutineLineByTrainerSuccessful").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    UpdateRoutineLineByTrainerSuccessful.updateRoutineLineByTrainerSuccessful)

  val UpdateRoutineLineByTrainerErrorScn = scenario("UpdateRoutineLineByTrainerError").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    UpdateRoutineLineByTrainerError.updateRoutineLineByTrainerError)

  setUp(
    UpdateRoutineLineByTrainerSuccessfulScn.inject(rampUsers(10) during (1 seconds)),
    UpdateRoutineLineByTrainerErrorScn.inject(rampUsers(10) during (1 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))

}