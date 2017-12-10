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


	public GameModel(KeyListener k) {
		this.setNextZoneTrigger(new ArrayList<Trigger>());
		objects = new ArrayList<GameObj>();
		actors = new ArrayList<Actor>();
		fileManager = new FileManager();
		player = new Player(2, 0, 0, 0, 0, 6);
		player.setPosition(new Position(3,3));
		f = new JFrame();
		kl = k;
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
		for (int i=0; i < (width / 32); i++) {
			for (int j=0; j < (height / 32); j++) {
				GameObj border = new GameObj();
				Position position = new Position(i*32, j*32);
				border.loadFromFile("border", position);
				this.objects.add(border);
			}
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
		return results;
	}
	
	/*public ArrayList<GameObj> getObject(Position pos) {
		return new GameObj();
	}*/
	
	public boolean canMoveTo(Position tile) {
		tile.setX(tile.getX() - (tile.getX() % 32));
		tile.setY(tile.getY() - (tile.getY() % 32));
		
		ArrayList<GameObj> objects = this.getObjectsAtPosition(tile);
		for(int index = 0; index < objects.size(); index++) {
			GameObj object = objects.get(index);
			if(object != null && object.isSolid()) {
				return false;
			}	
		}
		
		return true;
		
	}
	
	public ArrayList<GameObj> getAdjacent(Position tile, int range){
		ArrayList<GameObj> result = new ArrayList<GameObj>();
		for(int x = -range; x<range+1; x++) {
			for(int y = -range; y<range+1; y++) {
				ArrayList<GameObj> temp = this.getObjectsAtPosition(new Position(tile.getX() + (x*32), tile.getY()+(y*32)));
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
