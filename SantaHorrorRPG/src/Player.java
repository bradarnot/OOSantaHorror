
public class Player extends Actor {
	public Player(int speed, int direction, int health, int moveType, int gender, int animLength) {
		super(speed, direction, health, moveType, animLength);
		this.setGender(gender);
	}

	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void update(GameModel gm, Input input) {
		super.update(gm, input);
		this.currentTic++;
		if(input.isDown()) {
			this.setDirection(4);
			this.setImageFrame(new Position(this.getImageFrame().getX(),3));
		}
		if(input.isUp()) {
			this.setDirection(0);
			this.setImageFrame(new Position(this.getImageFrame().getX(),0));
		}
		if(input.isRight()) {
			this.setDirection(2);
			this.setImageFrame(new Position(this.getImageFrame().getX(),2));
		}
		if(input.isLeft()) {
			this.setDirection(6);
			this.setImageFrame(new Position(this.getImageFrame().getX(),1));
		}
		
		if(input.movement()) {
			if(this.currentTic%(speed*3)==0)
				this.getImageFrame().setX((this.getImageFrame().getX()-1)<=0?animLength-1:(this.getImageFrame().getX()-1));
			if(gm.canMoveTo(this.potentialMove(), this.name)) this.executeMove();
		} else {
			this.getImageFrame().setX(1);
			this.clipToTile(gm);
		}
		
	}
	
	public void loadFromFile(String name, Position position) {
		super.loadFromFile(name, position);
	}

}
