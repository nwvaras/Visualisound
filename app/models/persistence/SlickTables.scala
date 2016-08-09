package models.persistence

import models.entities.{Abbrev, Supplier}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

/**
  * The companion object.
  */
object SlickTables extends HasDatabaseConfig[JdbcProfile] {

  protected lazy val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import dbConfig.driver.api._

  abstract class BaseTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  }


  class SuppliersTable(tag: Tag) extends BaseTable[Supplier](tag, "suppliers") {
    def name = column[String]("name")
    def desc = column[String]("desc")
    def * = (id, name, desc) <> (Supplier.tupled, Supplier.unapply)
  }

  val suppliersTableQ : TableQuery[SuppliersTable] = TableQuery[SuppliersTable]

  class AbbrevTable(tag: Tag) extends BaseTable[Abbrev](tag, "ABBREV") {
    //def NDB_No = column[Option[String]]("NDB_No")
    def Shrt_Desc = column[Option[String]]("Shrt_Desc")
    def Water = column[Option[Double]]("Water_(g)")
    def Energ_Kcal = column[Option[Int]]("Energ_Kcal")
    def Protein = column[Option[Double]]("Protein_(g)")
    def Lipid_Tot = column[Option[Double]]("Lipid_Tot_(g)")
    // def Ash = column[Option[Double]]("Ash_(g)")
    def Carbohydrt = column[Option[Double]]("Carbohydrt_(g)")
    def Fiber_TD = column[Option[Double]]("Fiber_TD_(g)")
    def Sugar_Tot = column[Option[Double]]("Sugar_Tot_(g)")
    def Calcium = column[Option[Int]]("Calcium_(mg)")
    def Iron = column[Option[Double]]("Iron_(mg)")
    def Magnesium = column[Option[Double]]("Magnesium_(mg)")
    def Phosphorus = column[Option[Int]]("Phosphorus_(mg)")
    def Potassium = column[Option[Int]]("Potassium_(mg)")
    def Sodium = column[Option[Int]]("Sodium_(mg)")
    //  def Zinc = column[Option[Double]]("Zinc_(mg)")
    //   def Copper = column[Option[Double]]("Copper_mg)")
    //   def Manganese = column[Option[Double]]("Manganese_(mg)")
    //  def Selenium = column[Option[Double]]("Selenium_(picog)")
    def Vit_C = column[Option[Double]]("Vit_C_(mg)")
    //  def Thiamin = column[Option[Double]]("Thiamin_(mg)")
    //  def Riboflavin = column[Option[Double]]("Riboflavin_(mg)")
    // def Niacin = column[Option[Double]]("Niacin_(mg)")
    //  def Panto_Acid = column[Option[Double]]("Panto_Acid_mg)")
    def Vit_B6 = column[Option[Double]]("Vit_B6_(mg)")
    //   def Folate_Tot = column[Option[Double]]("Folate_Tot_(picog)")
    //   def Folic_Acid = column[Option[Double]]("Folic_Acid_(picog)")
    // def Food_Folate = column[Option[Double]]("Food_Folate_(picog)")
    //def Folate_DFE = column[Option[Double]]("Folate_DFE_(picog)")
    //  def Choline_Tot = column[Option[Double]]("Choline_Tot_ (mg)")
    //  def Vit_B12d = column[Option[Double]]("Vit_B12_(picog)")
    //  def Vit_A_IU = column[Option[Int]]("Vit_A_IU")
    //   def Vit_A_RAE = column[Option[Double]]("Vit_A_RAE")
    // def Retinol = column[Option[Double]]("Retinol_(picog)")
    // def Alpha_Carot = column[Option[Double]]("Alpha_Carot_(picog)")
    //def Beta_Carot= column[Option[Double]]("Beta_Carot_(picog)")
    // def Beta_Crypt = column[Option[Double]]("Beta_Crypt_(picog)")
    // def Lycopene = column[Option[Double]]("Lycopene_(picog)")
    //def LutZea = column[Option[Double]]("Lut+Zea_ (picog)")
    //   def Vit_E = column[Option[Double]]("Vit_E_(mg)")
    //def Vit_Dd = column[Option[Double]]("Vit_D_(picog)")
    //def Vit_D_IU = column[Option[Double]]("Vit_D_IU")
    ///  def Vit_Kd = column[Option[Double]]("Vit_K_(picog)")
    def FA_Sat = column[Option[Double]]("FA_Sat_(g)")
    def FA_Mono = column[Option[Double]]("FA_Mono_(g)")
    def FA_Poly = column[Option[Double]]("FA_Poly_(g)")
    def Cholestrl = column[Option[Int]]("Cholestrl_(mg)")

    def * = (Shrt_Desc,Water,Energ_Kcal,Protein,Lipid_Tot,Carbohydrt,Fiber_TD,Sugar_Tot,Calcium,Iron,Magnesium,Phosphorus,Potassium,Sodium,Vit_C,Vit_B6,FA_Sat,FA_Mono,FA_Poly,Cholestrl) <> (Abbrev.tupled, Abbrev.unapply)
  }
  val abbrevTableQ : TableQuery[AbbrevTable] = TableQuery[AbbrevTable]
}
