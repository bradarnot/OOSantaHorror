import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class MainMenu extends GameState {

	private Menu mainMenu;
	private JPanel buttonPanel;
	private JButton quit;
	private JButton start;
	private JButton loadGame;
	private JButton tutorial;
	
	public MainMenu(GameModel gm, int width, int height) {
		screenWidth = width;
		screenHeight = height;
		
		Image background = Toolkit.getDefaultToolkit().createImage("img" + File.separator +"fireplace.jpg");
		mainMenu = new Menu(background);
		buttonPanel = new JPanel();
		quit = new JButton("Quit");
		start = new JButton("Start New Game");
		loadGame = new JButton("Load Game");
		tutorial = new JButton("Tutorial");
		
		
		start.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            nextState = new Zone(gm, screenWidth, screenHeight);
	            gm.getFrame().remove(mainMenu);
	         }          
	      });
		loadGame.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            nextState = new LoadMenu(gm, screenWidth, screenHeight);
	            gm.getFrame().remove(mainMenu);
	         }
	      });
		quit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            gm.stop = true;
	            gm.getFrame().dispose();
	         }
	      });
		
		buttonPanel.add(start);
		buttonPanel.add(loadGame);
		buttonPanel.add(tutorial);
		buttonPanel.add(quit);
		
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainMenu.setLayout(new BorderLayout());
		
		mainMenu.add(buttonPanel,BorderLayout.EAST);
		
		gm.getFrame().add(mainMenu);
		gm.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
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
	
	public void render(GameModel gm) {
		gm.getFrame().repaint();
	}
	
	public void update(GameModel gm, Input input) {
		mainMenu.setBounds(0,0,screenWidth-20,screenHeight-20);    
        //mainMenu.setBackground(Color.gray); 
        
		
		gm.getFrame().setSize(screenWidth, screenHeight);  
		gm.getFrame().setLayout(null);  
		gm.getFrame().setVisible(true);
	}
	
	public String toString() {
		return "MainMenu";
	}
	
}
