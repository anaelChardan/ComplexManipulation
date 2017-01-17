object Test extends App {
  val add1 = Sub(IntLit(4), IntLit(2))
  val minus1 = Minus(IntLit(2))
  val add2 = Plus(add1, minus1)

  System.out.println(s"$add2 => ${Exp.eval(add2)}")
}