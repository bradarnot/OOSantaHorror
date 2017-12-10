import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import sun.audio.*;

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
		
		music();
		
		gm.getFrame().add(screen);
		
		nextState = this;
	}
	
	public void initZone(int zoneID) {
		
	}
	
	public static void music(String path) 
    {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream(path);
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
	
	
	public void update(GameModel gm, Input input) {
		screen.setBounds(0,0,screenWidth-20,screenHeight-20);
		
		if(gm.getPlayer() != null) {
			gm.getPlayer().update(gm, input);
		}
		for(Actor a : gm.getActors()) {
			a.update(gm, input);
		}
        
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
