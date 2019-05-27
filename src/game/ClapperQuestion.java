package game;

public class ClapperQuestion extends GameObject{
	
	static final int RADIUS = 80;
	static final int BLOCK_SIZE = 160;
	
	public ClapperQuestion(int x, int y) {
		super(x,y,BLOCK_SIZE,BLOCK_SIZE);
	}
	
	/**
	 * @author Derek 
	 * @param p
	 * @return collision if the position p is touching block
	 */
	public boolean touchBlock(Position p) {
		if((p.getX() >= this.getPosition().getX()-RADIUS)&&(p.getX() <= this.getPosition().getX()+RADIUS) &&
				(p.getY() >= this.getPosition().getY()-RADIUS)&&(p.getY() <= this.getPosition().getY()+RADIUS)) {
			return true;
		}
		return false;
	}

}
