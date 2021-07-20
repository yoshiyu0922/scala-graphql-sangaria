package modules

import entities._

trait MasterCache {

  def initialize(): Unit

  def allCategories: List[Category]

  def findCategoryById(categoryId: Id[Category]): List[Category]
}
