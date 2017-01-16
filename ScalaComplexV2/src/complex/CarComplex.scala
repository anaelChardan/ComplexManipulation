package complex

class CarComplex(val real: Double, val imag: Double) {
  def +(c: CarComplex): CarComplex = new CarComplex(real + c.real, imag + c.imag)

  override def equals(o: Any): Boolean = {
    if (!o.isInstanceOf[CarComplex]) return false
    val c: CarComplex = o.asInstanceOf[CarComplex]
    Comparison.isEqual(this.real, c.real) && Comparison.isEqual(this.imag, c.imag)
  }
}

object CarComplex {
  implicit def pol2Car(that: PolComplex): CarComplex = new CarComplex(Conversion.real(that.radius, that.azimuth), Conversion.imag(that.radius, that.azimuth))
}