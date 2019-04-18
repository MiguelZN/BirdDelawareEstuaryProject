package game;

public enum RedKnotAsset {
	BACKGROUND("BACKGROUND"),
	FOREST1("FOREST1");

	private String asset_key = null;

	private RedKnotAsset(String s){
		asset_key=s;
	}
}
