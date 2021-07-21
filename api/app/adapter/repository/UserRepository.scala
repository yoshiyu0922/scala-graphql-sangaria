package adapter.repository

import domain.entity.{Id, User}
import javax.inject.{Inject, Singleton}
import adapter.repository.ScalikeJDBCUtils._
import scalikejdbc._

object UserRepository extends SQLSyntaxSupport[User] {
  override val tableName = "users"

  def apply(s: SyntaxProvider[User])(rs: WrappedResultSet): User =
    apply(s.resultName)(rs)

  def apply(u: ResultName[User])(rs: WrappedResultSet): User =
    User(
      id = rs.toId[User](u.id),
      frontUserId = rs.string(u.frontUserId),
      name = rs.string(u.name),
      password = rs.string(u.password),
      createdAt = rs.zonedDateTime(u.createdAt),
      updatedAt = rs.zonedDateTime(u.updatedAt),
      isDeleted = rs.boolean(u.isDeleted),
      deletedAt = rs.zonedDateTimeOpt(u.deletedAt)
    )
}

trait UserRepository extends SQLSyntaxSupport[User] {
  override val tableName = "users"

  def auth(frontUserId: String, password: String)(implicit s: DBSession = autoSession): Option[User]

  def resolveById(userId: Id[User])(implicit s: DBSession = autoSession): Option[User]
}

@Singleton
class UserRepositoryImpl @Inject()() extends UserRepository {
  private val u = syntax("u")

  /**
    * ユーザIDとパスワードに紐づくUserを取得
    *
    * @param frontUserId ユーザーID（表示用）
    * @param password パスワード
    * @param s DBSession
    * @return Option[User]
    */
  override def auth(
    frontUserId: String,
    password: String
  )(implicit s: DBSession = autoSession): Option[User] =
    withSQL {
      select(
        u.result.id,
        u.result.frontUserId,
        u.result.password,
        u.result.name,
        u.result.createdAt,
        u.result.updatedAt,
        u.result.isDeleted,
        u.result.deletedAt
      ).from(UserRepository as u)
        .where
        .eq(u.frontUserId, frontUserId)
    }.map(UserRepository(u)).single.apply()

  /**
    * ユーザIDに紐づくUserを取得
    *
    * @param userId ユーザーID
    * @param s DBSession
    * @return Option[User]
    */
  override def resolveById(userId: Id[User])(implicit s: DBSession = autoSession): Option[User] =
    withSQL {
      select(
        u.result.id,
        u.result.frontUserId,
        u.result.password,
        u.result.name,
        u.result.createdAt,
        u.result.updatedAt,
        u.result.isDeleted,
        u.result.deletedAt
      ).from(UserRepository as u)
        .where
        .eq(u.id, userId.value)
    }.map(UserRepository(u)).single.apply()
}
