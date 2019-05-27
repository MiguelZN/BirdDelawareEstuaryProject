package game;

import java.io.Serializable;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: GameObject
 * -class that provides a Position (x,y) for each element within the game
 * (such as the plants, crabs, clouds, birds, etc)
 * 
 */
public class GameObject implements Serializable {
	protected HitBox hitBox; //Allows for collision-detection, keeps track of x,y position
	//also keeps track of width and height of the GameObject (for drawing)
	
	//keeps track of what the GameObject is (currently not used)
	protected Object type;  
	//Note: would be useful to draw background scenery things such as trees, etc
	//that do not contain official classes for them
	
	
	/*Contructor: 
	 * -takes in an x,y ints to set the starting Position of the GameObject
	 */
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public GameObject(int x, int y, int width, int height) {
		this.hitBox = new HitBox(x,y,width,height);
	}
	
	/**
	 * @param p
	 * @param s
	 */
	public GameObject(Position p, Size s) {
		this.hitBox = new HitBox(p.getX(),p.getY(),s.getWidth(),s.getHeight());
		this.type = null; //'type' is currently not used
	}
	
	/**
	 * @param p
	 * @param s
	 * @param type
	 */
	public GameObject(Position p, Size s, Object type) {
		this.hitBox = new HitBox(p.getX(),p.getY(),s.getWidth(),s.getHeight());
		this.type = type;
	}
	
	
	//ONLY USED BY CLAPPER RAIL GAME OBJECTS
	public void move() {
		Position p = this.getPosition();
		int newY = p.getY()+5;
		
		/*
		 * This if statement is only required for objects that must respawn.
		 * 
		 * It is now only in the platforms overrided move code.
		 */
		
		/*
		if(newY > ClapperRailGameState.GROUND+50) {
			newY = 0;
		}*/
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	/**@author Jake
	 * @param p
	 * @return
	 * Checks if the Y of the given Position is within the radius of the object. 
	 * Also checks if the X of the given Position is within the radius of the object.
	 */
	public boolean touchObject(Position p, int radius) {
		if((p.getX() >= this.getPosition().getX()-radius)&&(p.getX() <= this.getPosition().getX()+radius) &&
				(p.getY() >= this.getPosition().getY()-radius)&&(p.getY() <= this.getPosition().getY()+radius)) {
			return true;
		}
		return false;
	}
	
	/*Created by Miguel:
	 * -Getter for Position
	 * Note: Since we are using Java AWT's Rectangle(HitBox extends it),
	 * we currently have the position, height, width contained within the
	 * HitBox class in order to unify and keep everything organized 
	 */
	/**
	 * @return
	 */
	public Position getPosition() {
		return this.hitBox.getPosition();
	}
	
	/**
	 * @return
	 */
	public int getWidth() {
		return this.hitBox.width;
	}


	/**
	 * @return
	 */
	public int getHeight() {
		return this.hitBox.height;
	}
	
	/*Created by Miguel:
	 * -Takes in a Position instance, returns nothing
	 * -Sets the location of the HitBox (top left point of HitBox)
	 */
	/**
	 * @param newPos
	 */
	public void setPosition(Position newPos) {
		this.hitBox.setPosition(newPos);
		
	}
	
	public Size getSize() {
		return new Size(this.hitBox.width,this.hitBox.height);
	}
	
	/**
	 * @param v
	 */
	public void shiftGameObject(Velocity v) {
		this.getPosition().Shift(v);
	}
}
