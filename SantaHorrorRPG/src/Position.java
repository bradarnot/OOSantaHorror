
public class Position {

	private int x;
	private int y;
	
	public Position() {
		this.setX(0);
		this.setY(0);
	}
	
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equalPos(Position position) {
		if (this.getX() == position.getX() && this.getY() == position.getY()) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
