package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: DynamicGameObject
 * -Class that extends the GameObject class and implements the Moveable interface
 * -Embodies any element that moves on the screen (EX: moving crabs, moving birds, etc)
 *
 */
public abstract class DynamicGameObject extends GameObject implements Moveable {
	private Velocity v;

	/*Constructors*/
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param vx
	 * @param vy
	 */
	public DynamicGameObject(int x, int y, int width, int height, int vx, int vy) {
		super(x,y,width,height);
		v = new Velocity(vx,vy);
	}
	
	/**
	 * @param p - starting position
	 * @param s - starting size
	 * @param v - starting velocity
	 * 
	 */
	public DynamicGameObject(Position p, Size s, Velocity v) {
		super(p.getX(),p.getY(),s.getWidth(),s.getHeight());
		this.v = v;
	}

	/*Method: move()
	 *-takes in no arguments/returns nothing
	 *-updates the position of the DynamicGameObject by the velocity (v) 
	 */
	public abstract void move();
	
	/**
	 * @return Velocity
	 */
	public Velocity getVelocity() {
		return v;
	}
	
	/**
	 * @param newVel 
	 * -Sets the current velocity to a new velocity
	 */
	public void setVelocity(Velocity newVel) {
		v = newVel;
	}

}
