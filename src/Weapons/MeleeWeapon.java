package Weapons;

import java.awt.Rectangle;

public class MeleeWeapon extends Weapon{

	
<<<<<<< Updated upstream
	public Rectangle getHitbox(){
		return (new Rectangle(blahblahblah));		//Don't know what the variables for the position of the character are
													//so I'm going to leave it up to whomever knows that to set the location
													//of the hitbox to being infront of the character, thanks!
	}
=======
	private Rectangle hitbox;
	
	public Rectangle getHitBox(){
		return hitbox;
	}
	
>>>>>>> Stashed changes
}
