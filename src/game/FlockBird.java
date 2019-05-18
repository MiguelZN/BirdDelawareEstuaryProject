package game;

/**
 * @author MiguelZN
 *
 */
public class FlockBird extends Bird{
	private boolean isCollected; //boolean if false, this causes the 'move()' function to move the bird leftwards, if true, this moves the bird
								//alongside the RedKnot Player bird
	private boolean gotLostInStorm; //boolean that if false, this means the bird is still with the flock of birds and does not sink down
	
	/*Explanation: instead of having multiple sprite animations and have to link the spriteanimation to its data counterpart
	 * it was easier to just have each flockbird have sprite animation code (without the loading of images) and only the frameticks, 
	 * frameindex, etc. Then when drawing each FlockBird in RedKnotView, the RedKnot sprite animation loaded simply has a method which takes
	 * in a frameindex, etc and draws it onto the screen
	 */
	
	//Holds Animation Code in order to not create multiple Animation Instances (might change)
	int frameCount;
	private int tick;
	int frameIndex;//current frame index
	
	/*RedKnot Code for moving the FlockBirds up and down similar to RedKnot*/
	private int updownstop=0;
	
	/*Constants*/
	
	//Bird size modifiers:
	private final static int BIRD_MIN_WIDTH = 50;
	private final static int BIRD_MAX_WIDTH = 80;
	private final static int BIRD_MIN_HEIGHT = 50;
	private final static int BIRD_MAX_HEIGHT = 80;
	
	//Currently not used...
	private final static int BIRD_HEIGHT_OFFSET = 30;
	private final static int BIRD_WIDTH_OFFSET = 50;
	
	//Offsets the spawning of the birds
	static final int TOP_Y_MARGIN = 50; //Controls the y offset from the top of the screen, EX: y=0 + TOP_Y_MARGIN
	static final int BOTTOM_Y_MARGIN = 150;  //Controls the y offset from the bottom of the screen, EX: y= GameScreen.PlayHeight - BOTTOM_Y_MARGIN
	static final int X_MARGIN = 50;
	
	static final int BIRD_VX_MAX = 10;
	static final int BIRD_VX_MIN = 4;
	
	static final int RELATIVE_OFFSET_X = 50;
	static final int RELATIVE_OFFSET_Y = 50;
	
	private final String FLOCKBIRD_FILE_NAME = "sprite-6-flockbird.png";
	
	//Boundaries (if the birds go past these boundaries, they are removed from 'flock')
	static final int LEFT_MOST = -200; //the left-most point the birds get to until new ones are spawned
	static final int BOTTOM_MOST = GameScreen.PLAY_SCREEN_HEIGHT+300; //the most bottom most (y> GameScreen.HEIGHT) that the bird can go before getting removed

	
	
//	//The position should be similar to the Clouds where it initally starts
//	//at right side of the screen (x position) and at a random y position
//	public FlockBird(Position p, Size s, Velocity v) {
//		super(p,s,v);
//		this.isCollected = false; //this means the FlockBird is not collected by the Player
//		
//		
//	}
	
	/**@author Miguel
	 * @param p
	 * @param s
	 * @param v
	 * @param isCollected
	 */
	public FlockBird(Position p, Size s, Velocity v, boolean isCollected) {
		super(p,s,v);
		this.isCollected = isCollected;
		
		
		//Note: -Miguel
		//Added Animation Code and updates the frameIndex (could be changed) but then
		//we would have to add a bunch of Animation instances and this currently works
		frameIndex=0;
		tick=0;
		this.frameCount = Integer.parseInt(FLOCKBIRD_FILE_NAME.split("-")[1]);
	}
	
	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 * -When called, updates the FlockBird's position and moves its x position to the left
	 * by the FlockBird's x velocity
	 */
	@Override
	public void move() {
		if(!isCollected && !gotLostInStorm) { //Not collected = false which means the birds that are not collected move left otherwise they fall down
			Position new_position = this.getPosition().moveByVelocity(this.getVelocity());
			this.setPosition(new_position);
		}
		else if(isCollected && !gotLostInStorm) {
			switch(this.getFlyState()) {
			case 1:this.FlyUp();break;
			case -1:this.FlyDown();break;
			}
		}
		
		else if(isCollected && gotLostInStorm) {
			this.setVelocity(new Velocity(0,5));
			this.move(this.getVelocity());
		}
		
		//chance !isCollected to false 
	}
	
	/* (non-Javadoc)
	 * @see game.Bird#move(game.Velocity)
	 */
	public void move(Velocity v) {
		Position new_position = this.getPosition().moveByVelocity(v);
		this.setPosition(new_position);
		
		//System.out.println(this.getVelocity());
	}
	

	
	/*Created by Miguel:
	 * -Creates and returns a FlockBird instance on given x position and randomly 
	 * from [y_offset, range_y] with a random width and random height
	 * -This spawns a flock bird NEAR THE PLAYER Redknot
	 */
	public static FlockBird spawnNearbyFlockBird(RedKnot RK, FlockBird FB) {
		Position RK_position = RK.getPosition();
		int x_offset = 5; //adds 5 pixels so that the Flock Bird does not start at x=0 
		//int y_offset = 50; //How high the Flock bird is away from the Player's RedKnot bird
		
		
		//Generates a random x, random y values 
		int relative_x = Utility.randRangeInt(x_offset, RK_position.getX()-RELATIVE_OFFSET_X);
		int relative_y = Utility.randRangeInt(RK_position.getY()-RELATIVE_OFFSET_Y, RK_position.getY()+ RELATIVE_OFFSET_Y);
		
		Position Relative_Pos = new Position(relative_x, relative_y);
		
		return new FlockBird(new Position(relative_x, relative_y), FB.getSize(), new Velocity(0,RK.getVelocity().getYSpeed()), true);
	}
	
	/*Created by Miguel:
	 * Creates and returns a FlockBird instance on given x position and randomly 
	 * from [y_offset, range_y] with a random width and random height
	 * -This spawns a flock bird Randomly on the right side of the screen (similar to the clouds)
	 */
	public static FlockBird spawnRandomFlockBird(int x, int min_y, int max_y) {
		//Generates a random x, random y values 
		int random_x = x;
		int random_y = Utility.randRangeInt(min_y, max_y);

		//Chooses a random width and height for the clouds
//		int random_width = Utility.randRangeInt(BIRD_MIN_WIDTH, BIRD_MAX_WIDTH);
//		int random_height = Utility.randRangeInt(BIRD_MIN_HEIGHT, BIRD_MAX_HEIGHT);
		
		int random_width = Utility.randRangeInt(BIRD_MIN_WIDTH, BIRD_MAX_WIDTH);
		int random_height = Utility.randRangeInt(BIRD_MIN_HEIGHT, BIRD_MAX_HEIGHT);
		
		int random_vx = -1*Utility.randRangeInt(BIRD_VX_MIN, BIRD_VX_MAX);
		int vy = 0;
		
		return new FlockBird(new Position(random_x, random_y), new Size(random_width,random_height), new Velocity(random_vx,0), false);
	}
	
	/**
	 * @return
	 */
	public boolean getIsCollected() {
		return this.isCollected;
	}
	
	/**
	 * 
	 */
	public void incrementIndex(){
		frameIndex=(frameIndex+1)%frameCount;
	}
	

	/**@author Miguel
	 *-Increments the current FlockBird's 'tick' by 1. 
	 */
	
	/*Explanation: instead of having multiple sprite animations and have to link the spriteanimation to its data counterpart
	 * it was easier to just have each flockbird have sprite animation code (without the loading of images) and only the frameticks, 
	 * frameindex, etc. Then when drawing each FlockBird in RedKnotView, the RedKnot sprite animation loaded simply has a method which takes
	 * in a frameindex, etc and draws it onto the screen
	 */
	public void updateCurrImage() {
		this.tick++;
		if(GameScreen.GAME_FPS/frameCount==tick){
			incrementIndex();
			tick=0;
		}
	}
	
	/*Created by Miguel:
	 * Method that takes in a FlockBird object and returns a boolean
	 * value of whether or not the inputted FlockBird instance's x position has
	 * passed the 'x_bounds' integer.
	 * -Stops the flockbird from going past a certain left point (x position)
	 */
	public boolean checkIfOutOfBoundsLeft() {
		Position p = this.getPosition();
		
		if(p.getX()<LEFT_MOST){
//			System.out.println("CLOUDX:"+p.getX());
//			System.out.println("TRUE");
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**@author Miguel
	 * @return
	 * -Stops the flockbird from going past the bottom of the screen
	 */
	public boolean checkIfOutOfBoundsBottom() {
		Position p = this.getPosition();
		
		if(p.getY()>BOTTOM_MOST){
//			System.out.println("CLOUDX:"+p.getX());
//			System.out.println("TRUE");
			return true;
		}
		else {
			return false;
		}
	}

	/**@author Miguel
	 * @param bool
	 */
	public void setGotLostInStorm(boolean bool) {
		this.gotLostInStorm = bool;
	}
	
	/*------------------*/
	/*Methods for moving the FlockBird in accordance to the Player*/
	/**
	 * 
	 */
	public void newFlyUp() {
		updownstop=1;
	}
	/**
	 * 
	 */
	public void newFlyDown() {
		updownstop=-1;
	}
	/**
	 * 
	 */
	public void flyDownStop() {
		updownstop=0;
	}
	/**
	 * 
	 */
	public void flyUpStop() {
		updownstop=0;
	}
	/**
	 * @return
	 */
	public int getFlyState() {
		return updownstop;
	}
	/**
	 * @param x
	 */
	public void setFlyState(int x) {
		updownstop=x;
	}
	
	/*------------------*/
	
	
	/**@author Miguel
	 * -Moves the FlockBird upwards by its velocity property
	 */
	public void FlyUp() {
		Position p = this.getPosition();
		int new_y = p.getY()-this.getVelocity().getYSpeed();
		
		//if this will place the bird off the screen, dont move the bird.
		if(new_y < 0)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	/**@author Miguel
	 * -Moves the FlockBird downwards by its velocity property
	 */
	public void FlyDown() {
		Position p = this.getPosition();
		int new_y = p.getY()+this.getVelocity().getYSpeed();
		//if this will place the bird off the screen, dont move the bird.
		System.out.println(new_y);
		System.out.println(this.getSize().getHeight());
		if((new_y + this.getSize().getHeight() + GameScreen.TITLE_BAR_HEIGHT) > GameScreen.PLAY_SCREEN_HEIGHT)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
}
