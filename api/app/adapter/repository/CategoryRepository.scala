package adapter.repository

import domain.entity.{Category, User}
import adapter.repository.ScalikeJDBCUtils._
import scalikejdbc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

object CategoryRepository extends SQLSyntaxSupport[Category] {
  override val tableName = "categories"

  def apply(s: SyntaxProvider[Category])(rs: WrappedResultSet): Category =
    apply(s.resultName)(rs)

  def apply(pc: ResultName[Category])(rs: WrappedResultSet): Category =
    Category(
      id = rs.toId[Category](pc.id),
      userId = rs.toId[User](pc.userId),
      name = rs.string(pc.name),
      createdAt = rs.zonedDateTime(pc.createdAt),
      updatedAt = rs.zonedDateTime(pc.updatedAt),
      isDeleted = rs.boolean(pc.isDeleted),
      deletedAt = rs.zonedDateTimeOpt(pc.deletedAt)
    )
}

trait CategoryRepository extends SQLSyntaxSupport[Category] {
  override val tableName = "categories"

  def findAll()(implicit s: DBSession = autoSession): Future[List[Category]]
}

@Singleton
class CategoryRepositoryImpl @Inject()()(implicit val ec: ExecutionContext)
    extends SQLSyntaxSupport[Category]
    with CategoryRepository {
  private val pc = syntax("pc")

  /**
    * カテゴリを全て取得する
    *
    * @param s DBSession
    * @return List[Category]
    */
  override def findAll()(implicit s: DBSession = autoSession): Future[List[Category]] =
    Future {
      withSQL {
        select(
          pc.result.id,
          pc.result.userId,
          pc.result.name,
          pc.result.createdAt,
          pc.result.updatedAt,
          pc.result.isDeleted,
          pc.result.deletedAt
        ).from(CategoryRepository as pc)
      }.map(CategoryRepository(pc)).list.apply()
    }
}
