import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

 
import scala.collection.mutable
class WeightResource extends Controller {
    val db = mutable.Map[String, List[Weight]]()

    get("/weights/:user") { request: Request =>
        db.getOrElse(request.params("user"), List())
    }

    post("/weights") { weight: Weight => 
        val weightsForUser = db.get(weight.user) match {
            case Some(weights) => weights :+ weight
            case None => List(weight)
        }
        db.put(weight.user, weightsForUser)
        response.created.location(s"/weights/${weight.user}")
    }
}