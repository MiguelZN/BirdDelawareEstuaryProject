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
	private int forestx = 600;
	private int foresty = 300;
	
	

	//Enemy clouds
	private ArrayList<Cloud> clouds;
	private final int AMOUNT_OF_CLOUDS = 5;
	

	
	public RedKnotGameState(Controller controller){
		super(controller);
		this.RK = new Bird(new Position(20,20), new Size(90,90), new Velocity(5,16), BirdType.REDKNOT);
		this.flock = new ArrayList<>();
		this.clouds = new ArrayList<>();
		
		this.addClouds();
		this.addGameObject(new GameObject(new Position(5,5), new Size(30,30), RedKnotAsset.BACKGROUND));
		
	}
	
	public void collectBird() {
		this.flock.add(new Bird(0,0,3,0,BirdType.FLOCKBIRD));
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
//			this.clouds.add(new Cloud(screen_width,screen_height));
			this.clouds.add(Cloud.spawnCloud(GameScreen.PLAY_SCREEN_WIDTH, Cloud.LEFT_MOST, GameScreen.PLAY_SCREEN_HEIGHT));
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
	
	public int getForestx() {
		return forestx;
	}

	public int getForesty() {
		return foresty;
	}

	public int getAMOUNT_OF_CLOUDS() {
		return AMOUNT_OF_CLOUDS;
	}
	


	

	
	
}
