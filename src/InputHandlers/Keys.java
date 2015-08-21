package InputHandlers;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 16;
	
	public static boolean KeyState[] = new boolean[NUM_KEYS];
	public static boolean PrevKeyState[] = new boolean[NUM_KEYS];
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int BUTTON1 = 4;
	public static int BUTTON2 = 5;
	public static int BUTTON3 = 6;
	public static int BUTTON4 = 7;
	public static int ENTER = 8;
	public static int ESCAPE = 9;
	
	public static void keySet(int i, boolean b) {
		if(i == KeyEvent.VK_UP) KeyState[UP] = b;
		else if(i == KeyEvent.VK_LEFT) KeyState[LEFT] = b;
		else if(i == KeyEvent.VK_DOWN) KeyState[DOWN] = b;
		else if(i == KeyEvent.VK_RIGHT) KeyState[RIGHT] = b;
		else if(i == KeyEvent.VK_W) KeyState[BUTTON1] = b;
		else if(i == KeyEvent.VK_E) KeyState[BUTTON2] = b;
		else if(i == KeyEvent.VK_R) KeyState[BUTTON3] = b;
		else if(i == KeyEvent.VK_F) KeyState[BUTTON4] = b;
		else if(i == KeyEvent.VK_ENTER) KeyState[ENTER] = b;
		else if(i == KeyEvent.VK_ESCAPE) KeyState[ESCAPE] = b;
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			PrevKeyState[i] = KeyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return KeyState[i] && !PrevKeyState[i];
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(KeyState[i]) return true;
		}
		return false;
	}
	
}
