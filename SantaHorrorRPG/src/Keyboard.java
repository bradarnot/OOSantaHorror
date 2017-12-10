import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard extends Subject implements KeyListener {
	private Input input;
	
	public Keyboard() {
		super();
		this.input = new Input();
		System.out.println("Initialized");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Pressed");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
//				System.out.println("Down");
				this.input.setUp(true);
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
//				System.out.println("Up");
				this.input.setDown(true);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
//				System.out.println("Left");
				this.input.setLeft(true);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
//				System.out.println("Right");
				this.input.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
//				System.out.println("Space");
				this.input.setInteract(true);
				break;
		}
		this.notify(input);
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("Released");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
//				System.out.println("Down");
				this.input.setUp(false);
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
//				System.out.println("Up");
				this.input.setDown(false);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
//				System.out.println("Left");
				this.input.setLeft(false);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
//				System.out.println("Right");
				this.input.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
//				System.out.println("Space");
				this.input.setInteract(false);
				break;
		}
		this.notify(input);
	}

	@Override
	public void notify(Input input) {
		for(int index=0;index<this.observers.size();index++) {
			this.observers.get(index).update(input);
		}
		
	}
	
	public String toString() {
		return "Keyboard:)";
	}
}
