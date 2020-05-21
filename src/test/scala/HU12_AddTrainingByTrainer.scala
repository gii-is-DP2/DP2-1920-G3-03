package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU12_AddTrainingByTrainer extends Simulation {

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

  object GoToTrainingManagement {
    val goToTrainingManagement = exec(http("Training Management")
      .get("/trainer/trainer1/trainings")
      .headers(headers_0))
      .pause(10)
  }

  object AddTrainingSuccessful {
    val addTrainingSuccessful = exec(http("Add Training Successful")
      .get("/trainer/trainer1/clients/1/trainings/create")
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(40)
      .exec(http("request_5")
        .post("/trainer/trainer1/clients/1/trainings/create")
        .headers(headers_2)
        .formParam("id", "")
        .formParam("author", "trainer1")
        .formParam("name", "Prueba")
        .formParam("initialDate", "2020/08/02")
        .formParam("endDate", "2020/08/05")
        .formParam("editingPermission", "TRAINER")
        .formParam("_csrf", "${stoken}"))
      .pause(13)
  }

  object AddTrainingError {
    val addTrainingError = exec(http("Add Training Error")
      .get("/trainer/trainer1/clients/1/trainings/create")
      .headers(headers_0)
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(34)
      .exec(http("request_4")
        .post("/trainer/trainer1/clients/1/trainings/create")
        .headers(headers_2)
        .formParam("id", "")
        .formParam("author", "trainer1")
        .formParam("name", "prueba2")
        .formParam("initialDate", "2020/05/17")
        .formParam("endDate", "2020/05/19")
        .formParam("editingPermission", "TRAINER")
        .formParam("_csrf", "${stoken}"))
      .pause(10)
  }

  val AddTrainingSuccessfulScn = scenario("AddTrainingSuccessful").exec(
    Home.home,
    Login.login,
    GoToTrainingManagement.goToTrainingManagement,
    AddTrainingSuccessful.addTrainingSuccessful)

  val AddTrainingErrorScn = scenario("AddTrainingError").exec(
    Home.home,
    Login.login,
    GoToTrainingManagement.goToTrainingManagement,
    AddTrainingError.addTrainingError)

  setUp(
    AddTrainingSuccessfulScn.inject(rampUsers(10) during (1 seconds)),
    AddTrainingErrorScn.inject(rampUsers(10) during (1 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}