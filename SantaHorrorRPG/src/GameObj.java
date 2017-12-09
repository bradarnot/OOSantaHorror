import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	protected BufferedImage appearance;
	protected boolean solid;
	protected Position position;
	
	public void loadImage() {
		try {
			appearance = ImageIO.read(new File("img" + File.separator +"alicesheet.jpg"));
		} catch (IOException e) {
			System.out.println("Could not load image!!");
			e.printStackTrace();
		}
		System.out.println("Image Loaded");
	}
	
	public Image getAppearance() {
		return appearance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNonPlayerInteractState() {
		return nonPlayerInteractState;
	}

	public void setNonPlayerInteractState(int nonPlayerInteractState) {
		this.nonPlayerInteractState = nonPlayerInteractState;
	}

	public ArrayList<Interaction> getNonPlayerInteractions() {
		return nonPlayerInteractions;
	}

	public void setNonPlayerInteractions(ArrayList<Interaction> nonPlayerInteractions) {
		this.nonPlayerInteractions = nonPlayerInteractions;
	}

	public int getPlayerInteractState() {
		return playerInteractState;
	}

	public void setPlayerInteractState(int playerInteractState) {
		this.playerInteractState = playerInteractState;
	}

	public ArrayList<Interaction> getPlayerInteractions() {
		return playerInteractions;
	}

	public void setPlayerInteractions(ArrayList<Interaction> playerInteractions) {
		this.playerInteractions = playerInteractions;
	}

	public String getSwapInTo() {
		return swapInTo;
	}

	public void setSwapInTo(String swapInTo) {
		this.swapInTo = swapInTo;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setAppearance(Image appearance) {
		this.appearance = (BufferedImage) appearance;
	}
	
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
