
public class Monster extends Actor {
	private int attackPower;

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public void update(Zone zone) {
		
	}
	
	public void loadFromFile(String name, Position, position) {
		
	}

	public void attack(Actor victim) {
		victim.damage(attackPower);
	}
}
