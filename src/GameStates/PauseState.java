package GameStates;

import java.awt.Graphics2D;

import InputHandlers.Keys;
import Main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class PauseState extends GameState{

	private int CurrentChoice = 0;
	private String[] Options = {
		"Menu",
		"Resume"
	};
	
	private Font Font;
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		Init();
	}

	@Override
	public void Init() {
		Font = new Font("Arial", java.awt.Font.PLAIN, 20);
	}

	@Override
	public void Update() {
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setFont(Font);
		g.setColor(Color.WHITE);
		FontMetrics fm = g.getFontMetrics();
		
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(Options[0], g);
		int x = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		int y = (GamePanel.HEIGHT - (int)(rect.getHeight()))/2 - 70;
		
		g.setColor(Color.BLACK);
		g.fillRect(x-3, y - (int)rect.getHeight(), (int)rect.getWidth()+6, (int)rect.getHeight()+6);
		
		g.setColor(Color.WHITE);
		g.drawString(Options[0], x, y);
		
		rect = fm.getStringBounds(Options[0], g);
		x = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		y = (GamePanel.HEIGHT - (int)(rect.getHeight()))/2 + 70;
		
		g.setColor(Color.RED);
		g.fillRect(x-3, y - (int)rect.getHeight(), (int)rect.getWidth()+6, (int)rect.getHeight()+6);
		
		g.setColor(Color.WHITE);
		g.drawString(Options[0], x, y);
	}

	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(false);
		}
	}
	
	
}
