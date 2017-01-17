package complex

abstract class AbsComplex extends Complex {
  override def +(c: Complex): Complex = this.create(this.real + c.real, this.imag + c.imag)
  override def *(c: Complex): Complex = this.create(this.radius * c.radius, this.azimuth + c.azimuth)

  override def equals(o: Any): Boolean = {
    if (!o.isInstanceOf[Complex]) return false
    val c: Complex = o.asInstanceOf[Complex]
    Comparison.isEqual(this.real, c.real) && Comparison.isEqual(this.imag, c.imag)
  }

  override val toString: String = "(" + this.real + ", " + this.real + ")"
}