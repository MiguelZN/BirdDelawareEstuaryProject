package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Energy
 * -Class that acts is used by the Bird class which embodies the ClapperRail and RedKnot birds
 * -Controls/updates the energy of the birds from jumping, flying, or any movement 
 */
public class Energy {
	private int energy;
	private int depletionRate;
	private int restoreRate;
	
	/*Contructor: 
	 * -Takes in no arguments
	 * -sets the energy (int) to 100 to begin
	 * -sets the depletionRate (int) 
	 * -sets the restoreRate (int)
	 */
	public Energy() {
		this.energy = 100; //begins at 100 energy
	}
	
	
	
	public int getDepletionRate() {
		return depletionRate;
	}



	public void setDepletionRate(int depletionRate) {
		this.depletionRate = depletionRate;
	}



	public int getRestoreRate() {
		return restoreRate;
	}



	public void setRestoreRate(int restoreRate) {
		this.restoreRate = restoreRate;
	}



	/*Method: restoreFull()
	 *-takes in no arguments/returns nothing
	 *-restores the energy(int) to 100
	 */
	public void restoreFull() {
		
	}
	
	/*Method: restoreDefault()
	 *-takes in no arguments/returns nothing
	 *-similar to restoreFull(), restores all the energy properties to their original values
	 */
	public void restoreDefault() {
		
	}
	
	/*Method: depleteFull()
	 *-takes in no arguments/returns nothing
	 *-reduces (subtracts) the energy(int) property 
	 */
	public void depleteFull() {
		
	}
	
	/*Method: depleteDefault()
	 *-takes in no arguments/returns nothing
	 *-similar to deleteFull(), reduces (subtracts) the energy (int) property
	 */
	public void depleteDefault() {
		
	}
	
	/*
	 * Setter for the energy int
	 */
	public void setEnergy(int e){
		this.energy=e;
	}
	
	public int getEnergy(){
		return energy;
	}
	
}
