import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Pressed");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				System.out.println("Down");
				break;
			case KeyEvent.VK_UP:
				System.out.println("Up");
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Left");
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Right");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
