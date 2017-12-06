
public class Monster extends Actor {
	public Monster(int speed, int currentTic, int imageFrame, int direction, int health, int moveType, int attackPower) {
		super(speed, currentTic, imageFrame, direction, health, moveType);
		this.setAttackPower(attackPower);
		// TODO Auto-generated constructor stub
	}

	private int attackPower;

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public void update(Zone zone) {
		
	}
	
	public void loadFromFile(String name, Position position) {
		
	}

	public void attack(Actor victim) {
		victim.damage(attackPower);
	}
}
