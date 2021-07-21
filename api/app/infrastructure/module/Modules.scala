package infrastructure.module

import adapter.repository.{
  AccountRepository,
  AccountRepositoryImpl,
  CategoryRepository,
  CategoryRepositoryImpl,
  UserRepository,
  UserRepositoryImpl
}
import core.cache.{MasterCache, MasterCacheModule}
import com.google.inject.AbstractModule
import infrastructure.graphql.{Container, ContainerImpl}

/**
  * 実装クラスへbindする
  */
class Modules extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[MasterCache]).to(classOf[MasterCacheModule]).asEagerSingleton()
    bind(classOf[Container]).to(classOf[ContainerImpl]).asEagerSingleton()
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl]).asEagerSingleton()
    bind(classOf[CategoryRepository]).to(classOf[CategoryRepositoryImpl]).asEagerSingleton()
    bind(classOf[AccountRepository]).to(classOf[AccountRepositoryImpl]).asEagerSingleton()
  }
}
