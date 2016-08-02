package models.entities


case class Offer(id: Long, offUserId: Long, offProductId: Long, offAmount:Long, wantedUserId: Long, wantedProductId: Long ,wantedAmount: Long) extends BaseEntity
