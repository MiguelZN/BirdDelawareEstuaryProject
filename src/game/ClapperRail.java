package game;

public class ClapperRail extends Bird{
	
	
	/*
	 * Default Constructor for Clapper Rail, which will have the start values for
	 * the clapper rail game.
	 */
	
	
	private final static int XSTART = 100;
	private final static int YSTART = 380;
	private final static int VXSTART = 10;
	private final static int VYSTART = 10;
	private final static int SIZE = 80;
	private final static int JUMP_AMOUNT = 300;
	private final static int GRAVITY = 2;
	private Energy e;
	
	private boolean isJumping = false;
	private boolean isFalling = false;
	private int jumpPos;
	
	
	/**
	 * 
	 */
	public ClapperRail(){
		super(new Position(XSTART,ClapperRailGameState.GROUND), new Size(SIZE,SIZE), new Velocity(VXSTART,VYSTART));
	}
	
	/**
	 * 
	 */
	public void moveLeft() {
		move(-1*this.getVelocity().getXSpeed(),0);
	}
	
	/**
	 * 
	 */
	public void moveRight() {
		move(this.getVelocity().getXSpeed(),0);
	}
	
	
	public void startJump(Position currentPos) {
		setIsJumping(true);
		int yPos = currentPos.getY() - JUMP_AMOUNT;
		jumpPos = yPos;
	}
	
	/**
	 * 
	 */
	public void jump() {
		/*if(this.getPosition().getY() <= 300) {
			this.setPosition(new Position(this.getPosition().getX(),ClapperRailGameState.GROUND));
		} 
		this.move(0, -1* this.getVelocity().getYSpeed());
		*/
		
		if(isJumping) {
			this.move(0, -1* this.getVelocity().getYSpeed());
			if(this.getPosition().getY() == this.jumpPos) {
				this.isJumping = false;
				this.isFalling = true;
			}
		}
		else if(isFalling){
			if(this.getPosition().getY() != ClapperRailGameState.GROUND) {
				this.move(0, this.GRAVITY);
			}
			else {
				this.isFalling = false;
			}
		}
	}
	
	
	/*Method: eat()
	 *-takes in no arguments/returns nothing
	 *-restores/updates the Bird's Energy 
	 */
	/**
	 * @param f
	 */
	public void eat(Food f) {
		this.e.setEnergy(this.e.getEnergy() + f.getEnergyAdd());
	}
	/**
	 * @return
	 */
	public Energy getEnergy() {
		return e;
	}
	
	public boolean getIsJumping() {
		return this.isJumping;
	}

	public void setIsJumping(boolean b) {
		this.isJumping = b;
	}
	
	public boolean getIsFalling() {
		return this.isFalling;
	}

	public void setIsFalling(boolean b) {
		this.isFalling = b;
	}
}
