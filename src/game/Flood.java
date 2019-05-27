package game;

public class Flood extends GameObject {
	
	static final int FLOOD_HEIGHT = (int)(((1080*2)/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);

	/**@author Derek
	 * @param x
	 * @param y
	 */
	public Flood(int x, int y) {
		super(x, y, GameScreen.CR_SCREEN_WIDTH, FLOOD_HEIGHT);
		// TODO Auto-generated constructor stub
	}
	
	/*@author Derek
	 * (non-Javadoc)
	 * @see game.GameObject#move()
	 */
	@Override
	public void move() {
		Position p = this.getPosition();
		int newY = p.getY()+(int)((5d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);
		if(newY>(int)((1180d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT))
			newY=(int)((1180d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT);
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	/**@author Jake
	 * @param
	 * decrease the Y position of the flood
	 */
	public void increaseFlood() {
		Position pos = this.getPosition();
		
		
		/*
		 * commented out flood for testing
		 */
		this.setPosition(new Position(pos.getX(),pos.getY()-(int)((200d/1080d)*GameScreen.PLAY_SCREEN_HEIGHT)));
	}
	
	/**@author Jake
	 * @param pos
	 * @return
	 * Checks if the Y of the given Position is greater than or equal to the Flood
	 */
	public boolean touchingFlood(Position pos) {
		return pos.getY() >= this.getPosition().getY();
	}
	
}
