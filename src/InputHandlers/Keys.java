package InputHandlers;

import java.awt.event.KeyEvent;

public class Keys {												//Class meant to Handle Key Events

	public static final int NUM_KEYS = 30;						//Number of Keys Right now we have 11
	
	public static boolean KeyState[] = new boolean[NUM_KEYS];	//Current Key State
	public static boolean PrevKeyState[] = new boolean[NUM_KEYS];//Previous Key State
	
	public static int UP = 0;									//Corresponding Key Codes for Certain Key Presses
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int W = 4;
	public static int E = 5;
	public static int R = 6;
	public static int F = 7;
	public static int ENTER = 8;
	public static int ESCAPE = 9;
	public static int SPACE = 10;
	
	public static void keySet(int i, boolean b) {				//Sets that key pressed boolean as true or false
		if(i == KeyEvent.VK_UP) KeyState[UP] = b;
		else if(i == KeyEvent.VK_LEFT) KeyState[LEFT] = b;
		else if(i == KeyEvent.VK_DOWN) KeyState[DOWN] = b;
		else if(i == KeyEvent.VK_RIGHT) KeyState[RIGHT] = b;
		else if(i == KeyEvent.VK_W) KeyState[W] = b;
		else if(i == KeyEvent.VK_E) KeyState[E] = b;
		else if(i == KeyEvent.VK_R) KeyState[R] = b;
		else if(i == KeyEvent.VK_F) KeyState[F] = b;
		else if(i == KeyEvent.VK_ENTER) KeyState[ENTER] = b;
		else if(i == KeyEvent.VK_ESCAPE) KeyState[ESCAPE] = b;
		else if(i == KeyEvent.VK_SPACE) KeyState[SPACE] = b;
	}
	
	public static void update() {								//Updates the current Key State
		for(int i = 0; i < NUM_KEYS; i++) {
			PrevKeyState[i] = KeyState[i];
		}
	}
	
	public static boolean isPressed(int i) {					//Tells if the key is pressed
		return KeyState[i] && !PrevKeyState[i];
	}
	
	public static boolean anyKeyPress() {						//Looks to see if any key is pressed
		for(int i = 0; i < NUM_KEYS; i++) {
			if(KeyState[i]) return true;
		}
		return false;
	}
	
}
