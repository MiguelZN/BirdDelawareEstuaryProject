package game;

public enum RedKnotAsset {
	BACKGROUND("BACKGROUND"),
	SABACKGROUND("SABACKGROUND"),
	FOREST1("FOREST1"),
	ENEMYCLOUD("ENEMYCLOUD"),
	MAINBIRD("MAINBIRD"),
	FLOCKBIRD("FLOCKBIRD"),
	MAP("MAP"),
	OCEAN("OCEAN"),
	COAST("COAST"),
	QUESTIONCLOUD("QUESTIONCLOUD"),
	STATICREDKNOT("STATICREDKNOT");

	private String asset_key = null;

	private RedKnotAsset(String s){
		asset_key=s;
	}
}
