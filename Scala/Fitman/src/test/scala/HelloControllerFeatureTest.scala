import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class HelloControllerFeatureTest extends FeatureTest {
    override protected def server = new EmbeddedHttpServer(new FitmanServer)

    test("Server#Says hello") {
        server.httpGet(path = "/hello", andExpect = Ok, withBody = "Fitman says hello")
    }
}