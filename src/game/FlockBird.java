package game;

public class FlockBird extends Bird{
	private boolean isCollected;
	
	//Holds Animation Code in order to not create multiple Animation Instances (might change)
	int frameCount;
	private int tick;
	int frameIndex;//current frame index
	
	/*Constants*/
	
	//Bird size modifiers:
	private final static int BIRD_MIN_WIDTH = 50;
	private final static int BIRD_MAX_WIDTH = 125;
	private final static int BIRD_MIN_HEIGHT = 50;
	private final static int BIRD_MAX_HEIGHT = 100;
	
	//Currently not used...
	private final static int BIRD_HEIGHT_OFFSET = 30;
	private final static int BIRD_WIDTH_OFFSET = 50;
	
	//Offsets the spawning of the birds
	static final int TOP_Y_MARGIN = 50; //Controls the y offset from the top of the screen, EX: y=0 + TOP_Y_MARGIN
	static final int BOTTOM_Y_MARGIN = 150;  //Controls the y offset from the bottom of the screen, EX: y= GameScreen.PlayHeight - BOTTOM_Y_MARGIN
	static final int X_MARGIN = 50;
	
	static final int BIRD_VX_MAX = 10;
	static final int BIRD_VX_MIN = 4;
	
	private final String FLOCKBIRD_FILE_NAME = "sprite-6-flockbird.png";
	
	static final int LEFT_MOST = -200; //the left-most point the birds get to until new ones are spawned

	//The position should be similar to the Clouds where it initally starts
	//at right side of the screen (x position) and at a random y position
	public FlockBird(Position p, Size s, Velocity v) {
		super(p,s,v);
		this.isCollected = false; //this means the FlockBird is not collected by the Player
		
		
	}
	
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
		int new_x = this.getPosition().getX()+this.getVelocity().getXSpeed();
		this.setPosition(new Position(new_x, this.getPosition().getY()));
	}
	
	/*Created by Miguel:
	 * -Creates and returns a FlockBird instance on given x position and randomly 
	 * from [y_offset, range_y] with a random width and random height
	 * -This spawns a flock bird NEAR THE PLAYER Redknot
	 */
	public static FlockBird spawnFlockBird(RedKnot RK) {
		Position RK_position = RK.getPosition();
		int x_offset = 5; //adds 5 pixels so that the Flock Bird does not start at x=0 
		int y_offset = 50; //How high the Flock bird is away from the Player's RedKnot bird
		
		
		//Generates a random x, random y values 
		int relative_x = Utility.randRangeInt(x_offset, RK_position.getX());
		int relative_y = Utility.randRangeInt(RK_position.getY()-y_offset, RK_position.getY()+y_offset);

		
		
		//Chooses a random width and height for the clouds
		int random_width = Utility.randRangeInt(BIRD_MIN_WIDTH, BIRD_MAX_WIDTH);
		int random_height = Utility.randRangeInt(BIRD_MIN_HEIGHT,BIRD_MAX_HEIGHT);
		
		return new FlockBird(new Position(relative_x, relative_y), new Size(random_width,random_height), RK.getVelocity(), true);
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
		int random_width = Utility.randRangeInt(BIRD_MIN_WIDTH, BIRD_MAX_WIDTH);
		int random_height = Utility.randRangeInt(BIRD_MIN_HEIGHT, BIRD_MAX_HEIGHT);
		
		int random_vx = -1*Utility.randRangeInt(BIRD_VX_MIN, BIRD_VX_MAX);
		int vy = 0;
		
		return new FlockBird(new Position(random_x, random_y), new Size(random_width,random_height), new Velocity(random_vx,0), false);
	}
	
	public void incrementIndex(){
		frameIndex=(frameIndex+1)%frameCount;
	}
	
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
	 */
	public boolean checkIfOutOfBounds(int x_bounds) {
		Position p = this.getPosition();
		
		if(p.getX()<x_bounds){
//			System.out.println("CLOUDX:"+p.getX());
//			System.out.println("TRUE");
			return true;
		}
		else {
			return false;
		}
	}
}
