package com.twitter.example.finagle

import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import com.twitter.scrooge.ScroogeSBT

object FinagleExamples extends Build {
  val dependencies = Seq(
    resolvers ++= Seq(
      "Twitter Maven Repository" at "http://maven.twttr.com"
    ),

    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-core" % "6.7.1",
      "com.twitter" %% "finagle-http" % "6.7.1"
    )
  )

  val thrift = ScroogeSBT.newSettings ++ Seq(
    libraryDependencies ++= Seq(
      "org.apache.thrift" % "libthrift" % "0.8.0",
      "com.twitter" % "scrooge-core" % "3.9.0",
      "com.twitter" %% "finagle-thrift" % "6.7.1"
    )
  )

  val testing = Seq(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "1.9.2" % "test"
    )
  )

  val example = Project("finagle-examples", base = file(".")).
    settings(scalariformSettings:_*).
    settings(dependencies:_*).
    settings(thrift:_*).
    settings(testing:_*)
}