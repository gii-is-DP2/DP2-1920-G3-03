package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU17_CopyTrainingByTrainerFromOtherClient extends Simulation {

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

  object CreateEmptyTraining {
    val createEmptyTraining = exec(http("Training Management")
      .get("/trainer/trainer1/trainings"))
      .pause(20)
      .exec(http("Create Training Form")
        .get("/trainer/trainer1/clients/1/trainings/create")
        .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(27)
      .exec(http("New Training")
        .post("/trainer/trainer1/clients/1/trainings/create")
        .headers(headers_2)
        .formParam("id", "")
        .formParam("author", "trainer1")
        .formParam("name", "ToCpy")
        .formParam("initialDate", "2021/01/01")
        .formParam("endDate", "2021/01/05")
        .formParam("editingPermission", "TRAINER")
        .formParam("_csrf", "${stoken}"))
      .pause(19)
  }

  object CopyTrainingSuccessful {
    val copyTrainingSuccessful = exec(http("List Copy Training")
      .get("/trainer/trainer1/clients/1/trainings/15/copyTraining")
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(23)
      .exec(http("Copy Training Successful")
        .post("/trainer/trainer1/clients/1/trainings/15/copyTraining")
        .headers(headers_2)
        .formParam("trainerUsername", "trainer1")
        .formParam("trainingIdToCopy", "9")
        .formParam("_csrf", "${stoken}"))
      .pause(18)
  }

  object CopyTrainingNotEmptyError {
    val copyTrainingNotEmptyError = exec(http("request_5")
      .get("/trainer/trainer1/clients/1/trainings/9/copyTraining"))
      .pause(15)
  }

  val CopyTrainingSuccessfulScn = scenario("CopyTrainingSuccessful").exec(
    Home.home,
    Login.login,
    CreateEmptyTraining.createEmptyTraining,
    CopyTrainingSuccessful.copyTrainingSuccessful)

  val CopyTrainingNotEmptyErrorScn = scenario("CopyTrainingNotEmptyError").exec(
    Home.home,
    Login.login,
    CopyTrainingNotEmptyError.copyTrainingNotEmptyError)

  setUp(
    CopyTrainingSuccessfulScn.inject(rampUsers(10) during (1 seconds)),
    CopyTrainingNotEmptyErrorScn.inject(rampUsers(10) during (1 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}