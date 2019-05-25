package game;

public class Flood extends GameObject {
	
	static final int FLOOD_HEIGHT = (int)((2160d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);

	public Flood(int x, int y) {
		super(x, y, GameScreen.CR_SCREEN_WIDTH, FLOOD_HEIGHT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		Position p = this.getPosition();
		int newY = p.getY()+(int)((5d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);
		if(newY>(int)((1180d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT))
			newY=(int)((1180d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	public void increaseFlood() {
		Position pos = this.getPosition();
		
		
		/*
		 * commented out flood for testing
		 */
		this.setPosition(new Position(pos.getX(),pos.getY()-(int)((200d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT)));
	}
	
	public boolean touchingFlood(Position pos) {
		return pos.getY() >= this.getPosition().getY();
	}
	
}
