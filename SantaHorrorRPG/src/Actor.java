import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

public abstract class Actor extends GameObj {
	protected int speed;
	protected int currentTic;
	protected Position imageFrame;
	protected int direction;
	protected int health;
	protected int moveType;
	protected Random random;
	protected boolean canMove = true;
	
	public Actor(int speed, int currentTic, int direction, int health, int moveType) {
		super();
		this.setSpeed(speed);
		this.setCurrentTic(currentTic);
		this.setDirection(direction);
		this.setHealth(health);
		this.setMoveType(moveType);
		this.random = new Random();
		this.imageFrame = new Position();
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

	public void update(GameModel gm, Input input) {
		if(input.isDown()) this.setDirection(4);
		if(input.isUp()) this.setDirection(0);
		if(input.isRight()) this.setDirection(2);
		if(input.isLeft()) this.setDirection(4);
		
		//System.out.println("move? " + input.movement());
		if(input.movement()) {
			if(gm.canMoveTo(this.potentialMove())) this.executeMove();
			//System.out.println("Moving: " + gm.canMoveTo(this.potentialMove()));
		} else {
			this.clipToTile(gm);
		}
//		this.currentTic = (this.currentTic+1)%this.speed;
//		if(this.currentTic == 0) {
//			this.move();
//			if(gm.canMoveTo(this.potentialMove())) this.executeMove();
//		}	
	}
	
	public void clipToTile(GameModel gm) {
		this.position = new Position((this.position.getX()/32)*32, (this.position.getY()/32)*32);
	}
	
	public void render(GameModel gm, Graphics g) {
		
		g.drawImage(appearance, this.position.getX(), this.position.getY(), (this.position.getX()+32), (this.position.getY()+32),
				this.imageFrame.getX()*32, this.imageFrame.getY()*32, (this.imageFrame.getX()+1)*32, (this.imageFrame.getY()+1)*32, null);
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
		System.out.println(this.direction);
		if(this.direction == 0 || this.direction == 1 || this.direction == 7)
			y+=speed;
		if(this.direction == 3 || this.direction == 4 || this.direction == 5)
			y-=speed;
		if(this.direction == 1 || this.direction == 2 || this.direction == 3)
			x+=speed;
		if(this.direction == 5 || this.direction == 6 || this.direction == 7)
			x-=speed;
		return new Position(x,y);
		
	}
	
	public void executeMove() {
		if(canMove) {
			this.position = this.potentialMove();
			
		}
	}
	
	public void move() {
		switch (this.moveType){
			case 0:
				break;
			case 1:
				break;
			default:
				int change = random.nextInt(25);
				if(change < 8) this.direction = change;
				this.canMove = true;
				
				
				
				break;
		}
		
	}

}
