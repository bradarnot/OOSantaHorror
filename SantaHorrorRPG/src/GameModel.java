import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameModel {
	
	private ArrayList<GameObj> objects;
	private ArrayList<Actor> actors;
	private FileManager fileManager;
	private Player player;
	private JFrame f;
	private KeyListener kl;
	private DrawPanel drawScreen;
	private int tileSize;
	private int zone_id;
	public boolean stop = false;
	private ArrayList<Trigger> nextZoneTrigger;
	private int width;
	private int height;


	public GameModel(KeyListener kl) {
		this.setNextZoneTrigger(new ArrayList<Trigger>());
		objects = new ArrayList<GameObj>();
		actors = new ArrayList<Actor>();
		fileManager = new FileManager();
		player = new Player(2, 0, 0, 0, 0, 6);
		player.setPosition(new Position(128, 128));
		f = new JFrame();
		f.addKeyListener(kl);
		tileSize = 32;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public ArrayList<Trigger> getNextZoneTrigger() {
		return nextZoneTrigger;
	}


	public void setNextZoneTrigger(ArrayList<Trigger> nextZoneTrigger) {
		this.nextZoneTrigger = nextZoneTrigger;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	
	public int getZone_id() {
		return zone_id;
	}


	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}


	public DrawPanel getScreen() {
		return this.drawScreen;
	}
	
	public ArrayList<GameObj> getObjects() {
		return objects;
	}
	
	public void setObjects(ArrayList<GameObj> objects) {
		this.objects = objects;
		System.out.println(this.width);
		System.out.println(this.height);
		
		Position position;
		for(int i = -this.getTileSize(); i<=this.width; i+=this.getTileSize()) {
			GameObj borderTop = new GameObj();
			position = new Position(i,-32);
			borderTop.loadFromFile("border", position);
			this.objects.add(borderTop);
			
			GameObj borderBot = new GameObj();
			position = new Position(i, this.height - (this.height%this.getTileSize()));
			borderBot.loadFromFile("border", position);
			this.objects.add(borderBot);
		}
		for(int i = 0; i<this.height; i+=this.getTileSize()) {
			GameObj borderLeft = new GameObj();
			position = new Position(0,-32);
			borderLeft.loadFromFile("border", position);
			this.objects.add(borderLeft);
			
			GameObj borderRight = new GameObj();
			position = new Position(this.width - (this.width%this.getTileSize()), i);
			borderRight.loadFromFile("border", position);
			this.objects.add(borderRight);
		}		
	}
	
	public ArrayList<Actor> getActors() {
		return actors;
	}
	
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	
	public JFrame getFrame() {
		return f;
	}
	
	public KeyListener getKeyListener() {
		return kl;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Position getPosition(GameObj object) {
		for(int i=0;i<this.objects.size();i++) {
			if (this.objects.get(i).isObject(object.getName())) return this.objects.get(i).getPosition();
		}
		return null;
	}
	
	public ArrayList<GameObj> getObjectsAtPosition(Position position) {
		ArrayList<GameObj> results = new ArrayList<GameObj>();
		for(int index=0;index<this.objects.size();index++) {
			if (this.objects.get(index).getPosition().equalPos(position)) {
				results.add(this.objects.get(index));
			}
		}
		for(int index=0;index<this.actors.size();index++) {
			if (this.actors.get(index).getPosition().equalPos(position)) {
				results.add(this.actors.get(index));
			}
		}
		if (this.player.getPosition().equalPos(position)) {
			results.add(this.player);
		}
		return results;
	}
	
	/*public ArrayList<GameObj> getObject(Position pos) {
		return new GameObj();
	}*/
	
	public boolean canMoveTo(Position tile, String ignore) {
		tile.setX(tile.getX() - (tile.getX() % this.getTileSize()));
		tile.setY(tile.getY() - (tile.getY() % this.getTileSize()));
		
		ArrayList<GameObj> objectsTopLeft = this.getObjectsAtPosition(tile);
		tile.setX(tile.getX()+this.getTileSize());
		tile.setY(tile.getY()+this.getTileSize());
		
		ArrayList<GameObj> objectsBotRight = this.getObjectsAtPosition(tile);
		
		for(int index = 0; index < objectsTopLeft.size(); index++) {
			GameObj object = objectsTopLeft.get(index);
			if(object != null && object.isSolid() && !object.isObject(ignore)) {
				return false;
			}	
		}
		
		for(int index = 0; index < objectsBotRight.size(); index++) {
			GameObj object = objectsBotRight.get(index);
			if(object != null && object.isSolid() && !object.isObject(ignore)) {
				return false;
			}	
		}
		
		return true;
		
	}
	
	public ArrayList<GameObj> getAdjacent(Position tile, int range){
		ArrayList<GameObj> result = new ArrayList<GameObj>();
		for(int x = -range; x<range+1; x++) {
			for(int y = -range; y<range+1; y++) {
				ArrayList<GameObj> temp = this.getObjectsAtPosition(new Position(tile.getX() + (x*this.getTileSize()), tile.getY()+(y*this.getTileSize())));
				if((x != 0 || y !=0) && temp.size() !=  0) {
					for(int index = 0; index < temp.size(); index++) {
						result.add(temp.get(index));	
					}
				}
			}
		}
		return result;
	}
	
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
}
