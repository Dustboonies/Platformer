package GameStates;

import java.awt.Graphics2D;

public class GameStateManager {
	
	private GameState[] GameStates;
	private int CurrentState = 0;

	public static final int GAMESTATE_NUMSTATES = 4;
	public static final int GAMESTATE_NONE = 0;
	public static final int GAMESTATE_INTRO = 1;
	public static final int GAMESTATE_MENU = 2;
	public static final int GAMESTATE_LEVEL1 = 3;
	
	public GameStateManager(){
		GameStates = new GameState[GAMESTATE_NUMSTATES];
		
		CurrentState = GAMESTATE_MENU;
		SetActiveGameState(CurrentState);
	}
	
	private void LoadState(int GameStateID){
		if(GameStateID == GAMESTATE_MENU){
			GameStates[GameStateID] = new MenuState(this);
		}
	}
	
	private void UnloadState(int GameStateID){
		GameStates[GameStateID] = null;
	}
	
	public void Update(){
		if(GameStates[CurrentState] != null) GameStates[CurrentState].Update();
	}
	
	public void Draw(Graphics2D Renderer){
		GameStates[CurrentState].Draw(Renderer);
	}
	
	public void SetActiveGameState(int GameStateID){
		UnloadState(CurrentState);
		CurrentState = GameStateID;
		LoadState(GameStateID);
	}
	
	
	
}
