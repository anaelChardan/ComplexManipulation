package tweeterToGui

import akka.actor.{Actor, ActorRef, Props}
import registry.Registry
import tweeter.Tweeter.{Retweet, Tweet}
import tweeter.Tweeter
import tweeterToGui.TweeterView._
import view.TweeterViewGUI

object TweeterView {
  val nothingToRetweet = "Rien a retweeter"
  val impossibleToRetweet = "Impossible de retweeter"
  val alreadyRetweeted = "Vous avez déjà retweeter le dernier message"
  val receiveATweet: (String, String) => String = (tweeter: String, content: String) => s"$tweeter : $content"
  val youTweet: (String) => String = (content: String) => s"Vous avez tweeté : $content"
  val retweetedYourMessage: (String) => String =
    (retweeter: String) => s"$retweeter a retweeté votre message"
  val retweetedFrom: (String, String, String) => String =
    (retweeter: String, tweeter: String, content: String) => s"$retweeter a retweeté (depuis $tweeter) : $content"

  //Classe d'information a la vue
  case class TweetView(source: String, content: String)

  case class MakeATweet(content: String)

  case class RetweetView(retweeter: String, tweeter: String, content: String)

  //Retweeter
  case class RetweetLastTweet()

  //Enregistrement du tweeter pour prendre en charge les retweets
  case class RegisterTweeter(tweeter: Tweeter)

  def apply(name: String): ActorRef =
    Registry.system.actorOf(Props(new TweeterView(new TweeterViewGUI(name))))
}

//Class pour contenir a message et son utilisateur
case class UserContent(username: String, content: String, var retweeted: Boolean = false)

//Objet passif
class TweeterView(val tweeterViewGUI: TweeterViewGUI) extends Actor {
  var lastMessage: Option[UserContent] = None
  var tweeter: Option[Tweeter] = None

  tweeterViewGUI.view = Some(this)

  override def receive: Receive = {
    case TweetView(source, content) =>
      lastMessage = Some(UserContent(source, content))
      tweeterViewGUI.display(TweeterView.receiveATweet(source, content))

    case MakeATweet(content: String) =>
      tweeter.fold(tweeterViewGUI.display(TweeterView.impossibleToRetweet))(value => {
        value.self ! Tweet(content)
        tweeterViewGUI.display(TweeterView.youTweet(content))
      })

    case RetweetView(retweeter: String, tweeter: String, content: String) =>
      var message: String = ""
      if (this.tweeter.get.name != tweeter) {
        lastMessage = Some(UserContent(tweeter, content))
        message = TweeterView.retweetedFrom(retweeter, tweeter, content)
      } else {
        message = TweeterView.retweetedYourMessage(retweeter)
      }
      tweeterViewGUI.display(message)

    case RegisterTweeter(tweeter: Tweeter) => this.tweeter = Some(tweeter)

      //Gestion des differentes erreurs en utilisant les optionnels
    case RetweetLastTweet => tweeter
      .fold(tweeterViewGUI.display(TweeterView.impossibleToRetweet))(value => {
        lastMessage
          .fold(tweeterViewGUI.display(TweeterView.nothingToRetweet))(usercontent => {
            if (usercontent.retweeted) {
              tweeterViewGUI.display(TweeterView.alreadyRetweeted)
            } else {
              lastMessage = Some(UserContent(usercontent.username, usercontent.content, retweeted = true))
              value.self ! Retweet(usercontent.username, usercontent.content)
            }
          })
      })
  }
}