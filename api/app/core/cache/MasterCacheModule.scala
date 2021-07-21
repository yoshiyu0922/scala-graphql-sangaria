package core.cache

import com.google.inject._
import domain.entity._
import core.module.MasterCache
import adapter.repository._
import scalikejdbc.config.DBs

import javax.inject.Singleton
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class MasterCacheModule @Inject()(
  val categoryRepository: CategoryRepository,
) extends MasterCache {
  private var categories: List[Category] = Nil

  override def initialize(): Unit = {
    DBs.setupAll()
    import scala.concurrent.ExecutionContext.Implicits.global

    val process = categoryRepository
      .findAll()
      .map(c => categories = c)
      .recover {
        case e: Throwable => throw e;
      }
    Await.ready(process, Duration.Inf)
    DBs.closeAll()
  }

  override def allCategories: List[Category] = categories

  override def findCategoryById(categoryId: Id[Category]): List[Category] =
    categories.filter(_.id == categoryId)

  initialize()
}
