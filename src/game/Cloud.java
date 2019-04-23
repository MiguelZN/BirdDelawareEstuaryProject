package game;

//REDKNOT MINIGAME
public class Cloud extends DynamicGameObject{
	private static final int CLOUD_VX_MAX = 15;
	private static final int CLOUD_VX_MIN = 5;
	private static final int CLOUD_VY = 0; //never changes y velocity
	private int start_x, start_y;
	
	private final int CLOUD_WIDTH = 120;
	private final int CLOUD_HEIGHT = 120;
	private final int Y_MARGIN = 50; //Pushes the clouds' spawn lower than 1000 by 50 and the clouds' spawn higher than 0 by 50
	
	private int width, height;
	
	

	public Cloud(int x, int y) {
		super(x, y, Utility.getRandomInt(CLOUD_VX_MIN,CLOUD_VX_MAX), CLOUD_VY);
		this.start_x = x;
		this.start_y = y;
		
		this.width = Utility.getRandomInt(CLOUD_WIDTH, 30);
		this.height = Utility.getRandomInt(CLOUD_HEIGHT, 30);
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
		return width;
	}


	public int getHeight() {
		return height;
	}
	
	public void reSize() {
		this.width = Utility.getRandomInt(CLOUD_WIDTH, 30);
		this.height = Utility.getRandomInt(CLOUD_HEIGHT, 30);
	}


	//Clouds move right to left:
	@Override
	public void move() {
		//System.out.println("VX:"+this.getVelocity().getxSpeed());
		int new_x = this.getPosition().getX()-this.getVelocity().getxSpeed();
		this.setPosition(new Position(new_x, this.getPosition().getY()));
		
		this.resetCloud(this.start_x, 50, this.start_y-50);
	}
	
	
	//Places the cloud at the starting x position, and at a random y position
	public void resetCloud(int x, int min_y, int max_y) {
		int random_y = Utility.getRandomInt(min_y, max_y);
		
		//If the cloud goes offscreen 
		if(this.getPosition().getX()<-50) {
			this.setPosition(new Position(x, random_y));
			this.reSize(); //resizes the Cloud
		}
	}
	
	//Creates and returns a Cloud instance on given x position and randomly
	//from [y_offset, range_y]
	public static Cloud spawnCloud(int x, int range_y, int y_offset) {
		int random_y =  Utility.getRandomInt(range_y, y_offset);
		
		System.out.println(GameScreen.getPLAY_SCREEN_WIDTH());

		int random_x =  Utility.getRandomInt(GameScreen.getPLAY_SCREEN_WIDTH()+300, GameScreen.getPLAY_SCREEN_WIDTH());
		System.out.println(random_x);
		return new Cloud(random_x,random_y);
	}

}
