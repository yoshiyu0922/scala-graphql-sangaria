package caches

import codemaster.HowToPay
import entities.Category
import modules.MasterCache

import javax.inject.Inject

class MasterCacheImpl @Inject()(masterCache: MasterCache) {

  def findAllCategories: List[Category] = masterCache.allCategories

  def howToPays = HowToPay.list
}
