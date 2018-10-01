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
}