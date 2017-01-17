package complex

import scala.language.implicitConversions

class PolComplex(val radius: Double, val azimuth: Angle) {
  def +(c: PolComplex): PolComplex = {
    println("POL COMPLEX: +")
    val real: Double = Conversion.real(radius, azimuth) + Conversion.real(c.radius, c.azimuth)
    val imag: Double = Conversion.imag(radius, azimuth) + Conversion.imag(c.radius, c.azimuth)

    new PolComplex(Conversion.radius(real, imag), Conversion.azimuth(real, imag))
  }

//  def *()

  override def equals(o: Any): Boolean = {
    println("POL COMPLEX : EQUALS")
    if (!o.isInstanceOf[PolComplex]) return false
    val c: PolComplex = o.asInstanceOf[PolComplex]
    Comparison.isEqual(radius, c.radius) && Comparison.isEqual(azimuth.angle, c.azimuth.angle)
  }
}

object PolComplex {
  implicit def car2Pol(that: CarComplex): PolComplex = {
    println("CAR 2 POL")
    new PolComplex(Conversion.radius(that.real, that.imag), Conversion.azimuth(that.real, that.imag))
  }
}