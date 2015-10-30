package com.knoldus.domain.user

import com.knoldus.domain.{AggregateState, Entity}

case class User(id: String, firstName: String, lastName: String, email: String) extends Entity

final case class State(users: Map[String, User] = Map.empty) extends AggregateState {
  def update(user: User): State = copy(users = users + (user.id -> user))

  def get(id: String): Option[User] = users.get(id)
}
