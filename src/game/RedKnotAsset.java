package game;

public enum RedKnotAsset {
	BACKGROUND("BACKGROUND"),
	SABACKGROUND("SABACKGROUND"),
	FOREST1("FOREST1"),
	CLOUD("CLOUD");

	private String asset_key = null;

	private RedKnotAsset(String s){
		asset_key=s;
	}
}
