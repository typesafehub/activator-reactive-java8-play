import play.Project._

name := "activator-reactive-java8-play"

version := "1.0-SNAPSHOT"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

resolvers += Resolver.typesafeRepo("snapshots")

libraryDependencies ++= Seq(
  javaWs,
  "org.webjars" %% "webjars-play" % playVersion.value,
  "org.webjars" % "bootstrap" % "2.3.1")

playJavaSettings