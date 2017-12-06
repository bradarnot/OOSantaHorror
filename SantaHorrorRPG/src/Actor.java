public abstract class Actor extends GameObj {
	protected int speed;
	protected int currentTic;
	protected int imageFrame;
	protected int direction;
	protected int health;
	protected int moveType;
	
	public Actor(int speed, int currentTic, int imageFrame, int direction, int health, int moveType) {
		super();
		this.setSpeed(speed);
		this.setCurrentTic(currentTic);
		this.setImageFrame(imageFrame);
		this.setDirection(direction);
		this.setHealth(health);
		this.setMoveType(moveType);
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
		
	}
	
	//If it dies it returns false
	public boolean damage(int damage) {
		this.health -= damage;
		return this.health <= 0;
	}
	
	public void move() {
		
	}

}
