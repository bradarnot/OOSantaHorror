import java.util.ArrayList;
import static java.lang.Math.toIntExact;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game extends Observer{

	private double oldTime;
	private int targetfps;
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
		targetfps = 60;
	}
	
	public void startGame() {
		gameLoop();
	}
	
	public void gameLoop() {
		while(!model.stop) {
			double time = System.currentTimeMillis();
			if((time-oldTime) > (1000/targetfps)) {
				//System.out.println(input);
				state.update(model, input);
				state.render(model);
				state = state.getNextState();
				model.getFrame().setFocusable(true);
				
				if(model.getPlayer() != null) {
					Position playerPos = model.getPlayer().getPosition();
				
					for(int index = 0; index<model.getNextZoneTrigger().size();index++) {
						Trigger potential = model.getNextZoneTrigger().get(index);
						if(potential.inRange(playerPos)) {
							Game.loadLevel(FileManager.loadZone( potential.getNextZone() + ".json"));
						}
					}
				}
				//System.out.println(model.getFrame().getKeyListeners()[0]);
				oldTime = time;
			}
		}
	}
	
	public static void clearLevel() {
		model.setActors(new ArrayList<Actor>());
		model.setNextZoneTrigger(new ArrayList<Trigger>());
		model.setObjects(new ArrayList<GameObj>());
		
	}
	
	public static void loadLevel(JSONObject zone) {
		clearLevel();
		int zone_id = toIntExact((Long) zone.get("zone_id"));
		model.setZone_id(zone_id);
		//objects
		JSONArray objects = (JSONArray) zone.get("objects");
		ArrayList<GameObj> gameObjects = new ArrayList<GameObj>();
		System.out.println(objects.size());
		for (int i=0; i < objects.size(); i++) {
			JSONObject jsonObj = (JSONObject) objects.get(i);
			String name = (String) jsonObj.get("name");
			
			JSONArray jsonPosition = (JSONArray) jsonObj.get("position");
			int[] position = new int[2];
			for (int j=0; j < jsonPosition.size(); j++) {
				position[j] = toIntExact((Long) jsonPosition.get(j));
			}
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
			
			JSONArray jsonPosition = (JSONArray) jsonObj.get("position");
			int[] position = new int[2];
			for (int j=0; j < jsonPosition.size(); j++) {
				position[j] = toIntExact((Long) jsonPosition.get(j));
			}
			Position pos = new Position(position[0], position[1]);
			Monster temp = new Monster();
			temp.loadFromFile(name, pos);
			gameMonsters.add(temp);
		}	
		//NPC
		JSONArray npcs = (JSONArray) zone.get("npcs");
		ArrayList<NPC> gameNPCs = new ArrayList<NPC>();
		for (int i=0; i < npcs.size(); i++) {
			JSONObject jsonObj = (JSONObject) npcs.get(i);
			String name = (String) jsonObj.get("name");
			JSONArray jsonPosition = (JSONArray) jsonObj.get("position");
			int[] position = new int[2];
			for (int j=0; j < jsonPosition.size(); j++) {
				position[j] = toIntExact((Long) jsonPosition.get(j));
			}
			Position pos = new Position(position[0], position[1]);
			NPC temp = new NPC();
			temp.loadFromFile(name, pos);
			gameNPCs.add(temp);
		}
		
		ArrayList<Actor> gameActors = new ArrayList<Actor>();
		for(int index = 0; index < gameMonsters.size(); index++) {
			gameActors.add(gameMonsters.get(index));
		}
		for(int index = 0; index < gameNPCs.size(); index++) {
			gameActors.add(gameNPCs.get(index));
		}
		
		model.setActors(gameActors);
		
		//player
		
		JSONObject playerObj = (JSONObject) zone.get("player");
		String name = (String) playerObj.get("name");
		JSONArray jsonPosition = (JSONArray) playerObj.get("position");
		int[] playerPosition = new int[2];
		for (int i=0; i < jsonPosition.size(); i++) {
			playerPosition[i] = toIntExact((Long) jsonPosition.get(i));
		}
		Position playerPos = new Position(playerPosition[0], playerPosition[1]);
		
		Player player = new Player();
		player.loadFromFile(name, playerPos);
		
		model.setPlayer(player);
		
		JSONArray triggers = (JSONArray) zone.get("triggers");
		ArrayList<Trigger> gameTriggers = new ArrayList<Trigger>();
		for (int i=0; i < triggers.size(); i++) {
			JSONObject jsonObj = (JSONObject) triggers.get(i);
			String nextZone = (String) jsonObj.get("next_zone");
			JSONArray triggerPosition = (JSONArray) jsonObj.get("position");
			int[] triggerPos = new int[2];
			for (int j=0; j < triggerPosition.size(); j++) {
				triggerPos[j] = toIntExact((Long) triggerPosition.get(j));
			}
			
			Trigger temp = new Trigger(triggerPos[0], triggerPos[1], nextZone);
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
		this.input = input;
	}
	
	

}
