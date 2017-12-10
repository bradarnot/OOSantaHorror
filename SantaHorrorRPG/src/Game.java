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
		input = new Input();
	}
	
	public void startGame() {
		gameLoop();
	}
	
	public void gameLoop() {
		while(!model.stop) {
			//System.out.println(input);
			state.update(model, input);
			state.render(model);
			state = state.getNextState();
			model.getFrame().setFocusable(true);
			Position playerPos = model.getPlayer().getPosition();
			
			for(int index = 0; index<model.getNextZoneTrigger().size();index++) {
				Trigger potential = model.getNextZoneTrigger().get(index);
				if(potential.inRange(playerPos)) {
					Game.loadLevel(FileManager.loadZone( potential.getNextZone() + ".json"));
				}
			}
			//System.out.println(model.getFrame().getKeyListeners()[0]);
		}
	}
	
	public static void loadLevel(JSONObject zone) {
		int zone_id = (int) zone.get("zone_id");
		model.setZone_id(zone_id);
		//objects
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
		//Monster
		JSONArray monsters = (JSONArray) zone.get("monsters");
		ArrayList<Monster> gameMonsters = new ArrayList<Monster>();
		for (int i=0; i < monsters.size(); i++) {
			JSONObject jsonObj = (JSONObject) monsters.get(i);
			String name = (String) jsonObj.get("name");
			int[] position = (int[]) jsonObj.get("position");
			Position pos = new Position(position[0], position[1]);
			int speed = (int) jsonObj.get("speed");
			int direction = (int) jsonObj.get("direction");
			int health = (int) jsonObj.get("health");
			int moveType = (int) jsonObj.get("moveType");
			int attackPower = (int) jsonObj.get("attackPower");
			Monster temp = new Monster(speed, 0, direction, health, moveType, attackPower);
			temp.loadFromFile(name, pos);
			gameMonsters.add(temp);
		}	
		//NPC
		JSONArray npcs = (JSONArray) zone.get("npcs");
		ArrayList<NPC> gameNPCs = new ArrayList<NPC>();
		for (int i=0; i < npcs.size(); i++) {
			JSONObject jsonObj = (JSONObject) npcs.get(i);
			String name = (String) jsonObj.get("name");
			int[] position = (int[]) jsonObj.get("position");
			Position pos = new Position(position[0], position[1]);
			int speed = (int) jsonObj.get("speed");
			int direction = (int) jsonObj.get("direction");
			int health = (int) jsonObj.get("health");
			int moveType = (int) jsonObj.get("moveType");
			int fear = (int) jsonObj.get("fear");
			NPC temp = new NPC(speed, 0, direction, health, moveType, fear);
			temp.loadFromFile(name, pos);
			gameNPCs.add(temp);
		}
		
		ArrayList<Actor> gameActors = new ArrayList<Actor>();
		
		model.setObjects(gameObjects);		
		
		JSONArray triggers = (JSONArray) zone.get("triggers");
		ArrayList<Trigger> gameTriggers = new ArrayList<Trigger>();
		for (int i=0; i < triggers.size(); i++) {
			JSONObject jsonObj = (JSONObject) triggers.get(i);
			String nextZone = (String) jsonObj.get("next_zone");
			int[] position = (int[]) jsonObj.get("position");
			Trigger temp = new Trigger(position[0], position[1], nextZone);
			gameTriggers.add(temp);
		}
		model.setNextZoneTrigger(gameTriggers);
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
		System.out.println(input);
		this.input = input;
	}
	
	

}
