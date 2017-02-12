package tweeterToGui

import akka.actor.{Actor, ActorRef, Props}
import registry.Registry
import tweeter.Tweeter.Tweet
import tweeter.Tweeter
import tweeterToGui.TweeterView.{RegisterTweeter, Retweet, TweetView}
import view.TweeterViewGUI

object TweeterView {

  //Classe d'information a la vue
  case class TweetView(source: String, content: String)

  //Retweeter
  case class Retweet()

  //Enregistrement du tweeter pour prendre en charge les retweets
  case class RegisterTweeter(tweeter: Tweeter)

  def apply(name: String): ActorRef =
    Registry.system.actorOf(Props(new TweeterView(new TweeterViewGUI(name))))
}

//Class pour contenir a message et son utilisateur
case class UserContent(username: String, content: String)

//Objet passif
class TweeterView(val tweeterViewGUI: TweeterViewGUI) extends Actor {
  var lastMessage: Option[UserContent] = None
  var tweeter: Option[Tweeter] = None

  tweeterViewGUI.view = Some(this)

  override def receive: Receive = {
    case TweetView(source, content) =>
      lastMessage = Some(UserContent(source, content))
      tweeterViewGUI.display(lastMessage.get.username + ": " + lastMessage.get.content)

    case RegisterTweeter(tweeter: Tweeter) => this.tweeter = Some(tweeter)

      //Gestion des differentes erreurs en utilisant les optionnels
    case Retweet => tweeter
      .fold(tweeterViewGUI.display("Impossible de retweeter"))(value => {
        lastMessage
          .fold(tweeterViewGUI.display("Rien a retweeter"))(usercontent => {
            value.self ! Tweet("Retweet de " + usercontent.username + " : " + usercontent.content)
          })
      })
  }
}