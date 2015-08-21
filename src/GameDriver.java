import javax.swing.JFrame;


public class GameDriver {

<<<<<<< Updated upstream
	public static void main(String[] args) {
		
		GameFrame Frame = new GameFrame();
		
=======
	public static void main(String[] args) throws InterruptedException{
		JFrame Window = new JFrame("Simple Platformer");
		GamePanel game = new GamePanel();
		Window.setContentPane(game);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.setResizable(false);
		Window.pack();
		Window.setVisible(true);
		
        while (true) {
        	game.moveBall();
            game.repaint();
            Thread.sleep(10);
        }
>>>>>>> Stashed changes
	}
	
}
