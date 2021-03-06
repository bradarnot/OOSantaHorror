import static java.lang.Math.toIntExact;

import java.awt.Graphics;
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
	protected int imageSize;
	
	public void loadImage(String filename) {
		try {
			this.setAppearance(ImageIO.read(new File("img" + File.separator + filename)));
		} catch (IOException e) {
			System.out.println("Could not load image!!");
			e.printStackTrace();
		}
		//System.out.println("Image Loaded");
	}
	
	
	
	public int getImageSize() {
		return imageSize;
	}



	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
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
		if(nonPlayerInteractState != -1) {
			this.nonPlayerInteractState = nonPlayerInteractState;			
		}

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
		if(playerInteractState != -1) {
			this.playerInteractState = playerInteractState;	
		}
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
		//System.out.println(filename);
		JSONObject json = JsonParser.getJson("objects", filename + ".json");
		this.setName(filename);
		try{
			this.setNonPlayerInteractState(toIntExact((Long) json.get("nonPlayerInteractState")));			
		}
		catch(Exception e) {
			this.setNonPlayerInteractState(0);
		}
		try{
			this.setPlayerInteractState(toIntExact((Long) json.get("playerInteractState")));	
		}
		catch(Exception e) {
			this.setPlayerInteractState(0);
		}
		try{
			this.loadImage((String) json.get("src"));
		}
		catch(Exception e) {
			this.loadImage(this.getName() + ".png");
		}
		boolean solid;
		try{
			solid = (toIntExact((Long) json.get("solid")) != 0);
		}
		catch(Exception e) {
			solid = false;
		}
		this.setSolid(solid);
		try{
			this.setSwapInTo((String) json.get("swapInTo"));
		}
		catch(Exception e) {
			this.setSwapInTo(null);
		}
		
		this.setPosition(position);
		
		int imageSize;
		try {
		    imageSize = toIntExact((Long) json.get("imageSize"));
		}
		catch(Exception e){
			imageSize = 32;
		}
		this.setImageSize(imageSize);
		
		JSONArray fileInteractions = (JSONArray) json.get("nonPlayerInteractions");
		ArrayList<Interaction> nonPlayerInteractions = new ArrayList<Interaction>();
		for (int i=0; i < fileInteractions.size(); i++) {
			JSONObject jsonObj = (JSONObject) fileInteractions.get(i);
			String dialogue;
			try{
				dialogue = (String) jsonObj.get("dialog");
			}
			catch(Exception e) {
				dialogue = "...";
			}
			boolean swap;
			try{
				swap = toIntExact((Long) jsonObj.get("swap")) != 0;
			}
			catch(Exception e) {
				swap = false;
			}
			String effectDirection;
			try{
				effectDirection = (String) jsonObj.get("effect");
			}
			catch(Exception e) {
				effectDirection = null;
			}

			int northInteraction;
			try{
				northInteraction = toIntExact((Long) jsonObj.get("north"));
			}
			catch(Exception e) {
				northInteraction = -1;
			}
			int eastInteraction;
			try{
				eastInteraction = toIntExact((Long) jsonObj.get("east"));
			}
			catch(Exception e) {
				eastInteraction = -1;
			}
			int southInteraction;
			try{
				southInteraction = toIntExact((Long) jsonObj.get("south"));
			}
			catch(Exception e) {
				southInteraction = -1;
			}
			int westInteraction;
			try{
				westInteraction = toIntExact((Long) jsonObj.get("west"));
			}
			catch(Exception e) {
				westInteraction = -1;
			}
			
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
		
		JSONArray filepInteractions = (JSONArray) json.get("playerInteractions");
		ArrayList<Interaction> playerInteractions = new ArrayList<Interaction>();
		for (int i=0; i < filepInteractions.size(); i++) {
			JSONObject jsonObj = (JSONObject) filepInteractions.get(i);
			String dialogue;
			try{
				dialogue = (String) jsonObj.get("dialog");
			}
			catch(Exception e) {
				dialogue = "...";
			}
			boolean swap;
			try{
				swap = toIntExact((Long) jsonObj.get("swap")) != 0;
			}
			catch(Exception e) {
				swap = false;
			}
			String effectDirection;
			try{
				effectDirection = (String) jsonObj.get("effect");
			}
			catch(Exception e) {
				effectDirection = null;
			}

			int northInteraction;
			try{
				northInteraction = toIntExact((Long) jsonObj.get("north"));
			}
			catch(Exception e) {
				northInteraction = -1;
			}
			int eastInteraction;
			try{
				eastInteraction = toIntExact((Long) jsonObj.get("east"));
			}
			catch(Exception e) {
				eastInteraction = -1;
			}
			int southInteraction;
			try{
				southInteraction = toIntExact((Long) jsonObj.get("south"));
			}
			catch(Exception e) {
				southInteraction = -1;
			}
			int westInteraction;
			try{
				westInteraction = toIntExact((Long) jsonObj.get("west"));
			}
			catch(Exception e) {
				westInteraction = -1;
			}	
			Interaction temp = new Interaction();
			
			temp.setDialogue(dialogue);
			temp.setSwap(swap);
			temp.setEffectDirection(effectDirection);
			temp.setNorthInteraction(northInteraction);
			temp.setEastInteraction(eastInteraction);
			temp.setSouthInteraction(southInteraction);
			temp.setWestInteraction(westInteraction);
			
			playerInteractions.add(temp);
		}
		this.setNonPlayerInteractions(playerInteractions);
		
	}
	
	public Interaction interact(boolean byPlayer, String direction) {
		if((this.getNonPlayerInteractState() >= this.getNonPlayerInteractions().size() && !byPlayer) 
				|| this.getPlayerInteractState() >= this.getPlayerInteractions().size() && byPlayer) {
			return null;
		}
		
		
		
		Interaction result = nonPlayerInteractions.get(nonPlayerInteractState);
		if(byPlayer) {
			result = playerInteractions.get(playerInteractState);
			this.setPlayerInteractState(result.getNextInteraction(direction));
		}else {
			this.setNonPlayerInteractState(result.getNextInteraction(direction));
		}		
		return result;
		
	}
	
	public void render(GameModel gm, Graphics g) {
		g.drawImage(appearance, this.position.getX(), this.position.getY(), this.position.getX()+32, this.position.getY()+32,
				0, 0, imageSize, imageSize, null);
	}
	
	public void swap() {
		this.loadFromFile(swapInTo, this.getPosition());		
	}
	
	public boolean equals(GameObj go) {
		return go.getName().equals(this.getName()) && this.position.equalPos(go.getPosition());
	}
	
}
