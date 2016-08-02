package actors.messages

import akka.actor.ActorRef

/**
 * Created by Nicolas on 01-08-2016.
 */
object MessagesActor {
  case class ProductInterchange(offUser: Long, offQuantity: Long, offProductType: Long, wantedUser: Long, wantedQuantity: Long, wantedProductType: Long )
  case class OffQuantityOK(actorRef: ActorRef,productInterchange: ProductInterchange)
  case class WantedQuantityOK(actorRef: ActorRef,productInterchange: ProductInterchange)
  case class TransactionSuccessfully()
  case class TransactionError()

}
