public class Input {
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean interact; 
	
	public Input() {
		up = false;
		down = false;
		left = false;
		right = false;
		interact = false;
	}
	
	public boolean isInteract() {
		return interact;
	}
	
	public void setInteract(boolean interact) {
		this.interact = interact;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public boolean movement() {
		return this.up || this.right || this.down || this.left;
	}

}
