
public class Trigger extends Position {
	private int nextZone;
	
	
	public int getNextZone() {
		return nextZone;
	}


	public void setNextZone(int nextZone) {
		this.nextZone = nextZone;
	}


	public Trigger(int x, int y, int zone_id) {
		super(x, y);
		this.setNextZone(zone_id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean inRange(Position pos) {
		return ((pos.getX() >= this.getX()) && (pos.getX() <= this.getX() + 32)) 
				&& 
				((pos.getY() >= this.getY()) && (pos.getY() <= this.getY()+32));
	}

}
