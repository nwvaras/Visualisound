package models.entities

case class Transaction(id: Long, desc:String, offUserId: Long, offProductId: Long, offAmount:Long, offMarginal: Double, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long, wantedMarginal: Double,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
