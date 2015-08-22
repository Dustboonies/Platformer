package Entities;

public class Player extends Entity{
	
	private int HP;
	public static int WIDTH = 30, HEIGHT = 30;
	
	public Player(int x, int y) {
		super(x, y, WIDTH, HEIGHT);
		HP = 100;
	}
	
	public int getHP(){
		return HP;
	}
	
}
