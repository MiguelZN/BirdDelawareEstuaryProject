package game;

public class RedKnot extends Bird{
	
	
	private int updownstop=0;
	private double accel_mult_down = 1.0;
	private double accel_mult_up = 1.0;
	
	/*
	 * Default constructor/Start values for our red knot bird.
	 */
	public RedKnot(){
		super(new Position(100,20), new Size(100,50), new Velocity(5,5));
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
		int new_y = p.getY()-((int)(this.getVelocity().getYSpeed() * accel_mult_up));
		accel_mult_down=1.0;
		accel_mult_up+=0.02;
		//if this will place the bird off the screen, dont move the bird.
		if(new_y < 0)
			return;
		this.setPosition(new Position(p.getX(),new_y));
	}
	
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
