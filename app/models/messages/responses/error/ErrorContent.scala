package models.messages.responses.error

import models.messages.responses.Content
import play.api.libs.json.{JsValue, Json, Writes}

/**
 * Created by Nicolas on 26-06-2016.
 */
object ErrorContent{
  implicit def contentWrite: Writes[ErrorContent]= new Writes[ErrorContent] {
    override def writes(o:ErrorContent): JsValue  = Json.obj(
      "error" ->  o.body
    )
  }
}

case class ErrorContent(body: String) extends Content