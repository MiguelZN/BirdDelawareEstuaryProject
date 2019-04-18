package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.util.ArrayList;

/*Class: RedKnotGameState
 * -extends the abstract class GameState (Model) 
 * -keeps track of the gamestate data of the RedKnot minigame
 */
public class RedKnotGameState extends GameState {
	private Bird RL;
	private ArrayList<Bird> flock;
	private ArrayList<GameObject> objects; //all game objects that we have to draw on the screen
	
	public RedKnotGameState(Controller controller){
		super(controller);
		this.RL = new Bird(50,30,5,20, 70, BirdType.REDKNOT);
		this.flock = new ArrayList<>();
		objects = new ArrayList<>();
	}
	
	public void collectBird() {
		this.flock.add(new Bird(0,0,3,0,BirdType.FLOCKBIRD));
	}
	
	public void lostBird() {
		if(this.flock.size()>0) {
			this.flock.remove(0);
		}
	}
	
	public int countBirds() {
		return flock.size();
	}

	public Bird getRL() {
		return RL;
	}

	public ArrayList<Bird> getFlock() {
		return flock;
	}
	

	
	
}
