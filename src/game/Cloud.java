package game;

//REDKNOT MINIGAME
public class Cloud extends DynamicGameObject{
	private static final int CLOUD_VX_MAX = 8;
	private static final int CLOUD_VX_MIN = 2;
	private static final int CLOUD_VY = 0; //never changes y velocity
	

	public Cloud(int x, int y) {
		super(x, y, Utility.getRandomInt(CLOUD_VX_MAX, CLOUD_VX_MIN), CLOUD_VY);
	}

	
	//Clouds move right to left:
	@Override
	public void move() {
		//System.out.println("VX:"+this.getVelocity().getxSpeed());
		int new_x = this.getPosition().getX()-this.getVelocity().getxSpeed();
		this.setPosition(new Position(new_x, this.getPosition().getY()));
	}
	
	//Creates and returns a Cloud instance on given x position and randomly
	//from [y_offset, range_y]
	public static Cloud spawnCloud(int x, int range_y, int y_offset) {
		int random_y =  Utility.getRandomInt(range_y, y_offset);
		return new Cloud(x,random_y);
	}

}
