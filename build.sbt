import com.softwaremill.SbtSoftwareMillCommon.commonSmlBuildSettings

lazy val commonSettings = commonSmlBuildSettings ++ Seq(
  organization := "com.softwaremill.kafka",
  scalaVersion := "3.5.2"
)

val scalaTest = "org.scalatest" %% "scalatest" % "3.2.18" % Test

lazy val rootProject = (project in file("."))
  .settings(commonSettings: _*)
  .settings(publishArtifact := false, name := "kafka-ox-pres")
  .aggregate(core)

lazy val core: Project = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server-sync" % "1.11.9",
      "com.softwaremill.ox" %% "kafka" % "0.5.3",
      "ch.qos.logback" % "logback-classic" % "1.5.8",
      "org.apache.pekko" %% "pekko-connectors-kafka" % "1.1.0",
      "org.apache.pekko" %% "pekko-stream" % "1.1.2",
      "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M19",
      scalaTest
    )
  )
