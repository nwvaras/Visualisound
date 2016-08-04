package models.entities
case class Market(id: Long, name: String, desc: String, override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
case class Offer(id: Long, marketId: Long, offProductId: Long, offAmount:Long, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long ,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
case class Product(id: Long, userId: Long, productTypeId: Long, productQuantity :Long, productConstant: Double, productExponential: Double,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
case class ProductType(id: Long, name: String,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
case class Supplier(id: Long, name: String, desc: String  ) extends BaseEntity
case class User(id: Long, username: String, mail: String, password: String, userType: String,  marketId : Long,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
case class Transaction(id: Long, desc: String,offUserId: Long, offProductId: Long, offAmount:Long, offMarginal: Double, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long, wantedMarginal: Double,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
