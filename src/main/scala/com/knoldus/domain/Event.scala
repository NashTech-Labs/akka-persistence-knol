package com.knoldus.domain

import org.joda.time.{DateTime, DateTimeZone}

trait Event {
  val id: String
  val creationTime = new DateTime().withZone(DateTimeZone.UTC)
}
