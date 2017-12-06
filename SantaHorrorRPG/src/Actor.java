import java.util.Random;

public abstract class Actor extends GameObj {
	protected int speed;
	protected int currentTic;
	protected int imageFrame;
	protected int direction;
	protected int health;
	protected int moveType;
	protected Random random;
	protected boolean canMove = false;
	
	public Actor(int speed, int currentTic, int imageFrame, int direction, int health, int moveType) {
		super();
		this.setSpeed(speed);
		this.setCurrentTic(currentTic);
		this.setImageFrame(imageFrame);
		this.setDirection(direction);
		this.setHealth(health);
		this.setMoveType(moveType);
		this.random = new Random();
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

	public int getImageFrame() {
		return imageFrame;
	}

	public void setImageFrame(int imageFrame) {
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

	public void update(Zone zone) {
		this.currentTic = (this.currentTic++)%this.speed;
		if(this.currentTic == 0) {
			this.move();
			zone.canMoveTo(this.potentialMove());
			this.exicuteMove();
		}

		
	}
	
	//If it dies it returns false
	public boolean damage(int damage) {
		this.health -= damage;
		return this.health <= 0;
	}
	
	public Position potentialMove() {
		int x = this.position.getX();
		int y = this.position.getY();
		if(this.direction == 0 || this.direction == 1 || this.direction == 7)
			y++;
		if(this.direction == 3 || this.direction == 4 || this.direction == 5)
			y--;
		if(this.direction == 1 || this.direction == 2 || this.direction == 3)
			x++;
		if(this.direction == 5 || this.direction == 6 || this.direction == 7)
			x--;
		return new Position(x,y);
		
	}
	
	public void exicuteMove() {
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
