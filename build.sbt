name := """bitClassroom"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

val appDependencies = Seq(
  // Add your project dependencies here,
  "mysql" % "mysql-connector-java" % "5.1.36"
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

