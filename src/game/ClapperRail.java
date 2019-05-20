package game;

import java.util.ArrayList;

public class ClapperRail extends Bird{
	
	
	/*
	 * Default Constructor for Clapper Rail, which will have the start values for
	 * the clapper rail game.
	 */
	
	
	private final static int XSTART = 50;
	private final static int YSTART = 880;
	private final static int VXSTART = 5;
	private final static int VYSTART = 10;
	private final static int SIZE = 80;
	private final static int JUMP_AMOUNT = 300;
	private final static int GRAVITY = 3;
	static final int MAX_ENERGY = 100;
	static final int ENERGY_LOSS = 20;
	static final int ENERGY_GAIN = 10;
	
	
	private int energy = MAX_ENERGY;
	private int materialCount = 0;
	
	private boolean isJumping = false;
	private boolean isFalling = false;
	private boolean onPlatform = false;
	private int jumpPos;
	private int jumpState=-1;
	private int fallState=0;
	private boolean colliding = false;
	private int leftRightState = 0;
	boolean gameOver = false;

	private ArrayList<Platform> platforms;
	


	/**
	 * 
	 */
	public ClapperRail(){
		super(new Position(XSTART, ClapperRailGameState.GROUND), new Size(SIZE,SIZE), new Velocity(VXSTART,VYSTART));
		platforms = null;
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
		
		if(newX > GameScreen.CR_SCREEN_WIDTH)
			newX = 0;
		else if(newX+getSize().getWidth() < 0)
			newX = GameScreen.CR_SCREEN_WIDTH;
		
		if((newY + vy) < (ClapperRailGameState.GROUND)){
			newY+=vy;
			colliding = false;
		}else{
			newY = ClapperRailGameState.GROUND;
			if(fallState > 0) {
				this.energy-= ENERGY_LOSS;
				if(this.energy <= 0) {
					this.energy = 0;
					System.out.println("Game Over");
					gameOver = true;
				}
			}
			colliding = true;
			fallState=0;
		}
		
		this.setPosition(new Position(newX,newY));
	}
	
	public Platform getCollidingWithPlatform(int vy){
		for(Platform p : platforms){
			if(p.willTouchPlatform(getPosition(),vy))
				return p;
		}
		return null;
	}
	
	/*
	 * The clapper rail has its own on tick method,
	 * as it has many things to handle on tick. (Gravity, among other things)
	 * 
	 */
	public void ontick(ArrayList<Platform> platforms){
		this.platforms=platforms;
		//if we aren't jumping.
		if(jumpState == -1)
			handleCurrentFall();
		else
			handleCurrentJump();
	}
	
	public void handleCurrentFall(){
		if(!colliding){
			Platform p;
			if((p=getCollidingWithPlatform(fallState))!=null){
				setPosition(new Position(getPosition().getX(),p.getPosition().getY()));
				colliding=true;
				fallState=0;
			}else{
				move(0,fallState);
				fallState++;
			}
		}
	}
	
	/*
	 * This is essentially an accelerated jump that we are creating.
	 * 0    1  2 3 4 5 6 7 8
	 * -10 -9 -8 -7 -6
	 */
	public void handleCurrentJump(){
		move(0,-24 + jumpState);
		jumpState++;
		if(jumpState==24)
			jumpState=-1;
		
	}
	
	/**
	 * 
	 */
	public void jump() {
		if(jumpState==-1 && colliding)
			jumpState=0;
	}
	
	
	public int getLeftRightState() {
		return leftRightState;
	}

	public void setLeftRightState(int leftRightState) {
		this.leftRightState = leftRightState;
	}
//	public void keepFallingUntilPos(Position p, Size s) {
//		if(this.getPosition().getY())
//	}

	
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int e) {
		this.energy = e;
	}
	
	public void gainEnergy() {
		int newEnergy = this.energy + ENERGY_GAIN;
		if(newEnergy > MAX_ENERGY) {
			this.energy = MAX_ENERGY;
		}
		else {
			this.energy = newEnergy;
		}
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
	public int getJumpState(){
		return this.jumpState;
	}
	public int getMaterialCount() {
		return this.materialCount;
	}
	public void setMaterialCount(int count) {
		this.materialCount = count;
	}
}
