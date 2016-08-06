package controllers

import javax.inject._
import models.daos._
import models.entities.{User, Market, Supplier}
import models.persistence.SlickTables.SuppliersTable
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import scala.concurrent.{Future, ExecutionContext}

@Singleton
class SuppliersController @Inject()(marketDAO: MarketDAO,userDAO: UserDAO,multipleDAO: MultipleDAO,suppliersDAO : AbstractBaseDAO[SuppliersTable,Supplier])(implicit exec: ExecutionContext) extends Controller {

  implicit val supplierWrites = new Writes[Supplier] {
    def writes(sup: Supplier) = Json.obj(
      "id" -> sup.id,
      "name" -> sup.name,
      "desc" -> sup.desc
    )
  }

  def supplier(id : Long) = Action.async {
    suppliersDAO.findById(id) map { sup => sup.fold(NoContent)(sup => Ok(Json.toJson(sup))) }
  }
  def test ()=Action.async{
    multipleDAO.holi  map { re => Ok( re.toString())}
  }
  def test2=Action.async{
    multipleDAO.holu  map { re => Ok( re.toString())}
  }
  def addTestUser =Action.async{
    userDAO.insert(User(0,"a","a","a","a",0))  map { re => Ok( re.toString())}
  }
  def addTestMarket = Action.async{
    marketDAO.insert(Market(0,"a","a"))  map { re => Ok( re.toString())}
  }
  def getTestUser =Action.async{
    userDAO.all  map { re => Ok( re.toString())}
  }
  def run= Action{ Ok(multipleDAO.run)}
  def drop= Action{Ok(multipleDAO.drop)}
  def test3 = Action.async{ multipleDAO.test3  map { re => Ok( re.toString())}}
  def insertSupplier = Action.async(parse.json) {
    request => {
      {
        for {
          name <- (request.body \ "name").asOpt[String]
          desc <- (request.body \ "desc").asOpt[String]

        } yield {
          suppliersDAO.insert(Supplier(0, name, desc)) map { n => Ok("Id of Supplier Added : " + n) }
        }
      }.getOrElse(Future{BadRequest("Wrong json format")})
    }
  }

}
