
public class Player extends Actor {
	public Player(int speed, int currentTic, int imageFrame, int direction, int health, int moveType, int gender) {
		super(speed, currentTic, imageFrame, direction, health, moveType);
		this.setGender(gender);
	}

	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void update(GameModel gm) {
		super.update(gm);
		
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

}
