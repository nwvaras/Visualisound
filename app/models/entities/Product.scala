package models.entities

case class Product(id: Long, userId: Long, productTypeId: Long, productQuantity :Long, productConstant: Double, productExponential: Double,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
