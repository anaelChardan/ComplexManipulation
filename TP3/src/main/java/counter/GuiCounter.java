package counter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuiCounter extends JFrame implements Observer {
	JLabel label;
	JButton buttonPlus;
	JButton buttonMinus;
	Counter counter;
	
	public GuiCounter(final Counter counter) {
		super(counter.name);
		this.counter = counter;
		label = new JLabel(Integer.toString(counter.getCount()));
		
		buttonPlus = new JButton("+");
		buttonPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter.increment();
			}
		});
		buttonMinus = new JButton("-");
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter.decrement();
			}
		});
	}
	
	@Override
	public void update(Observable o, Object arg) {
		label.setText(arg.toString());	
	}
	
	public void addComponentsToPane() {
		Container pane = getContentPane();
		pane.add(buttonPlus, BorderLayout.NORTH);
		pane.add(label, BorderLayout.CENTER);
		pane.add(buttonMinus, BorderLayout.SOUTH);
	}
	private static void createAndShowGUI(Counter counter) {
		GuiCounter frame = new GuiCounter(counter);
		counter.addObserver(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentsToPane();
		frame.pack(); 
		frame.setVisible(true);
	}
	public static void main(String[] args) {
        createAndShowGUI(new Counter("Like", 0)); 

	}
}
