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
	/**
	 * 
	 */
	public Energy() {
		this.energy = 100; //begins at 100 energy
	}
	
	
	
	/**
	 * @return
	 */
	public int getDepletionRate() {
		return depletionRate;
	}



	/**
	 * @param depletionRate
	 */
	public void setDepletionRate(int depletionRate) {
		this.depletionRate = depletionRate;
	}



	/**
	 * @return
	 */
	public int getRestoreRate() {
		return restoreRate;
	}



	/**
	 * @param restoreRate
	 */
	public void setRestoreRate(int restoreRate) {
		this.restoreRate = restoreRate;
	}



	/*Method: restoreFull()
	 *-takes in no arguments/returns nothing
	 *-restores the energy(int) to 100
	 */
	/**
	 * 
	 */
	public void restoreFull() {
		this.energy = 100;
	}
	
	/*Method: restoreDefault()
	 *-takes in no arguments/returns nothing
	 *-similar to restoreFull(), restores based on the restoration rate
	 */
	/**
	 * 
	 */
	public void restoreDefault() {
		this.energy+=this.restoreRate;
	}
	
	/*Method: depleteFull()
	 *-takes in no arguments/returns nothing
	 *-reduces (subtracts) the energy(int) property 
	 */
	/**
	 * 
	 */
	public void depleteFull() {
		this.energy = 0;
	}
	
	/*Method: depleteDefault()
	 *-takes in no arguments/returns nothing
	 *-similar to deleteFull(), reduces (subtracts) the energy (int) property
	 */
	/**
	 * 
	 */
	public void depleteDefault() {
		this.energy-=this.depletionRate;
	}
	
	/*
	 * Setter for the energy int
	 */
	/**
	 * @param e
	 */
	public void setEnergy(int e){
		this.energy=e;
	}
	
	/**
	 * @return
	 */
	public int getEnergy(){
		return energy;
	}
	
}
