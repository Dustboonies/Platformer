package Entities;

public class Projectile extends Entity{

	private boolean removable;
	
	public Projectile(int x, int y, int vx){
		this.x = x;
		this.y = y;
		this.vx = vx;
		removable = false;
	}
	
	@Override
	public void Update(){
		x += vx;
		for(int i = (int)x; i < x + width; i++){
			for(int j = (int)y; j < y + height; j++){
				if(i >= 0 && i < bitmask.width)
				if(bitmask.Solid[i][j]){
					removable = true;
				}
			}
		}
	}
	
	public boolean getRemovable(){return removable;}
	
}
