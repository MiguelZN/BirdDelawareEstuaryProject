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
	protected boolean isGameRunning;
	
	
	//ArrowKey detection
	protected boolean left_key_pressed;
	protected boolean right_key_pressed;
	protected boolean down_key_pressed;
	protected boolean up_key_pressed;
	
	/**
	 * @param controller
	 */
	public GameState(Controller controller) {
		super(controller);
		objects = new ArrayList<>();
		this.isGameRunning = true;
		
		//ArrowKeys
		this.left_key_pressed = false;
		this.right_key_pressed = false;
		this.up_key_pressed = false;
		this.down_key_pressed = false;
	}
	
	/**
	 * 
	 */
	public abstract void ontick();
	
	/**
	 * @return
	 */
	public abstract ArrayList<GameObject> getUpdateableGameObjects();
	
	/**
	 * @param o
	 */
	public abstract void addGameObject(GameObject o);
	
	/**
	 * @param x
	 */
	public void setBackgroundX(int x) {
		this.background_x = x;
	}
	
	/**
	 * @return
	 */
	public int getBackgroundX() {
		return this.background_x;
	}
	
	public boolean getIsGameRunning() {
		return this.isGameRunning;
	}
	
	
	public void setIsGameRunning(boolean game_running) {
		this.isGameRunning = game_running;
	}

	public boolean isLeft_key_pressed() {
		return left_key_pressed;
	}

	public void setLeft_key_pressed(boolean left_key_pressed) {
		this.left_key_pressed = left_key_pressed;
	}

	public boolean isRight_key_pressed() {
		return right_key_pressed;
	}

	public void setRight_key_pressed(boolean right_key_pressed) {
		this.right_key_pressed = right_key_pressed;
	}

	public boolean isDown_key_pressed() {
		return down_key_pressed;
	}

	public void setDown_key_pressed(boolean down_key_pressed) {
		this.down_key_pressed = down_key_pressed;
	}

	public boolean isUp_key_pressed() {
		return up_key_pressed;
	}

	public void setUp_key_pressed(boolean up_key_pressed) {
		this.up_key_pressed = up_key_pressed;
	}
	
	
}
