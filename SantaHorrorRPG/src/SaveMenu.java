import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SaveMenu extends GameState {

	private int screenWidth;
	private int screenHeight;
	private JPanel saveMenu;
	private JButton saves;
	private JButton cancel;
	private GridLayout layout;
	private FileManager fileManager;
	
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
	
}