package models.entities

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Calendar

trait BaseEntity {
  val nov = Calendar.getInstance()
  val id : Long
  def isValid = true
  val createdAt = Timestamp.valueOf(LocalDateTime.now())
  val updatedAt = Timestamp.valueOf(LocalDateTime.now())
}