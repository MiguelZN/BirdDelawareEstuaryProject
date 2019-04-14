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

	/*Contructor:
	 * -Takes in an x,y ints as the DynamicGameObject's starting position
	 */
	public DynamicGameObject(int x, int y, int vx, int vy) {
		super(x,y);
		v = new Velocity(vx,vy);
	}

	/*Method: move()
	 *-takes in no arguments/returns nothing
	 *-updates the position of the DynamicGameObject by the velocity (v) 
	 */
	public abstract void move();
	
	public Velocity getVelocity() {
		return v;
	}
	
	public void setVelocity(Velocity newVel) {
		v = newVel;
	}

}
