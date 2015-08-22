package GameStates;

import java.awt.Graphics2D;

import InputHandlers.Keys;
import Main.GamePanel;
import java.awt.Color;

public class PauseState extends GameState{

	public PauseState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void Init() {
		
	}

	@Override
	public void Update() {
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(GamePanel.WIDTH/2 - 100, 100, 200, 50);
		
		g.fillRect(GamePanel.WIDTH/2 - 100, 300, 200, 50);
		
		g.fillRect(GamePanel.WIDTH/2 - 100, 500, 200, 50);
	}

	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(false);
		}
	}
	
	
}
