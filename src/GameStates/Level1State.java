package GameStates;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import InputHandlers.Keys;
import Main.GamePanel;

public class Level1State extends GameState{

	private int x, y, vx, vy;
	private Rectangle Ball, Stage, Thingy;
	private boolean inAir;
	private Rectangle[] Rects = new Rectangle[2];
	public static int BALL_HEIGHT = 30, BALL_WIDTH = 30;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		x = (GamePanel.WIDTH - BALL_WIDTH)/2;
		y = GamePanel.HEIGHT - BALL_HEIGHT;
		
		Stage = new Rectangle(0, GamePanel.HEIGHT, GamePanel.WIDTH, 10);
		Rects[0] = Stage;
		
		Thingy = new Rectangle(20, GamePanel.HEIGHT-50, 100, 10);
		Rects[1] = Thingy;
		
		inAir = false;
	}

	@Override
	public void Init() {
		
		
	}

	@Override
	public void Update() {
//		for(int i = 0; i < Math.abs(vx); i++){
//			if(vx > 0) x += 1;
//			if(vx < 0) x -= 1;
			x += vx;
			Ball = new Rectangle(x, y, BALL_WIDTH, BALL_HEIGHT);
			for(Rectangle rect: Rects){
				if(Ball.intersects(rect)){
					if(vx > 0){											//Intersecting from Right
						x = rect.x - BALL_WIDTH;
					} else if(vx < 0){									//Intersecting from Left
						x = rect.x + rect.width;
					}
				}
			}
//		}
		
//		for(int i = 0; i < Math.abs(vy); i++){
//			if(vy > 0) y -= 1;
//			if(vy < 0) y += 1;
			y -= vy;
			Ball = new Rectangle(x, y, BALL_WIDTH, BALL_HEIGHT);
			for(Rectangle rect: Rects){
				if(Ball.intersects(rect)){
					if(vy < 0){											//Intersecting from Above
						y = rect.y - BALL_HEIGHT;
						inAir = false;
					} else if(vy > 0){									//Intersecting from Below
						y = rect.y + rect.height;
					}
				}
			}
//		}
		

		if(inAir) vy -= 1;
		
		Ball = new Rectangle(x, y, BALL_WIDTH, BALL_HEIGHT);
		
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
		g.fillOval(x, y, BALL_WIDTH, BALL_HEIGHT);
		g.fillRect(Thingy.x, Thingy.y, Thingy.width, Thingy.height);
	}

	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.RIGHT)){
			vx = 5;
		} else if(vx > 0){
			vx = 0;
		}
		if(Keys.isPressed(Keys.LEFT)){
			vx = -5;
		} else if(vx < 0){
			vx = 0;
		}
		if(Keys.isPressed(Keys.SPACE) && !inAir){
			inAir = true;
			vy = 15;
		}
//		if(Keys.isPressed(Keys.DOWN)){
//			
//		}
	}

}
