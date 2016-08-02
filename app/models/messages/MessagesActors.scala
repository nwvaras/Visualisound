package models.messages

/**
 * Created by Nicolas on 20-06-2016.
 */
object MessagesActors {
  trait Vote{
    def rut: String
    def senderId: String
    def eventId: Long
    def tuiId: String
  }
  trait MessageOk

  case class VoteWithoutFolio(rut: String,senderId: String, eventId:Long, tuiId: String = "0") extends Vote
  case class VoteWithFolio(rut: String,senderId: String, eventId:Long,folio: Long, tuiId: String = "0") extends Vote
  case class VoteRut(rut: String,senderId: String, eventId:Long,folio: Long)
  case class CanVote(tuiId: Long, name: String, lastName: String, career:String) extends MessageOk
  case class VoteReady() extends MessageOk
  trait vError{
    def error:String
  }
  case class VoteError(error:String) extends vError


}








