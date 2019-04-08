/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Velocity
 * -class that contains how fast/direction an object is moving in terms of x,y velocities
 */
public class Velocity {
	int xSpeed;
	int ySpeed;
	
	/*Constructor:
	 * -takes in two integers: an x,y in order to provide the velocity of a moving object
	 */
	public Velocity(int x, int y) {
		xSpeed = x;
		ySpeed = y;
	}
}
