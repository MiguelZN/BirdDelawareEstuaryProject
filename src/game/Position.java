package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Position
 * -class that allows us to keep track of each element's position (x,y).
 */
public class Position {
	private int x;
	private int y;
	
	/*Constructor:
	 * -takes in two integers: an x,y in order to provide where the element is located (x,y)
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position moveByVelocity(Velocity v) {
		return new Position(this.getX()+v.getXSpeed(), this.getY()+v.getYSpeed());
	}

	/*Getters, Setters*/
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/*toString*/
	public String toString() {
		return "("+this.getX()+","+this.getY()+")";
	}
	
}
