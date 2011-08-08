import java.util.regex.Pattern
import net.liftweb.mongodb.record._
import net.liftweb.mongodb.record.field._
import net.liftweb.record.field._
import net.liftweb.record._
import org.bson.types._
import org.joda.time.{DateTime, DateTimeZone}
import net.liftweb.mongodb.record.MongoRecord
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST.JObject


class User extends MongoRecord[User] with MongoId[User] {
  def meta = User

  object username extends StringField(this, 200)
  object password extends StringField(this, 200)

  def userIdAsString: String = id.toString

  def login(u: String, p: String): Option[User] = {
    val user = User.findAll(("username" -> u), ("password" -> p))
	Some(user.first)
  }
}
object User extends User with MongoMetaRecord[User] {
}
