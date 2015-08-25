package Weapons;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sword extends MeleeWeapon{

	
	public Sword(){
		damage = 2;
		knockback = 1;
		atkspeed = 1;
	}
	public void setWeapon(){
		currentWeapon = new Sword();
	}
	public BufferedImage getImage(){
		if (isAttacking ==true){
			try{
				weaponImage = ImageIO.read(new File("Images/Weapons/Sword.png"));
			}
			catch (Exception e){
				
			}
			return weaponImage;
		}
		else{
			try{
				weaponImageAttack = ImageIO.read(new File("Images/Weapons/SwordAttack.png"));
			}
			catch (Exception e){
				
			}
			return weaponImageAttack;
		}
	}
}
