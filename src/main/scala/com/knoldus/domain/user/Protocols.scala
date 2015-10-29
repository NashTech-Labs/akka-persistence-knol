package com.knoldus.domain.user

import com.knoldus.domain.Event

object Protocols {
  sealed trait UserCommand {
    val id: String
  }

  case class CreateUser(id: String, firstName: String, lastName: String, email: String) extends UserCommand
  case class UserCreated(id: String, firstName: String, lastName: String, email: String) extends Event

  case class ChangeEmail(id: String, newEmail: String) extends UserCommand
  case class EmailChanged(id: String, newEmail: String) extends Event

  case object Print
}
