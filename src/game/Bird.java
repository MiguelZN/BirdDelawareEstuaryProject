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
	private Size size;
	private int updownstop=0;
	
	//For Easier organization, made use of position, size, and velocity class rather than a bunch of ints
	public Bird(Position p, Size s, Velocity v, BirdType bird_type) {
		super(p, s, v);
		this.size = s;
	}

	public BirdType getBird_type() {
		return bird_type;
	}

	public Size getSize() {
		return this.size;
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
		updownstop=x;
	}
	public void FlyUp() {
		Position p = this.getPosition();
		int new_y = p.getY()-this.getVelocity().getYSpeed();
		//if this will place the bird off the screen, dont move the bird.
		if(new_y < 0)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	public void FlyDown() {
		Position p = this.getPosition();
		int new_y = p.getY()+this.getVelocity().getYSpeed();
		//if this will place the bird off the screen, dont move the bird.
		System.out.println(new_y);
		System.out.println(this.getSize().getHeight());
		if((new_y + this.getSize().getHeight() + GameScreen.TITLE_BAR_HEIGHT) > GameScreen.PLAY_SCREEN_HEIGHT)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	public void eat(Food f) {
		this.e.setEnergy(this.e.getEnergy() + f.getEnergyAdd());
	}
	
	
}
