trait Exp

case class Plus(e1: Exp, e2: Exp) extends Exp

case class Sub(e1: Exp, e2: Exp) extends Exp

case class Minus(e: Exp) extends Exp

case class IntLit(v: Int) extends Exp

case class And(e1: Exp, e2: Exp) extends Exp

case class Or(e1: Exp, e2: Exp) extends Exp

case class Not(e1: Exp) extends Exp

case class BoolLit(v: Boolean) extends Exp

case class Compare(e1: Exp, e2: Exp) extends Exp

case class If(test: Exp, e1: Exp, e2: Exp) extends Exp

object Exp {
  def eval(e: Exp): Val = e match {
    case IntLit(v) => ValInt(v)
    case BoolLit(v) => ValBool(v)
    case Minus(e1) =>
      eval(e1) match {
        case ValInt(v) => ValInt(-v)
        case _ => throw new Exception(s"Unexpected combine of exp : ${Minus(e1)}")
      }
    case Plus(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ValInt(v1), ValInt(v2)) => ValInt(v1 + v2)
        case _ => throw new Exception(s"Unexpected combine of exp : ${Plus(e1, e2)}")
      }
    case Sub(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ValInt(v1), ValInt(v2)) => ValInt(v1 - v2)
        case _ => throw new Exception(s"Unexpected combine of exp : ${Sub(e1, e2)}")
      }
    case And(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ValBool(v1), ValBool(v2)) => ValBool(v1 && v2)
        case _ => throw new Exception(s"Unexpected combine of exp : ${And(e1, e2)}")
      }
    case Or(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ValBool(v1), ValBool(v2)) => ValBool(v1 || v2)
        case _ => throw new Exception(s"Unexpected combine of exp : ${Or(e1, e2)}")
      }
    case Not(e1) =>
      eval(e1) match {
        case (ValBool(v1)) => ValBool(!v1)
        case _ => throw new Exception(s"Unexpected combine of exp : ${Not(e1)}")
      }
    case Compare(e1, e2) =>
      (eval(e1), eval(e2)) match {
        case (ValBool(v1), ValBool(v2)) => ValBool(v1 == v2)
        case (ValInt(v1), ValInt(v2)) => ValBool(v1 == v2)
        case _ => ValBool(false)
      }
    case If(t, e1, e2) =>
      eval(t) match {
        case ValBool(true) => eval(e1)
        case ValBool(false) => eval(e2)
        case _ => throw new Exception(s"Unexpected combine of exp : ${If(t, e1, e2)}")
      }

  }

  def check(e: Exp): T = e match {
    case IntLit(v) => I
    case Minus(e1) =>
      check(e1) match {
        case I => I
      }
    case Plus(e1, e2) =>
      (check(e1), check(e2)) match {
        case (I, I) => I
      }
    case Sub(e1, e2) =>
      (check(e1), check(e2)) match {
        case (I, I) => I
      }

    case And(e1, e2) =>
      (check(e1), check(e2)) match {
        case (B, B) => B
      }

    case Or(e1, e2) =>
      (check(e1), check(e2)) match {
        case (B, B) => B
      }

    case Not(e1) =>
      check(e1) match {
        case (B) => B
      }

    case Compare(e1, e2) =>
      (check(e1), check(e2)); B

    case If(t, e1, e2) => (check(t), check(t), check(t)) match {
      case (B, B, B) => B
      case (B, I, I) => I
    }

    case BoolLit(v) => B
  }

  def toString(e: Exp): String = e match {
    case IntLit(v) => v.toString
    case BoolLit(v) => v.toString
    case Minus(e1) => s"(- ${toString(e1)})"
    case Not(e1) => s"! ${toString(e1)})"
    case Plus(e1, e2) => s"(${toString(e1)} + ${toString(e2)})"
    case Sub(e1, e2) => s"(${toString(e1)} - ${toString(e2)})"
    case And(e1, e2) => s"(${toString(e1)} && ${toString(e2)})"
    case Or(e1, e2) => s"(${toString(e1)} || ${toString(e2)})"
    case Compare(e1, e2) => s"(${toString(e1)} == ${toString(e2)})"
    case If(p, e1, e2) => s"if ${toString(p)} { ${toString(e1)} } else { ${toString(e2)} }"
  }
}

