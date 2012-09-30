name := "sandbox"

version := "1.0-SNAPSHOT"

scalaVersion := "2.9.2"
 
resolvers += Classpaths.typesafeResolver

libraryDependencies ++= Seq (
	"com.typesafe.akka" % "akka-actor" % "2.0.3",
	"junit" % "junit" % "4.10" % "test",
	"org.scalatest" %% "scalatest" % "1.8" % "test"
)

EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)
