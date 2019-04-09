package game;

public enum BirdType {
	REDKNOT("REDKNOT"),
	FLOCKBIRD("FLOCKBIRD"),
	CLAPPERRAIL("CLAPPERRAIL");
	
	private String name = null;
	
	private BirdType(String s){
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
}
