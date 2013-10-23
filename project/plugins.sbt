resolvers ++= Seq(
  "Twitter" at "http://maven.twttr.com",
  "Sonatype OSS" at "https://oss.sonatype.org/content/repositories/releases/",
  Classpaths.typesafeResolver
)

addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % "3.3.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.1")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")
