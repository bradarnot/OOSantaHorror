import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends GameState {

	private int screenWidth;
	private int screenHeight;
	private JPanel mainMenu;
	private JButton quit;
	private JButton start;
	private JButton loadGame;
	private JButton tutorial;
	private GridLayout layout;
	
	public MainMenu() {
		screenWidth = 600;
		screenHeight = 600;
		
		mainMenu = new JPanel();
		quit = new JButton("Quit");
		start = new JButton("Start New Game");
		loadGame = new JButton("Load Game");
		tutorial = new JButton("Tutorial");
		
		layout = new GridLayout();
		
		mainMenu.add(start);
		mainMenu.add(loadGame);
		mainMenu.add(tutorial);
		mainMenu.add(quit);
		mainMenu.setLayout(layout);
		
		
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
		mainMenu.setBounds(10,10,screenWidth-20,screenHeight-20);    
        mainMenu.setBackground(Color.gray); 
        
		gm.getFrame().add(mainMenu);
		gm.getFrame().setSize(screenWidth, screenHeight);  
		gm.getFrame().setLayout(null);  
		gm.getFrame().setVisible(true);
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
}
