package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Bird
 * -class that embodies the game birds: RedKnot and ClapperRail
 * and contains the methods to be used for the birds.
 */
public class Bird extends DynamicGameObject {
	private Size size;
	
	//For Easier organization, made use of position, size, and velocity class rather than a bunch of ints
	/**
	 * @param p
	 * @param s
	 * @param v
	 */
	public Bird(Position p, Size s, Velocity v) {
		super(p, s, v);
		this.size = s;
	}
	/**
	 * @return
	 */
	public Size getSize() {
		return this.size;
	}
	
	/*Method: move()
	 *-takes in two integers: vx, vy, so we can move the bird
	 *based on what we want to move it by/returns nothing
	 *-updates the position of the bird 
	 */
	/**
	 * @param vx
	 * @param vy
	 */
	public void move(int vx, int vy) {
		int newX = this.getPosition().getX() + vx;
		int newY = this.getPosition().getY() + vy;
		this.setPosition(new Position(newX,newY));
	}
	/*Method: move()
	 *-takes in a velocity v, so we can move the bird
	 *based on what we want to move it by/returns nothing
	 *-updates the position of the bird 
	 */
	/**
	 * @param v
	 */
	public void move(Velocity v) {
		move(v.getXSpeed(),v.getYSpeed());
	}

	
	/*
	 * default move, moves based on the birds velocity.
	 */
	/* (non-Javadoc)
	 * @see game.DynamicGameObject#move()
	 */
	@Override
	public void move() {
		move(this.getVelocity());
		
	}
}
