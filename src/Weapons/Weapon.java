package Weapons;

import java.awt.image.BufferedImage;

public class Weapon {
	
	int knockback, damage;
	double atkspeed;
	public MeleeWeapon currentWeapon;
	public BufferedImage weaponImage;
	public BufferedImage weaponImageAttack;
	public boolean isAttacking;
	
	
	public void setWeapon(){
		
	}
	
	public void Draw(){
		
	}
	
	public Weapon getWeapon(){
		return currentWeapon;
	}
	
	public BufferedImage getImage(){
		return currentWeapon.getImage();
	}
}