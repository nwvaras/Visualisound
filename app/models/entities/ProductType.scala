package models.entities

case class ProductType(id: Long, name: String,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
