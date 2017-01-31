/**
  * Created by anael on 24/01/2017.
  */
object ListToolBox {
  def myLength[T](l: List[T]): Int = {
    l match {
      case Nil => 0
      case _ :: t => 1 + myLength(t)
    }
  }

  def myReverse[T](l: List[T]): List[T] = {
    l match {
      case Nil => List.empty
      case h :: t => myReverse(t) :+ h
    }
  }

  def myReverse1[T](l: List[T]): List[T] = {
    def reverse(l: List[T], l2: List[T]): List[T] = {
      (l, l2) match {
        case (Nil, x) => x
        case (x :: xs, a) => reverse(xs, x :: a)
      }
    }

    reverse(l, List.empty)
  }
}

trait Reverser[T] {

  def reverseTmp(l: List[T], l2: List[T]): List[T] = {
    (l, l2) match {
      case (Nil, x) => x
      case (x :: xs, a) => reverseTmp(xs, x :: a)
    }
  }

  def myReverse2(l: List[T]): List[T] = {
    reverseTmp(l, List.empty)
  }

  val reverseF: List[T] = (l: List[T], l2: List[T]) => reverseTmp(l, l2)
}