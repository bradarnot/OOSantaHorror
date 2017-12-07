import java.util.ArrayList;
import javax.swing.*;

public class GameModel {
	
	private ArrayList<GameObj> objects;
	private ArrayList<Actor> actors;
	private FileManager fileManager;
	private Player player;
	private JFrame f;
	public boolean stop = false;
	
	public GameModel() {
		objects = new ArrayList<GameObj>();
		actors = new ArrayList<Actor>();
		fileManager = new FileManager();
		player = new Player();
		f = new JFrame();
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
		return new Position();
	}
	
	public GameObj getObject(Position pos) {
		return new GameObj();
	}
	
}
