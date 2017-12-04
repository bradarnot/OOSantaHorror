import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameModel {
	
	private ArrayList<GameObj> objects;
	private ArrayList<Actor> actors;
	private FileManager fileManager;
	private Player player;
	private JPanel menu;
	private ArrayList<JButton> buttons;
	
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
	
	public JPanel getMenu() {
		return menu;
	}
	
	public void setMenu(JPanel menu) {
		this.menu = menu;
	}
	
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	
	public Position getPosition(GameObj object) {
		return new Position();
	}
	
	public GameObj getObject(Position pos) {
		return new GameObj();
	}
	
}
