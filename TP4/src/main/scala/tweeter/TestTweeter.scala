package tweeter

object TestTweeter extends App {

  import Tweeter._

  val ALICE = "Alice"
  val BOB = "Bob"

  val r = Registry("registry")

  val bob = Tweeter(BOB, r)
  val alice = Tweeter(ALICE, r)

  alice ! Tweet("I am Alice")

  bob ! Follow(ALICE)
  alice ! Follow(BOB)

  Thread.sleep(1000)

  alice ! Tweet("I am Alice")
  //  bob ! Tweet("I am Bob")
}