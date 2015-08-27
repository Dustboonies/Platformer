package Weapons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import BitMask.BitMask;
import Entities.Projectile;

public class TestGun extends RangedWeapon{

	public TestGun (){
		damage = 2;
		knockback = 1;
		atkspeed = .5;
		
		projectileWidth = 30;
		projectileHeight = 10;
		
		try {
			weaponImage = ImageIO.read(new File("Images/Weapons/TestGun.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Projectile rangedAttack(int playerX, int playerY, int playerWidth, int playerHeight, boolean isFacingRight, BitMask b){
		return super.rangedAttack(playerX, playerY, playerWidth, playerHeight, isFacingRight, b);
	}
	
	public BufferedImage getImage(){
		return weaponImage;
	}

	
}
