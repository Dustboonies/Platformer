package GameStates;
import java.awt.Graphics2D;

public abstract class GameState {

protected GameStateManager Manager;
	
	public GameState(GameStateManager gsm) {
		Manager = gsm;
	}
	
	public abstract void Init();
	public abstract void Update();
	public abstract void Draw(Graphics2D g);
	public abstract void HandleInput();
	
}
