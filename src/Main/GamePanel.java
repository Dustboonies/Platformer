package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import GameStates.GameStateManager;
import InputHandlers.Keys;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener{
	
	//Dimensions of the Panel
	public static final int WIDTH = 800;	//The Width of our panel
	public static final int HEIGHT = 600; 	//The Height of our panel
	public static final int SCALING = 1;	//The Scaling of our panel
	
	int x = 0;
	
	//Game State Manager
	private GameStateManager Manager;		//Manages the Multiple GameStates
	
	GamePanel(){
		super();							//Call our super because we are extending JPanel
		setPreferredSize(new Dimension(WIDTH * SCALING, HEIGHT * SCALING)); //Set our JPanel Size which our JFrame will resize to
		setVisible(true);					//Make our Panel Visible
		setFocusable(true);					//Make our Panel able to take key/mouse/other inputs
		requestFocus();						//Request Focus to take in key/mouse/other inputs
		Init();								//Initialize Variables
	}
	
	public void Init(){						//Function to Initialize our variables
		Manager = new GameStateManager();	//Set up the Game State Manager
		addKeyListener(this);				//Add this as a KeyListener
	}
	
	@Override
	public void paintComponent(Graphics g){	//Draws Graphics to Screen
		Graphics2D g2 = (Graphics2D)g;		//Sets the Graphics to 2D
		super.paintComponent(g2);			//Set super for Override
		
		Manager.Draw(g2);					//Game State Draws to Screen

		g.dispose();						//Dispose of g
		g2.dispose();						//and g2 to save space
	}
	
	public void Update(){					//Function that Updates the GamePanel and GameState
		Manager.Update();					//Current Game State Updates
		Keys.update();						//Key Input Gets Handled
	}

	//OBVIOUS
	@Override
	public void keyTyped(KeyEvent Key) {
	}

	@Override
	public void keyPressed(KeyEvent Key) {
		//System.out.println("keyPressed="+KeyEvent.getKeyText(Key.getKeyCode()));
		
		Keys.keySet(Key.getKeyCode(), true);//Give Key Handler the Key Code
	}

	@Override
	public void keyReleased(KeyEvent Key) {
		//System.out.println("keyReleased="+KeyEvent.getKeyText(Key.getKeyCode()));
		
		Keys.keySet(Key.getKeyCode(), false);//Give Key Handler the Key Code

	}

	
}
