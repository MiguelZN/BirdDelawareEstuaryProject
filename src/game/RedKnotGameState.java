package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;

/*Class: RedKnotGameState
 * -extends the abstract class GameState (Model) 
 * -keeps track of the gamestate data of the RedKnot minigame
 */
public class RedKnotGameState extends GameState {
	private RedKnot RK;
	private int score;
	static final String SCORE_TEXT = "Score: ";
	static final int SCORE_FONT_SIZE = 40;
	
	private boolean debug_mode;
	

	//Enemy clouds
	private ArrayList<Cloud> clouds; 
	private final int AMOUNT_OF_CLOUDS = 3;
	
	//FlockBirds
	private ArrayList<FlockBird> flock; 
	//The range which controls how low/high of a chance for a FlockBird to appear
	private final int FB_CHANCE_MAX = 1000; 
	private final int FB_CHANCE_LOW = 0;
	private final int FB_THRESHOLD = 10;
	
	//Variable to stop the game from continously removing FlockBirds from 'flock' when touching the same cloud
	private Cloud lastCloudTouched;
	
	
	//GAME_TIME: (NOTE: ALL TIMING IS DONE IN MILLISECONDS)
	//EX: GameTimer.ONE_SECOND == 1000 for 1000 milliseconds as 
	//this is what the Java.util.Timer takes in
	static final int MAX_GAME_TIME = GameTimer.ONE_MINUTE;
	private GameTimer game_timer;
	private int current_time;
	
	
	/*Score final constants*/
	static final int COLLECTED_BIRD_SCORE = 200;
	static final int TOUCHED_CLOUD_SCORE = -200;

	
	/**
	 * @param controller
	 */
	public RedKnotGameState(Controller controller){
		super(controller);
		this.lastCloudTouched = null;
		this.score=0; //Sets the initial score 
		this.RK = new RedKnot();
		this.flock = new ArrayList<>();
		this.clouds = new ArrayList<>();
		this.addGameObject(new GameObject(new Position(5,5), new Size(30,30), RedKnotAsset.BACKGROUND));
		debug_mode = false; //initially turns off debug mode
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				
				if(getIsGameRunning()) {
					//System.out.println("TIMER");
					current_time+=GameTimer.ONE_SECOND;
					//System.out.println("GAMETIME RAN:"+current_time +" milliseconds");
					
					if(current_time>= MAX_GAME_TIME) {
						//System.exit(0);
						setIsGameRunning(false);
					}
				}
			}
		};
		
		//the game timer runs every second and updates the counter 'current_time'
		this.game_timer = new GameTimer(GameTimer.ONE_SECOND,task);
		
	}
	
//	public void collectBird() {
//		this.flock.add(FlockBird.spawnFlockBird(RK));
//	}
//	
//	public void collectBird(ListIterator<FlockBird> fb_iter) {
//		fb_iter.add(FlockBird.spawnFlockBird(RK));
//	}
	
	
	/**
	 * @param fb_iter
	 * @param FB
	 */
	public void collectBird(ListIterator<FlockBird> fb_iter, FlockBird FB) {
		fb_iter.add(FlockBird.spawnFlockBird(RK, FB));
	}
	
	/**
	 * 
	 */
	public void lostBird() {
		if(this.flock.size()>0) {
			this.flock.remove(0);
		}
	}
	

	/* (non-Javadoc)
	 * @see game.GameState#ontick()
	 */
	@Override
	public void ontick() {
		//Modify GameObjects, then GameObjects are passed to the controller	
		//Only runs the game if the game is currently running
		if(this.getIsGameRunning()) {
			checkClouds();
			RK.move();
			checkFlockBirds();
		}
	
	}
	
	/**
	 * @return
	 */
	public boolean updateDebugging() {
		this.switchDebugMode();
		return this.debug_mode;
	}

	
	
	
	
	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 * -Using an iterator, iterates through the 'clouds' ArrayList
	 * -Handles any cloud related actions in order to keep it O(n) and
	 * to keep game logic more organized
	 */
	
	/*NOTE: 
	 * Added collision with clouds and everything works perfectly but
	 * if the Red Knot touches more than one cloud at a time the game
	 * 'overticks' and removes all the birds/removes the player's score a lot
	 */
	/**
	 * 
	 */
	public void checkClouds(){
		addClouds(); //adds the clouds intially and readds clouds
		
		//Added iterator to remove clouds once they reach the end
		Iterator<Cloud> cloud_iter = clouds.iterator();
		while(cloud_iter.hasNext()) {
			Cloud c = cloud_iter.next();
			c.move();

			if(detectCloudCollision(RK, c) && this.lastCloudTouched !=c) {
				this.lastCloudTouched = c;
				this.incrementScore(TOUCHED_CLOUD_SCORE);
				Iterator<FlockBird> fb_iter = flock.iterator();
				while(fb_iter.hasNext()) {
					FlockBird FB = fb_iter.next();
					//removes a bird from the flock if it is collected by the Player
					if(FB.getIsCollected()) {
						FB.setGotLostInStorm(true); //indicates that the Player touched a cloud, and one of the Flock birds got 'lost in the storm'
						break;
					}
				}
			}
		
			
			//If the cloud goes out of bounds (exits the left side of screen)
			//it then gets removed from 'clouds' and a new 'Cloud'
			//instance is created
			boolean outofbounce = c.checkIfOutOfBounds(Cloud.LEFT_MOST);

			if(outofbounce){
				//System.out.println("REMOVING");
				cloud_iter.remove();
			}
		}
		
	}
	
	/*Created by Derek:
	 * -Takes in no arguments, returns nothing
	 * -Uses MVC design to pass over GameObjects to the Controller
	 * which then passes them to the RedKnotView in order to draw
	 * them onto the screen
	 */
	/* (non-Javadoc)
	 * @see game.GameState#getUpdateableGameObjects()
	 */
	public ArrayList<GameObject> getUpdateableGameObjects(){
//		RK flock clouds
		ArrayList<GameObject> output = new ArrayList<>();
		output.add(RK);//it is important that we insert the redknot first.
		output.addAll(clouds);
		output.addAll(flock);
		
		output = updateGameObjects(RK, output);
		
		return output;
		
	}
	
	/**
	 * @param RK
	 * @param GO_AL
	 * @return
	 */
	public ArrayList<GameObject> updateGameObjects(RedKnot RK, ArrayList<GameObject> GO_AL) {
		for(GameObject GO:GO_AL) {
			
			//Shifts all of the game objects by the RedKnots velocity
			if(GO!=RK && (GO instanceof FlockBird == false)) {
				//System.out.println("BEFORE POS:"+GO.getPosition());
				int x_speed=  -1*RK.getVelocity().getXSpeed();
				GO.shiftGameObject(new Velocity(x_speed,0));
				//System.out.println("UPDATING VELOCITIES:"+x_speed);
				//System.out.println("AFTER POS:"+GO.getPosition());
			}
		}
		
		return GO_AL;
	}
	
	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 * -Adds 'Cloud' instances into 'clouds' ArrayList
	 * -Currently re-adds 'Cloud' instances when they go off-screen
	 */
	/**
	 * 
	 */
	public void addClouds() {
//		System.out.println("READDING CLOUDS");
//		System.out.println("CLOUDS SIZE:"+clouds.size());
		
		//Checks if there are not enough clouds and adds the amount needed
		if(clouds.size()<this.AMOUNT_OF_CLOUDS) {
			for(int i=0;i<this.AMOUNT_OF_CLOUDS-clouds.size();i++) {
				int screen_width = this.controller.getScreen().getX();
				int screen_height = this.controller.getScreen().getY();
				this.clouds.add(Cloud.spawnCloud(GameScreen.PLAY_SCREEN_WIDTH, Cloud.Y_MARGIN, GameScreen.PLAY_SCREEN_HEIGHT-Cloud.Y_MARGIN));
			}
		}
	}
	
	/**
	 * 
	 */
	public void checkFlockBirds() {
		addRandomFlockBirds();
		
		//Added iterator to remove clouds once they reach the end
		ListIterator<FlockBird> fb_iter = flock.listIterator();
		
		while(fb_iter.hasNext()) {
			FlockBird FB = fb_iter.next();
			FB.move();
			
			detectFlockBirdCollection(this.getRK(),FB,fb_iter);
			
			//If the cloud goes out of bounds (exits the left side of screen)
			//it then gets removed from 'clouds' and a new 'Cloud'
			//instance is created
			if(FB.checkIfOutOfBoundsLeft()){
				//System.out.println("REMOVING");
				fb_iter.remove();
			}
			
			if(FB.checkIfOutOfBoundsBottom()) {
				fb_iter.remove();
			}
		}
	}
	
	/**
	 * 
	 */
	public void setFlyStateAllFlockBirds() {
		ListIterator<FlockBird> fb_iter = flock.listIterator();
		
		while(fb_iter.hasNext()) {
			FlockBird FB = fb_iter.next();
			
			if(FB.getFlyState() == -1) {
				FB.setFlyState(0);
			}
			else {
				FB.setFlyState(FB.getFlyState());
			}
			
			if(FB.getFlyState() == 1) {
				FB.setFlyState(0);
			}
			else {
				FB.setFlyState(FB.getFlyState());
			}
		}
	}
	
	/**
	 * 
	 */
	public void allFlockBirdsFlyUp() {
		ListIterator<FlockBird> fb_iter = flock.listIterator();
		
		while(fb_iter.hasNext()) {
			FlockBird FB = fb_iter.next();
			FB.newFlyUp();
		}
	}
	
	/**
	 * 
	 */
	public void allFlockBirdsFlyDown() {
		ListIterator<FlockBird> fb_iter = flock.listIterator();
		
		while(fb_iter.hasNext()) {
			FlockBird FB = fb_iter.next();
			FB.newFlyDown();
		}
	}
	
	/**
	 * @param RK
	 * @param FB
	 * @param fb_iter
	 */
	public void detectFlockBirdCollection(RedKnot RK, FlockBird FB, ListIterator fb_iter) {
		if(Utility.GameObjectCollision(RK, FB) && !FB.getIsCollected()) {
			fb_iter.remove(); //removes the FlockBird
			this.collectBird(fb_iter, FB);
			this.incrementScore(COLLECTED_BIRD_SCORE);
			//System.out.println(this.score);
		}
	}
	
	/**
	 * @param RK
	 * @param c
	 * @return
	 */
	public boolean detectCloudCollision(RedKnot RK, Cloud c) {
		if(Utility.GameObjectCollision(RK, c)){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	public void addRandomFlockBirds() {
		int chance = Utility.randRangeInt(this.FB_CHANCE_LOW, this.FB_CHANCE_MAX);
		int threshold = this.FB_THRESHOLD;
		
		if(chance<threshold) {
			this.flock.add(FlockBird.spawnRandomFlockBird(GameScreen.PLAY_SCREEN_WIDTH+FlockBird.X_MARGIN, 0+FlockBird.TOP_Y_MARGIN, GameScreen.PLAY_SCREEN_HEIGHT-FlockBird.BOTTOM_Y_MARGIN));
		}
	}

	
	
	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 * -Switches the 'debug_mode' boolean from true -> false
	 * and false -> true when called
	 * -When true, this allows the RedKnotView to draw the hitBoxes
	 * and any future possible debug mode for bug testing
	 */
	/**
	 * 
	 */
	public void switchDebugMode() {
		if(this.debug_mode) {
			this.debug_mode = false;
		}
		else {
			this.debug_mode = true;
		}
	}

	
	
	
	/* (non-Javadoc)
	 * @see game.GameState#addGameObject(game.GameObject)
	 */
	@Override
	public void addGameObject(GameObject o) {
		
	}
	
	/*Getters*/
	
	/**
	 * @return
	 */
	public ArrayList<Cloud> getClouds(){
		return this.clouds;
	}
	
	/**
	 * @return
	 */
	public int countBirds() {
		return flock.size();
	}
	
	/**
	 * @return
	 */
	public RedKnot getRK() {
		return RK;
	}

	/**
	 * @return
	 */
	public ArrayList<FlockBird> getFlock() {
		return flock;
	}
	
	/**
	 * @return
	 */
	public int getScore(){
		return score;
	}
	/**
	 * @param x
	 */
	public void setScore(int x){
		score=x;
	}
	/**
	 * @param x
	 */
	public void incrementScore(int x){
		score+=x;
	}

	/**
	 * @return
	 */
	public int getAMOUNT_OF_CLOUDS() {
		return AMOUNT_OF_CLOUDS;
	}

	
}
