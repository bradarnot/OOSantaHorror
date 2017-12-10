import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
	public void loadImage(String filename) {
		try {
			this.setAppearance(ImageIO.read(new File("img" + File.separator + filename)));
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
	
	public void loadFromFile(String filename, Position position) {
		JSONObject json = JsonParser.getJson("objects", filename);
		this.setName(filename);
		this.setNonPlayerInteractState((int) json.get("nonPlayerInteractState"));
		this.setPlayerInteractState((int) json.get("playerInteractState"));
		this.loadImage((String) json.get("src"));
		this.setSolid((boolean) json.get("solid"));
		this.setPosition(position);
		this.setSwapInTo((String) json.get("swapInTo"));	

		JSONArray fileInteractions = (JSONArray) json.get("nonPlayerInteractions");
		ArrayList<Interaction> nonPlayerInteractions = new ArrayList<Interaction>();
		for (int i=0; i < fileInteractions.size(); i++) {
			JSONObject jsonObj = (JSONObject) fileInteractions.get(i);
			String dialogue = (String) jsonObj.get("dialog");
			boolean swap = (boolean) jsonObj.get("swap");
			String effectDirection = (String) jsonObj.get("effect");
			int northInteraction = (int) jsonObj.get("north");
			int eastInteraction = (int) jsonObj.get("east");
			int southInteraction = (int) jsonObj.get("south");
			int westInteraction = (int) jsonObj.get("west");	
			Interaction temp = new Interaction();
			
			temp.setDialogue(dialogue);
			temp.setSwap(swap);
			temp.setEffectDirection(effectDirection);
			temp.setNorthInteraction(northInteraction);
			temp.setEastInteraction(eastInteraction);
			temp.setSouthInteraction(southInteraction);
			temp.setWestInteraction(westInteraction);
			
			nonPlayerInteractions.add(temp);
		}
		this.setNonPlayerInteractions(nonPlayerInteractions);
		
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
		this.loadFromFile(swapInTo, this.getPosition());		
	}
	
	public boolean isObject(String name) {
		return name.equals(this.name);
	}
	
}
