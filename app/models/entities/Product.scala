package models.entities

case class Product(id: Long, userId: Long, productTypeId: Long, productQuantity :Long, productConstant: Double, productExponential: Double) extends BaseEntity
