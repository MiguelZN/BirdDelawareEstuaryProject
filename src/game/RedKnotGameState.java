package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*Class: RedKnotGameState
 * -extends the abstract class GameState (Model) 
 * -keeps track of the gamestate data of the RedKnot minigame
 */
public class RedKnotGameState extends GameState {
	private Bird RK;
	private ArrayList<Bird> flock;
	private int score;
	static final String SCORE_TEXT = "Score: ";
	static final int SCORE_FONT_SIZE = 40;
	
	boolean debug_mode;
	

	//Enemy clouds
	private ArrayList<Cloud> clouds; 
	private final int AMOUNT_OF_CLOUDS = 30;
	

	
	public RedKnotGameState(Controller controller){
		super(controller);
		this.score=0; //Sets the initial score 
		this.RK = new Bird(new Position(100,20), new Size(100,50), new Velocity(5,10), BirdType.REDKNOT);
		this.flock = new ArrayList<>();
		this.clouds = new ArrayList<>();
		this.addClouds();
		this.addGameObject(new GameObject(new Position(5,5), new Size(30,30), RedKnotAsset.BACKGROUND));
		
		this.debug_mode = false; //initially turns off debug mode
		
	}
	
	public void collectBird() {
		this.flock.add(new Bird(new Position(0,0), new Size(20,20), new Velocity(10,0), BirdType.FLOCKBIRD));
	}
	
	public void lostBird() {
		if(this.flock.size()>0) {
			this.flock.remove(0);
		}
	}
	
	public ArrayList<Cloud> getClouds(){
		return this.clouds;
	}
	
	public void addClouds() {
		for(int i=0;i<this.AMOUNT_OF_CLOUDS;i++) {
			int screen_width = this.controller.getScreen().getX();
			int screen_height = this.controller.getScreen().getY();
			this.clouds.add(Cloud.spawnCloud(GameScreen.PLAY_SCREEN_WIDTH, 0, GameScreen.PLAY_SCREEN_CONSTRAINT_H-Cloud.Y_MARGIN));
		}
	}
	
	public void switchDebugMode() {
		if(this.debug_mode) {
			this.debug_mode = false;
		}
		else {
			this.debug_mode = true;
		}
	}

	
	public int countBirds() {
		return flock.size();
	}

	public Bird getRK() {
		return RK;
	}

	public ArrayList<Bird> getFlock() {
		return flock;
	}
	
	@Override
	public void addGameObject(GameObject o) {
		
	}
	public int getScore(){
		return score;
	}
	public void setScore(int x){
		score=x;
	}
	public void incrementScore(int x){
		score+=x;
	}

	public int getAMOUNT_OF_CLOUDS() {
		return AMOUNT_OF_CLOUDS;
	}
	


	

	
	
}
