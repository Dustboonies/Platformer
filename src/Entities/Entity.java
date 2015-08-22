package Entities;

import java.awt.Rectangle;

public class Entity {

	private int x, y, Width, Height;
	private Rectangle Box;
		
	public Entity (int x, int y, int w, int h){
			this.x = x;
			this.y = y;
			Width = w;
			Height = h;
			Box = new Rectangle(x, y, Width, Height);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return Width;
	}
	
	public int getHeight(){
		return Height;
	}
	
	public Rectangle getBox(){
		return Box;
	}
}
