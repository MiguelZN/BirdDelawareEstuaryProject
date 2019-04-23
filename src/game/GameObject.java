package game;

import java.awt.Rectangle;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: GameObject
 * -class that provides a Position (x,y) for each element within the game
 * (such as the plants, crabs, clouds, birds, etc)
 * 
 */
public class GameObject {
	protected HitBox hitBox; //Allows for collision-detection, keeps track of x,y position
	//also keeps track of width and height of the GameObject (for drawing)

	
	/*Contructor: 
	 * -takes in an x,y ints to set the starting Position of the GameObject
	 */
	public GameObject(int x, int y) {
		this.hitBox = new HitBox(x,y,0,0);
	}
	
	public GameObject(int x, int y, int width, int height) {
		this.hitBox = new HitBox(x,y,width,height);
	}
	
	public Position getPosition() {
		return new Position(this.hitBox.x, this.hitBox.y);
	}
	
	//Sets the location of the HitBox (top left)
	public void setPosition(Position newPos) {
		hitBox.setLocation(newPos.getX(), newPos.getY());
	}
}
