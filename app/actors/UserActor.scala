package actors


import akka.actor._
import actors.messages.MessagesActor.{GetAllOffers, Petition, TakeOffer}
import models.daos.{MultipleDAO, TransactionDAO, ProductDAO, OfferDAO}
import models.entities._
import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.{Future, Await}

object UserActor{
  def props = Props[UserActor]
  def props(market: ActorRef,userId: Long,offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO) =
    Props(classOf[UserActor],market,userId,offerDAO,productDAO,transactionDAO)
}
class UserActor(market: ActorRef,userId: Long, offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO, multipleDAO: MultipleDAO) extends Actor{
  import context._
  println(self.path)


  def getOffer(offerId: Long):Future[Option[Offer]] ={
    offerDAO.byId(offerId).flatMap{
      case x => Future(x)
    }
  }
  def processOffer(o: Offer) ={
    val product = Await.result(getUserProduct(o.offProductId),1000 milli)
    if(product.productQuantity >= o.offAmount){
        sender ! Petition(userId,o.offProductId,o)

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
    }

  }

  def receive = {
    case tOffer:TakeOffer =>
    {
       val offer = Await.result(getOffer(tOffer.offerId),1000 milli)
        offer match{
          case Some(o) =>
            sender ! processOffer(o)
          case None => sender ! "No existe oferta"
        }

    }

  }

}