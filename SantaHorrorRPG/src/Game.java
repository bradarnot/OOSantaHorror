
public class Game {

	private int oldTime;
	private Zone zone;
	private FileManager fileManager;
	private int zoneid;
	private int deaths;
	private GameState state;
	private GameModel model;
	
	public Game() {
		state = new MainMenu();
		zoneid = 0;
		model = new GameModel();
	}
	
	public void startGame() {
		state.update(model, null);
		state.render(model);
		state = state.getNextState();
	}
	
	public void gameLoop() {
		
	}
	
	public void loadLevel() {
		
	}
	
	public void save() {
		
	}
	
	public void exitGame() {
		
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.startGame();

	}

}
