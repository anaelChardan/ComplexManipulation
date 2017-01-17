trait Exp
case class Plus(e1: Exp, e2: Exp) extends Exp // addition
case class Sub(e1: Exp, e2: Exp) extends Exp // subtraction
case class Minus(e: Exp) extends Exp // unary minus
case class IntLit(v: Int) extends Exp // integer literal

object Exp {
  def eval(e: Exp): Int = e match {
    case IntLit(v) => v
    case Minus(e1) => - eval(e1)
    case Plus(e1, e2) => eval(e1) + eval(e2)
    case Sub(e1, e2) => eval(e1) - eval(e2)
  }
}