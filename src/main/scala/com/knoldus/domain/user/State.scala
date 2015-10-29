package com.knoldus.domain.user

import com.knoldus.domain.{Event, AggregateState}
import com.knoldus.domain.user.Protocols.{EmailChanged, UserCreated}

case class User(id: String, firstName: String, lastName: String, email: String)

final case class State(users: Map[String, User] = Map.empty) extends AggregateState {
  private def updateStates(user: User): State = copy(users = users + (user.id -> user))

  def get(id: String): Option[User] = users.get(id)

  def update: PartialFunction[Event, AggregateState] = {
    case UserCreated(id, firstName, lastName, email) => updateStates(User(id, firstName, lastName, email))
    case EmailChanged(id, newEmail)                  => updateStates(get(id).get.copy(email = newEmail))
  }
}
