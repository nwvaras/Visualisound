package controllers

import javax.inject._
import models.daos.{AbbrevDAO, AbstractBaseDAO, BaseDAO}
import models.entities.{Abbrev, Supplier}
import models.persistence.SlickTables.{AbbrevTable, SuppliersTable}
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import scala.concurrent.{Future, ExecutionContext}

@Singleton
class SuppliersController @Inject()(abbrevDAO : AbbrevDAO)(implicit exec: ExecutionContext) extends Controller {


  implicit val abbrevTotalWrites = new Writes[Abbrev] {
    def writes(abb: Abbrev) = Json.obj(
     // "NDB_No" ->abb.NDB_No,
      "name" ->abb.Shrt_Desc.get.toLowerCase(),
      "Water" ->abb.Water,
      "Energ_Kcal" ->abb.Energ_Kcal,
      "Protein" ->abb.Protein,
      "Lipid_Tot" ->abb.Lipid_Tot,
      //"Ash" ->abb.Ash,
      "Carbohydrt" ->abb.Carbohydrt,
      "Fiber_TD" ->abb.Fiber_TD,
      "Sugar_Tot" ->abb.Sugar_Tot,
      "Calcium" ->abb.Calcium,
      "Iron" ->abb.Iron,
      "Magnesium" ->abb.Magnesium,
      "Phosphorus" ->abb.Phosphorus,
      "Potassium" ->abb.Potassium,
      "Sodium" ->abb.Sodium,
      /*"Zinc" ->abb.Zinc,
      "Copper" ->abb.Copper,
      "Manganese" ->abb.Manganese,
      "Seleniumd" ->abb.Selenium,
      "Vit_C" ->abb.Vit_C,
      "Thiamin" ->abb.Thiamin,
      "Riboflavin" ->abb.Riboflavin,
      "Niacin" ->abb.Niacin,
      "Panto_Acid" ->abb.Panto_Acid,
      "Vit_B6" ->abb.Vit_B6,
      "Folate_Tot" ->abb.Folate_Tot,
      "Folic_Acid" ->abb.Folic_Acid,
      "Food_Folate" ->abb.Food_Folate,
      "Folate_DFE" ->abb.Folate_DFE,
      "Choline_Tot" ->abb.Choline_Tot,
      "Vit_B12d" ->abb.Vit_B12d,
      "Vit_A_IU" ->abb.Vit_A_IU,
      "Vit_A_RAE" ->abb.Vit_A_RAE,
      "Retinol" ->abb.Retinol,
      "Alpha_Carot" ->abb.Alpha_Carot,
      "Beta_Caro"->abb.Beta_Carot,
      "Beta_Crypt" ->abb.Beta_Crypt,
      "Lycopene" ->abb.Lycopene,
      "LutZea" ->abb.LutZea,
      "Vit_E" ->abb.Vit_E,
      "Vit_Dd" ->abb.Vit_Dd,
      "Vit_D_IU" ->abb.Vit_D_IU,
      "Vit_Kd" ->abb.Vit_Kd,*/
      "FA_Sat" ->abb.FA_Sat,
      "FA_Mono" ->abb.FA_Mono,
      "FA_Poly" ->abb.FA_Poly,
      "Cholestrl" ->abb.Cholestrl/*,
      "GmWt_1" ->abb.GmWt_1,
      "GmWt_Desc1" ->abb.GmWt_Desc1,
      "GmWt_2" ->abb.GmWt_2,
      "GmWt_Desc2" ->abb.GmWt_Desc2,
      "Refuse_Pct" ->abb.Refuse_Pct*/
    )
  }

  def getItemLike(str: String,limit:Int) ={
    implicit val abbrevNameWrite = new Writes[Abbrev] {
      def writes(abb: Abbrev) = Json.obj(
        "name" ->abb.Shrt_Desc.get.toLowerCase()
      )
    }
    implicit val abbrevSeq= new Writes[Seq[Abbrev]] {
     override def writes(abb: Seq[Abbrev]) = Json.toJson(abb.map(x=>Json.toJson(x)(abbrevNameWrite)))
    }
    Action.async{
    abbrevDAO.findByLikeness(str,limit).map{ rooms =>
      Ok(Json.toJson(rooms))
    }}
  }

  def getItem(name: String) = Action.async{
    abbrevDAO.findByName(name).map{ rooms =>
      Ok(Json.toJson(rooms))
    }}




}
