package graphql

import caches.MasterCacheImpl
import entities.User
import repositories.AccountRepository
import services.UserService

import scala.concurrent.Future

/**
  * query/mutationで実行するクラスを定義
  */
trait Container {
  def masterCache: MasterCacheImpl

  def auth: UserService

  def accountRepo: AccountRepository

  def resolveUserByToken(token: Option[String]): Future[Option[User]]
}
