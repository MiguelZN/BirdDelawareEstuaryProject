package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/*Class: GameState
 * -abstract superClass which embodies the Model of MVC
 * -RedKnot, ClapperRail GameStates extend this class
 * -contains the current state for each game (Model)
 */
public abstract class GameState extends Model {
	protected GameScreen screen;
	protected int score;
	protected MiniMap map;
	protected int background_x = 5; //Starts the background image at x=5, and moves left
	protected ArrayList<GameObject> objects; //all game objects that we have to draw on the screen
	
	public GameState(Controller controller) {
		super(controller);
		objects = new ArrayList<>();
	}
	
	public void update() {
		;
	}
	
	public abstract void addGameObject(GameObject o);
	
	public void setBackgroundX(int x) {
		this.background_x = x;
	}
	
	public int getBackgroundX() {
		return this.background_x;
	}
}
