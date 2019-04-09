package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: MiniMap
 * -extends the GameObject in order to have a position
 * -contains methods/properties to track where the bird currently is in its travel
 */
public class MiniMap extends GameObject{
	private Position birdPosition;
	
	public MiniMap(int x, int y) {
		super(x,y);
	}
}
