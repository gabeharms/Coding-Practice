import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class WeightResourceFeatureTest extends FeatureTest {
    override val server = new EmbeddedHttpServer(
        twitterServer = new FitmanServer
    )

    test("Weight Resource should save user weight when POST request is made") {
        server.httpPost(
            path = "/weights",
            postBody =
                """
                    |{
                    |"user":"gabe",
                    |"weight":85,
                    |"status":"Feeling great!!!"
                    |}
                """.stripMargin,
                andExpect = Status.Created,
                withLocation = "/weights/gabe"
        )
    }

    test("WeightResource should list all weights for a user when GET request is made") {
        val response = server.httpPost(
            path = "/weights",
            postBody =
            """
                |{
                |"user":"test_user_1",
                |"weight":80,
                |"posted_at" : "2017-09-14T23:16:06.871Z"
                |}
                """.stripMargin,
            andExpect = Status.Created
        )
        
        server.httpGetJson[List[Weight]](
            path = response.location.get,
            andExpect = Status.Ok,
            withJsonBody =
            """
                |[
                |  {
                |    "user" : "test_user_1",
                |    "weight" : 80,
                |    "posted_at" : "2017-09-14T23:16:06.871Z"
                |  }
                |]
                """.stripMargin
        )
    }

    test("Bad request when user is not present in request") {
        server.httpPost(
            path = "/weights",
            postBody =
            """
                |{
                |"weight":85
                |}
            """.stripMargin,
            andExpect = Status.BadRequest
        )
    }

}