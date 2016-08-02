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
case class User(id: Long, username: String, mail: String, password: String, userType: String,  marketId : Long) extends BaseEntity
