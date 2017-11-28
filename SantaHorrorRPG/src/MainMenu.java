
public class MainMenu extends GameState {

	private int screenWidth;
	private int screenHeight;
	private JPanel mainMenu;
	private JButton quit;
	private JButton start;
	private JButton loadGame;
	private JButton tutorial;
	private GridLayout layout;
	
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
		
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
}
