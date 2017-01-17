package complex

import scala.language.implicitConversions

class CarComplex(val real: Double, val imag: Double) {
  def +(c: CarComplex): CarComplex = {
    println("CAR COMPLEX : +")
    new CarComplex(real + c.real, imag + c.imag)
  }

  override def equals(o: Any): Boolean = {
    println("CAR COMPLEX : equals")
    if (!o.isInstanceOf[CarComplex]) {
      println("THIS IS NOT COMPARABLE")
      return false
    }
    val c: CarComplex = o.asInstanceOf[CarComplex]
    Comparison.isEqual(this.real, c.real) && Comparison.isEqual(this.imag, c.imag)
  }
}

object CarComplex {
  implicit def pol2Car(that: PolComplex): CarComplex = {
    println("POL 2 CAR")
    new CarComplex(Conversion.real(that.radius, that.azimuth), Conversion.imag(that.radius, that.azimuth))
  }
}