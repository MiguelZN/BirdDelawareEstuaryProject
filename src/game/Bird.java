package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Bird
 * -class that embodies the game birds: RedKnot and ClapperRail
 * and contains the methods to be used for the birds.
 */
public class Bird extends DynamicGameObject {
	private Energy e;
	private BirdType bird_type;
	private int size;
	
	/*Contructor:
	 * -Takes in an x,y ints as the bird's starting position
	 */
	public Bird(int x, int y, int vx, int vy, int size, BirdType bird_type) {
		super(x,y, vx, vy);
		this.size = size;
		
	}

	public BirdType getBird_type() {
		return bird_type;
	}

	public int getSize() {
		return size;
	}

	public Bird(int x, int y, int vx, int vy, BirdType bird_type) {
		super(x,y, vx, vy);
		this.bird_type = bird_type;
		this.size = 20;
	}
	
	public Energy getEnergy() {
		return e;
	}
	
	/*Method: move()
	 *-takes in no arguments/returns nothing
	 *-updates the position of the bird 
	 */
	public void move() {
		int newX = this.getPosition().getX() + this.getVelocity().getxSpeed();
		int newY = this.getPosition().getY() + this.getVelocity().getySpeed();
		this.setPosition(new Position(newX,newY));
	}
	
	public void jump() {
		int newX = this.getPosition().getX();
		int newY = this.getPosition().getY() - 50;
		this.setPosition(new Position(newX,newY));
	}
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	public void eat(Food f) {
		this.e.setEnergy(this.e.getEnergy() + f.getEnergyAdd());
	}
	
	
}
