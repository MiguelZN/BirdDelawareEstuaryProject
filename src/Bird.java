/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Bird
 * -class that embodies the game birds: RedKnot and ClapperRail
 * and contains the methods to be used for the birds.
 */
public class Bird extends DynamicGameObject {
	private Energy e;
	
	/*Contructor:
	 * -Takes in an x,y ints as the bird's starting position
	 */
	public Bird(int x, int y, int vx, int vy) {
		super(x,y, vx, vy);
	}
	
	/*Method: move()
	 *-takes in no arguments/returns nothing
	 *-updates the position of the bird 
	 */
	public void move() {
		
	}
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	public void eat() {
		
	}
}
