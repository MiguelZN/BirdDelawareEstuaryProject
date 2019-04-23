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
public abstract class GameState extends Model {
	private GameScreen screen;
	private int score;
	private MiniMap map;
	private ArrayList<GameObject> objects; //all game objects that we have to draw on the screen
	
	public GameState(Controller controller) {
		super(controller);
		objects = new ArrayList<>();
	}
	
	public void update() {
		;
	}
}
