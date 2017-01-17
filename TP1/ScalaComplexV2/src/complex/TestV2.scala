package complex

import scala.math.{Pi, sqrt}

object TestV2 {
  var score = 0
  var total = 0

  def main(args: Array[String]): Unit = {
    test()
    println("-----------------------------------------------------------")
    println("Score final : " + score + " / " + total)
  }

  private def test() {
    val a = new CarComplex(1,-1)

    testEquality[CarComplex](a, new PolComplex(sqrt(2.0), new Angle(7.0 * Pi / 4.0)))
    testEquality[PolComplex](a, new PolComplex(sqrt(2.0), new Angle(7.0 * Pi / 4.0)))

    val b = new PolComplex(2, new Angle(Pi/2.0))
    testEquality[PolComplex](b, new CarComplex(0.0, 2.0))
    testEquality[CarComplex](b, new CarComplex(0.0, 2.0))

    val c = a + b
    testEquality[CarComplex](c, new CarComplex(1.0, 1.0))
    testEquality[PolComplex](c, new CarComplex(1.0, 1.0))


//    val c = a ++ b
//    testEquality(c, new PolComplex(sqrt(2.0), new Angle(Pi / 4.0)))

//    val c2 = b ++ a
//    testEquality(c2, new CarComplex(1.0, 1.0))
//    testEquality(c2, new PolComplex(sqrt(2.0), new Angle(Pi / 4.0)))	
//
//    val c3 = b ++ b
//    testEquality(c3, new CarComplex(0, 4.0))
//    testEquality(c3, new PolComplex(4.0, new Angle(Pi/2.0)))
//
//    val d = a * c
//    testEquality(d, new CarComplex(2.0, 0.0));
//    testEquality(d, new PolComplex(2.0, new Angle(0.0)))
  }

  def testEquality[T](c1: T, c2: T){
    total = total + 1
    if (c1 == c2)
      score = score + 1
    else {
      println("-----------------------------------------------------------")
      println("Error in equality test: " 
        + c1 + ": " + c1.getClass
        + " equal to " + c2 + " : " + c2.getClass )
    }
  }
}
