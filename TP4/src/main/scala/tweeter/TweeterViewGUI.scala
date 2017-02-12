package tweeter

import java.awt.event.ActionEvent
import java.awt.{Container, Dimension}
import javax.swing._

import akka.actor.ActorRef
import tweeter.TweeterView.Retweet

/**
  * Created by anael on 12/02/2017.
  */
class TweeterViewGUI(val name: String) extends JFrame {
  val textArea: JTextArea = new JTextArea()
  val widthContent: Int = 400
  val heightContent: Int = 200
  var view: Option[TweeterView] = None

  (() => {
    this setDefaultCloseOperation WindowConstants.EXIT_ON_CLOSE
    this setPreferredSize new Dimension(widthContent, heightContent)

    this pack()
    this setVisible true
    this setTitle name

    getContentPane setLayout new BoxLayout(getContentPane, BoxLayout.Y_AXIS)
    getContentPane add textArea
    val button = new JButton("Retweeter")

    button.addActionListener((_: ActionEvent) => view.fold(this display "TweeterView inconnue")(value => value.self ! Retweet))

    getContentPane add button
    this display ".....::::ZONE DE TWEET::::....."
  }) ()


  def display(message: String): Unit = textArea append message + "\n"

}

object TweeterViewGUI {

}

