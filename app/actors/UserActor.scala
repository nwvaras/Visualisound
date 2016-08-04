package actors


import akka.actor._
import actors.messages.MessagesActor.{Petition, TakeOffer}
import models.daos.{MultipleDAO, TransactionDAO, ProductDAO, OfferDAO}
import models.entities._

object UserActor{
  def props = Props[UserActor]
  def props(market: ActorRef,userId: Long,offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO) =
    Props(classOf[UserActor],market,userId,offerDAO,productDAO,transactionDAO)
}
class UserActor(market: ActorRef,userId: Long, offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO, multipleDAO: MultipleDAO) extends Actor{
  import context._
  println(self.path)


  def getOffer(offerId: Long) : Option[Offer]


  def processOffer(o: Offer) ={
    val product = getUserProduct(o.offProductId)
    if(product.productQuantity >= o.offAmount){
        sender ! Petition(userId,o.offProductId,o)

    }

  }

  def getUserProduct(productId: Long): Product


  def processPetition(p: Petition) ={
    val product= getUserProduct(p.productId)
    val otherProduct = getUserProduct(p.otherProductId)
    if(product.productQuantity >= p.amount){
            product.productQuantity-= p.amount
            otherProduct.productQuantity -= p.offer.offAmount
            val newTransaction = Transaction(0,"test",p.userId,p.offer.offProductId,p.offer.offAmount,0.0,this.userId,p.offer.wantedProductId,p.offer.wantedAmount,0.0,null,null)
            multipleDAO.completeTransaction(product,otherProduct,newTransaction,p.offer)
            /*val dbAction = (
              for {
                product1 <- productDAO.update(product)
                product2 <- productDAO.update(otherProduct)
                offer <- offerDAO.delete(p.offer)
                transaction <- transactionDAO.insert(newTransaction)
              } yield()
              ).transactionally

            val resultFuture = db run dbAction*/


    }

  }

  def receive = {
    case tOffer:TakeOffer => {
     val offer = getOffer(tOffer.offerId)
      offer match{
        case Some(o) =>
          sender ! processOffer(o)
        case None => sender ! "No existe oferta"
      }



    }

  }

}