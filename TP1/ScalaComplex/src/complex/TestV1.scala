package complex

import complex.Comparison.isEqual

import scala.math.{Pi, sqrt}

object TestV1 {
  var score = 0
  var total = 0

  def main(args: Array[String]): Unit = {
    var factory: Complex = new CarComplex(0, 0)
    test(new CarComplex(0, 0))
    factory = new PolComplex(0, 0)
    test(factory)
    println("-----------------------------------------------------------")
    println("Score final : " + score + " / " + total)
  }

  private def test(factory: Complex){
    val a = factory.create(1,-1);
    testCoordinates(a, 1.0, -1.0, sqrt(2.0), (7.0 * Pi / 4.0))

    val b: Complex = factory.create(2, new Angle(Pi/2.0))
    testCoordinates(b, 0.0, 2.0, 2.0, (Pi / 2.0))

    val c: Complex = a + b
    testEquality(c, factory.create(1.0, 1.0))
    testCoordinates(c, 1.0, 1.0, sqrt(2.0), Pi / 4.0)

    val d: Complex = a * c
    testEquality(d, factory.create(2.0, 0.0))
    testCoordinates(d, 2.0, 0.0, 2.0, 0.0)
  }

  private def testCoordinates(c: Complex,
                              real: Double,
                              imag: Double,
                              radius: Double,
                              azimuth: Double) {
    total = total + 1
    if (isEqual(c.real, real))
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error for complex " + c + ": " + c.getClass)
      println("x = " + c.real + " instead of " + real)
    }
    total = total+1
    if (isEqual(c.imag, imag))
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error for complex " + c + ": " + c.getClass)
      println("y = " + c.imag + " instead of " + imag)
    }
    total = total + 1
    if (isEqual(c.radius, radius))
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error for complex " + c + ": " + c.getClass)
      println("rho = " + c.radius + " instead of " + radius)
    }
    total = total + 1
    if (isEqual(c.azimuth.angle, azimuth))
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error for complex " + c + ": " + c.getClass)
      System.out.println("theta = " + c.azimuth.angle + " instead of " + azimuth)
    }
  }

  def testEquality(c1: Complex, c2: Complex){
    total = total + 1
    if(c1 == c2)
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error in equality test: "
	+ c1 + ": " + c1.getClass
	+ " equal to " + c2 + " : " + c2.getClass)
    }
  }
}
