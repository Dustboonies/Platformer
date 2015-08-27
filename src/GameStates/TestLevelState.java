package GameStates;

import java.awt.Graphics2D;

import Entities.Player;
import InputHandlers.Keys;

public class TestLevelState extends GameState{

	Player player;
	
	public TestLevelState(GameStateManager gsm) {
		super(gsm);
		Init();
	}

	@Override
	public void Init() {
		//player = new Player(0, 0);
	}

	@Override
	public void Update() {
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
		
	}

	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(true);
		}
	}

}
