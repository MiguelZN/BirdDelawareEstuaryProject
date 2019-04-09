package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: GameObject
 * -class that provides a Position (x,y) for each element within the game
 * (such as the plants, crabs, clouds, birds, etc)
 * 
 */
public class GameObject {
	private Position p;
	
	/*Contructor: 
	 * -takes in an x,y ints to set the starting Position of the GameObject
	 */
	public GameObject(int x, int y) {
		p = new Position(x,y);
	}
}
