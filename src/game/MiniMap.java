package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*Class: MiniMap
 * -extends the GameObject in order to have a position
 * -contains methods/properties to track where the bird currently is in its travel
 */
public class MiniMap extends GameObject{
	private BufferedImage map_image;
	private int birdPosition; //This is going to be a percentage indicating how much of the game has ran thus far
	
	//The margins of how far away the map is the the bottom left corner
	static final int LEFT_MARGIN = 20;
	static final int BOTTOM_MARGIN = 30;
	
	
	
	//Note:
	/*The minimap shall use the Size s from within hitbox but will not be needed for collision detection*/
	
	public MiniMap(Position p, Size s) {
		super(p, s);
		
		this.birdPosition = 0;
	}
	
	
	public int getBirdPosition() {
		return birdPosition;
	}
	
	public void setBirdPosition(int pos) {
		this.birdPosition = pos;
	}
	
}
