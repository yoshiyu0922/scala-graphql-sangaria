package domain.entity

import java.time.ZonedDateTime

case class Category(
  id: Id[Category],
  userId: Id[User],
  name: String,
  createdAt: ZonedDateTime,
  updatedAt: ZonedDateTime,
  isDeleted: Boolean,
  deletedAt: Option[ZonedDateTime]
)
