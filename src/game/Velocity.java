package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Velocity
 * -class that contains how fast/direction an object is moving in terms of x,y velocities
 */
public class Velocity {
	private int xSpeed;
	private int ySpeed;
	
	/*Constructor:
	 * -takes in two integers: an x,y in order to provide the velocity of a moving object
	 */
	public Velocity(int x, int y) {
		xSpeed = x;
		ySpeed = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	
}
