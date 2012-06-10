package cz.vmencik.sandbox

class SPoint(val x: Int, val y: Int) {
  override def toString = "SPoint(" + x + "," + y + ")"
}

object SPointRun extends App {
  val p = new SPoint(1, 2)
  println(p)
}