package game;

public enum GameMode {
	REDKNOT("REDKNOT"),
	INSTRUCTIONS("INSTRUCTIONS"),
	CLAPPERRAIL("CLAPPERRAIL"),
	TITLESCREEN("TITLESCREEN");
	
	private String game_mode = null;
	
	private GameMode(String s){
		this.game_mode = s;
	}
	public String getGameMode() {
		return this.game_mode;
	}
}
