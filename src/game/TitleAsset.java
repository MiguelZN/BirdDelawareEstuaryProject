package game;

public enum TitleAsset {
	BACKGROUND("BACKGROUND"),
	TITLELOGO("TITLELOGO");

	private String asset_key = null;

	private TitleAsset(String s){
		asset_key=s;
	}
}
