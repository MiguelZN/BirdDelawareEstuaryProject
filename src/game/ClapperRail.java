package game;

public class ClapperRail extends Bird{
	
	
	/*
	 * Default Constructor for Clapper Rail, which will have the start values for
	 * the clapper rail game.
	 */
	
	
	private final static int XSTART = 100;
	private final static int YSTART = 880;
	private final static int VXSTART = 10;
	private final static int VYSTART = 10;
	private final static int SIZE = 80;
	private final static int JUMP_AMOUNT = 300;
	private final static int GRAVITY = 3;
	private Energy e;
	
	private boolean isJumping = false;
	private boolean isFalling = false;
	private boolean onPlatform = false;
	private int jumpPos;
	private int jumpState=-1;
	private int fallState=0;
	private boolean colliding = false;
	
	
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
	
	/*
	 * Called once per ontick, moves the bird down, in a gravity-like way.
	 * checks to make sure bird stays on screen.
	 * 
	 */
	@Override
	public void move(int vx, int vy){
		int newX = this.getPosition().getX() + vx;
		int newY = this.getPosition().getY();
		if((newY + vy) < (GameScreen.PLAY_SCREEN_HEIGHT-this.getSize().getHeight()-GameScreen.TITLE_BAR_HEIGHT)){
			newY+=vy;
			colliding = false;
		}else{
			newY = GameScreen.PLAY_SCREEN_HEIGHT-this.getSize().getHeight()-GameScreen.TITLE_BAR_HEIGHT;
			colliding = true;
			fallState=0;
		}
		this.setPosition(new Position(newX,newY));
	}
	
	
	/*
	 * The clapper rail has its own on tick method,
	 * as it has many things to handle on tick. (Gravity, among other things)
	 * 
	 */
	public void ontick(){
		//if we aren't jumping.
		if(jumpState == -1)
			handleCurrentFall();
		else
			handleCurrentJump();
	}
	
	public void handleCurrentFall(){
		if(!colliding){
			move(0,fallState);
			fallState++;
		}
	}
	
	/*
	 * This is essentially an accelerated jump that we are creating.
	 * 0    1  2 3 4 5 6 7 8
	 * -10 -9 -8 -7 -6
	 */
	public void handleCurrentJump(){
		move(0,-20 + jumpState);
		jumpState++;
		if(jumpState==20)
			jumpState=-1;
		
	}
	
	/**
	 * 
	 */
	public void jump() {
		if(jumpState==-1 && colliding)
			jumpState=0;
	}
	
//	public void keepFallingUntilPos(Position p, Size s) {
//		if(this.getPosition().getY())
//	}
	
	
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
	
	public boolean getOnPlatform() {
		return this.onPlatform;
	}
	
	public void setOnPlatform(boolean b) {
		this.onPlatform = b;
	}
	public void setColliding(boolean b){
		this.colliding=b;
	}
	public boolean getColliding(){
		return this.colliding;
	}
}
