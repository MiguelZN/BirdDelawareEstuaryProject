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
	public Bird(Position p, Size s, Velocity v) {
		super(p, s, v);
		this.size = s;
	}
	public Size getSize() {
		return this.size;
	}
	
	/*Method: move()
	 *-takes in no arguments/returns nothing
	 *-updates the position of the bird 
	 */
	public void move() {
		int newX = this.getPosition().getX() + this.getVelocity().getXSpeed();
		int newY = this.getPosition().getY() + this.getVelocity().getYSpeed();
		this.setPosition(new Position(newX,newY));
	}
	
	public void moveLeft() {
		int newX = this.getPosition().getX() - this.getVelocity().getXSpeed();
		int newY = this.getPosition().getY() + this.getVelocity().getYSpeed();
		this.setPosition(new Position(newX,newY));
	}
	
	public void jump() {
		if(this.getPosition().getY() <= 300) {
			this.setPosition(new Position(this.getPosition().getX(),this.getPosition().getY()));
		}
		int newX = this.getPosition().getX();
		int newY = this.getPosition().getY() - 50;
		this.setPosition(new Position(newX,newY));
	}
}
