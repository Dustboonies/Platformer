package Weapons;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Fist extends MeleeWeapon{
	
	
	public Fist (){
		damage = 1;
		knockback = 0;
		atkspeed = 2;
	}
	public void setWeapon(){
		currentWeapon = new Fist();
	}
	public BufferedImage getImage(){
		try{
			weaponImage = ImageIO.read(new File("Images/Weapons/Fist.png"));
		}
		catch (Exception e){
			
		}
		return weaponImage;
	}
	
}
