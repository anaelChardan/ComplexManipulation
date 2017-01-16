package complex

object Comparison {
  var precision: Double = 1E-15

  def isEqual(x: Double, y: Double): Boolean = Math.abs(x - y) < precision
}
