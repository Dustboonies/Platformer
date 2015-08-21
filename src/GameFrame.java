import javax.swing.*;


public class GameFrame extends JFrame{
	
	private GamePanel Panel = new GamePanel();
	
	public GameFrame(){
		super("Simple Platformer Frame");
		this.setVisible(true);
		this.setBounds(20, 20, 800, 600);
		this.add(Panel);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
		
}
