package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Food
 * -class that upon 'eaten' by the Birds (RedKnot/ClapperRail) increases/restores the energy
 * of the birds by calling Energy's EX: restoreFull() method
 * -controls updates between state and the view 
 */
public class Food extends GameObject {
	private static final int RADIUS = 30;
	
	static final int CRAB_SIZE = 80;
	
	private int energyAdd;
	
	/**
	 * @param x
	 * @param y
	 * @param energy
	 */
	public Food(int x, int y, int energy) {
		super(x,y,CRAB_SIZE,CRAB_SIZE);
		energyAdd = energy;
	}
	
	
	public void move() {
		Position p = this.getPosition();
		
		this.setPosition(new Position(p.getX(),p.getY()+5));
	}
	
	
	public boolean touchFood(Position p) {
		if((p.getX() >= this.getPosition().getX()-RADIUS)&&(p.getX() <= this.getPosition().getX()+RADIUS) &&
				(p.getY() >= this.getPosition().getY()-RADIUS)&&(p.getY() <= this.getPosition().getY()+RADIUS)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return
	 */
	public int getEnergyAdd() {
		return energyAdd;
	}
	
}
