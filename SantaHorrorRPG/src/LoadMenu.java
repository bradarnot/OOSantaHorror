import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class LoadMenu extends GameState {

	private int screenWidth;
	private int screenHeight;
	private Menu loadMenu;
	private JPanel buttonPanel;
	private JButton[] loads;
	private JButton cancel;
	private FileManager fileManager;
	
	public LoadMenu(GameModel gm, int width, int height) {
		screenWidth = width;
		screenHeight = height;
		
		Image background = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"fireplace.jpg");
		loadMenu = new Menu(background);
		buttonPanel = new JPanel();
		loads = new JButton[3];
		cancel = new JButton("Cancel");
		
		
		cancel.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            nextState = new MainMenu(gm, screenWidth, screenHeight);
	            gm.getFrame().remove(loadMenu);
	         }          
	      });
		
		buttonPanel.setOpaque(false);
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
