package game;

public class Flood extends GameObject {
	
	static final int FLOOD_HEIGHT = GameScreen.CR_SCREEN_HEIGHT*2;

	public Flood(int x, int y) {
		super(x, y, GameScreen.CR_SCREEN_WIDTH, FLOOD_HEIGHT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		Position p = this.getPosition();
		int newY = p.getY()+5;
		if(newY>700)
			newY=700;
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	public void increaseFlood() {
		Position pos = this.getPosition();
		
		
		/*
		 * commented out flood for testing
		 */
		this.setPosition(new Position(pos.getX(),pos.getY()-100));
	}
	
	public boolean touchingFlood(Position pos) {
		return pos.getY() >= this.getPosition().getY();
	}
	
}
