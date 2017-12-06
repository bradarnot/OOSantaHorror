
public class NPC extends Actor {
	public NPC(int speed, int currentTic, int imageFrame, int direction, int health, int moveType, int fear) {
		super(speed, currentTic, imageFrame, direction, health, moveType);
		this.setFear(fear);
		// TODO Auto-generated constructor stub
	}

	private int fear;

	public int getFear() {
		return fear;
	}

	public void setFear(int fear) {
		this.fear = fear;
	}
	
	public void update(Zone zone) {
		
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

}
