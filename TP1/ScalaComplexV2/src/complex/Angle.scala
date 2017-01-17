package complex

class Angle(radians: Double) {
  val theta: Double = radians - 2 * Math.PI * Math.floor(radians / (2 * Math.PI))
  val angle: Double = this.theta

  def +(a: Angle): Angle = new Angle(a.angle + this.angle)
  def *(k: Double): Angle = new Angle(k * this.angle)
}
