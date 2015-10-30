package com.knoldus.domain

import java.util.UUID

import akka.actor.{ActorSystem, Props}
import com.knoldus.domain.user.Protocols.{ChangeEmail, CreateUser, Print}
import com.knoldus.domain.user.UserProcessor

object Boot extends App {
  val system = ActorSystem("user-management")
  val userProcessor = system.actorOf(Props[UserProcessor], "userProcessor")

  val userId = UUID.randomUUID.toString
  val createUser = CreateUser(userId, "Frodo", "Baggins", "frodo@example.com")

  userProcessor ! createUser
  userProcessor ! Print
  userProcessor ! ChangeEmail(userId, "baggins@example.com")
  userProcessor ! Print

  Thread.sleep(20 * 1000)
}
