package tweeter

import akka.actor.{Actor, ActorRef, Props}
import registry.Registry
import registry.Registry.{Bind, Lookup, LookupAnswer}
import tweeterToGui.TweeterView
import tweeterToGui.TweeterView.{RegisterTweeter, RetweetView, TweetView}

/**
  * Created by anael on 31/01/2017.
  */
object Tweeter {

  /** *****
    * Les différents messages pouvant être envoyés à un Tweeter
    */
  case class Tweet(content: String)

  case class HandleTweet(user: String, content: String)

  case class Retweet(tweeter: String, content: String)

  case class HandleRetweet(retweeter: String, tweeter: String, content: String)

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

    case Tweet(content: String) =>
      followers.foreach(follower => follower.self ! HandleTweet(name, content))

    case HandleTweet(user: String, content: String) =>
      tweeterView ! TweetView(user, content)

    case Retweet(tweeter: String, content: String) =>
      followers.foreach(follower => follower.self ! HandleRetweet(name, tweeter, content))

    case HandleRetweet(retweeter: String, tweeter: String, content: String) =>
      tweeterView ! RetweetView(retweeter, tweeter, content)

    case Follow(user: String) => registry ! Lookup(user)

    case AddFollower(follower: Tweeter) => this.followers += follower

    case LookupAnswer(value: Option[Any]) =>
      value match {
        case Some(follower: Tweeter) => follower.self ! AddFollower(this)
        case None => println("La personne que vous avez demandée n'existe pas")
      }
  }
}
