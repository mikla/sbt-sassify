ThisBuild / organization := "io.github.mikla"
ThisBuild / organizationName := "Han van Venrooij"
ThisBuild / organizationHomepage := None

ThisBuild / developers := List()

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/mikla/sbt-sassify"),
    "scm:git@github.com:mikla/sbt-sassify.git"
  )
)

ThisBuild / description := "An sbt plugin that enables you to use Sass in your sbt-web project."
ThisBuild / licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
ThisBuild / homepage := Some(url("https://github.com/irundaia/sbt-sassify")) // original repo

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  // For accounts created after Feb 2021:
  // val nexus = "https://s01.oss.sonatype.org/"
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := false