package GameStates;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import BitMask.BitMask;
import InputHandlers.Keys;
import Main.GamePanel;

public class Level1State extends GameState{							//Not Fully Developed, Just a bouncing ball

	private int numSecs;
	private double x, y, vx, vy;
	private Rectangle Stage, Thingy;
	private boolean inAir;
	private BitMask BitMask;
	private Rectangle[] Rects = new Rectangle[2];
	public static int BALL_HEIGHT = 30, BALL_WIDTH = 30;
	public BufferedImage Image;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		x = (GamePanel.WIDTH - BALL_WIDTH)/2;
		y = 0;
		
		Stage = new Rectangle(0, GamePanel.HEIGHT, GamePanel.WIDTH, 10);
		Rects[0] = Stage;
		
		Thingy = new Rectangle(50, GamePanel.HEIGHT-100, 100, 50);
		Rects[1] = Thingy;
		
		inAir = true;
		
		Init();
	}

	@Override
	public void Init() {
		try{
			Image = ImageIO.read(new File("Images/level1.png"));
			BitMask = new BitMask(Image);
		} catch(Exception e){
			
		}
		
	}

	@Override
	public void Update() {
		if(numSecs > 0) numSecs--;
		boolean hitNorm = false, hitSmall = false;
		x += vx;
		for(int i = (int)x; i < x+30; i++){
			for(int j = (int)y; j < y+30; j++){
				if(i >= 0 && i < 800)
				if(BitMask.Solid[i][j]){
					if(j < y + BALL_HEIGHT-4){
						hitNorm = true;
						hitSmall = true;
					} else {
						hitNorm = true;
					}
					if(hitNorm && hitSmall){
						if(vx < 0){											//Intersecting from Right
							x = i+1;
						} else if(vx > 0){									//Intersecting from Left
							x = i - BALL_WIDTH;
						}
					}
				}
			}
		}

		if(hitNorm && !hitSmall){
			int numMoveUp = 0;
			for(int j = (int)y; j < y+BALL_HEIGHT; j++){
				if(x >= 0 && x < 800)
				if(BitMask.Solid[(int)x][j]){
					numMoveUp++;
				}
			}
			y-=numMoveUp;
		}
		
		
		if (x < 0) x = 0;
		if(x + BALL_WIDTH > GamePanel.WIDTH) x = GamePanel.WIDTH - BALL_WIDTH;
		
		boolean hitSomethingBelow = false;
		
		y -= vy;
		for(int i = (int)x; i < x+BALL_WIDTH; i++){
			for(int j = (int)y; j < y+BALL_HEIGHT; j++){
				if(j >= 0 && j < 600)
				if(BitMask.Solid[i][j]){
					if(vy < 0){											//Intersecting from Above
						y = j - BALL_HEIGHT;
						vy = -0.5;
						hitSomethingBelow = true;
						inAir = false;
					} else if(vy >= 0){									//Intersecting from Below
						y = j + 1;
						vy = 0;
					}
				}
			}
		}		

		if(!hitSomethingBelow) inAir = true;
		else inAir = false;
		
		if (y < 0){
			y = 0;
			vy = -1;
		}
		if(y + BALL_HEIGHT > GamePanel.HEIGHT){
			y = GamePanel.HEIGHT - BALL_HEIGHT;
			inAir = false;
		}
		
		if(inAir) vy -= 0.5;
		
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
		if(Image != null) g.drawImage(Image, 0, 0, null);
		g.fillOval((int)x, (int)y, BALL_WIDTH, BALL_HEIGHT);
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
			vy = 11;
			numSecs = 10;
		}
		if(Keys.KeyState[Keys.SPACE] && inAir && numSecs > 0){
			vy = 11;
		}
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(true);
		}
	}

}
