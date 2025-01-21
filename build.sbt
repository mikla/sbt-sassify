lazy val sbtSassify = project
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin)
  .enablePlugins(ScriptedPlugin)

name := "sbt-sassify"
startYear := Some(2018)
sbtPlugin := true

pluginCrossBuild / sbtVersion := "1.5.0"

Test / fork := false

javaOptions += "-Djna.nosys=true"

addSbtPlugin("com.github.sbt" % "sbt-web" % "1.5.8")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % "test",
  "org.scalatest" %% "scalatest-mustmatchers" % "3.2.19" % "test",
  "org.scalatest" %% "scalatest-funspec" % "3.2.19" % "test",
  "net.java.dev.jna" % "jna" % "5.16.0",
  "org.webjars" % "webjars-locator-core" % "0.59",
)

// Compiler settings
sourcesInBase := false
crossPaths := false
scalacOptions ++= Seq(
  "-unchecked",
  "-Xlint",
  "-deprecation",
  "-feature",
  "-encoding",
  "UTF-8"
)
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

// Scalastyle settings
lazy val testScalastyle = taskKey[Unit]("testScalastyle")
testScalastyle := scalastyle.in(Compile).toTask("").value
scalastyleFailOnError := true

// Scripted settings
scriptedBufferLog := false
scriptedLaunchOpts += "-Dplugin.version=" + version.value

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("test"),
  releaseStepCommandAndRemaining("scripted"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepCommandAndRemaining("^ publish"),
  releaseStepCommandAndRemaining("bintrayRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges)
