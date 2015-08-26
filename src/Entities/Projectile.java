package Entities;

import java.awt.image.BufferedImage;

public class Projectile extends Entity{

	private static BufferedImage sprite;
	private boolean friendly;
	
	public Projectile(int x, int y, int w, int h) {
		super(x, y, w, h, h, h, sprite);
	}
	
	public void SetFriendly(boolean b){
		friendly = b;
	}
	
	public boolean GetFriendly(){
		return friendly;
	}

}
