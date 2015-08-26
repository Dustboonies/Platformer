package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	protected double x, y, vx, vy;
	protected BufferedImage sprite;
	protected int Width, Height;
	protected Rectangle Box;
		
	public Entity (double x, double y, double vx, double vy, int w, int h, BufferedImage i){
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
			Width = w;
			Height = h;
			Box = new Rectangle((int)x, (int)y, Width, Height);
			
			sprite = i;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getWidth(){
		return Width;
	}
	
	public double getHeight(){
		return Height;
	}
	
	public Rectangle getBox(){
		return Box;
	}
	
}
