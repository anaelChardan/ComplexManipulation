package complex

class CarComplex(val real: Double, val imag: Double) extends AbsComplex {
  def this(radius: Double, azimuth: Angle) {
    this(Conversion.real(radius, azimuth), Conversion.imag(radius, azimuth))
  }

  override val radius: Double = Conversion.radius(this.real, this.imag)
  override val azimuth: Angle = Conversion.azimuth(this.real, this.imag)

  override def create(real: Double, imag: Double): Complex = new CarComplex(real, imag)
  override def create(radius: Double, azimuth: Angle): Complex = new CarComplex(radius, azimuth)
}