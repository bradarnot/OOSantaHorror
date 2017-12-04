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
	
	public Image getAppearance() {
		return appearance;
	}

	public void setAppearance(Image appearance) {
		this.appearance = appearance;
	}

	protected boolean solid;
	protected Position position;
	
	public void loadFromFile(String name, Position position) {
		
	}
	
	public Interaction interact(boolean byPlayer, String direction) {
		Interaction result = nonPlayerInteractions.get(nonPlayerInteractState);
		if(byPlayer) {
			result = playerInteractions.get(playerInteractState);
			playerInteractState = result.getNextInteraction(direction);
		}else {
			nonPlayerInteractState = result.getNextInteraction(direction);
		}		
		return result;
		
	}
	
	public void swap() {
		this.loadFromFile(swapInTo, position);		
	}
	
	public boolean isObject(String name) {
		return name.equals(this.name);
	}
	
}
