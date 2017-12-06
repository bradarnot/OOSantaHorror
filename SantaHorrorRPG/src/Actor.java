public abstract class Actor extends GameObj {
	protected int speed;
	protected int currentTic;
	protected int imageFrame;
	protected int direction;
	protected int health;
	
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
