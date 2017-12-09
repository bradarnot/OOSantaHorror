import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game extends Observer{

	private int oldTime;
	private Zone zone;
	private FileManager fileManager;
	private DBConnection dbc;
	private int zoneid;
	private int deaths;
	private GameState state;
	private static GameModel model;
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
	
	public static void loadLevel(JSONObject zone) {
		JSONArray objects = (JSONArray) zone.get("objects");
		ArrayList<GameObj> gameObjects = new ArrayList<GameObj>();
		for (int i=0; i < objects.size(); i++) {
			JSONObject jsonObj = (JSONObject) objects.get(i);
			String name = (String) jsonObj.get("name");
			int[] position = (int[]) jsonObj.get("position");
			Position pos = new Position(position[0], position[1]);
			GameObj temp = new GameObj();
			temp.loadFromFile(name, pos);
			gameObjects.add(temp);
		}
		model.setObjects(gameObjects);
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
