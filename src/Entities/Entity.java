package Entities;

import com.sun.javafx.geom.Rectangle;

public class Entity {

	private int Ewidth, Eheigth, hp, attack, exp;
	private int EposX, EposY;
	private boolean inAir;
	private Rectangle character;
		public Entity (){
			
			
		}
	
	public int getHP(){
		return hp;
	}
	
	public int getAttack(){
		return attack;
	}
	
	public boolean airborne(){
		return inAir;
	}
	
	
	
	
}
