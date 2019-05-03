package game;

/*Note: -Miguel
 * -Changed the Cloud spawning, instead of resetting the clouds and having them change speeds,size, etc
 * when they go offscreen, instead made it so they get removed from 'clouds' ArrayList
 * and have new clouds respawn. We can modify the spawning so they spawn by chance
 * or every time have n amount of clouds or have them spawn on a y-grid etc.
 */

//REDKNOT MINIGAME
public class Cloud extends DynamicGameObject{
	private static final int CLOUD_VX_MAX = 10;
	private static final int CLOUD_VX_MIN = 4;
	private static final int CLOUD_VY = 0; //never changes y velocity
	
	private static final int CLOUD_WIDTH = 120;
	private static final int CLOUD_HEIGHT = 120;
	
	private static final int CLOUD_SIZE_MOD = 20; //the range that the size of a cloud can change
	static final int Y_MARGIN = 60; //Pushes the clouds' spawn lower than 1000 by 60 and the clouds' spawn higher than 0 by 60
	static final int X_MARGIN = 60; //Pushes the clouds' spawn lower than 1000 by 60 and the clouds' spawn higher than 0 by 60
	
	//Multipliers that affect the size of the clouds (MIN,MAX mods are multiplied with CLOUD_WIDTH/HEIGHT
	private static final double MIN_MOD = .8; //Higher value: Similar sized clouds
	private static final double MAX_MOD = .3; //Lower value: Similar sized clouds
	static final int LEFT_MOST = -200; //the left-most point the clouds get to until new ones are spawned
	
	
	
	
	public Cloud(int x, int y, int width, int height) {
		super(x, y, width, height, -1*Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX), CLOUD_VY);
		this.reSize(); //resizes the Cloud
		this.hitBox.updateRectangleHitBox();
	}

	/*Getters*/
	public static int getCloudVxMax() {
		return CLOUD_VX_MAX;
	}


	public static int getCloudVxMin() {
		return CLOUD_VX_MIN;
	}


	public static int getCloudVy() {
		return CLOUD_VY;
	}


	public int getCLOUD_WIDTH() {
		return CLOUD_WIDTH;
	}


	public int getCLOUD_HEIGHT() {
		return CLOUD_HEIGHT;
	}


	public int getWidth() {
		return this.hitBox.width;
	}


	public int getHeight() {
		return this.hitBox.height;
	}
	
	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 *-Creates min,max widths and heights and applies them to the clouds
	 */
	public void reSize() {
		int min_width = (int)(CLOUD_WIDTH*MIN_MOD);
		int max_width = (int)(CLOUD_WIDTH*MAX_MOD)+CLOUD_WIDTH;
		int new_width = Utility.randRangeInt(min_width, max_width);
		this.hitBox.changeWidth(new_width);
		
		int min_height = (int)(CLOUD_HEIGHT*MIN_MOD);
		int max_height = (int)(CLOUD_HEIGHT*MAX_MOD)+CLOUD_HEIGHT;
		int new_height = Utility.randRangeInt(min_height,max_height);
		this.hitBox.changeHeight(new_height);
	}


	/*Created by Miguel:
	 * -Takes in no arguments, returns nothing
	 * -When called, updates the Cloud's position and moves its x position to the left
	 * by the Cloud's x velocity
	 */
	@Override
	public void move() {
		int new_x = this.getPosition().getX()+this.getVelocity().getXSpeed();
		this.setPosition(new Position(new_x, this.getPosition().getY()));
	}
	
	/*Created by Miguel:
	 * -Takes no arguments and returns nothing
	 * -Changes the Current Cloud instance's x velocity
	 */
	public void changeSpeed() {
		int new_vx = Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX);
		this.setVelocity(new Velocity(new_vx,CLOUD_VY));
	}
	
	
	/*Created by Miguel:
	 * -Takes in three integers: x, min y, max y values
	 * -Re-randomizes the current Cloud instance (assigns it a new position, size, and speed)
	 */
	//Places the cloud at the starting x position, and at a random y position
	public void resetCloud(int x, int min_y, int max_y) {
		int random_y = Utility.randRangeInt(min_y, max_y);
		this.setPosition(new Position(x, random_y));
		this.reSize(); //resizes the Cloud
		this.changeSpeed();
		
	}
	
	
	/*Created by Miguel:
	 * Method that takes in a Cloud object and returns a boolean
	 * value of whether or not the inputted Cloud instance's x position has
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

	
	/*Created by Miguel:
	 * Creates and returns a Cloud instance on given x position and randomly 
	 * from [y_offset, range_y] with a random width and random height
	 */
	public static Cloud spawnCloud(int x, int min_y, int max_y) {
		//Generates a random x, random y values 
		int random_y =  Utility.randRangeInt(min_y, max_y);
		int random_x =  Utility.randRangeInt(GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_WIDTH+X_MARGIN);

		
		
		//Chooses a random width and height for the clouds
		int min_width = (int)(CLOUD_WIDTH*MIN_MOD);
		int max_width = (int)(CLOUD_WIDTH*MAX_MOD)+CLOUD_WIDTH;
		int new_width = Utility.randRangeInt(min_width, max_width);
		
		int min_height = (int)(CLOUD_HEIGHT*MIN_MOD);
		int max_height = (int)(CLOUD_HEIGHT*MAX_MOD)+CLOUD_HEIGHT;
		int new_height = Utility.randRangeInt(min_height,max_height);
		
		return new Cloud(random_x,random_y, new_width, new_height);
	}

}
