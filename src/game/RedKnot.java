package game;

public class RedKnot extends Bird{
	
	
	private int updownstop=0;
	private double accel_mult_down = 1.0;
	private double accel_mult_up = 1.0;
	
	//Constants for the RedKnot (to keep it organized)
	private final static int RK_WIDTH = 120;
	private final static int RK_HEIGHT = 80;
	
	private final static int START_X = 200;
	private final static int START_Y = 20;
	
	private final static int RK_VX = 4;
	private final static int RK_VY = 5;
	
	
	/*
	 * Default constructor/Start values for our red knot bird.
	 */
	/**
	 * 
	 */
	public RedKnot(){
		super(new Position(START_X,START_Y), new Size(RK_WIDTH,RK_HEIGHT), new Velocity(RK_VX, RK_VY));
	}
	
	/* (non-Javadoc)
	 * @see game.Bird#move()
	 */
	public void move(){
		switch(this.getFlyState()) {
		case 1:this.FlyUp();break;
		case -1:this.FlyDown();break;
		}
	}

	/**
	 * 
	 */
	public void newFlyUp() {
		updownstop=1;
	}
	/**
	 * 
	 */
	public void newFlyDown() {
		updownstop=-1;
	}
	/**
	 * 
	 */
	public void flyDownStop() {
		updownstop=0;
	}
	/**
	 * 
	 */
	public void flyUpStop() {
		updownstop=0;
	}
	/**
	 * @return
	 */
	public int getFlyState() {
		return updownstop;
	}
	/**
	 * @param x
	 */
	public void setFlyState(int x) {
		updownstop=x;
	}
	/**
	 * 
	 */
	public void FlyUp() {
		Position p = this.getPosition();
		int new_y = p.getY()-((int)(this.getVelocity().getYSpeed() * accel_mult_up));
		accel_mult_down=1.0;
		accel_mult_up+=0.02;
		//if this will place the bird off the screen, dont move the bird.
		if(new_y < 0)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	/**
	 * 
	 */
	public void FlyDown() {
		Position p = this.getPosition();
		int new_y = p.getY()+((int)(this.getVelocity().getYSpeed()*accel_mult_down));
		//if this will place the bird off the screen, dont move the bird.
		accel_mult_up=1.0;
		accel_mult_down+=0.02;
		if((new_y + this.getSize().getHeight() + GameScreen.TITLE_BAR_HEIGHT) > GameScreen.PLAY_SCREEN_HEIGHT)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	
}
