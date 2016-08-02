package models.entities

case class Transaction(id: Long, offUserId: Long, offProductId: Long, offAmount:Long, offMarginal: Double, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long, wantedMarginal: Double) extends BaseEntity
