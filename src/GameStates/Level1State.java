package GameStates;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import InputHandlers.Keys;
import Main.GamePanel;

public class Level1State extends GameState{							//Not Fully Developed, Just a bouncing ball

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
		
		Thingy = new Rectangle(50, GamePanel.HEIGHT-100, 100, 50);
		Rects[1] = Thingy;
		
		inAir = false;
	}

	@Override
	public void Init() {
		
		
	}

	@Override
	public void Update() {
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
		
		if (x < 0) x = 0;
		if(x + BALL_WIDTH > GamePanel.WIDTH) x = GamePanel.WIDTH - BALL_WIDTH;
		
		y -= vy;
		Ball = new Rectangle(x, y, BALL_WIDTH, BALL_HEIGHT);
		for(Rectangle rect: Rects){
			if(Ball.intersects(rect)){
				if(vy < 0){											//Intersecting from Above
					y = rect.y - BALL_HEIGHT;
					inAir = false;
				} else if(vy > 0){									//Intersecting from Below
					y = rect.y + rect.height;
					vy = 0;
				}
			}
		}		

		if (y < 0) y = 0;
		if(y + BALL_HEIGHT > GamePanel.HEIGHT) y = GamePanel.HEIGHT - BALL_HEIGHT;
		
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
		if(Keys.KeyState[Keys.RIGHT]){
			vx = 5;
		} else if(vx > 0){
			vx = 0;
		}
		if(Keys.KeyState[Keys.LEFT]){
			vx = -5;
		} else if(vx < 0){
			vx = 0;
		}
		if(Keys.isPressed(Keys.SPACE) && !inAir){
			inAir = true;
			vy = 15;
		}
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(true);
		}
	}

}
