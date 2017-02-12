package tweeter

import akka.actor.{Actor, ActorRef, Props}
import tweeter.Registry.{Bind, Lookup, LookupAnswer}
import tweeter.Tweeter.Tweet
import tweeter.TweeterView.{RegisterTweeter, Retweet, TweetView}

/**
  * Created by anael on 31/01/2017.
  */
object TweeterView {

  case class TweetView(source: String, content: String)

  case class Retweet()

  case class RegisterTweeter(tweeter: Tweeter)

  def apply(name: String): ActorRef =
    Registry.system.actorOf(Props(new TweeterView(new TweeterViewGUI(name))))
}

class TweeterView(val tweeterViewGUI: TweeterViewGUI) extends Actor {
  var lastMessage: Option[String] = None
  var tweeter: Option[Tweeter] = None

  tweeterViewGUI.view = Some(this)

  override def receive: Receive = {
    case TweetView(source, content) =>
      lastMessage = Some(s"$source : $content")
      tweeterViewGUI.display(lastMessage.get)
    case RegisterTweeter(tweeter: Tweeter) => this.tweeter = Some(tweeter)
    case Retweet => tweeter
      .fold(tweeterViewGUI.display("Impossible de retweeter"))(value => {
        lastMessage.fold(tweeterViewGUI.display("Rien a retweeter"))(value.self ! Tweet(_))
      })

  }
}