import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.json.simple.JSONObject;

public class LoadMenu extends GameState {

	private Menu loadMenu;
	private JPanel buttonPanel;
	private ArrayList<JButton> loads;
	private JButton cancel;
	private FileManager fileManager;
	
	public LoadMenu(GameModel gm, int width, int height) {
		screenWidth = width;
		screenHeight = height;
		DBConnection dbc = DBConnection.getInstance();
		
		List<Map<String, Object>> saves = dbc.getSaves();
		System.out.println(saves.size());
		
		Image background = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"fireplace.jpg");
		loadMenu = new Menu(background);
		buttonPanel = new JPanel();
		loads = new ArrayList<JButton>();
		cancel = new JButton("Cancel");
		
		for(Map<String,Object> map : saves) {
			for(String key : map.keySet()) {
				//System.out.println(key);
				if(key.equals("player")) {
					JButton load = new JButton((String)map.get(key));
					load.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				        	 	String player = (String) map.get(key);
				        	 	int zoneID = (int) DBConnection.getInstance().getZoneID(player);
				        		gm.getFrame().remove(loadMenu);
					        nextState = new Zone(gm, screenWidth, screenHeight);
					        Game.loadLevel(FileManager.loadZone(zoneID + ".json"));
				         }          
				      });
					
					loads.add(load);
				}
			}
		}
		
		cancel.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            nextState = new MainMenu(gm, screenWidth, screenHeight);
	            gm.getFrame().remove(loadMenu);
	         }          
	      });
		
		if(loads.isEmpty()) {
			loads.add(new JButton("No Save Files"));
		}
		
		buttonPanel.setOpaque(false);
		for(JButton load : loads) {
			buttonPanel.add(load);
		}
		buttonPanel.add(cancel);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		loadMenu.setLayout(new BorderLayout());
	
		loadMenu.add(buttonPanel, BorderLayout.EAST);
		
		gm.getFrame().add(loadMenu);
		
		nextState = this;
	}

	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public void setScreenWidth(int x) {
		this.screenWidth = x;
	}
	
	public void setScreenHeight(int y) {
		this.screenHeight = y;
	}
	
	public void setFileManager(FileManager fm) {
		this.fileManager = fileManager;
	}
	
	public void render(GameModel gm) {
		
		gm.getFrame().repaint();
	}
	
	public void update(GameModel gm, Input input) {
		loadMenu.setBounds(0,0,screenWidth-20,screenHeight-20);    
        loadMenu.setBackground(Color.gray); 
        
		
		gm.getFrame().setSize(screenWidth, screenHeight);  
		gm.getFrame().setLayout(null);  
		gm.getFrame().setVisible(true);
	}
	
	public String toString() {
		return "LoadMenu";
	}
	
}
