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
					System.out.println(
						this.getName() + " hit " + 
						adjacent.get(i).getName() + " for " 
						+ this.getAttackPower());
					if(this.attack((Actor) adjacent.get(i))) {
						System.out.println(adjacent.get(i).getName() + " has died.");
					}
			}
		}

	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
		JSONObject json = JsonParser.getJson("objects", name + ".json");
		int attackDamage;
		try{
			attackDamage = toIntExact((Long) json.get("attackPower"));
		}
		catch(Exception e) {
			attackDamage = 10;
		}
		this.setAttackPower(attackDamage);
		
	}

	public boolean attack(Actor victim) {
		return victim.damage(attackPower);
	}
}
