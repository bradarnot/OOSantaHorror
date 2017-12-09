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
		screenWidth = width;
		screenHeight = height;

		//Image character = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"alicesheet.png");
		BufferedImage character = null;
		try {
			character = ImageIO.read(new File("img" + File.separator +"alicesheet.jpg"));
		} catch (IOException e) {
			System.out.println("Could not load character image");
			e.printStackTrace();
		}
		screen = new DrawPanel(character, gm);
		
		gm.getPlayer().loadImage();
		
		gm.getFrame().add(screen);
		
		nextState = this;
	}
	
	public void initZone(int zoneID) {
		
	}
	
	public void interact(Position tile) {
		
	}
	
	
	public void update(GameModel gm, Input input) {
		screen.setBounds(0,0,screenWidth-20,screenHeight-20);    
        
		gm.getFrame().setSize(screenWidth, screenHeight);  
		gm.getFrame().setLayout(null);  
		gm.getFrame().setVisible(true);
	}
	
	public void render(GameModel gm)
	{
		//gm.getPlayer().render(gm, g);
		gm.getFrame().repaint();
	}
	
}
