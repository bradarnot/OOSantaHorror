
public class NPC extends Actor {
	public NPC(int speed, int direction, int health, int moveType, int fear, int animLength) {
		super(speed, direction, health, moveType, animLength);
		this.setFear(fear);
	}

	private int fear;

	public int getFear() {
		return fear;
	}

	public void setFear(int fear) {
		this.fear = fear;
	}
	
	public void update(GameModel gm, Input i) {
		super.update(gm, i);
		if(this.fear == 0) {
			super.move();			
		}else {
			this.fear -= 1;
			if( gm.canMoveTo(this.potentialMove())) {
				this.position = this.potentialMove();
			}else {
				int temp = random.nextInt(8);
				while (temp == this.direction) {
					temp = random.nextInt(8);
				}
				this.direction = temp;
			}
			
		}
		
	}
	
	public boolean damage(int damage) {
		this.setFear(damage);
		return super.damage(damage);
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

}
