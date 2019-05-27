package game;

/**
 * @author Miguel
 *-Assets used by the TitleView to pull/load images easily from a HashMap
 */
public enum TitleAsset {
	BACKGROUND("BACKGROUND"),
	TITLELOGO("TITLELOGO");

	private String asset_key = null;

	private TitleAsset(String s){
		asset_key=s;
	}
}
