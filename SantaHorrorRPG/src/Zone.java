import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Zone extends GameState {

	private int zoneid;
	private JPanel screen;
	
	public Zone(GameModel gm, int width, int height) {
		screenWidth = width;
		screenHeight = height;

		Image background = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"fireplace.jpg");
		screen = new DrawPanel(background);
		
		gm.getFrame().add(screen);
		
		nextState = this;
	}
	
	public void initZone(int zoneID) {
		
	}
	
	public void interact(Position tile) {
		
	}
	
	public boolean canMoveTo(Position tile) {
		return true;
		
	}
	
	public ArrayList<GameObj> getAdjacent(Position tile){
		return new ArrayList<GameObj>();
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
	public void render(GameModel gm) {
		
	}
	
}
