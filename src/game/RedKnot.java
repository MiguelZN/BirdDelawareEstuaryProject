package game;

/**
 *
 * -Class for our RedKnot bird character for the RedKnot minigame
 *
 */
public class RedKnot extends Bird{
	
	
	private int updownstop=0;
	private double accel_mult_down = 1.0;
	private double accel_mult_up = 1.0;
	
	//Constants for the RedKnot (to keep it organized)
	final static int RK_WIDTH = 120;
	final static int RK_HEIGHT = 80;
	
	private final static int START_X = 200;
	private final static int START_Y = 20;
	
	private final static int RK_VX = 4;
	private final static int RK_VY = 5;
	

	
	/*
	 * Default constructor/Start values for our red knot bird.
	 */
	public RedKnot(){
		super(new Position(START_X,START_Y), new Size(RK_WIDTH,RK_HEIGHT), new Velocity(RK_VX,RK_VY));
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
	 * -When called, moves the bird's position up by the bird's velocity
	 */
	public void FlyUp() {
		Position p = this.getPosition();
		int new_y = p.getY()-this.getVelocity().getYSpeed();
		
		//if this will place the bird off the screen, dont move the bird.
		if(new_y < 0)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
	/**
	 * 
	 * -When called, moves the bird's position down by the bird's velocity
	 */
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
	
	
}
