package Main;

import javax.swing.JFrame;

public class GameDriver {

	public static void main(String[] args) throws InterruptedException{
		JFrame Window = new JFrame("Simple Platformer"); 		//Creates the JFrame
		GamePanel Game = new GamePanel();						//Creates the JPanel
		Window.setContentPane(Game);							//Sets the JFrame's Content to be What happens in the JPanel
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//Sets it so that clicking the x on the top left will close the operation
		Window.setResizable(false);								//Makes Window non resizable
		Window.pack();											//Makes the JFrame resize to the JPanel Content
		Window.setLocationRelativeTo(null);						//Makes the JFrame appear in the middle of the screen
		Window.setVisible(true);								//Makes the JFrame Visible
		
		while(true){
			Game.Update();
			Game.repaint();
			Thread.sleep(10);
		}
		
	}
	
}