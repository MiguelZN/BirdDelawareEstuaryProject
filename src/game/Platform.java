package game;

//ClapperRail Game

public class Platform extends GameObject {
	
	private static final int PLATFORM_VX = 0;
	private static final int PLATFORM_VY = 0;
	
	private static final int PLATFORM_WIDTH = 250;
	private static final int PLATFORM_WIDTH_CORRECTION = 100; //image must be messed up, this is tempoary
	private static final int PLATFORM_HEIGHT = 160;
	
	private boolean hasFood = false;
	private Food f;
	
	public Platform(int x, int y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		f = null;
	}
	
	public int getWidth() {
		return PLATFORM_WIDTH;
	}
	
	public int getHeight() {
		return PLATFORM_HEIGHT;
	}
	/* 
	 * the only collision we want is the collision during falling,
	 * and we would like to check if the next tick's movement will bring it into collision
	 * with the platform.
	 */
	public boolean willTouchPlatform(Position pos, int vy){
		if(pos.getY()+vy >= this.getPosition().getY()) {
			if((pos.getX() >= this.getPosition().getX()) 
					&& (pos.getX() <= (this.getPosition().getX() + (this.getWidth()-PLATFORM_WIDTH_CORRECTION)))) {
				return true;
			}
		}
		return false;
	}
	public boolean touchPlatform(Position pos) {
		if(pos.getY() == this.getPosition().getY() || pos.getY() == this.getPosition().getY()+1) {
			if((pos.getX() >= this.getPosition().getX()) 
					&& (pos.getX() <= (this.getPosition().getX() + (this.getWidth()-PLATFORM_WIDTH_CORRECTION)))) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public Food getFood() {
		return f;
	}


	public void addFood() {
		f = new Food(this.getPosition().getX()+(this.getWidth()/4),this.getPosition().getY()+20,0);
	}
	
	public void removeFood() {
		f = null;
	}
	
	public boolean getHasFood() {
		return hasFood;
	}
	
	public void setHasFood(boolean b) {
		hasFood = b;
	}

}
