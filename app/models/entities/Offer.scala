package models.entities

case class Offer(id: Long, offGood: Long, offAmount: Long, wantedGood: Long, wantedAmount: Long) extends BaseEntity
