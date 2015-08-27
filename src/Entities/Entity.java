package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import BitMask.BitMask;

public class Entity {

	protected double x, y, vx, vy;
	protected int width, height;
	protected Rectangle HitBox;
	protected boolean OnScreen, FacingRight, AffectedByGravity;
	protected BitMask bitmask;
	
	public Entity(){
		HitBox = new Rectangle();
		OnScreen = false;
		FacingRight = true;
		AffectedByGravity = true;
	}
	
	public int getX(){return (int)x;}
	public void setX(double x){this.x = x;}
	
	public int getY(){return (int)y;}
	public void setY(double y){this.y = y;}
	
	public double getVX(){return vx;}
	public void setVX(double vx){this.vx = vx;}
	
	public double getVY(){return vy;}
	public void setVY(double vy){this.vy = vy;}
	
	public int getWidth(){return width;}
	public void setWidth(int w){width = w;}
	
	public int getHeight(){return height;}
	public void setHeight(int h){height = h;}
	
	public Rectangle getHitBox(){return HitBox;}
	public void setHitBox(){ HitBox = new Rectangle((int)x, (int)y, width, height);}
	public void setHitBox(int x, int y, int width, int height){ HitBox = new Rectangle(x, y, width, height);}
	
	public void Update(){}
	public void Draw(Graphics2D g){}
	
	public boolean getOnScreen(){return OnScreen;}
	public void setOnScreen(boolean os){OnScreen = os;}
	
	public boolean getFacingRight(){return FacingRight;}
	public void setFacingRight(boolean fr){FacingRight = fr;}
	
	public boolean getAffectedByGravity(){return AffectedByGravity;}
	public void setAffectedByGravity(boolean abg){AffectedByGravity = abg;}
	
	public BitMask getBitMask(){return bitmask;}
	public void setBitMask(BitMask bm){bitmask = bm;}
	
}
