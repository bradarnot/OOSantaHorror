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
		
		playMusic("audio" + File.separator + "sugar_plum_fairy.wav");
		
		gm.getFrame().add(screen);
		
		nextState = this;
	}
	
	public void initZone(int zoneID) {
		
	}
	
	// solution adapted from https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
	public static void playMusic(String path) 
    {       
        AudioPlayer player = AudioPlayer.player;
        AudioStream stream;
        AudioData data;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream(path);
            stream = new AudioStream(test);
            AudioPlayer.player.start(stream);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        player.start(loop);
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
