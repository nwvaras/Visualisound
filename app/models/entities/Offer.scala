package models.entities


case class Offer(id: Long, offUserId: Long, offProductId: Long, offAmount:Long, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long ,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
