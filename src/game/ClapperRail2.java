package game;

import java.util.ArrayList;

public class ClapperRail2 extends Bird{
	
	
	/*
	 * Default Constructor for Clapper Rail, which will have the start values for
	 * the clapper rail game.
	 */
	
	private int score = 0;

	private final static int JUMP_HEIGHT = (int)((300/ClapperRailGameState.dereks_height)*GameScreen.PLAY_SCREEN_HEIGHT);
	private final static int VXSTART = (int)((10d/ClapperRailGameState.dereks_width)*GameScreen.PLAY_SCREEN_WIDTH);
	private final static int VYSTART = (int)((10d/ClapperRailGameState.dereks_height)*GameScreen.PLAY_SCREEN_HEIGHT);
	private final static int SIZE = (int)((160/ClapperRailGameState.dereks_height)*GameScreen.PLAY_SCREEN_HEIGHT);
	
	private final static int XSTART = (int)((50/ClapperRailGameState.dereks_width)*GameScreen.PLAY_SCREEN_WIDTH);
	private final static int YSTART = (int)(((ClapperRailGameState.dereks_height-SIZE)/ClapperRailGameState.dereks_height)*GameScreen.PLAY_SCREEN_HEIGHT);
	static final int MAX_ENERGY = 150;
	static final int START_ENERGY = 100;
	static final int ENERGY_LOSS = 25;
	static final int ENERGY_GAIN = 25;
	static final int FLOOD_ENERGY = 10;
	
	
	private int energy = START_ENERGY;
	private int materialCount = 0;
	
	private boolean isJumping = false;
	private boolean isFalling = false;
	private boolean onPlatform = false;
	private int jumpState=-1;
	private int fallState=0;
	private boolean colliding = false;
	private int leftRightState = 0;
	boolean gameOver = false;

	private ArrayList<Platform> platforms;
	


	/**
	 * 
	 */
	public ClapperRail2(){
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
//			if(fallState > 0) {
//				this.energy-= ENERGY_LOSS;
//				if(this.energy <= 0) {
//					this.energy = 0;
//				}
//			}
			colliding = true;
			fallState=0;
			jump();
		}
		
		this.setPosition(new Position(newX,newY));
	}
	
	public Platform getCollidingWithPlatform(int vy){
		for(Platform p : platforms){
			Position pos = getPosition();
			if(p.willTouchPlatform(new Position(pos.getX()+this.getSize().getWidth()/2,pos.getY()+this.getSize().getHeight()),vy))
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
				setPosition(new Position(getPosition().getX(),p.getPosition().getY()-this.getSize().getHeight()));
				colliding=true;
				fallState=0;
				jump();
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
		move(0,-28 + jumpState);
		jumpState++;
		if(jumpState==28)
			jumpState=-1;
		
	}
	public int getScoreIncrease(){
		double mult = ((double)getMaterialCount())/10.0;
		return (int)(5.0*(1.0+mult));
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
	public int getScore(){
		return this.score;
	}
	public void setScore(int x){
		this.score=x;
	}
}
