package Weapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import BitMask.BitMask;
import Entities.Projectile;

public class Weapon {
	
	int knockback, damage;
	double atkspeed;
	public MeleeWeapon currentWeapon;
	public BufferedImage weaponImage;
	public BufferedImage weaponImageAttack;
	public boolean isAttacking;
	
	
	public void setWeapon(){
		
	}
	
	public void draw(){
		
	}
		
	public void attack(){
		
	}
	
	public Projectile rangedAttack(int x, int y, int width, int height, boolean facingRight, BitMask bitmask) {

		return new Projectile(0, 0, 0);
	}
	
	public Rectangle getHitbox(){
		return currentWeapon.getHitbox();
	}
	
	public Weapon getWeapon(){
		return currentWeapon;
	}
	
	public BufferedImage getImage(){
		return currentWeapon.getImage();
	}


}