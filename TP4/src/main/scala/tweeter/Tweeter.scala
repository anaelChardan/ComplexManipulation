package tweeter

import akka.actor.{Actor, ActorRef, Props}
import registry.Registry
import registry.Registry.{Bind, Lookup, LookupAnswer}
import tweeterToGui.TweeterView
import tweeterToGui.TweeterView.{RegisterTweeter, TweetView}

/**
  * Created by anael on 31/01/2017.
  */
object Tweeter {

  /** *****
    * Les différents messages pouvant être envoyés à un Tweeter
    */
  case class Tweet(content: String)

  case class HandleTweet(user: String, content: String)

  case class Follow(user: String)

  case class AddFollower(follower: Tweeter)


  def apply(name: String, registry: ActorRef): ActorRef = Registry.system.actorOf(Props(new Tweeter(name, registry, TweeterView(name))), name)
}

//L'objet passif
class Tweeter(val name: String, val registry: ActorRef, val tweeterView: ActorRef) extends Actor {

  import Tweeter._

  registry ! Bind(name, this)
  tweeterView ! RegisterTweeter(this)

  var followers: Set[Tweeter] = Set.empty

  override def receive: Receive = {

    case Tweet(content: String) => followers.foreach(follower => follower.self ! HandleTweet(name, content))

    case Follow(user: String) => registry ! Lookup(user)

    case HandleTweet(user: String, content: String) =>
      println(s"$name receive ... $content ... from $user")
      tweeterView ! TweetView(user, content)

    case AddFollower(follower: Tweeter) =>
      var followerName = follower.name
      println(s"$name have a new follower called $followerName")
      this.followers += follower

    case LookupAnswer(value: Option[Any]) =>
      value.foreach {
        case (follower: Tweeter) => follower.self ! AddFollower(this)
        case _ =>
      }
  }
}
