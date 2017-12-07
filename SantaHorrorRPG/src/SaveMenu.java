import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SaveMenu extends GameState {

	private int screenWidth;
	private int screenHeight;
	private JPanel saveMenu;
	private JButton[] saves;
	private JButton cancel;
	private GridLayout layout;
	private FileManager fileManager;
	
	public SaveMenu(GameModel gm, int width, int height) {
		screenWidth = width;
		screenHeight = height;
		
		saveMenu = new JPanel();
		saves = new JButton[3];
		cancel = new JButton("Cancel");
		
		layout = new GridLayout();
		
		
		cancel.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            nextState = new MainMenu(gm, screenWidth, screenHeight);
	            gm.getFrame().remove(saveMenu);
	         }          
	      });
		
	
		saveMenu.add(cancel);
		saveMenu.setLayout(layout);
		
		gm.getFrame().add(saveMenu);
		
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
		
	}
	
	public void render(GameModel gm) {
		
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
	public String toString() {
		return "SaveMenu";
	}
	
}
