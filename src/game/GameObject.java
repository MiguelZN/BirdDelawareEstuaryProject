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
	
	//keeps track of what the GameObject is (currently not used)
	protected Object type;  
	//Note: would be useful to draw background scenery things such as trees, etc
	//that do not contain official classes for them
	
	
	/*Contructor: 
	 * -takes in an x,y ints to set the starting Position of the GameObject
	 */
	public GameObject(int x, int y, int width, int height) {
		this.hitBox = new HitBox(x,y,width,height);
	}
	
	public GameObject(Position p, Size s) {
		this.hitBox = new HitBox(p.getX(),p.getY(),s.getWidth(),s.getHeight());
		this.type = null; //'type' is currently not used
	}
	
	public GameObject(Position p, Size s, Object type) {
		this.hitBox = new HitBox(p.getX(),p.getY(),s.getWidth(),s.getHeight());
		this.type = type;
	}
	
	/*Created by Miguel:
	 * -Getter for Position
	 * Note: Since we are using Java AWT's Rectangle(HitBox extends it),
	 * we currently have the position, height, width contained within the
	 * HitBox class in order to unify and keep everything organized 
	 */
	public Position getPosition() {
		return this.hitBox.getPosition();
	}
	
	/*Created by Miguel:
	 * -Takes in a Position instance, returns nothing
	 * -Sets the location of the HitBox (top left point of HitBox)
	 */
	public void setPosition(Position newPos) {
		this.hitBox.setPosition(newPos);
		
	}
	
	public void shiftGameObject(Velocity v) {
		this.getPosition().Shift(v);
	}
}
