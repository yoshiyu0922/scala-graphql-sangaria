package graphql.schema

import codemaster.HowToPay
import entities._
import graphql.Container
import repositories.AccountSearchCondition
import sangria.schema._

import java.time.format.DateTimeFormatter

/**
  * queryのスキーマ定義
  */
trait QueryType extends ArgType {
  private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
  private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  lazy val UserType = ObjectType(
    name = "User",
    description = "ユーザー",
    fields = fields[Container, User](
      Field(
        name = "id",
        fieldType = LongType,
        description = Some("ユーザーID"),
        resolve = _.value.id.value
      ),
      Field(
        name = "frontUserId",
        fieldType = StringType,
        description = Some("表示用ユーザーID"),
        resolve = _.value.frontUserId
      ),
      Field(
        name = "name",
        fieldType = StringType,
        description = Some("ユーザー名"),
        resolve = _.value.name
      ),
      Field(
        name = "createdAt",
        fieldType = StringType,
        description = Some("作成日時"),
        resolve = _.value.createdAt.format(dateTimeFormatter)
      ),
      Field(
        name = "update",
        fieldType = StringType,
        description = Some("更新日時"),
        resolve = _.value.updatedAt.format(dateTimeFormatter)
      ),
      Field(
        name = "isDeleted",
        fieldType = BooleanType,
        description = Some("true: 削除済み, false: 削除済みでない"),
        resolve = _.value.isDeleted
      ),
      Field(
        name = "deletedAt",
        fieldType = OptionType(StringType),
        description = Some("削除日時"),
        resolve = _.value.deletedAt.map(_.format(dateTimeFormatter))
      )
    )
  )

  lazy val CategoryType = ObjectType(
    name = "Category",
    description = "カテゴリ",
    fields = fields[Container, Category](
      Field(
        name = "id",
        fieldType = LongType,
        description = Some("カテゴリID"),
        resolve = _.value.id.value
      ),
      Field(
        name = "userId",
        fieldType = LongType,
        description = Some("ユーザーID"),
        resolve = _.value.userId.value
      ),
      Field(
        name = "name",
        fieldType = StringType,
        description = Some("カテゴリ名"),
        resolve = _.value.name
      ),
      Field(
        name = "createdAt",
        fieldType = StringType,
        description = Some("作成日時"),
        resolve = _.value.createdAt.format(dateTimeFormatter)
      ),
      Field(
        name = "update",
        fieldType = StringType,
        description = Some("更新日時"),
        resolve = _.value.updatedAt.format(dateTimeFormatter)
      ),
      Field(
        name = "isDeleted",
        fieldType = BooleanType,
        description = Some("true: 削除済み, false: 削除済みでない"),
        resolve = _.value.isDeleted
      ),
      Field(
        name = "deletedAt",
        fieldType = OptionType(StringType),
        description = Some("削除日時"),
        resolve = _.value.deletedAt.map(_.format(dateTimeFormatter))
      )
    )
  )

  lazy val HowToPayType = ObjectType(
    name = "HowToPay",
    description = "支払い方法",
    fields = fields[Container, HowToPay](
      Field(
        name = "id",
        fieldType = IntType,
        description = Some("支払い方法ID"),
        resolve = _.value.id
      ),
      Field(
        name = "name",
        fieldType = StringType,
        description = Some("支払い方法名"),
        resolve = _.value.name
      )
    )
  )

  lazy val AccountType = ObjectType(
    name = "Account",
    description = "口座",
    fields = fields[Container, Account](
      Field(
        name = "id",
        fieldType = LongType,
        description = Some("口座ID"),
        resolve = _.value.id.value
      ),
      Field(
        name = "userId",
        fieldType = LongType,
        description = Some("ユーザーID"),
        resolve = _.value.userId.value
      ),
      Field(
        name = "name",
        fieldType = StringType,
        description = Some("口座名"),
        resolve = _.value.name
      ),
      Field(
        name = "balance",
        fieldType = IntType,
        description = Some("口座残高"),
        resolve = _.value.balance
      ),
      Field(
        name = "sortIndex",
        fieldType = IntType,
        description = Some("表示順"),
        resolve = _.value.sortIndex
      ),
      Field(
        name = "createdAt",
        fieldType = StringType,
        description = Some("作成日時"),
        resolve = _.value.createdAt.format(dateTimeFormatter)
      ),
      Field(
        name = "update",
        fieldType = StringType,
        description = Some("更新日時"),
        resolve = _.value.updatedAt.format(dateTimeFormatter)
      ),
      Field(
        name = "isDeleted",
        fieldType = BooleanType,
        description = Some("true: 削除済み, false: 削除済みでない"),
        resolve = _.value.isDeleted
      ),
      Field(
        name = "deletedAt",
        fieldType = OptionType(StringType),
        description = Some("削除日時"),
        resolve = _.value.deletedAt.map(_.format(dateTimeFormatter))
      )
    )
  )

  val Query = ObjectType(
    name = "Query",
    fields = fields[Container, Unit](
      Field(
        name = "user",
        fieldType = OptionType(UserType),
        description = Some("ログインユーザを取得"),
        arguments = TokenArg :: Nil,
        tags = Authorised :: Nil,
        resolve = ctx => ctx.ctx.resolveUserByToken(Some(ctx.arg(TokenArg)))
      ),
      Field(
        name = "categories",
        fieldType = ListType(CategoryType),
        description = Some("カテゴリを全て取得"),
        tags = Authorised :: Nil,
        resolve = ctx => ctx.ctx.masterCache.findAllCategories
      ),
      Field(
        name = "howToPays",
        fieldType = ListType(HowToPayType),
        description = Some("支払い方法を全て取得"),
        tags = Authorised :: Nil,
        resolve = ctx => ctx.ctx.masterCache.howToPays
      ),
      Field(
        name = "accounts",
        fieldType = ListType(AccountType),
        description = Some("口座を検索"),
        tags = Authorised :: Nil,
        arguments = UserIdArg :: AccountIdOptArg :: Nil,
        resolve = ctx => ctx.ctx.accountRepo.search(AccountSearchCondition(
          userId = Id[User](ctx.arg(UserIdArg)),
          accountId = ctx.arg(AccountIdOptArg).map(Id[Account])
        ))
      ),
    )
  )

}
