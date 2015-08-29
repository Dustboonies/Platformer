package Entities;

public class Enemy extends Entity{
	
	protected int HP;
	protected boolean inAir, wasHittingSomething;
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		HP = 100;
	}
	
	@Override
	public void Update(){
		boolean hitNorm = false, hitSmall = false;
		x += vx;
		for(int i = (int)x; i < x + width; i++){
			for(int j = (int)y; j < y + height; j++){
				if(i >= 0 && i < bitmask.width)
				if(bitmask.Solid[i][j]){
					if(j < y + height-4){
						hitNorm = true;
						hitSmall = true;
					} else {
						hitNorm = true;
					}
					if(hitNorm && hitSmall){
						if(vx < 0){											//Intersecting from Right
							x = i+1;
						} else if(vx > 0){									//Intersecting from Left
							x = i - width;
						}
					}
				}
			}
		}

		if(hitNorm && !hitSmall){
			int numMoveUp = 0;
			for(int j = (int)y; j < y + height; j++){
				if(x >= 0 && x < width)
				if(bitmask.Solid[(int)x][j]){
					numMoveUp++;
				}
			}
			y-=numMoveUp;
		}
		
		if (x < 0) x = 0;
		if(x + width > bitmask.width) x = bitmask.width - width;
		
		boolean hitSomethingBelow = false;
		
		y -= vy;
		for(int i = (int)x; i < x + width; i++){
			for(int j = (int)y; j < y + height; j++){
				if(j >= 0 && j < bitmask.height)
				if(bitmask.Solid[i][j]){
					if(vy < 0){											//Intersecting from Above
						y = j - height;
						vy = -0.5;
						hitSomethingBelow = true;
						wasHittingSomething = true;
						inAir = false;
					} else if(vy >= 0){									//Intersecting from Below
						y = j + 1;
						vy = 0;
					}
				}
			}
		}		

		if(!hitSomethingBelow){
			inAir = true;
			wasHittingSomething = false;
		}
		else inAir = false;
		
		if (y < 0){
			y = 0;
			vy = -1;
		}
		if(y + height > bitmask.height){
			y = bitmask.height - height;
			inAir = false;
		}
		
		if(inAir) vy -= 0.5;
		
		setHitBox();
	}
	
	public int getHP(){return HP;}
	public void setHP(int hp){HP = hp;}
}
