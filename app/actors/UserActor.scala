package actors

import akka.actor._
import play.api.libs.json.Json

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


class UserActor( ) extends Actor{
  import context._

  println(self.path)

  def receive = {
    case _ => {
      println("jalp")

    }

  }

}