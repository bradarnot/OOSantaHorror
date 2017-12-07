import java.util.ArrayList;

public class Zone extends GameState {

	private int zoneid;
	
	public void initZone(int zoneID) {
		
	}
	
	public void interact(Position tile) {
		
	}
	
	public boolean canMoveTo(Position tile) {
		return true;
		
	}
	
	public ArrayList<GameObj> getAdjacent(Position tile){
		return new ArrayList<GameObj>();
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
	public void render(GameModel gm) {
		
	}
	
}
