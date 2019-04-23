package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.util.ArrayList;
import java.util.List;

/*Class: RedKnotGameState
 * -extends the abstract class GameState (Model) 
 * -keeps track of the gamestate data of the RedKnot minigame
 */
public class RedKnotGameState extends GameState {
	private Bird RK;
	private ArrayList<Bird> flock;
	
	
	//Enemy clouds
	private ArrayList<Cloud> clouds;
	private final int AMOUNT_OF_CLOUDS = 5;
	

	
	public RedKnotGameState(Controller controller){
		super(controller);
		this.RK = new Bird(50,30,5,20, 70, BirdType.REDKNOT);
		this.flock = new ArrayList<>();
		this.clouds = new ArrayList<>();
		
		this.addClouds();
		
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
			this.clouds.add(Cloud.spawnCloud(this.controller.getScreen().getPLAY_SCREEN_WIDTH(), Cloud.LEFT_MOST, this.controller.getScreen().getPLAY_SCREEN_HEIGHT()));
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
	public void addGameObject(int x, int y) {
		
	}

	

	
	
}
