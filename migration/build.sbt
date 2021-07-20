enablePlugins(FlywayPlugin)
name := "plugtest"
version := "0.0.1"
name := "flyway-sbt-test1"

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.16"

flywayUrl := "jdbc:mysql://localhost:3306/kakeibooo"
flywayUser := "root"
flywayPassword := "root"
flywayLocations += "migration"
flywayUrl in Test := "jdbc:mysql://localhost:3306/kakeibooo;shutdown=true"
flywayUser in Test := "root"
flywayPassword in Test := "root"
