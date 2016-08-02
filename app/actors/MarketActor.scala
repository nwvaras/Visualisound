package actors

import akka.actor._


class MarketActor( ) extends Actor{

  println(self.path)

  def receive = {
    case _ => {
      println("jalp")

    }

  }

}