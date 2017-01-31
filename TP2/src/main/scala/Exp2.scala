/**
  * Created by anael on 24/01/2017.
  */
trait Exp2

trait Plus extends Exp2 {
  val e1: Exp2
  val e2: Exp2
}

trait Sub extends Exp2 {
  val e1: Exp2
  val e2: Exp2
}

trait Minus extends Exp2 {
  val e1: Exp2
}
