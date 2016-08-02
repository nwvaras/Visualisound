package models.messages.responses

/**
 * Created by Nicolas on 26-06-2016.
 */

trait Response {
  def code: Int
  def content: Content

}
