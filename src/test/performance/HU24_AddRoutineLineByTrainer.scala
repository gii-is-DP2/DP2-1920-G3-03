package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU24_AddRoutineLineByTrainer extends Simulation {

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

  object AddRoutineLineSuccessful {
    val addRoutineLineSuccessful = exec(http("Add Routine Line Form")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/create")
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(25)
      .exec(http("Add Routine Line Succesful")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/create")
        .headers(headers_2)
        .formParam("exercise.id", "1")
        .formParam("routineId", "9")
        .formParam("reps", "2")
        .formParam("time", "")
        .formParam("weight", "12")
        .formParam("series", "1")
        .formParam("_csrf", "${stoken}"))
      .pause(17)
  }

  object AddRoutineLineError {
    val addRoutineLineError = exec(http("Add Routine Line Form")
      .get("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/create")
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(9)
      .exec(http("Add Routine Line Succesful")
        .post("/trainer/trainer1/clients/1/trainings/9/routines/9/routineLine/create")
        .headers(headers_2)
        .formParam("exercise.id", "1")
        .formParam("routineId", "9")
        .formParam("reps", "1")
        .formParam("time", "1")
        .formParam("weight", "1")
        .formParam("series", "a")
        .formParam("_csrf", "${stoken}"))
      .pause(19)
  }

  val AddRoutineLineSuccessfulScn = scenario("AddRoutineLineSuccessful").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    AddRoutineLineSuccessful.addRoutineLineSuccessful)

  val AddRoutineLineErrorScn = scenario("AddRoutineLineError").exec(
    Home.home,
    Login.login,
    GoToRoutine9.goToRoutine,
    AddRoutineLineError.addRoutineLineError)

  setUp(
    AddRoutineLineSuccessfulScn.inject(rampUsers(2000) during (100 seconds)),
    AddRoutineLineErrorScn.inject(rampUsers(500) during (100 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}