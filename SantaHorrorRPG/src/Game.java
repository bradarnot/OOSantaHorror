
public class Game {

	private int oldTime;
	private Zone zone;
	private FileManager fileManager;
	private DBConnection dbc;
	private int zoneid;
	private int deaths;
	private GameState state;
	private GameModel model;
	
	public Game() {
		model = new GameModel();
		state = new MainMenu(model, 900, 500);
		zoneid = 0;
		dbc = new DBConnection("santa_horror", "santa", "password");
	}
	
	public void startGame() {
		gameLoop();
	}
	
	public void gameLoop() {
		while(!model.stop) {
			state.update(model, null);
			state.setDBConnection(dbc);
			state.render(model);
			state = state.getNextState();
			//System.out.println(state);
		}
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
