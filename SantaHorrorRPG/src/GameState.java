
public class GameState {
	protected GameState nextState;

	public GameState getNextState() {
		return nextState;
	}

	public void setNextState(GameState nextState) {
		this.nextState = nextState;
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
	public void render(GameModel gm) {
		
	}

}
