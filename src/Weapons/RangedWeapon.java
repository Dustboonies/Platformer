package Weapons;

import BitMask.BitMask;
import Entities.Projectile;



public class RangedWeapon extends Weapon{
	
	public int projectileWidth;
	public int projectileHeight;
	
	public Projectile rangedAttack(int playerX, int playerY, int playerWidth, int playerHeight, boolean isFacingRight, BitMask b){

		
		System.out.println("rangedAttack");
		
		Projectile p;
		
		if(isFacingRight){
			p = new Projectile(playerX + playerWidth + 2, playerY + playerHeight/2, 15);
		}
		else p = new Projectile(playerX - 12, playerY + playerHeight/2, -15);
		
		p.setWidth(projectileWidth);
		p.setHeight(projectileHeight);
		p.setHitBox();
		p.setBitMask(b);
		
		return p;
	}
	
}
