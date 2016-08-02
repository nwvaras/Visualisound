package models.messages.responses.ok

import play.api.libs.json.{JsValue, Json, Writes}
import models.messages.responses.Response

/**
 * Created by Nicolas on 26-06-2016.
 */
object OKResponse{
  implicit def responseWrite: Writes[OKResponse]= new Writes[OKResponse] {
    override def writes(o:OKResponse): JsValue  = Json.obj(
      "code" ->  o.code,
      "content" -> Json.toJson(o.content)
    )
  }
}

case class OKResponse(content: OKContent) extends Response{
  def code= 200

}
