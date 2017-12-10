import static java.lang.Math.toIntExact;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

import org.json.simple.JSONObject;

public abstract class Actor extends GameObj {
	protected int speed;
	protected int currentTic;
	protected int animLength;
	protected Position imageFrame;
	protected int direction;
	protected int health;
	protected int moveType;
	protected Random random;
	protected boolean canMove = true;
	protected int moveSpeed = 0;
	
	public Actor() {
		super();
		this.setSpeed(1);
		this.setCurrentTic(0);
		this.setAnimLength(0);
		this.setImageFrame(new Position(0,0));
		this.setDirection(0);
		this.random = new Random();
	}

	int getAnimLength() {
		return this.animLength;
	}
	
	public void setAnimLength(int al) {
		this.animLength = al;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCurrentTic() {
		return currentTic;
	}

	public void setCurrentTic(int currentTic) {
		this.currentTic = currentTic;
	}

	public Position getImageFrame() {
		return imageFrame;
	}

	public void setImageFrame(Position imageFrame) {
		this.imageFrame = imageFrame;
	}

	public int getDirection() { 
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMoveType() {
		return moveType;
	}

	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}
	

	public int getMoveSpeed() {
		return moveSpeed;
	}


	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}


	public void update(GameModel gm, Input input) {
		this.currentTic++;
		if(this.currentTic%(15/speed)==0) {
			this.getImageFrame().setX((this.getImageFrame().getX()+1)%animLength);
			this.move(gm);
		}
		
		
		if(this.getDirection() == 4) {
			this.setImageFrame(new Position(this.getImageFrame().getX(),3));
		}
		if(this.getDirection() == 0) {
			this.setImageFrame(new Position(this.getImageFrame().getX(),0));
		}
		if(this.getDirection() == 2) {
			this.setImageFrame(new Position(this.getImageFrame().getX(),2));
		}
		if(this.getDirection() == 6) {
			this.setImageFrame(new Position(this.getImageFrame().getX(),1));
		}
	}
	
	public void clipToTile(GameModel gm) {
		int xdir = this.position.getX()%32;
		int ydir = this.position.getY()%32;
		if (xdir <= 15) {
			this.position.setX(this.position.getX()-xdir);
		} else {
			this.position.setX(this.position.getX()+32-xdir);
		}
		if (ydir <= 15) {
			this.position.setY(this.position.getY()-ydir);
		} else {
			this.position.setY(this.position.getY()+32-ydir);
		}
		
	}
	
	public void render(GameModel gm, Graphics g) {
		g.drawImage(appearance, this.position.getX(), this.position.getY(), (this.position.getX()+32), (this.position.getY()+32),
				this.imageFrame.getX()*this.getImageSize(), this.imageFrame.getY()*this.getImageSize(), (this.imageFrame.getX()+1)*this.getImageSize(), (this.imageFrame.getY()+1)*this.getImageSize(), null);
	}
	
	//If it dies it returns false
	public boolean damage(int damage) {
		this.setHealth(this.getHealth() - damage);
		return this.getHealth() <= 0;
	}
	
	
	// 0 is north and the directions go around clockwise including diagonals i.e. 6 is west 
	public Position potentialMove() {
		int x = this.position.getX();
		int y = this.position.getY();
		if(this.direction == 0 || this.direction == 1 || this.direction == 7)
			y+=this.getMoveSpeed();
		if(this.direction == 3 || this.direction == 4 || this.direction == 5)
			y-=this.getMoveSpeed();
		if(this.direction == 1 || this.direction == 2 || this.direction == 3)
			x+=this.getMoveSpeed();
		if(this.direction == 5 || this.direction == 6 || this.direction == 7)
			x-=this.getMoveSpeed();
		return new Position(x,y);
		
	}
	
	public void executeMove() {
		if(canMove) {
			this.position = this.potentialMove();
		}
	}
	
	public void move(GameModel gm) {
		switch (this.moveType){
			case 0:
				if(this.getPosition().inTile(gm.getTileSize())) {
					int change = random.nextInt(8);
					if(change < 4) this.direction = change*2;
					while(!gm.canMoveTo(this.potentialMove(), this.getName())) {
						change = random.nextInt(4);
						if(change < 4) this.direction = change*2;
					}	
				}
				if(gm.canMoveTo(this.potentialMove(), this.name)) {
					this.executeMove();
				}
				break;
			case 1:
				break;
			default:
				break;
		}
		
	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
		JSONObject json = JsonParser.getJson("objects", name + ".json");
		

		int speed;
		try{
			speed = toIntExact((Long) json.get("animationSpeed"));			
		}
		catch(Exception e) {
			speed = 1;
		}
		int direction;
		try{
			direction = toIntExact((Long) json.get("direction"));
		}
		catch(Exception e) {
			direction = 4;
		}
		int health;
		try{
			health = toIntExact((Long) json.get("health"));
		}
		catch(Exception e) {
			health = 100;
		}
		int moveType;
		try{
			moveType = toIntExact((Long) json.get("moveType"));
		}
		catch(Exception e) {
			moveType = -1;
		}
		int frame;
		try{
			frame = toIntExact((long) json.get("frameLength"));
		}
		catch(Exception e) {
			frame = 0;
		}
		int moveSpeed;
		try{
			moveSpeed = toIntExact((long) json.get("moveSpeed"));
		}
		catch(Exception e) {
			moveSpeed = 4;
		}

		
		this.setSpeed(speed);
		this.setDirection(direction);
		this.setHealth(health);
		this.setMoveType(moveType);
		this.setAnimLength(frame);
		this.setMoveSpeed(moveSpeed);
	}

}
