package cz.vmencik.sandbox

object Dummy extends App {
  println("This is just an example project")
  println(new DummyJava().say())
  
  def text() = "With Java and Scala classes mixed together, cross-compiled by Scala compiler"
}