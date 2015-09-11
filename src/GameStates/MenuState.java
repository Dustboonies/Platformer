package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import InputHandlers.Keys;
import Main.GamePanel;

public class MenuState extends GameState{
	
	private int CurrentChoice = 0;
	private String[] Options = {
		"Start",
		"Mini Game" ,
		"Quit"
	};
	
	private Color TitleColor;
	private Font TitleFont;
	
	private Font Font;
	private Font SecondFont;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		TitleColor = Color.WHITE;
		TitleFont = new Font("Times New Roman", java.awt.Font.PLAIN, 50);
		Font = new Font("Arial", java.awt.Font.PLAIN, 20);
		SecondFont = new Font("Arial", java.awt.Font.PLAIN, 15);
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		String Title = "C O O L N A M E";
		String Start = "Mini Game";
		String Exit = "Exit";
		String Level1 = "Level 1";

		
		// draw title
		g.setColor(TitleColor);
		g.setFont(TitleFont);
		FontMetrics fm = g.getFontMetrics();
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(Title, g);
		int x = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		int y = (GamePanel.HEIGHT - (int)(rect.getHeight()))/2;
		g.drawString(Title, x, y);
		
		
		// draw menu options
		g.setFont(Font);
		g.setColor(Color.WHITE);
		fm = g.getFontMetrics();
		
		//draw Start
		rect = fm.getStringBounds(Level1, g);
		x = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		y = (GamePanel.HEIGHT - (int)(rect.getHeight()))/2 + 70;
		g.drawString(Level1, x, y);
		
		rect = fm.getStringBounds(Start, g);
		int x2 = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		int y2 = y + 25;
		g.drawString(Start, x2, y2);
		
		rect = fm.getStringBounds(Exit, g);
		int x3 = (GamePanel.WIDTH - (int)(rect.getWidth()))/2;
		int y3 = y2 + 25;
		g.drawString(Exit, x3, y3);
		
		//Draw Choice Marker
		g.setColor(Color.RED);
		if(CurrentChoice == 0){
			g.fillRect(x2-20, y-13, 10, 10);
		}
		else if(CurrentChoice == 1){
			g.fillRect(x2-20, y2-13, 10, 10);
		} else if(CurrentChoice == 2){
			g.fillRect(x2-20, y3-13, 10, 10);
		}
		
		// other
		g.setFont(SecondFont);
		g.drawString("2015 DIED Inc.", 10, GamePanel.HEIGHT - 10);
	}

	private void select() {
		if(CurrentChoice == 0) {
			System.out.println("Game Not Created Yet");
			Manager.SetActiveGameState(GameStateManager.GAMESTATE_LEVEL1);
		}
		else if(CurrentChoice == 1) {
			System.out.println("Game Not Created Yet");
			Manager.SetActiveGameState(GameStateManager.GAMESTATE_MINIGAME);
		}
		else if(CurrentChoice == 2) {
			System.exit(0);
		}
	}
	
	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(CurrentChoice > 0) {
				CurrentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(CurrentChoice < Options.length - 1) {
				CurrentChoice++;
			}
		}
	}

}
