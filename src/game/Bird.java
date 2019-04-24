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
	private int updownstop=0;
	
	//For Easier organization, made use of position, size, and velocity class rather than a bunch of ints
	public Bird(Position p, Size s, Velocity v, BirdType bird_type) {
		super(p, s, v);
		this.size = s.getWidth(); //temp
		
	}

	public BirdType getBird_type() {
		return bird_type;
	}

	public int getSize() {
		return size;
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
			this.setPosition(new Position(this.getPosition().getX(),this.getPosition().getY()));
		}
		int newX = this.getPosition().getX();
		int newY = this.getPosition().getY() - 50;
		this.setPosition(new Position(newX,newY));
	}
	public void newFlyUp() {
		updownstop=1;
	}
	public void newFlyDown() {
		updownstop=-1;
	}
	public void flyDownStop() {
		updownstop=0;
	}
	public void flyUpStop() {
		updownstop=0;
	}
	public int getFlyState() {
		return updownstop;
	}
	public void setFlyState(int x) {
		System.out.println("WE SET THE FLY STATE");
		updownstop=x;
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
