
public class GameState {
	protected GameState nextState;
	protected DBConnection dbc;

	public GameState getNextState() {
		return nextState;
	}
	
	public void setDBConnection(DBConnection connection) {
		this.dbc = connection;
	}

	public void setNextState(GameState nextState) {
		this.nextState = nextState;
	}
	
	public void update(GameModel gm, Input input) {
		
	}
	
	public void render(GameModel gm) {
		
	}

}
