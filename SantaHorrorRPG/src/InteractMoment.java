
public class InteractMoment {
	private boolean byPlayer;
	private String direction;
	private Position position;
	
	
	
	
	
	
	public InteractMoment(boolean byPlayer, String direction, Position position) {
		super();
		this.setByPlayer(byPlayer);
		this.setDirection(direction);
		this.setPosition(position);
	}
	public boolean isByPlayer() {
		return byPlayer;
	}
	public void setByPlayer(boolean byPlayer) {
		this.byPlayer = byPlayer;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
