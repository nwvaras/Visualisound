package controllers

import javax.inject._

import models.daos.OfferDAO.ProductDAO
import models.daos._
import models.entities.Supplier
import models.persistence.SlickTables.SuppliersTable
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketsController @Inject()(userDAO: UserDAO,marketDAO: MarketDAO,offerDAO: OfferDAO,productDAO: ProductDAO, productTypeDAO: ProductTypeDAO, transactionDAO: TransactionDAO)(implicit exec: ExecutionContext) extends Controller {



}
