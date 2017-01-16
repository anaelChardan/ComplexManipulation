package complex

class PolComplex(val radius: Double, val azimuth: Angle) {
  def +(c: PolComplex): PolComplex = {
    val real: Double = Conversion.real(radius, azimuth) + Conversion.real(c.radius, c.azimuth)
    val imag: Double = Conversion.imag(radius, azimuth) + Conversion.imag(c.radius, c.azimuth)

    new PolComplex(Conversion.radius(real, imag), Conversion.azimuth(real, imag))
  }

//  override def equals(o: Any): Boolean = {
//    if (!o.isInstanceOf[PolComplex]) return false
//    val c: PolComplex = o.asInstanceOf[PolComplex]
//    Comparison.isEqual(this.radius, c.radius) && Comparison.isEqual(this.azimuth, c.imag)
//  }
}

object PolComplex {
  implicit def car2Pol(that :CarComplex): PolComplex = new PolComplex(Conversion.radius(that.real, that.imag), Conversion.azimuth(that.real, that.imag))
}