package game;

/**
 * @author MiguelZN
 *-Allows us to have an emeration of the different game modes and have a property in Controller
 *indicating which gameMode it currently is
 */
public enum GameMode {
	REDKNOT("REDKNOT"),
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
