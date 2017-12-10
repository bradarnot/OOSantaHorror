import static java.lang.Math.toIntExact;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Monster extends Actor {
	public Monster() {
		super();
	}

	private int attackPower;

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public void update(GameModel gm, Input input) {
		super.update(gm, input);
		if(this.currentTic == speed-1) {
			ArrayList<GameObj> adjacent = gm.getAdjacent(this.position, 1);
			for(int i = 0; i<adjacent.size(); i++) {
				if(adjacent.get(i) instanceof Player || (this.attackPower > 20 && adjacent.get(i) instanceof NPC))
					this.attack((Actor) adjacent.get(i));
			}
		}

	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
		JSONObject json = JsonParser.getJson("objects", name + ".json");
		int attackDamage = toIntExact((Long) json.get("attackPower"));
		this.setAttackPower(attackDamage);
	}

	public void attack(Actor victim) {
		victim.damage(attackPower);
	}
}
