package game;

//ClapperRail Game

public class Platform extends DynamicGameObject {
	
	private static final int PLATFORM_VX = 0;
	private static final int PLATFORM_VY = 0;
	
	private static final int PLATFORM_WIDTH = 250;
	private static final int PLATFORM_WIDTH_CORRECTION = 100; //image must be messed up, this is tempoary
	private static final int PLATFORM_HEIGHT = 160;
	
	public Platform(int x, int y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT, PLATFORM_VX, PLATFORM_VY);
	}

	
	/*
	 * For the case of platforms, they only move down.
	 * (non-Javadoc)
	 * @see game.DynamicGameObject#move()
	 */
	@Override
	public void move() {
		Position p = this.getPosition();
		int newY = p.getY()+5;
		if(newY > ClapperRailGameState.GROUND+50) {
			newY = 0;
		}
		
		this.setPosition(new Position(p.getX(),newY));
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

}
