package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.io.Serializable;

/*Class: Velocity
 * -class that contains how fast/direction an object is moving in terms of x,y velocities
 */
public class Velocity implements Serializable {
	private int xSpeed;
	private int ySpeed;
	
	/*Constructor:
	 * -takes in two integers: an x,y in order to provide the velocity of a moving object
	 */
	/**
	 * @param x
	 * @param y
	 */
	public Velocity(int x, int y) {
		this.xSpeed = x;
		this.ySpeed = y;
	}

	/*Getters, Setters*/
	/**
	 * @return
	 */
	public int getXSpeed() {
		return xSpeed;
	}

	/**
	 * @param xSpeed
	 */
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * @return
	 */
	public int getYSpeed() {
		return ySpeed;
	}

	/**
	 * @param ySpeed
	 */
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	/*toString*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Velocity:("+this.getXSpeed()+","+this.getYSpeed()+")";
	}
	
	
}
