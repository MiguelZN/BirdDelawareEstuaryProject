package game;

//REDKNOT MINIGAME
public class Cloud extends DynamicGameObject{
	private static final int CLOUD_VX_MAX = 10;
	private static final int CLOUD_VX_MIN = 4;
	private static final int CLOUD_VY = 0; //never changes y velocity
	private int start_x, start_y;
	
	private static final int CLOUD_WIDTH = 120;
	private static final int CLOUD_HEIGHT = 120;
	
	private static final int CLOUD_SIZE_MOD = 20; //the range that the size of a cloud can change
	private static final int Y_MARGIN = 50; //Pushes the clouds' spawn lower than 1000 by 50 and the clouds' spawn higher than 0 by 50
	
	//Multipliers that affect the size of the clouds (MIN,MAX mods are multiplied with CLOUD_WIDTH/HEIGHT
	private static final double MIN_MOD = .8; //Higher value: Similar sized clouds
	private static final double MAX_MOD = .3; //Lower value: Similar sized clouds
	
	//private static final int TOPMOST_Y = 0; //the highest x position on the screen
//	private int width, height;
	
	static final int LEFT_MOST = -200; //the left-most point the clouds get to until new ones are spawned
	
	//Should be the screen width:x, screen height:y
	//(Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX): gives random negative x velocity
//	public Cloud(int x, int y) {
//		super(x, y, Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX), CLOUD_VY);
//		this.start_x = x; 
//		this.start_y = y;
//		
//		this.reSize(); //resizes the Cloud
//	}
	
	public Cloud(int x, int y, int width, int height) {
		super(x, y, width, height, Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX), CLOUD_VY);
		this.start_x = x; 
		this.start_y = y;
		
		this.reSize(); //resizes the Cloud
	}

	
	public static int getCloudVxMax() {
		return CLOUD_VX_MAX;
	}


	public static int getCloudVxMin() {
		return CLOUD_VX_MIN;
	}


	public static int getCloudVy() {
		return CLOUD_VY;
	}


	public int getStart_x() {
		return start_x;
	}


	public int getStart_y() {
		return start_y;
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
	
	//Creates min,max widths and heights and applies them to the clouds
	public void reSize() {
		int min_width = (int)(CLOUD_WIDTH*MIN_MOD);
		int max_width = (int)(CLOUD_WIDTH*MAX_MOD)+CLOUD_WIDTH;
		int new_width = Utility.randRangeInt(min_width, max_width);
		this.hitBox.changeWidth(new_width);
		System.out.println("WIDTH:"+this.hitBox.width);
		
		int min_height = (int)(CLOUD_HEIGHT*MIN_MOD);
		int max_height = (int)(CLOUD_HEIGHT*MAX_MOD)+CLOUD_HEIGHT;
		int new_height = Utility.randRangeInt(min_height,max_height);
		this.hitBox.changeHeight(new_height);
	}


	//Clouds move right to left:
	//(takes the x-velocity (positive) of the cloud and subtracts
	//the x position by it
	@Override
	public void move() {
		//System.out.println("VX:"+this.getVelocity().getxSpeed());
		int new_x = this.getPosition().getX()-this.getVelocity().getXSpeed();
		this.setPosition(new Position(new_x, this.getPosition().getY()));
		
		this.resetCloud(this.start_x, 0, this.start_y);
	}
	
	public void changeSpeed() {
		int new_vx = Utility.randRangeInt(CLOUD_VX_MIN,CLOUD_VX_MAX);
		this.setVelocity(new Velocity(new_vx,CLOUD_VY));
	}
	
	
	//Places the cloud at the starting x position, and at a random y position
	public void resetCloud(int x, int min_y, int max_y) {
		int random_y = Utility.randRangeInt(min_y, max_y);
		
		//If the cloud goes offscreen 
		if(this.getPosition().getX()<LEFT_MOST) {
			this.setPosition(new Position(x, random_y));
			this.reSize(); //resizes the Cloud
			this.changeSpeed();
		}
	}
	
	//Creates and returns a Cloud instance on given x position and randomly
	//from [y_offset, range_y]
	public static Cloud spawnCloud(int x, int min_y, int max_y) {
		int random_y =  Utility.randRangeInt(min_y, max_y);
		
		System.out.println(GameScreen.PLAY_SCREEN_WIDTH);

		int random_x =  Utility.randRangeInt(GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_WIDTH+Y_MARGIN);
		System.out.println(random_x);
		
		
		int min_width = (int)(CLOUD_WIDTH*MIN_MOD);
		int max_width = (int)(CLOUD_WIDTH*MAX_MOD)+CLOUD_WIDTH;
		int new_width = Utility.randRangeInt(min_width, max_width);
		
		int min_height = (int)(CLOUD_HEIGHT*MIN_MOD);
		int max_height = (int)(CLOUD_HEIGHT*MAX_MOD)+CLOUD_HEIGHT;
		int new_height = Utility.randRangeInt(min_height,max_height);
		
		return new Cloud(random_x,random_y, new_width, new_height);
	}

}
