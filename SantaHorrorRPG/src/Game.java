
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
		model = new GameModel();
		state = new MainMenu(model, 900, 500);
		zoneid = 0;
		keyboard = new Keyboard();
		keyboard.attach(this);
	}
	
	public void startGame() {
		gameLoop();
	}
	
	public void gameLoop() {
		while(!model.stop) {
			state.update(model, null);
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

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		this.input = input;
		System.out.println("YAY :) ");
	}

}
