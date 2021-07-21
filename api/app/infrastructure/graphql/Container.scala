package infrastructure.graphql

import core.cache.MasterCacheImpl
import domain.entity.User
import adapter.repository.AccountRepository
import usecase.UserUsecase

import scala.concurrent.Future

/**
  * query/mutationで実行するクラスを定義
  */
trait Container {
  def masterCache: MasterCacheImpl

  def auth: UserUsecase

  def accountRepo: AccountRepository

  def resolveUserByToken(token: Option[String]): Future[Option[User]]
}
