val BetterMonadicForVersion = "0.3.1"
val CatsEffectVersion = "2.1.4"
val CatsVersion = "2.1.1"
val GitHub4sVersion = "0.24.1"
val KindProjectorVersion = "0.11.0"
val MonixVersion = "3.2.2"
val ScalaCheckVersion = "1.14.3"
val ScalaTestPlusVersion = "3.2.0.0"
val ScalaTestVersion = "3.2.0"
val SimulacrumVersion = "1.0.0"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.12",
    crossScalaVersions := Seq("2.12.12", "2.13.3"),
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedLaunchOpts ++= List(
      "-Xms1024m",
      "-Xmx1024m",
      "-XX:ReservedCodeCacheSize=128m",
      "-XX:MaxPermSize=256m",
      "-Xss2m",
      "-Dfile.encoding=UTF-8"
    ),
    resolvers += Resolver.url("typesafe", url("https://repo.typesafe.com/typesafe/ivy-releases/"))(
      Resolver.ivyStylePatterns
    ),
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    // Adding dependencies in order to force Scala Steward to help us
    // update the g8 template as well
    libraryDependencies ++= Seq(
      "io.monix" %% "monix" % MonixVersion % Test,
      "org.scalacheck" %% "scalacheck" % ScalaCheckVersion % Test,
      "org.scalatest" %% "scalatest" % ScalaTestVersion % Test,
      "org.scalatestplus" %% "scalacheck-1-14" % ScalaTestPlusVersion % Test,
      "org.typelevel" %% "cats-core" % CatsVersion % Test,
      "org.typelevel" %% "cats-effect-laws" % CatsEffectVersion % Test,
      "org.typelevel" %% "cats-effect" % CatsEffectVersion % Test,
      "org.typelevel" %% "cats-laws" % CatsVersion % Test,
      "org.typelevel" %% "simulacrum" % SimulacrumVersion % Test,
      compilerPlugin(("org.typelevel" % "kind-projector" % KindProjectorVersion).cross(CrossVersion.full) % Test),
      compilerPlugin("com.olegpy" %% "better-monadic-for" % BetterMonadicForVersion % Test)
    )
  )
