package cz.vmencik.sandbox

object PMatch extends App {
  
  pmatch(List(null, Person("Mike", Email("mike", "gmail.com"))))

  case class Email(local: String, domain: String)
  case class Person(name: String, email: Email)
  
  def pmatch(x: Any): Unit = {
    x match {
      case List(_, Person(_, Email(_, "gmail.com")), _*) =>
        println("Gmail user in 2nd place")
      case _ => println("Nothing special")
    }
  }
}