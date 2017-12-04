import java.awt.Image;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Nicholas
 *
 */
public class GameObj {
	protected String name;
	protected int nonPlayerInteractState;
	protected ArrayList<Interaction> nonPlayerInteractions;
	protected int playerInteractState;
	protected ArrayList<Interaction> playerInteractions;
	protected String swapInTo;
	protected Image appearance;
	protected boolean solid;
	protected Position position;
	
	public void loadFromFile(String name, Position position) {
		
	}
	
	public Interaction interact(boolean byPlayer, String direction) {
		
	}
	
	public void swap() {
		
	}
	
	public boolean isObject(String name) {
		
	}
	
}
