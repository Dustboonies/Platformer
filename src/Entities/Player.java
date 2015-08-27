package Entities;

import InputHandlers.Keys;

public class Player extends Entity{

	private boolean inAir, wasHittingSomething, hasWeapon;
	private int numFrames, numSecs;
	
	public Player(){
		
	}
	
	public boolean getHasWeapon(){return hasWeapon;}
	public void setHasWeapon(boolean hw){hasWeapon = hw;}
	
	public void HandleInput(){
		if(Keys.KeyState[Keys.RIGHT]){
			FacingRight = true;
			vx = 3;
		} else if(vx > 0){
			vx = 0;
		}
		if(Keys.KeyState[Keys.LEFT]){
			FacingRight = false;
			vx = -3;
		} else if(vx < 0){
			vx = 0;
		}
		if(Keys.isPressed(Keys.SPACE) && (!inAir || numFrames > 0)){
			inAir = true;
			vy = 7;
			numSecs = 10;
		}
		if(Keys.KeyState[Keys.SPACE] && inAir && numSecs > 0){
			vy = 7;
		}
	}
	
	@Override
	public void Update(){
		if(numSecs > 0) numSecs--;
		if(numFrames > 0) numFrames--;
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
			if(wasHittingSomething) numFrames = 10;
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
}
