
public class Interaction {

	private String dialogue;
	private boolean swap;
	private String effectDirection;
	private int northInteraction;
	private int eastInteraction;
	private int southInteraction;
	private int westInteraction;
	
	public String getDialogue() {
		return dialogue;
	}
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
	public boolean isSwap() {
		return swap;
	}
	public void setSwap(boolean swap) {
		this.swap = swap;
	}
	public String getEffectDirection() {
		return effectDirection;
	}
	public void setEffectDirection(String effectDirection) {
		this.effectDirection = effectDirection;
	}
	
	
	public int getNextInteraction(String direction) {
		if(direction.equals("north")) {
			return northInteraction;
		}
		else if(direction.equals("east")) {
			return eastInteraction;
		}
		else if(direction.equals("south")) {
			return southInteraction;
		}
		else if(direction.equals("west")) {
			return westInteraction;
		}
		return -1;
	}
	
	public int getNorthInteraction() {
		return northInteraction;
	}
	public void setNorthInteraction(int northInteraction) {
		this.northInteraction = northInteraction;
	}
	public int getEastInteraction() {
		return eastInteraction;
	}
	public void setEastInteraction(int easttInteraction) {
		this.eastInteraction = easttInteraction;
	}
	public int getSouthInteraction() {
		return southInteraction;
	}
	public void setSouthInteraction(int southInteraction) {
		this.southInteraction = southInteraction;
	}
	public int getWestInteraction() {
		return westInteraction;
	}
	public void setWestInteraction(int westtInteraction) {
		this.westInteraction = westtInteraction;
	}
	
	
	

	
}
