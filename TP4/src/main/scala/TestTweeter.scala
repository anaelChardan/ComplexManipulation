import registry.Registry
import tweeter.Tweeter

object TestTweeter extends App {

  import Tweeter._

  val ALICE = "Alice"
  val BOB = "Bob"
  val CAROL = "Carol"

  val r = Registry("registry")

  val bob = Tweeter(BOB, r)
  val alice = Tweeter(ALICE, r)
  val carol = Tweeter(CAROL, r)

  carol ! Follow(BOB)
  bob ! Follow(ALICE)
  alice ! Follow(BOB)
  alice ! Follow(CAROL)

  bob ! Follow("TEST")

  Thread.sleep(1000)

  alice ! Tweet("I am Alice")
}