import static java.lang.Math.toIntExact;

import java.awt.Graphics;

import org.json.simple.JSONObject;

public class Player extends Actor {
	public Player() {
		super();
	}

	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void update(GameModel gm, Input input) {
		super.update(gm, input);
		this.currentTic++;
		if(input.isDown()) {
			if(this.getDirection() != 4) {
				this.clipToTile(gm);
			}
			this.setDirection(4);
			this.setImageFrame(new Position(this.getImageFrame().getX(),3));
		}
		if(input.isUp()) {
			if(this.getDirection() != 0) {
				this.clipToTile(gm);
			}
			this.setDirection(0);
			this.setImageFrame(new Position(this.getImageFrame().getX(),0));
		}
		if(input.isRight()) {
			if(this.getDirection() != 2) {
				this.clipToTile(gm);
			}
			this.setDirection(2);
			this.setImageFrame(new Position(this.getImageFrame().getX(),2));
		}
		if(input.isLeft()) {
			if(this.getDirection() != 6) {
				this.clipToTile(gm);
			}
			this.setDirection(6);
			this.setImageFrame(new Position(this.getImageFrame().getX(),1));
		}
		
		if(input.movement()) {
			if(this.currentTic%(speed*3)==0)
				this.getImageFrame().setX((this.getImageFrame().getX()-1)<=0?animLength-1:(this.getImageFrame().getX()-1));
			if(gm.canMoveTo(this.potentialMove(), this.name)) this.executeMove();
		} else {
			this.getImageFrame().setX(1);
			this.clipToTile(gm);
		}
		
	}
	
	public void render(GameModel gm, Graphics g) {
		g.drawImage(appearance, this.position.getX(), this.position.getY(), (this.position.getX()+32), (this.position.getY()+32),
				this.imageFrame.getX()*32, this.imageFrame.getY()*32, (this.imageFrame.getX()+1)*32, (this.imageFrame.getY()+1)*32, null);
	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
		JSONObject json = JsonParser.getJson("objects", name + ".json");
		int gender;
		try{
			gender = toIntExact((Long) json.get("gender"));
		}
		catch(Exception e) {
			gender = 0;
		}
		this.setGender(gender);
	}

}
