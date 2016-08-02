package models.messages.responses.ok

import models.entities.BaseEntity
import models.messages.responses.Content
import play.api.libs.json.{JsValue, Json, Writes}

/**
 * Created by Nicolas on 26-06-2016.
 */

object OKContent {
  implicit def contentWrite: Writes[OKContent] = new Writes[OKContent] {
    override def writes(o: OKContent): JsValue = {
      Json.toJson(
        o.body.map { t =>
          Map("entity" -> t.toJson)
        }
      )
    }
  }
}

case class OKContent(body: Seq[BaseEntity]) extends Content