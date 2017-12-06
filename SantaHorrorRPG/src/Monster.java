import java.util.ArrayList;

public class Monster extends Actor {
	public Monster(int speed, int currentTic, int imageFrame, int direction, int health, int moveType, int attackPower) {
		super(speed, currentTic, imageFrame, direction, health, moveType);
		this.setAttackPower(attackPower);
	}

	private int attackPower;

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public void update(Zone zone) {
		super.update(zone);
		if(this.currentTic == speed-1) {
			ArrayList<GameObj> adjacent = zone.getAdjacent(this.position);
			for(int i = 0; i<adjacent.size(); i++) {
				if(adjacent.get(i) instanceof Player || (this.attackPower > 20 && adjacent.get(i) instanceof NPC))
					this.attack((Actor) adjacent.get(i));
			}
		}

	}
	
	public void loadFromFile(String name, Position position) {
		
	}

	public void attack(Actor victim) {
		victim.damage(attackPower);
	}
}
