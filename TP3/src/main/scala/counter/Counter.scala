package counter

import java.util.Observable

case class Counter(var name: String, var _count: Int) extends Observable {

  def count: Int = _count

  def increment() {
    System.out.println("increment");
    _count += 1
    setChanged()
    notifyObservers(count)
  }

  def decrement() {
    System.out.println("decrement");
    _count -= 1
    setChanged()
    notifyObservers(count)
  }
}
