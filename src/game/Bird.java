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
	private int xStart;
	private int yStart;
	
	/*Contructor:
	 * -Takes in an x,y ints as the bird's starting position
	 */
	public Bird(int x, int y, int width, int height, int vx, int vy, BirdType bird_type) {
		super(x,y, width, height, vx, vy);
		this.size = width; //temp
		this.xStart = x;
		this.yStart = y;
		
	}
	
	
	//For Easier organization, made use of position, size, and velocity class rather than a bunch of ints
	public Bird(Position p, Size s, Velocity v, BirdType bird_type) {
		super(p, s, v);
		this.size = s.getWidth(); //temp
		this.xStart = p.getX();
		this.yStart = p.getY();
		
	}

	public BirdType getBird_type() {
		return bird_type;
	}

	public int getSize() {
		return size;
	}
	
	public int getXStart() {
		return this.xStart;
	}
	
	public int getYStart() {
		return this.yStart;
	}

	public Bird(int x, int y, int vx, int vy, BirdType bird_type) {
		super(x,y, 0,0, vx, vy);
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
		int newX = this.getPosition().getX() + this.getVelocity().getXSpeed();
		int newY = this.getPosition().getY() + this.getVelocity().getYSpeed();
		this.setPosition(new Position(newX,newY));
	}
	
	public void moveLeft() {
		int newX = this.getPosition().getX() - this.getVelocity().getXSpeed();
		int newY = this.getPosition().getY() + this.getVelocity().getYSpeed();
		this.setPosition(new Position(newX,newY));
	}
	
	public void jump() {
		if(this.getPosition().getY() <= 300) {
			this.setPosition(new Position(this.getPosition().getX(), yStart));
		}
		int newX = this.getPosition().getX();
		int newY = this.getPosition().getY() - 50;
		this.setPosition(new Position(newX,newY));
	}
	
	public void FlyUp() {
		Position p = this.getPosition();
		int new_y = p.getY()-this.getVelocity().getYSpeed();
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	public void FlyDown() {
		Position p = this.getPosition();
		System.out.println("RK POSITION:"+p.getX()+","+p.getY());
		int new_y = p.getY()+this.getVelocity().getYSpeed();
		System.out.println("NEW Y:"+new_y);
		this.setPosition(new Position(p.getX(),new_y));
		System.out.println("RK NEW POSITION:"+p.getX()+","+p.getY());
		
	}
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	public void eat(Food f) {
		this.e.setEnergy(this.e.getEnergy() + f.getEnergyAdd());
	}
	
	
}
