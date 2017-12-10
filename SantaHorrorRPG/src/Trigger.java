
public class Trigger extends Position {
	private String nextZone;
	
	
	public String getNextZone() {
		return nextZone;
	}


	public void setNextZone(String nextZone) {
		this.nextZone = nextZone;
	}


	public Trigger(int x, int y, String zone_id) {
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
