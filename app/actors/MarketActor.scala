package actors

import akka.actor._
import models.daos._
import models.entities._
object MarketActor{
  def props = Props[MarketActor]
  def props(marketId: Long,name: String,userDAO: UserDAO,offerDAO: OfferDAO,productDAO: ProductDAO,transactionDAO: TransactionDAO,multipleDAO: MultipleDAO) =
    Props(classOf[MarketActor],marketId,name,userDAO,offerDAO,productDAO,transactionDAO,multipleDAO)
}
class MarketActor(marketId: Long,name: String,userDAO: UserDAO,offerDAO:OfferDAO,productDAO:ProductDAO,transactionDAO:TransactionDAO,multipleDAO: MultipleDAO) extends Actor{

  import context._
  import Actor._
  println(self.path)
  val initUsers ={
    println("init busqueda de actors hijos")
    userDAO.byMarket(marketId).onSuccess{
      case users:Seq[User] =>
        println("users encontrados")
        users.foreach( user=>  context.actorOf(UserActor.props(user.id,offerDAO,productDAO,transactionDAO,multipleDAO),user.id.toString))

      case _ =>
        println("no encontre actores :C")

    }

  }
  def receive = {
    case v:Market => {

    }

  }

}