package GameStates;
import java.awt.Graphics2D;

public abstract class GameState {						//Abstract Class Game State which all Game States inherit

protected GameStateManager Manager;						//All Game States needs to have access to the Manager
	
	public GameState(GameStateManager gsm) {			//Game State Constructor
		Manager = gsm;									//Sets the Manager
	}
	
	public abstract void Init();						//Initialization of Game State Function
	public abstract void Update();						//Updating of Game State Function
	public abstract void Draw(Graphics2D g);			//Drawing for Game State Function
	public abstract void HandleInput();					//Input Handling for the Current Game State
	
}
