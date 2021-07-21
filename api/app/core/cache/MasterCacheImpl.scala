package core.cache

import core.codemaster.HowToPay
import domain.entity.Category
import core.module.MasterCache

import javax.inject.Inject

class MasterCacheImpl @Inject()(masterCache: MasterCache) {

  def findAllCategories: List[Category] = masterCache.allCategories

  def howToPays = HowToPay.list
}
