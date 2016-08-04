package actors.messages

import akka.actor.ActorRef
import models.entities.Offer

/**
 * Created by Nicolas on 01-08-2016.
 */
object MessagesActor {
  case class TakeOffer(userId: Long, offerId: Long)
  case class OffQuantityOK(actorRef: ActorRef,productInterchange: TakeOffer)
  case class WantedQuantityOK(actorRef: ActorRef,productInterchange: TakeOffer)
  case class TransactionSuccessfully()
  case class TransactionError()
  case class Petition(userId: Long,otherProductId: Long, offer: Offer){
    val amount = offer.wantedAmount
    val productId = offer.wantedProductId
  }
  case class GetAllOffers()

}
