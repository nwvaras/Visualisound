package models.entities


/**
 *
 * @param id
 * @param username
 * @param mail
 * @param password
 * @param userType
 * @param marketId
 */
case class User(id: Long, username: String, mail: String, password: String, userType: String,  marketId : Long,override val createdAt: java.sql.Timestamp,override val updatedAt: java.sql.Timestamp) extends BaseEntity
