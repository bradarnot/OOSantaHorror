import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Zone extends GameState {

	private int zoneid;
	private JPanel screen;
	
	public Zone(GameModel gm, int width, int height) {
		screenWidth = 1024;
		screenHeight = 1024;
		
		gm.setWidth(1024);
		gm.setHeight(1024);
		
		gm.setDimensions(width, height);
		//Image character = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"alicesheet.png");
		screen = new DrawPanel(gm, screenWidth, screenHeight);
		
		gm.getPlayer().loadImage("alicesheet.png");
		
		gm.getFrame().add(screen);
		
		nextState = this;
	}
	
	public void initZone(int zoneID) {
		
	}
	
	public void interact(Position tile) {
		
	}
	
	
	public void update(GameModel gm, Input input) {
		screen.setBounds(0,0,screenWidth-20,screenHeight-20);  
		
		gm.getPlayer().update(gm, input);
        
		gm.getFrame().setSize(screenWidth, screenHeight);  
		gm.getFrame().setLayout(null);  
		gm.getFrame().setVisible(true);
	}
	
	public void render(GameModel gm)
	{
		//gm.getPlayer().render(gm, g);
		gm.getFrame().repaint();
	}
	
	public String toString() {
		return "Zone";
	}
	
}
