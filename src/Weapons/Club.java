package Weapons;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Club extends MeleeWeapon{
	
	
	public Club (){
		damage = 3;
		knockback = 2;
		atkspeed = .75;
	}
	public void setWeapon(){
		currentWeapon = new Club();
	}
	public BufferedImage getImage(){
		if (isAttacking ==true){
			try{
				weaponImage = ImageIO.read(new File("Images/Weapons/Club.png"));
			}
			catch (Exception e){
				
			}
			return weaponImage;
		}
		else{
			try{
				weaponImageAttack = ImageIO.read(new File("Images/Weapons/ClubAttack.png"));
			}
			catch (Exception e){
				
			}
			return weaponImageAttack;
		}
	}
	
	
}
