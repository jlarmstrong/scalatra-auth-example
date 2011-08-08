import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import net.iharder.Base64
import org.scalatra.{ScalatraKernel}
import net.liftweb.mongodb._
import net.liftweb.mongodb.record._
import org.scalatra.auth.strategy.{BasicAuthStrategy, BasicAuthSupport}
import org.scalatra.auth.{ScentrySupport, ScentryConfig}

class OurBasicAuthStrategy(protected override val app: ScalatraKernel,
     realm: String)
  extends BasicAuthStrategy[User](app, realm) {

  protected def validate(userName: String, password: String): Option[User] = {
    return User.login(userName, password)
  }

  protected def getUserId(user: User): String = user.userIdAsString
}
