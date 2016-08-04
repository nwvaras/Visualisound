package controllers

import javax.inject._


import models.daos._
import models.entities.{Market, Supplier}
import models.persistence.SlickTables.SuppliersTable
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketsController @Inject()(userDAO: UserDAO,marketDAO: MarketDAO,offerDAO: OfferDAO,productDAO: ProductDAO, productTypeDAO: ProductTypeDAO, transactionDAO: TransactionDAO)(implicit exec: ExecutionContext) extends Controller {



}
