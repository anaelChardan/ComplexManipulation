package counter

import java.awt.BorderLayout
import java.awt.Container
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.Observable
import java.util.Observer

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel

class GuiCounter(counter: Counter) extends JFrame(counter.name) with Observer {


  val label = new JLabel(Integer.toString(counter.count))

  val buttonPlus = new JButton("+")
  buttonPlus.addActionListener(_ => counter.increment())


  val buttonMinus = new JButton("-")
  buttonMinus.addActionListener(_ => counter.decrement())

  override def update(o: Observable, arg: scala.Any): Unit = {
    label.setText(arg.toString)
  }

  def addComponentsToPane: Unit = {
    val pane = getContentPane
    pane.add(buttonPlus, BorderLayout.NORTH)
    pane.add(label, BorderLayout.CENTER)
    pane.add(buttonMinus, BorderLayout.SOUTH)
  }


}


object GuiCounter {
  def main(args: Array[String]): Unit = {
    createAndShowGUI(Counter("Like", 0))
  }

  def createAndShowGUI(counter: Counter): Unit = {
    val frame = new GuiCounter(counter)
    counter.addObserver(frame)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.addComponentsToPane
    frame.pack()
    frame.setVisible(true)
  }
}