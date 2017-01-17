object Test extends App {
//  val add1 = Sub(IntLit(4), IntLit(2))
//  val minus1 = Minus(IntLit(2))
//  val add2 = Plus(add1, minus1)
//
//  System.out.println(s"$add2 => ${Exp.eval(add2)}")
//
//  println(Exp.toString(add2))


  val add1 = Sub(IntLit(4), Sub(IntLit(4), IntLit(2)))

  val test = If(
    Or(BoolLit(true), And(BoolLit(false), BoolLit(true))),
    Sub(IntLit(4), IntLit(2)),
    Sub(IntLit(5), IntLit(2)))


  println(s"$test => \n ${Exp.eval(test)}")
  println(s"$test => \n ${Exp.toString(test)}")
}