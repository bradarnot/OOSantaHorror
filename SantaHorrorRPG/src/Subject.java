import java.util.ArrayList;

public abstract class Subject {
	protected ArrayList<Observer> observers;

	public abstract void notify(Input input);
	
	public Subject() {
		super();
		this.observers = new ArrayList<Observer>();
	}

	public void attach(Observer o) {
		observers.add(o);
		System.out.println("Attatched");
	}
	
	public void dettach(Observer o) {
		observers.remove(o);
		System.out.println("Dettached");
	}
}
