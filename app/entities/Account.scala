package entities

import java.time.ZonedDateTime

case class Account(
  id: Id[Account],
  userId: Id[User],
  name: String,
  balance: Int,
  sortIndex: Int,
  createdAt: ZonedDateTime,
  updatedAt: ZonedDateTime,
  isDeleted: Boolean,
  deletedAt: Option[ZonedDateTime]
)
