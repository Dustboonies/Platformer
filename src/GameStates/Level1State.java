package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import BitMask.BitMask;
import InputHandlers.Keys;
import Main.GamePanel;

public class Level1State extends GameState{							//Not Fully Developed, Just a bouncing ball

	private int numSecs, numFrames;
	private double x, y, vx, vy;
	private int LEVEL_WIDTH, LEVEL_HEIGHT;
	private Rectangle Stage, Thingy, Camera;
	private boolean inAir, wasHittingSomething;
	private BitMask Bitmask;
	private Rectangle[] Rects = new Rectangle[2];
	public static int CHARACTER_HEIGHT = 34, CHARACTER_WIDTH = 34;
	public static BufferedImage Foreground, GeometryMap, LevelMap, Sprite;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		x = (GamePanel.WIDTH - CHARACTER_WIDTH)/2;
		y = 0;
		
		LEVEL_WIDTH = 640;
		LEVEL_HEIGHT = 480;
		
		Camera = new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		int cx = ((int)x+CHARACTER_WIDTH/2)-GamePanel.WIDTH/2;
		int cy = ((int)y+CHARACTER_HEIGHT/2)-GamePanel.HEIGHT/2;
		
		if(cx < 0) cx = 0;
		if(cx + GamePanel.WIDTH > LEVEL_WIDTH) cx = LEVEL_WIDTH - GamePanel.WIDTH;
		if(cy < 0) cy = 0;
		if(cy + GamePanel.HEIGHT > LEVEL_HEIGHT) cy = LEVEL_HEIGHT - GamePanel.HEIGHT;
		
		Camera.x = cx;
		Camera.y = cy;
		
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
//			Background = ImageIO.read(new File("Images/Background.png"));
			Foreground = ImageIO.read(new File("Images/Foreground.png"));
			GeometryMap = ImageIO.read(new File("Images/GeometryMap.png"));
			Sprite = ImageIO.read(new File("Images/Sprite.png"));
			Bitmask = new BitMask(GeometryMap, LEVEL_WIDTH, LEVEL_HEIGHT);
		} catch(Exception e){
			
		}
		
	}

	@Override
	public void Update() {
		if(numSecs > 0) numSecs--;
		if(numFrames > 0) numFrames--;
		boolean hitNorm = false, hitSmall = false;
		x += vx;
		for(int i = (int)x; i < x+CHARACTER_WIDTH; i++){
			for(int j = (int)y; j < y+CHARACTER_HEIGHT; j++){
				if(i >= 0 && i < LEVEL_WIDTH)
				if(Bitmask.Solid[i][j]){
					if(j < y + CHARACTER_HEIGHT-4){
						hitNorm = true;
						hitSmall = true;
					} else {
						hitNorm = true;
					}
					if(hitNorm && hitSmall){
						if(vx < 0){											//Intersecting from Right
							x = i+1;
						} else if(vx > 0){									//Intersecting from Left
							x = i - CHARACTER_WIDTH;
						}
					}
				}
			}
		}

		if(hitNorm && !hitSmall){
			int numMoveUp = 0;
			for(int j = (int)y; j < y+CHARACTER_HEIGHT; j++){
				if(x >= 0 && x < LEVEL_WIDTH)
				if(Bitmask.Solid[(int)x][j]){
					numMoveUp++;
				}
			}
			y-=numMoveUp;
		}
		
		
		if (x < 0) x = 0;
		if(x + CHARACTER_WIDTH > LEVEL_WIDTH) x = LEVEL_WIDTH - CHARACTER_WIDTH;
		
		boolean hitSomethingBelow = false;
		
		y -= vy;
		for(int i = (int)x; i < x+CHARACTER_WIDTH; i++){
			for(int j = (int)y; j < y+CHARACTER_HEIGHT; j++){
				if(j >= 0 && j < LEVEL_HEIGHT)
				if(Bitmask.Solid[i][j]){
					if(vy < 0){											//Intersecting from Above
						y = j - CHARACTER_HEIGHT;
						vy = -0.5;
						hitSomethingBelow = true;
						wasHittingSomething = true;
						inAir = false;
					} else if(vy >= 0){									//Intersecting from Below
						y = j + 1;
						vy = 0;
					}
				}
			}
		}		

		if(!hitSomethingBelow){
			inAir = true;
			if(wasHittingSomething) numFrames = 10;
			wasHittingSomething = false;
		}
		else inAir = false;
		
		if (y < 0){
			y = 0;
			vy = -1;
		}
		if(y + CHARACTER_HEIGHT > LEVEL_HEIGHT){
			y = LEVEL_HEIGHT - CHARACTER_HEIGHT;
			inAir = false;
		}
		
		if(inAir) vy -= 0.5;
		
		int cx = ((int)x+CHARACTER_WIDTH/2)-GamePanel.WIDTH/2;
		int cy = ((int)y+CHARACTER_HEIGHT/2)-GamePanel.HEIGHT/2;
		
		if(cx < 0) cx = 0;
		if(cx + GamePanel.WIDTH > LEVEL_WIDTH) cx = LEVEL_WIDTH - GamePanel.WIDTH;
		if(cy < 0) cy = 0;
		if(cy + GamePanel.HEIGHT > LEVEL_HEIGHT) cy = LEVEL_HEIGHT - GamePanel.HEIGHT;
		
		Camera.x = cx;
		Camera.y = cy;
		
		HandleInput();
	}

	@Override
	public void Draw(Graphics2D g) {
//		if(Background != null) g.drawImage(Background.getSubimage(Camera.x, Camera.y, Camera.width, Camera.height), 0, 0, null);
		g.setColor(Color.darkGray.darker());
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
//		g.setColor(Color.WHITE);
//		g.fillOval((int)x - Camera.x, (int)y - Camera.y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		if(Sprite != null) g.drawImage(Sprite, (int)x - Camera.x, (int)y - Camera.y, CHARACTER_WIDTH, CHARACTER_HEIGHT, null);
		if(Foreground != null) g.drawImage(Foreground.getSubimage(Camera.x, Camera.y, Camera.width, Camera.height), 0, 0, null);
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
		if(Keys.isPressed(Keys.SPACE) && (!inAir || numFrames > 0)){
			inAir = true;
			vy = 9;
			numSecs = 10;
		}
		if(Keys.KeyState[Keys.SPACE] && inAir && numSecs > 0){
			vy = 9;
		}
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(true);
		}
//		if(Keys.isPressed(Keys.R)){									//Attack "if" statement, will enable once I finish other necessary methods
//			
//		}
	}

}
