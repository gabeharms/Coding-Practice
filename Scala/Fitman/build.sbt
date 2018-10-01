// The simplest possible sbt build file is just one line:

scalaVersion := "2.12.6"
name := "fitman"
version := "1.0"

lazy val versions = new {
  val finatra = "2.13.0"
  val logback = "1.1.7"
  val guice = "4.0"
}
 
libraryDependencies += "com.twitter" %% "finatra-http" % versions.finatra
libraryDependencies += "ch.qos.logback" % "logback-classic" % versions.logback
