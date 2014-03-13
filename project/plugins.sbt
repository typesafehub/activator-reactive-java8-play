// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.typesafeIvyRepo("snapshots")
)

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3-2014-03-12-237b90f-SNAPSHOT")
