package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
	
	private int HP;
	public static int WIDTH = 34, HEIGHT = 34;
	
	public Player(double x, double y, double vx, double vy, BufferedImage image) {
		super(x, y, vx, vy, WIDTH, HEIGHT, image);
		HP = 100;
		
	}
	
	public int getHP(){
		return HP;
	}
	
	public void drawPlayer(Graphics2D g, Rectangle camera){
		g.drawImage(sprite, (int)x - camera.x, (int)y - camera.y, WIDTH, HEIGHT, null);
	}
	
	public void Move(){
		
	}
	
}
