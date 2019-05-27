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
	static final int RADIUS = 60;
	static final int CRAB_SIZE = 120;
	
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
	
	
	/**@author Jake
	 * @param p
	 * @return
	 * Checks if the Y of the given Position is within the radius of the Food object. 
	 * Also checks if the X of the given Position is within the radius of the Food object.
	 */
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
