package game;

//ClapperRail Game

public class Platform extends DynamicGameObject {
	
	private static final int PLATFORM_VX = 0;
	private static final int PLATFORM_VY = 0;
	
	private static final int PLATFORM_WIDTH = 250;
	private static final int PLATFORM_HEIGHT = 160;
	
	public Platform(int x, int y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT, PLATFORM_VX, PLATFORM_VY);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}
	
	public int getWidth() {
		return PLATFORM_WIDTH;
	}
	
	public int getHeight() {
		return PLATFORM_HEIGHT;
	}

}
