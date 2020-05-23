package yogogym

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU13_TrainingManagementByTrainer extends Simulation {

  val httpProtocol = http
    .baseUrl("http://www.dp2.com")
    .inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg""", """.*.jpeg""", """.*.woff""", """.*.woff2"""), WhiteList())
    .acceptEncodingHeader("gzip, deflate")
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

  object UpdateTrainingByTrainerSuccessful {
    val updateTrainingByTrainerSuccessful = exec(http("Update Training By Trainer Form")
      .get("/trainer/trainer1/clients/1/trainings/9/edit")
      .headers(headers_0)
      .check(css("input[name=_csrf]", "value").saveAs("stoken")))
      .pause(23)
      .exec(http("Update Training By Trainer Successful")
        .post("/trainer/trainer1/clients/1/trainings/9/edit")
        .headers(headers_2)
        .formParam("id", "9")
        .formParam("author", "trainer1")
        .formParam("name", "Test")
        .formParam("initialDate", "2020/08/28")
        .formParam("endDate", "2020/08/30")
        .formParam("editingPermission", "BOTH")
        .formParam("_csrf", "${stoken}"))
      .pause(23)
  }

  object DeleteTrainingOtherTrainerError {
    val deleteTrainingOtherTrainerError = exec(http("Delete Training by Trainer Error")
      .get("/trainer/trainer2/clients/3/trainings/4/delete"))
      .pause(12)
  }

  val UpdateTrainingByTrainerSuccessfulScn = scenario("AddTrainingSuccessful").exec(
    Home.home,
    Login.login,
    GoToTraining9.goToTraining,
    UpdateTrainingByTrainerSuccessful.updateTrainingByTrainerSuccessful)

  val DeleteTrainingOtherTrainerErrorScn = scenario("AddTrainingError").exec(
    Home.home,
    Login.login,
    GoToTraining9.goToTraining,
    DeleteTrainingOtherTrainerError.deleteTrainingOtherTrainerError)

  setUp(
    UpdateTrainingByTrainerSuccessfulScn.inject(rampUsers(10) during (1 seconds)),
    DeleteTrainingOtherTrainerErrorScn.inject(rampUsers(10) during (1 seconds))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.responseTime.mean.lt(1000),
      global.successfulRequests.percent.gt(95))
}