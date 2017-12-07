import java.util.ArrayList;

public abstract class Subject {
	protected ArrayList<Observer> observers;

	public abstract void notify(Input input);
	
	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void dettach(Observer o) {
		observers.remove(o);
	}
}
