package controllers

import javax.inject._

import models.daos.AbstractBaseDAO
import models.entities.Supplier
import models.persistence.SlickTables.SuppliersTable
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketsController @Inject()(suppliersDAO : AbstractBaseDAO[SuppliersTable,Supplier])(implicit exec: ExecutionContext) extends Controller {



}
