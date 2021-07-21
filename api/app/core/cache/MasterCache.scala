package core.cache

import domain.entity.{Category, Id}

trait MasterCache {

  def initialize(): Unit

  def allCategories: List[Category]

  def findCategoryById(categoryId: Id[Category]): List[Category]
}
