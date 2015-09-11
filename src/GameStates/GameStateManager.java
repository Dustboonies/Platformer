package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class GameStateManager {									//Manages Game States
	
	private GameState[] GameStates;								//Array of GameStates to find Current State
	private int CurrentState = 0;								//Current State Identifier

	private PauseState PauseState;								//Has the ability to Pause during different states
	private boolean Paused;										//Boolean telling if paused or not
	
	public static final int GAMESTATE_NUMSTATES = 6;			//Number of Game States
	public static final int GAMESTATE_NONE = 0;					//Game State ID for No State
	public static final int GAMESTATE_INTRO = 1;				//Game State ID for an Intro *Currently Uncreated*
	public static final int GAMESTATE_MENU = 2;					//Game State ID for the Menu State
	public static final int GAMESTATE_LEVEL1 = 3;				//Game State ID for Level 1 
	public static final int GAMESTATE_FAILED = 4;				//Game State ID for when you fail
	public static final int GAMESTATE_MINIGAME = 5;				//Game State ID for when you enter minigame
	
	public int numKills;
	
	public GameStateManager(){									//Constructor for GameState Manager
		GameStates = new GameState[GAMESTATE_NUMSTATES];		//Initialize Array
		
		PauseState = new PauseState(this);						//Initialize the Pause State
		Paused = false;											//Not Paused in the beginning
		
		CurrentState = GAMESTATE_MENU;							//Set the Current Game State to go to Menu
		SetActiveGameState(CurrentState);						//Set the Active Game State to the Current State which is Menu
	}
	
	private void LoadState(int GameStateID){					//Loads a GameState by identifier
		if(GameStateID == GAMESTATE_MENU){						//Loads Menu State
			GameStates[GameStateID] = new MenuState(this);
		} else if(GameStateID == GAMESTATE_LEVEL1){				//Loads Level1 State
			GameStates[GameStateID] = new Level1State(this);
		} else if(GameStateID == GAMESTATE_FAILED){				//Loads Level1 State
			GameStates[GameStateID] = new FailedState(this);
		} else if(GameStateID == GAMESTATE_MINIGAME){				//Loads Level1 State
			GameStates[GameStateID] = new MiniGameState(this);
		}
	}

	private void UnloadState(int GameStateID){					//Unloads a GameState
		GameStates[GameStateID] = null;							//Sets it to NULL
	}
	
	public void Update(){										//Update Function calls Current State's Update Function
		if(Paused) PauseState.Update();
		else if(GameStates[CurrentState] != null) GameStates[CurrentState].Update();
	}
	
	public void Draw(Graphics2D Renderer){						//Draw Function calls Current State's Draw Function
		if(GameStates[CurrentState] != null){
			GameStates[CurrentState].Draw(Renderer);
			if(Paused) PauseState.Draw(Renderer);
		}
		else{
			Renderer.setColor(Color.BLACK);
			Renderer.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}
	
	public void SetActiveGameState(int GameStateID){			//Sets the Current Game State according to identifier
		UnloadState(CurrentState);								//Sets the old one to null
		CurrentState = GameStateID;								//Sets the Current Game State ID to the inputed one
		LoadState(GameStateID);									//Loads that Game State
	}
	
	public void SetPaused(boolean p){
		Paused = p;
	}
	
	public void setNumKills(int x){
		numKills = x;
	}
	
}
