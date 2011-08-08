
import org.scalatra.auth.{ScentryConfig, ScentrySupport}
import org.scalatra.ScalatraKernel
import org.scalatra.auth.strategy.{BasicAuthStrategy, BasicAuthSupport}
import org.scalatra.auth.{ScentrySupport, ScentryConfig}




  trait AuthenticationSupport extends ScentrySupport[User] with BasicAuthSupport[User] { self: ScalatraKernel =>

    val realm = "omnipass"
    protected def contextPath = request.getContextPath

    protected def fromSession = { case id: String => User.find("_id",id) openOr null  }
    protected def toSession   = { case usr: User => usr.id.toString() }

    protected val scentryConfig = (new ScentryConfig {}).asInstanceOf[ScentryConfiguration]


    override protected def configureScentry = {
      scentry.unauthenticated {
        scentry.strategies('Basic).unauthenticated()
      }
    }

    override protected def registerAuthStrategies = {

      scentry.registerStrategy('Basic, app => new OurBasicAuthStrategy(app, realm))
    }

  }
