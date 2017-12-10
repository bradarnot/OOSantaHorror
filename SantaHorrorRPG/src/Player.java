
public class Player extends Actor {
	public Player(int speed, int direction, int health, int moveType, int gender, int animLength) {
		super(speed, direction, health, moveType, animLength);
		this.setGender(gender);
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
		
	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
	}

}
