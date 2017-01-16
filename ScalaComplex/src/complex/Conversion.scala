package complex

object Conversion {
  /*
   * Conversions between polar and cartesian coordinates
   */
  def real(radius: Double, azimuth: Angle): Double = radius * Math.cos(azimuth.angle)

  def imag(radius: Double, azimuth: Angle): Double = radius * Math.sin(azimuth.angle)

  def radius(real: Double, imag: Double): Double = Math.sqrt(real * real + imag * imag)

  def azimuth(real: Double, imag: Double): Angle = new Angle(Math.atan2(imag, real))
}