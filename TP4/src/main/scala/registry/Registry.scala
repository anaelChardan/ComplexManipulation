package registry

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object Registry {

  // definition of specific messages received by the actor
  case class Bind(name: String, value: Any)

  // definition of specific message wanted by an actor
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

  def receive: PartialFunction[Any, Unit] = {
    case Bind(name, value) => registry = registry + (name -> value)
    case Lookup(name) => sender ! LookupAnswer(registry.get(name))
  }
}
