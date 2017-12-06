
public class Player extends Actor {
	public Player(int speed, int currentTic, int imageFrame, int direction, int health, int moveType, int gender) {
		super(speed, currentTic, imageFrame, direction, health, moveType);
		this.setGender(gender);
		// TODO Auto-generated constructor stub
	}

	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void update(Zone zone) {
		super.update(zone);
		
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

}
