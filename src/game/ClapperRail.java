package game;

public class ClapperRail extends Bird{
	
	
	/*
	 * Default Constructor for Clapper Rail, which will have the start values for
	 * the clapper rail game.
	 */
	
	
	private final static int XSTART = 100;
	private final static int YSTART = 380;
	private final static int VXSTART = 10;
	private final static int VYSTART = 50;
	private final static int SIZE = 80;
	private Energy e;
	
	
	public ClapperRail(){
		super(new Position(XSTART,YSTART), new Size(SIZE,SIZE), new Velocity(VXSTART,VYSTART));
	}
	
	public void moveLeft() {
		move(-1*this.getVelocity().getXSpeed(),0);
	}
	
	public void moveRight() {
		move(this.getVelocity().getXSpeed(),0);
	}
	
	
	public void jump() {
		if(this.getPosition().getY() <= 300) {
			this.setPosition(new Position(this.getPosition().getX(),ClapperRailGameState.GROUND));
		}
		
		this.move(0, -1* this.getVelocity().getYSpeed());
	}
	
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	public void eat(Food f) {
		this.e.setEnergy(this.e.getEnergy() + f.getEnergyAdd());
	}
	public Energy getEnergy() {
		return e;
	}

}
