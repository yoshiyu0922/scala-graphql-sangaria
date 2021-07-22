package infrastructure.graphql.schema

import sangria.schema.{Argument, BooleanType, IntType, LongType, OptionInputType, StringType}

/**
  * graphqlのリクエストパラメータの定義
  */
trait ArgType {

  val TokenArg = Argument(name = "token", argumentType = StringType, description = "token")
  val UserIdArg = Argument(name = "userId", argumentType = LongType, description = "user id")
  val FrontUserIdArg =
    Argument(name = "userId", argumentType = StringType, description = "front user id")
  val PasswordArg = Argument(name = "password", argumentType = StringType, description = "password")
  val AccountIdOptArg = Argument(
    name = "account_id",
    argumentType = OptionInputType(LongType),
    description = "account id (optional)"
  )
}
