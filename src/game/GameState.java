package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.util.ArrayList;

/*Class: GameState
 * -abstract superClass which embodies the Model of MVC
 * -RedKnot, ClapperRail GameStates extend this class
 * -contains the current state for each game (Model)
 */
public abstract class GameState {
	private GameScreen screen;
	private int score;
	private Bird RL;
	private ArrayList<Bird> flock;
	private Bird CR;
	private MiniMap map;
	
	public void update() {
		
	}
}
