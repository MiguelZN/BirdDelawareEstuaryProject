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
	private int energyAdd;
	
	public Food(int x, int y, int energy) {
		super(x, y);
		energyAdd = energy;
	}
	
	public int getEnergyAdd() {
		return energyAdd;
	}
	
}
