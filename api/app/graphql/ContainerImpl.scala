package graphql

import caches.{MasterCacheImpl, MasterCacheModule}
import entities.{Id, User}
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pdi.jwt.JwtJson
import play.api.libs.json.JsObject
import repositories._
import services.UserService
import utils.TryUtil._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

/**
  * query/mutationで実行するクラスをインスタンス化するクラス（実装クラス）
  *
  * @param masterCacheModule MasterCacheModule
  * @param userRepository UserRepository
  * @param accountRepository AccountRepository
  * @param bCrypt BCryptPasswordEncoder
  * @param ec ExecutionContext
  */
class ContainerImpl @Inject()()(
  masterCacheModule: MasterCacheModule,
  userRepository: UserRepository,
  accountRepository: AccountRepository,
  bCrypt: BCryptPasswordEncoder
)(
  implicit val ec: ExecutionContext
) extends Container {

  override def masterCache = new MasterCacheImpl(masterCacheModule)

  override def auth = new UserService(userRepository, bCrypt)

  override def accountRepo = new AccountRepository()

  override def resolveUserByToken(tokenOpt: Option[String]): Future[Option[User]] =
    tokenOpt match {
      case Some(token) =>
        val value = token.replaceAll("Bearer ", "")

        JwtJson
          .decodeJson(value)
          .toFuture
          .map(authorize)
      case None => Future.successful(None)
    }

  private def authorize(json: JsObject): Option[User] = {
    val userId =
      json.value
        .get("id")
        .ensuring(_.isDefined, "userId is empty")
        .get("value")

    userRepository.resolveById(Id[User](userId.toString().toLong))
  }
}
