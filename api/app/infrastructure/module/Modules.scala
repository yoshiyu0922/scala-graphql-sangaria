package infrastructure.module

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
  }
}
