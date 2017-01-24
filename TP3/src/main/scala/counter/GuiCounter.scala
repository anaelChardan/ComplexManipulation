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

object GuiCounter {
  private def createAndShowGUI(counter: Counter) {
    val frame = new GuiCounter(counter)
    counter.addObserver(frame)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.addComponentsToPane()
    frame.pack()
    frame.setVisible(true)
  }

  def main(args: Array[String]) {
    createAndShowGUI(new Counter("Like", 0))
  }
}

class GuiCounter(var counter: Counter) extends JFrame(counter.name) with Observer {
  label = new JLabel(Integer.toString(counter.getCount))
  buttonPlus = new JButton("+")
  buttonPlus.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) {
      counter.increment()
    }
  })
  buttonMinus = new JButton("-")
  buttonMinus.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) {
      counter.decrement()
    }
  })
  private[counter] var label = null
  private[counter] var buttonPlus = null
  private[counter] var buttonMinus = null

  def update(o: Observable, arg: Any) {
    label.setText(arg.toString)
  }

  def addComponentsToPane() {
    val pane = getContentPane
    pane.add(buttonPlus, BorderLayout.NORTH)
    pane.add(label, BorderLayout.CENTER)
    pane.add(buttonMinus, BorderLayout.SOUTH)
  }
}
