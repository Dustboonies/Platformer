package Entities;

public class Projectile extends Entity{

	private boolean friendly;
	
	public Projectile(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void SetFriendly(boolean b){
		friendly = b;
	}
	
	public boolean GetFriendly(){
		return friendly;
	}

}
