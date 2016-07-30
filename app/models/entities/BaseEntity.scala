package models.entities

import java.sql.Timestamp

trait BaseEntity {
  val id : Long
  def isValid = true
  val createdAt : Timestamp
  val updatedAt : Timestamp
}