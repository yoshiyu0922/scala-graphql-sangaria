package domain.entity

import java.time.ZonedDateTime

import play.api.libs.json._

case class User(
  id: Id[User],
  frontUserId: String,
  name: String,
  password: String,
  createdAt: ZonedDateTime,
  updatedAt: ZonedDateTime,
  isDeleted: Boolean,
  deletedAt: Option[ZonedDateTime]
) {}

object User {
  implicit val write: OWrites[User] = Json.writes[User]
}
