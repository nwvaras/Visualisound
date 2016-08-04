package actors

import akka.actor._
import models.entities.Market

class MarketActor( ) extends Actor{

  println(self.path)

  def receive = {
    case v:Market => {

    }

  }

}