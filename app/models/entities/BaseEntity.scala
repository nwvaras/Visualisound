package models.entities

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Calendar

import play.api.libs.json.JsValue

trait BaseEntity {
  val id : Long
  def isValid = true
  val created: java.sql.Timestamp
}