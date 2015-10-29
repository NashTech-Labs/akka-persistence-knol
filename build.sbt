name := """akka-persistence-knol"""

version := "0.1"

scalaVersion := "2.11.7"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.0",
  "com.github.scullxbones" %% "akka-persistence-mongo-rxmongo" % "1.0.6",
  "org.reactivemongo" %% "reactivemongo" % "0.11.7",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "joda-time" % "joda-time" % "2.9"
)
