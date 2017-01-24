package counter;
import java.util.Observable;

public class Counter extends Observable {
	public String name;
	private int count;

	public Counter(String name, int count) {
		this.name = name;
		this.count = count;
	}
	int getCount() {
		return count;
	}
	void increment() {
//		System.out.println("increment");
		count++;
		setChanged();
		notifyObservers(count);
	}
	void decrement() {
//		System.out.println("decrement");
		count--;
		setChanged();
		notifyObservers(count);
	}
}
