package models.entities

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Calendar

import play.api.libs.json.JsValue

trait BaseEntity {
  val nov = Calendar.getInstance()
  val id : Long
  def isValid = true
  //def toJson:JsValu
  val createdAt = Timestamp.valueOf(LocalDateTime.now())
  val updatedAt = Timestamp.valueOf(LocalDateTime.now())
}