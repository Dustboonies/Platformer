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

public class Level1State extends GameState{

	private BufferedImage GeometryMap, Foreground, Sprite;
	private Rectangle Camera;
	private BitMask bitmask;
	private Player player;
	public static int LEVEL_WIDTH, LEVEL_HEIGHT;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		Init();
	}

	@Override
	public void Init() {
		Camera = new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		player = new Player();
		player.setX(50);
		player.setY(50);
		player.setWidth(34);
		player.setHeight(34);
		
		player.setWeapon(new TestGun()); //testing
		
		try{
			Foreground = ImageIO.read(new File("Images/Foreground.png"));
			GeometryMap = ImageIO.read(new File("Images/GeometryMap.png"));
			Sprite = ImageIO.read(new File("Images/sprite.png"));
		} catch(Exception e){
			System.out.println("Image Loading Failed");
		}
		
		bitmask = new BitMask(GeometryMap, GeometryMap.getWidth(), GeometryMap.getHeight());
		
		player.setBitMask(bitmask);
		
		LEVEL_WIDTH = GeometryMap.getWidth();
		LEVEL_HEIGHT = GeometryMap.getHeight();
	}

	@Override
	public void Update() {
		HandleInput();
		player.HandleInput();
		player.Update();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).Update();
			if(!Camera.intersects(projectiles.get(i).getHitBox()) || projectiles.get(i).getRemovable()){
				projectiles.remove(i);
				i--;
			}
		}
		
		if(player.getX() < 0) player.setX(0);
		else if(player.getX() + player.getWidth() > LEVEL_WIDTH) player.setX(LEVEL_WIDTH - player.getWidth());
		if(player.getY() < 0) player.setY(0);
		else if(player.getY() + player.getHeight() > LEVEL_HEIGHT) player.setY(LEVEL_HEIGHT - player.getHeight());
		
		UpdateCamera();
		
	}

	@Override
	public void Draw(Graphics2D g) {
		g.drawImage(Foreground.getSubimage(Camera.x, Camera.y, Camera.width, Camera.height), 0, 0, Camera.width, Camera.height, null);
		if(player.getFacingRight())
		g.drawImage(Sprite, player.getX() - Camera.x, player.getY() - Camera.y, player.getWidth(), player.getHeight(), null);
		else g.drawImage(Sprite, player.getX() + player.getWidth() - Camera.x, player.getY() - Camera.y, -1 * player.getWidth(), player.getHeight(), null);
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
			System.out.println(projectiles.size());
		}
		
		
	}
	
	public void UpdateCamera(){
		int cx = (player.getX()+player.getWidth()/2)-Camera.width/2;
		int cy = (player.getY()+player.getHeight()/2)-Camera.height/2;
		
		if(cx < 0) cx = 0;
		if(cx + Camera.width > LEVEL_WIDTH) cx = LEVEL_WIDTH - Camera.width;
		if(cy < 0) cy = 0;
		if(cy + Camera.height > LEVEL_HEIGHT) cy = LEVEL_HEIGHT - Camera.height;
		
		Camera.x = cx;
		Camera.y = cy;
	}
	
}
