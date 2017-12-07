
public class Game extends Observer{

	private int oldTime;
	private Zone zone;
	private FileManager fileManager;
	private DBConnection dbc;
	private int zoneid;
	private int deaths;
	private GameState state;
	private GameModel model;
	private Keyboard keyboard;
	private Input input;
	
	public Game() {
		keyboard = new Keyboard();
		keyboard.attach(this);
		model = new GameModel(keyboard);
		state = new MainMenu(model, 900, 500);
		zoneid = 0;
	}
	
	public void startGame() {
		gameLoop();
	}
	
	public void gameLoop() {
		while(!model.stop) {
			state.update(model, null);
			state.render(model);
			state = state.getNextState();
			model.getFrame().setFocusable(true);
			//System.out.println(model.getFrame().getKeyListeners()[0]);
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

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		this.input = input;
	}

}
