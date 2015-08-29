package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import BitMask.BitMask;
import Entities.Player;
import Entities.Projectile;
import InputHandlers.Keys;
import Main.GamePanel;
import Weapons.TestGun;

public class Level1State extends GameState{										//This is the Level 1 GameState

	private BufferedImage GeometryMap, Foreground, Sprite;						//Images for our game to use
	private Rectangle Camera;													//The Portion of the Map which needs to be Rendered
	private BitMask bitmask;													//The BitMask which determines what is solid and what is not
	private Player player;														//The Player of our Game
	public static int LEVEL_WIDTH, LEVEL_HEIGHT;								//The Level's width and height
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();	//The Projectiles in the game
	
	public Level1State(GameStateManager gsm) {									//The Level 1 GameState constructor
		super(gsm);																//Pass into the Super the Game State Manager
		Init();																	//Initialize Function is Called
	}


	public void Init() {														//Initializes our variables
		Camera = new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);		//Initialize the Camera Rectangle
		player = new Player();													//Initialize the Player
		player.setX(303);														//Set the Player's X Coordinate
		player.setY(50);														//Set the Player's Y Coordinate
		player.setWidth(34);													//Set the Player's width
		player.setHeight(34);													//Set the Player's height
		player.setHasWeapon(true);												//We set the Player as having a weapon for testing purposes
		player.setWeapon(new TestGun());
		
		try{
			Foreground = ImageIO.read(new File("Images/Foreground.png"));		//Initialize Foreground image which is the image we see
			GeometryMap = ImageIO.read(new File("Images/GeometryMap.png"));		//Initialize the Geometry Map image which we cant see but determines what pizels are solid
			Sprite = ImageIO.read(new File("Images/sprite.png"));				//Initialize the Sprite image which will represent our character
		} catch(Exception e){
			e.printStackTrace();												//If there is an error show it
		}
		
		bitmask = new BitMask(GeometryMap, GeometryMap.getWidth(), GeometryMap.getHeight());//Our BitMask is initialized based of the GeometryMap
		
		player.setBitMask(bitmask);												//Set the Player's BitMask to be the level's for physics purposes
		
		LEVEL_WIDTH = GeometryMap.getWidth();									//Set the Level's width
		LEVEL_HEIGHT = GeometryMap.getHeight();									//Set the Level's height
		
		SetCameraOnPlayer();
	}


	public void Update() {														//Every frame the GameState update
		HandleInput();															//GameState handles key inputs
		player.HandleInput();													//Player handles key inputs
		player.Update();														//Player updates according to BitMask
		for(int i = 0; i < projectiles.size(); i++){							//Go through all the Projectiles
			projectiles.get(i).Update();										//Updates Projectiles
			if(!Camera.intersects(projectiles.get(i).getHitBox()) || projectiles.get(i).getRemovable()){//If projectile is off screen or hit something remove it
				projectiles.remove(i);											//Remove the laser
				i--;															//Go back one space to make up for lost projectile
			}
			
		}
		
		if(player.getX() < 0) player.setX(0);									//if off screen stop it from going off screen
		else if(player.getX() + player.getWidth() > LEVEL_WIDTH) player.setX(LEVEL_WIDTH - player.getWidth());
		if(player.getY() < 0) player.setY(0);
		else if(player.getY() + player.getHeight() > LEVEL_HEIGHT) player.setY(LEVEL_HEIGHT - player.getHeight());
		
		UpdateCamera();															//Update the Camera according to the player
		
	}

	@Override
	public void Draw(Graphics2D g) {		
		if(player.getFacingRight()) g.drawImage(Sprite, player.getX() - Camera.x, player.getY() - Camera.y, player.getWidth(), player.getHeight(), null);
		else g.drawImage(Sprite, player.getX() + player.getWidth() - Camera.x, player.getY() - Camera.y, -1 * player.getWidth(), player.getHeight(), null);

		g.drawImage(Foreground.getSubimage(Camera.x, Camera.y, Camera.width, Camera.height), 0, 0, Camera.width, Camera.height, null);
		
		for(int i = 0; i < projectiles.size(); i++){
			g.setColor(Color.RED);
			g.fillRect(projectiles.get(i).getX() - Camera.x, projectiles.get(i).getY() - Camera.y, projectiles.get(i).getWidth(), projectiles.get(i).getHeight());
		}
	}

	@Override
	public void HandleInput() {
		if(Keys.isPressed(Keys.ESCAPE)){
			Manager.SetPaused(true);
		}
		
		if(Keys.isPressed(Keys.R) && player.getHasWeapon()){ //attack
			projectiles.add(player.getWeapon().rangedAttack(player.getX(), player.getY(), player.getWidth(), player.getHeight(), player.getFacingRight(), bitmask));
		}
		
		
	}
	
	public void UpdateCamera(){		
		if(player.getX() < Camera.x + Camera.width/2 - 45 && player.getVX() < 0){
			int cx = Camera.x - 3;
			
			if(cx < 0) cx = 0;
			if(cx + Camera.width > LEVEL_WIDTH) cx = LEVEL_WIDTH - Camera.width;
			
			Camera.x = cx;
		} else if(player.getX() > Camera.x + Camera.width/2 + 45 - player.getWidth() && player.getVX() > 0){
			int cx = Camera.x + 3;
			
			if(cx < 0) cx = 0;
			if(cx + Camera.width > LEVEL_WIDTH) cx = LEVEL_WIDTH - Camera.width;
			
			Camera.x = cx;
		}
		
		int cy = (player.getY()+player.getHeight()/2)-Camera.height/2;
		
		if(cy < 0) cy = 0;
		if(cy + Camera.height > LEVEL_HEIGHT) cy = LEVEL_HEIGHT - Camera.height;
		
		
		Camera.y = cy;
	}
	
	public void SetCameraOnPlayer(){
		int cx = (player.getX()+player.getWidth()/2)-Camera.width/2;
		
		if(cx < 0) cx = 0;
		if(cx + Camera.width > LEVEL_WIDTH) cx = LEVEL_WIDTH - Camera.width;
		
		int cy = (player.getY()+player.getHeight()/2)-Camera.height/2;
		
		if(cy < 0) cy = 0;
		if(cy + Camera.height > LEVEL_HEIGHT) cy = LEVEL_HEIGHT - Camera.height;
		
		Camera.x = cx;
		Camera.y = cy;
	}
	
}
