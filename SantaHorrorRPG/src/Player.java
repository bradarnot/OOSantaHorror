
public class Player extends Actor {
	public Player(int speed, int currentTic, int direction, int health, int moveType, int gender) {
		super(speed, currentTic, direction, health, moveType);
		this.setGender(gender);
	}

	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void update(GameModel gm, Input i) {
		super.update(gm, i);
		
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

}
