package counter

import java.util.Observable

class Counter(var name: String, var count: Int) extends Observable {
  private[counter] def getCount = count

  private[counter] def increment() {
    //		System.out.println("increment");
    count += 1
    setChanged()
    notifyObservers(count)
  }

  private[counter] def decrement() {
    //		System.out.println("decrement");
    count -= 1
    setChanged()
    notifyObservers(count)
  }
}
