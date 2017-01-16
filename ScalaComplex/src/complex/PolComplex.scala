package complex

class PolComplex(val radius: Double, val azimuth: Angle) extends AbsComplex {

  def this(real: Double, imag: Double) { this(Conversion.radius(real, imag), Conversion.azimuth(real, imag)) }

  override val real: Double = Conversion.real(radius, azimuth)
  override val imag: Double = Conversion.imag(radius, azimuth)

  override def create(real: Double, imag: Double): Complex = new PolComplex(real, imag)
  override def create(radius: Double, azimuth: Angle): Complex = new PolComplex(radius, azimuth)
}
