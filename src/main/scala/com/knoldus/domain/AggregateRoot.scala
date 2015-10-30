package com.knoldus.domain

import akka.actor.ActorLogging
import akka.persistence.PersistentActor

trait AggregateState

trait AggregateRoot[S <: AggregateState, E <: Entity] extends PersistentActor with ActorLogging {
  type AggregateRootFactory = PartialFunction[Event, E]
  var state: S = _

  val factory: AggregateRootFactory

  protected def updateState(event: Event): Unit

  def publish(event: Event): Unit =
    context.system.eventStream.publish(event)
}
