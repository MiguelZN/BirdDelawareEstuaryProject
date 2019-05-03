package game;

import java.awt.Rectangle;

//Helps keep track of the GameObject's position, width and height to make it more organized
public class HitBox extends Rectangle{
	int width,height;
	Position p;
	
	/*Constructors*/
	HitBox(int x, int y, int width, int height){
		super(x, y,width,height); //Sets the Rectangle position and width/height
		this.p  =new Position(x,y);
		this.width = width;
		this.height = height;
	}
	
	HitBox(Position p, int width, int height){
		super(p.getX(), p.getY(), width, height);
		this.p = p;
		this.width = width;
		this.height = height;
	}
	
	/*Getters*/
	public Position getPosition() {
		return this.p;
	}
	
	/*Created by Miguel:
	 *-Takes in a Position instance, returns nothing
	 *-Updates the position of the hitBox
	 *
	 *NOTE: The position of the HitBox instance is the Actual position of
	 *the GameObject (in order to unify them and only have one 'true' position
	 *and width/height)
	 */
	public void setPosition(Position p) {
		this.p = p;
		this.updateRectangleHitBox();
	}
	
	/*Created by Miguel:
	 *-Takes in no arguments, returns nothing
	 *-Sets the Java AWT Rectangle to a new bounds (or containing area)
	 *in order to make sure the size of the 'hitbox' for collisions is updated
	 */
	public void updateRectangleHitBox() {
		this.setBounds(p.getX(), p.getY(), this.width, this.height);
//		System.out.println(this.getBounds());
	}
	
	/*Created by Miguel:
	 *-Takes in an integer, returns nothing
	 *-Changes the width property to a new width value
	 */
	public void changeWidth(int new_width) {
		this.width = new_width;
	}
	
	/*Created by Miguel:
	 *-Takes in an integer, returns nothing
	 *-Changes the height property to a new height value
	 */
	public void changeHeight(int new_height) {
		this.height = new_height;
	}
	
	/*Created by Miguel:
	 *-Takes in two integers, returns nothing
	 *-Sets the width, height properties to new values 
	 */
	public void setSize(int new_width, int new_height) {
		this.width = new_width;
		this.height = new_height;
		this.updateRectangleHitBox();
	}
	
	
}
