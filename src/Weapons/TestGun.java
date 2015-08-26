package Weapons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestGun extends RangedWeapon{

	public TestGun (){
		damage = 2;
		knockback = 1;
		atkspeed = .5;
		
		try {
			weaponImage = ImageIO.read(new File("Images/Weapons/TestGun.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(){
		return weaponImage;
	}
	
}
