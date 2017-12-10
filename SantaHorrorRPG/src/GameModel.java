import java.awt.event.*;
import java.util.*;
import javax.swing.*;

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


	public GameModel(KeyListener k) {
		this.setNextZoneTrigger(new ArrayList<Trigger>());
		objects = new ArrayList<GameObj>();
		actors = new ArrayList<Actor>();
		fileManager = new FileManager();
		player = new Player(2, 0, 0, 0, 0, 0);
		player.setPosition(new Position(3,3));
		f = new JFrame();
		kl = k;
		f.addKeyListener(kl);
		tileSize = 32;
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
	
	public GameObj getObjectAtPosition(Position position) {
		for(int index=0;index<this.objects.size();index++) {
			if (this.objects.get(index).getPosition().equalPos(position)) {
				return this.objects.get(index);
			}
		}
		return null;
	}
	
	public GameObj getObject(Position pos) {
		return new GameObj();
	}
	
	public boolean canMoveTo(Position tile) {
		GameObj object = this.getObject(tile);
		if(object != null && object.isSolid()) {
			return false;
		}
		return true;
		
	}
	
	public ArrayList<GameObj> getAdjacent(Position tile, int range){
		ArrayList<GameObj> result = new ArrayList<GameObj>();
		for(int x = -range; x<range+1; x++) {
			for(int y = -range; y<range+1; y++) {
				GameObj temp = this.getObject(new Position(tile.getX() + x, tile.getY()+y));
				if((x != 0 || y !=0) && temp != null) {
					result.add(temp);
				}
			}
		}
		return result;
	}
	
}
