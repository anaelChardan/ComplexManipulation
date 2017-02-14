package view

import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing._

import tweeterToGui.TweeterView
import tweeterToGui.TweeterView.{MakeATweet, RetweetLastTweet}

/**
  * Created by anael on 12/02/2017.
  */
class TweeterViewGUI(val name: String) extends JFrame(name) {
  val textArea: JTextArea = {
    val area = new JTextArea(".....::::ZONE DE TWEET::::.....\n")
    area.setEditable(false)
    area
  }

  var view: Option[TweeterView] = None

  invokeLater(() => createAndShowGUI())

  private def createAndShowGUI(): Unit = {

    def doIfViewIsDefined(f: TweeterView => Unit): Unit = {
      view.fold(this display "TweeterView inconnue")(value => f(value))
    }

    val WIDTH_CONTENT: Int = 400
    val HEIGHT_CONTENT: Int = 400

    this setDefaultCloseOperation WindowConstants.EXIT_ON_CLOSE
    this setPreferredSize new Dimension(WIDTH_CONTENT, HEIGHT_CONTENT)

    this pack()
    this setVisible true

    getContentPane setLayout new BoxLayout(getContentPane, BoxLayout.Y_AXIS)
    getContentPane add textArea

    getContentPane add new JSeparator()

    val tweetButton = new JButton("Tweeter")
    val retweetButton = new JButton("Retweeter")

    val tweetZone = new JTextArea()

    retweetButton.addActionListener((_: ActionEvent) => doIfViewIsDefined((value) => value.self ! RetweetLastTweet))
    tweetButton.addActionListener((_: ActionEvent) => if (!tweetZone.getText.trim.isEmpty) {
      doIfViewIsDefined((value) => value.self ! MakeATweet(tweetZone.getText.trim))
      tweetZone.setText("")
    })

    getContentPane add tweetZone
    getContentPane add tweetButton
    getContentPane add retweetButton
  }

  //Permet d'afficher les tweets reçu
  def display(message: String): Unit = {
    invokeLater(() => textArea append message + "\n")
  }

  private def invokeLater(runnable: Runnable): Unit = {
    javax.swing.SwingUtilities.invokeLater(runnable)
  }
}

