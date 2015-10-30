package com.knoldus.domain.user

import com.knoldus.domain.user.Protocols._
import com.knoldus.domain.{AggregateRoot, Event}

class UserProcessor extends AggregateRoot[State, User] {
  def persistenceId: String = "user-persistence-processor"

  state = State()

  val factory: AggregateRootFactory = {
    case UserCreated(id, firstName, lastName, email) =>
      User(id, firstName, lastName, email)
    case EmailChanged(id, newEmail)                 =>
      state.get(id).get.copy(email = newEmail)
  }

  val receiveRecover: Receive = {
    case event: UserCreated  =>
      updateState(event)
      log.info(s"state after creating user, $state")
    case event: EmailChanged =>
      updateState(event)
      log.info(s"state after updating email $state")
  }

  val receiveCommand: Receive = {
    case CreateUser(id, firstName, lastName, email) =>
      persist(UserCreated(id, firstName, lastName, email))(event => updateState(event))
    case ChangeEmail(id, newEmail)                  =>
      persist(EmailChanged(id, newEmail))(event => updateState(event))
    case Print                                      =>
      log.info(s"current state is $state")
  }

  protected def updateState(event: Event): Unit = {
    val newState = state.update(factory(event))
    state = newState
  }
}
