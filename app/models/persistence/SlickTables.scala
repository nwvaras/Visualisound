package models.persistence

import models.entities._
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import java.sql.Timestamp
import slick.profile.SqlProfile.ColumnOption.SqlType
/**
  * The companion object.
  */
object SlickTables extends HasDatabaseConfig[JdbcProfile] {

  protected lazy val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import dbConfig.driver.api._
  abstract class BaseTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def createdAt = column[Timestamp]("created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
    def updatedAt = column[Timestamp]("updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  }

  case class SimpleSupplier(name: String, desc: String)

  class SuppliersTable(tag: Tag) extends BaseTable[Supplier](tag, "suppliers") {
    def name = column[String]("name")
    def desc = column[String]("desc")
    def * = (id, name, desc) <> (Supplier.tupled, Supplier.unapply)
  }
  val suppliersTableQ : TableQuery[SuppliersTable] = TableQuery[SuppliersTable]
//////////////////////////////////////////////////////////////
  class UsersTable(tag: Tag) extends BaseTable[User](tag, "users") {
    def username = column[String]("username")
    def mail = column[String]("mail")
    def password = column[String]("password")
    def usertype = column[String]("usertype")
    def market = foreignKey("market_k", marketId, marketsTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def marketId = column[Long]("market_id")

    def * = (id, username, mail,password,usertype,marketId,createdAt,updatedAt) <> (User.tupled, User.unapply)
  }
  val usersTableQ : TableQuery[UsersTable] = TableQuery[UsersTable]

/////////////////////////////////////////////////////////////////
  class OffersTable(tag: Tag) extends BaseTable[Offer](tag, "offers") {
    def offUserId = column[Long]("off_user_id")
    def offUser = foreignKey("wanted_user_k", offUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def offGoodId = column[Long]("off_good_id")
    //def offGood = foreignKey("wanted_user_k", wantedUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def offAmount = column[Int]("off_amount")
    def wantedUserId = column[Long]("wanted_user_id")
    def wantedUser = foreignKey("wanted_user_k", wantedUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def wantedGoodId = column[Long]("wanted_good_id")
    //def wantedUser = foreignKey("wanted_user_k", wantedUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def wantedAmount = column[Int]("wanted_amount")

    def * = (id, offUserId,offGoodId, offAmount,wantedUserId, wantedGoodId, wantedAmount,createdAt,updatedAt) <> (Offer.tupled, Offer.unapply)
  }
  val offersTableQ : TableQuery[OffersTable] = TableQuery[OffersTable]
////////////////////////////////////////////////////////////////
  class MarketsTable(tag: Tag) extends BaseTable[Market](tag, "markets") {
    def name= column[String]("name")
    def desc = column[String]("desc")
    def * = (id,name, desc,createdAt,updatedAt) <> (Market.tupled, Market.unapply)
  }
  val marketsTableQ : TableQuery[MarketsTable] = TableQuery[MarketsTable]
///////////////////////////////////////////////////////////////////
  class TransactionsTable(tag: Tag) extends BaseTable[Transaction](tag, "transactions") {
    def desc = column[String]("desc")
    def offUserId = column[Long]("off_user_id")
    def offUser = foreignKey("wanted_user_k", offUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

    def offGoodId = column[Long]("off_good_id")
    //

    def offAmount = column[Int]("off_amount")
    def offMarginal = column[Long]("off_marginal")
    def wantedUserId = column[Long]("wanted_user_id")
    def wantedUser = foreignKey("wanted_user_k", wantedUserId, usersTableQ)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def wantedGoodId = column[Long]("wanted_good_id")
  //
    def wantedAmount = column[Int]("wanted_amount")
    def wantedMarginal = column[Long]("wanted_marginal")
    def * = (id, desc,offUserId, offGoodId, offAmount,wantedUserId, wantedGoodId,wantedAmount,wantedMarginal,createdAt,updatedAt) <> (Transaction.tupled, Transaction.unapply)
  }
  val transactionsTableQ : TableQuery[TransactionsTable] = TableQuery[TransactionsTable]
}
