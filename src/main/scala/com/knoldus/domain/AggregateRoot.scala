package com.knoldus.domain

import akka.actor.ActorLogging
import akka.persistence.PersistentActor

trait AggregateState {
  def update: PartialFunction[Event, AggregateState]
}

trait AggregateRoot[S <: AggregateState] extends PersistentActor with ActorLogging {
  var state: S = _

  protected def updateState(event: Event): Unit = {
    val newState = state.update(event)
    state = newState.asInstanceOf[S]
  }

  def publish(event: Event): Unit =
    context.system.eventStream.publish(event)
}
