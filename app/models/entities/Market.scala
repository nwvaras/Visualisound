package models.entities

case class Market(id: Long, name: String, desc: String, override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
