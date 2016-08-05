package actors


import akka.actor._
import actors.messages.MessagesActor._
import models.daos.{MultipleDAO, TransactionDAO, ProductDAO, OfferDAO}
import models.entities._
import akka.actor.Props
import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.{Future, Await}

object UserActor{
  def props = Props[UserActor]
  def props(market: ActorRef,userId: Long,offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO) =
    Props(classOf[UserActor],market,userId,offerDAO,productDAO,transactionDAO)
}
class UserActor(market: ActorRef,userId: Long, offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO, multipleDAO: MultipleDAO) extends Actor with Stash{
  import context._
  println(self.path)
  var offerSender: (Long,ActorRef) = (0,null)


  def getOffer(offerId: Long):Future[Option[Offer]] ={
    offerDAO.byId(offerId).flatMap{
      case x => Future(x)
    }
  }
  def processOffer(o: Offer) ={
    val product = Await.result(getUserProduct(o.offProductId),1000 milli)
    if(product.productQuantity >= o.offAmount){
        Petition(userId,o.offProductId,o)

    }

  }

  def getUserProduct(id: Long)={ productDAO.byId(id).flatMap{
    case Some(room) => Future(room)

    case _ => Future(null)
  }
  }


  def processPetition(p: Petition) ={
    val product= Await.result(getUserProduct(p.productId),1000 milli)
    val otherProduct = Await.result(getUserProduct(p.otherProductId),1000 milli)
    if(product.productQuantity >= p.amount){
            val product1 = product.copy(productQuantity = product.productQuantity - p.amount)
            val product2 = otherProduct.copy(productQuantity = otherProduct.productQuantity - p.offer.offAmount)
            val newTransaction = Transaction(0,"test",p.userId,p.offer.offProductId,p.offer.offAmount,0.0,this.userId,p.offer.wantedProductId,p.offer.wantedAmount,0.0,null,null)
            multipleDAO.completeTransaction(product1,product2,newTransaction,p.offer)
      "yep?"
    }
    else{
      "nope"
    }

  }

  def matchThis(msg: Any) = msg match{
    case _ => "TODO"
    /*case CreateOffer =>
    case DeleteOffer =>
    case GetBienestar =>
    case GetUtilidadMarginal =>*/
  }

  def receive = {
    case tOffer:TakeOffer =>
    {
       val offer = Await.result(getOffer(tOffer.offerId),1000 milli)
      offer match{
        case Some(o) => //Existia esta oferta
          offerSender = (o.wantedUserId,sender)
          become(waitingResponse)
          system.scheduler.scheduleOnce(5000 milliseconds) {
            self ! TimeOutMsg
          }
          sender ! processOffer(o)
        case None => sender ! "No existe oferta"

      }
    }
    case p:Petition =>
      sender ! processPetition(p)

    case msg =>
      matchThis(msg)



  }

  def waitingResponse: Receive ={
    case TakeOffer => //Estoy procesando una oferta, por lo tanto no puedo recibir mensajes
      stash()
    case Petition => //Idem anterior
      stash()
    case v:TransactionCompleted => //Me llego un mensaje diciendo que la transaccion se termino de forma correcta, por lo tanto proceso los mensajes anteriores de ofertas
      if(v.userId == offerSender._1){
        unstashAll()
        unbecome()
        offerSender._2 ! "Transaccion completada"

      }
      else{
        context.parent ! "error transaction completd?"
      }
    case d:DeadUser => //Usuario murio, proceso los mensajes anteriores
      if(d.userId == offerSender._1){
        unstashAll()
        unbecome()
        offerSender._2 ! "Error"
      }
      else{
        context.parent ! "error deadleter?"
      }
    case TimeOutMsg =>
      unstashAll()
      unbecome()
      offerSender._2 ! "TimeOut"

    case msg =>
      matchThis(msg)
  }


}