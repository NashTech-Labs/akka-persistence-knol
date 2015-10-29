package com.knoldus.domain.user

import com.knoldus.domain.AggregateRoot
import com.knoldus.domain.user.Protocols._

class UserProcessor extends AggregateRoot[State] {
  def persistenceId: String = "user-persistence-processor"

  state = State()

  val receiveRecover: Receive = {
    case event: UserCreated  =>
      updateState(event)
      log.info(s"state after creating user, $state")
    case event: EmailChanged =>
      updateState(event)
      log.info(s"state after updating email $state")
  }

  val receiveCommand: Receive = {
    case command: CreateUser  =>
      persist(UserCreated(command.id, command.firstName, command.lastName, command.email))(event => updateState(event))
    case command: ChangeEmail =>
      persist(EmailChanged(command.id, command.newEmail))(event => updateState(event))
    case Print                =>
      log.info(s"current state is $state")
  }
}
