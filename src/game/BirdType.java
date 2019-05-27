package game;

/**
 * @author Miguel
 *-An enumeration to distinguish what type of bird, the current bird is
 */
public enum BirdType {
	REDKNOT("REDKNOT"),
	FLOCKBIRD("FLOCKBIRD"),
	CLAPPERRAIL("CLAPPERRAIL");
	
	private String name = null;
	
	private BirdType(String s){
		this.name = s;
	}
	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
