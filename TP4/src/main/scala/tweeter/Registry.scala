package tweeter

import akka.actor.{Actor, ActorRef, ActorSystem, Inbox, Props}
import scala.concurrent.duration._
import Registry._

object Registry {

  // definition of specific messages received by the actor
  case class Bind(name: String, value: Any)

  case class Lookup(name: String)

  // definition of specific message sent by the actor
  case class LookupAnswer(value: Option[Any])

  // create dedicated actor infrastructure
  val system = ActorSystem("myActorInfrastructure")

  // factory creates actor with a name and register it with the infrastructure
  def apply(name: String): ActorRef = system.actorOf(Props(new Registry()), name)
}

// L'objet passif
class Registry extends Actor {

  import Registry._

  private var registry: Map[String, Any] = Map()

  def receive = {
    case Bind(name, value) => registry = registry + (name -> value)
    case Lookup(name) => sender ! LookupAnswer(registry.get(name))
  }
}

object TestRegistry extends App {
  val r = Registry("registry")
  // create an "inbox" to interact with registry actor 
  // (via an internal actor of the inbox) 
  val i = Inbox.create(Registry.system)
  // test the registry
  r ! Bind("Alice", "F")
  // r ! Lookup("Bob") would not properly instantiate "sender" on the registry 
  // side as the execution of the send action does not take place in the context
  // of an actor
  i.send(r, Lookup("Bob"))
  i.send(r, Lookup("Alice"))
//  r ? Lookup("Bobo")
  println("PLOP")
  println(i.receive(FiniteDuration(1, MILLISECONDS)))
  println(i.receive(FiniteDuration(1, MILLISECONDS)))
}
