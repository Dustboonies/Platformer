import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	//Dimensions of the Panel
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	GamePanel(){
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
		setFocusable(true);
		
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}
	
	boolean inAir = false;
	int starty = HEIGHT/2-15;
	int x = WIDTH/2-15;
	int y = HEIGHT/2-15;
	int xa = 0;
	int ya = 0;

	public void moveBall() {
		if (inAir) ya--;
		
		x = x + xa;
		y = y - ya;
		if(y > starty){
			y = starty;
			ya = 0;
			inAir = false;
		}
		
		if(x < 0) x = 0;
		if(x + 30 > WIDTH) x = WIDTH - 30;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval(x, y, 30, 30);

	}
	
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(!inAir){
					inAir = true;
					ya = 16;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				xa = 10;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				xa = -10;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				xa = 0;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				xa = 0;
			}
		}
	}
	
}
