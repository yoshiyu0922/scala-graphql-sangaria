package graphql.schema

import dto.response.AuthTokenResponse
import graphql.Container
import sangria.schema._

import java.time.format.DateTimeFormatter

/**
  * mutationのスキーマ定義
  */
trait MutationType extends ArgType {
  private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  lazy val AuthType = ObjectType(
    name = "Auth",
    description = "ログイン認証",
    fields = fields[Container, AuthTokenResponse](
      Field(
        name = "token",
        fieldType = StringType,
        description = Some("トークン"),
        resolve = _.value.token
      )
    )
  )

  val Mutation = ObjectType(
    "Mutation",
    fields[Container, Unit](
      Field(
        name = "auth",
        fieldType = AuthType,
        description = Some("IDとパスワードで認証"),
        arguments = FrontUserIdArg :: PasswordArg :: Nil,
        resolve = ctx => ctx.ctx.auth.auth(ctx.arg(FrontUserIdArg), ctx.arg(PasswordArg))
      ),
    )
  )
}
